package ead1.repeat.Model;

import ead1.repeat.DatabaseConfig;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chef_OrderDAO {
    
    // 🛠️ FIX 1: ADDED 'o.total_amount' to the SELECT list
    private static final String FETCH_ALL_ORDERS_SQL = 
        "SELECT o.order_id, o.order_date, o.status, o.total_amount, " + 
        "oi.menu_id, oi.quantity, m.name AS menu_name, m.price, m.image_path " + 
        "FROM orders o " +
        "JOIN order_items oi ON o.order_id = oi.order_id " +
        "JOIN menus m ON oi.menu_id = m.menu_id " +
        "ORDER BY o.status DESC, o.order_id ASC"; 

    /**
     * Fetches all orders from the database using a real connection.
     */
    public List<OrderModel> fetchAllOrders() {
        Map<Integer, OrderModel> orderMap = new HashMap<>(); 
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(FETCH_ALL_ORDERS_SQL);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                
                // Date Handling
                Timestamp orderTimestamp = rs.getTimestamp("order_date");
                LocalDateTime orderDateTime = orderTimestamp.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime(); 

                // --- 1. Get/Create the OrderModel (Header) ---
                OrderModel order = orderMap.get(orderId);
                
                if (order == null) {
                    // 🛠️ FIX 2: Read the total_amount from the result set
                    double totalAmount = rs.getDouble("total_amount"); 
                    
                    // 🛠️ FIX 3: Use the new 5-argument constructor (orderId, status, items, date, totalAmount)
                    order = new OrderModel(
                        orderId, 
                        rs.getString("status"), 
                        new ArrayList<>(), 
                        orderDateTime,
                        totalAmount // <-- PASS THE DB TOTAL AMOUNT
                    );
                    orderMap.put(orderId, order);
                }

                // --- 2. Create the OrderItemModel (Item Row) ---
                // We still need to fetch item details to display the order items list
                MenusModel menu = new MenusModel(
                    rs.getInt("menu_id"), 
                    rs.getString("menu_name"),
                    rs.getDouble("price"),
                    rs.getString("image_path")
                );
                
                OrderItemModel orderItem = new OrderItemModel(
                    menu,
                    rs.getInt("quantity")
                );
                
                order.getItems().add(orderItem);
            }
            
        } catch (SQLException e) {
            System.err.println("DB ERROR: Failed to fetch all orders for Chef Panel.");
            e.printStackTrace();
            return new ArrayList<>(); 
        }

        return new ArrayList<>(orderMap.values());
    }

    /**
     * Updates the status of a specific order to 'complete' in the database.
     */
    public boolean updateOrderStatusToComplete(int orderId) {
        System.out.println("DAO: Attempting to update Order ID " + orderId + " to 'complete'...");
        
        String sql = "UPDATE orders SET status = 'complete' WHERE order_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, orderId);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("DAO: Update successful for Order ID " + orderId);
                return true;
            } else {
                System.err.println("DAO: Update failed. Order ID " + orderId + " not found or status already 'complete'.");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("DB ERROR: Failed to update order status in database.");
            e.printStackTrace();
            return false;
        }
    }
}