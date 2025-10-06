package ead1.repeat.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import ead1.repeat.DatabaseConfig; // Assuming DatabaseConfig is in this package or properly imported

public class Cashier_OrderDAO {

    /**
     * Saves the entire order (Header and Items) to the database in a transaction.
     * @param cashierId The employee ID of the cashier.
     * @param totalAmount The grand total amount of the order.
     * @param items The list of OrderItemModel objects.
     * @return true if the order was saved successfully, false otherwise.
     */
    public boolean placeOrder(int cashierId, double totalAmount, List<OrderItemModel> items) {
        if (items.isEmpty()) {
            System.out.println("DEBUG: Cannot place order, item list is empty.");
            return false;
        }

        Connection conn = null;
        long orderId = -1; // Initialize orderId outside the block

        // Use a single try-catch for the entire transaction
        try {
            conn = DatabaseConfig.getConnection();
            conn.setAutoCommit(false); // 💡 Start transaction

            // 1. Insert into orders table (Header)
            String orderSql = "INSERT INTO orders (order_date, total_amount, cashier_id, status) VALUES (NOW(), ?, ?, 'incomplete')";
            
            // Use Statement.RETURN_GENERATED_KEYS to get the new order_id
            try (PreparedStatement orderPst = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                orderPst.setDouble(1, totalAmount);
                orderPst.setInt(2, cashierId);
                
                if (orderPst.executeUpdate() == 0) {
                    throw new Exception("Order header insertion failed. No rows affected.");
                }

                // Get the generated order_id
                try (ResultSet generatedKeys = orderPst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getLong(1);
                        System.out.println("DEBUG: Order header saved. Generated Order ID: " + orderId);
                    } else {
                        throw new Exception("Order header saved, but failed to retrieve generated ID.");
                    }
                }
                
                // --------------------------------------------------------------------
                // 2. Insert into order_items table (Details)
                // NO INNER TRY-CATCH BLOCK HERE. Let the outer catch handle the rollback.
                // --------------------------------------------------------------------
                String itemSql = "INSERT INTO order_items (order_id, menu_id, quantity, subtotal_price) VALUES (?, ?, ?, ?)";
                int expectedItems = items.size();

                try (PreparedStatement itemPst = conn.prepareStatement(itemSql)) {
                    
                    System.out.println("DEBUG: Preparing to insert " + expectedItems + " order items for Order ID " + orderId);

                    for (OrderItemModel item : items) {
                        
                        int currentMenuId = item.getMenu().getId(); // Get the Menu ID
                        
                        // --- CRITICAL DEBUG LOG ---
                        // Check this line for menu_id = 0, which causes the Foreign Key error.
                        System.out.println("DEBUG: Batching Item - Menu ID: " + currentMenuId + 
                                           ", Qty: " + item.getQuantity() + 
                                           ", Subtotal: " + item.getTotalPrice());
                        // ---------------------------
                        
                        itemPst.setLong(1, orderId);
                        itemPst.setInt(2, currentMenuId); // menu_id
                        itemPst.setInt(3, item.getQuantity());
                        itemPst.setDouble(4, item.getTotalPrice());
                        itemPst.addBatch();
                    }

                    // Execute all item insertions
                    int[] results = itemPst.executeBatch(); 
                    
                    // CRITICAL ERROR CHECK: If the batch reports fewer successful inserts than expected, force rollback.
                    int actualUpdates = java.util.stream.IntStream.of(results).sum();
                    
                    if (actualUpdates != expectedItems) {
                        // This will throw an exception that is caught by the OUTER CATCH BLOCK and triggers the rollback.
                        throw new Exception("Batch update failed. Expected " + expectedItems + 
                                            " insertions but only " + actualUpdates + " were reported.");
                    }
                    
                    System.out.println("DEBUG: All " + actualUpdates + " order items batched and executed successfully.");

                } // itemPst closed automatically
                
            } // orderPst closed automatically

            conn.commit(); // 💡 Commit transaction if all succeeded
            System.out.println("DEBUG: Transaction committed for Order ID " + orderId);
            return true;

        } catch (Exception e) {
            // This single catch block handles ALL failures (header or items)
            System.err.println("FATAL ERROR: Transaction failed! Order ID " + orderId + ". Initiating rollback.");
            e.printStackTrace(); // ⬅️ The key to seeing the original SQL error
            if (conn != null) {
                try {
                    conn.rollback(); // 💡 Rollback on ANY error
                    System.err.println("DEBUG: Rollback successful for Order ID " + orderId);
                } catch (Exception ex) {
                    System.err.println("ERROR: Failed to execute rollback!");
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restore default
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}