package ead1.repeat.Model;

import ead1.repeat.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles database operations for fetching the cashier's order history.
 */
public class Cashier_OrderHistoryDAO {

    /**
     * Retrieves the complete order history for a given cashier, grouped by Order ID.
     * @param cashierId The ID of the cashier whose history is required.
     * @return A list of OrderHistoryModel objects.
     */
    public List<OrderHistoryModel> getOrderHistoryByCashier(int cashierId) {
        // SQL JOIN query to link orders, order_items, and menus tables
        String sql = "SELECT " +
                     "o.order_id, o.order_date, o.total_amount, o.status, " +
                     "oi.quantity, oi.subtotal_price, m.name AS menu_name " +
                     "FROM orders o " +
                     "JOIN order_items oi ON o.order_id = oi.order_id " +
                     "JOIN menus m ON oi.menu_id = m.menu_id " +
                     "WHERE o.cashier_id = ? " +
                     "ORDER BY o.order_date DESC, o.order_id ASC";

        // Map to temporarily hold the data, keyed by order_id, to build the nested structure
        Map<Integer, OrderHistoryModel> orderMap = new LinkedHashMap<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cashierId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    
                    // --- 1. Get or Create the OrderHeader (OrderHistoryModel) ---
                    OrderHistoryModel order = orderMap.get(orderId);
                    if (order == null) {
                        // If it's a new order ID, create the header
                        String dateStr = rs.getTimestamp("order_date").toString(); // Use toString for full date/time
                        double totalAmount = rs.getDouble("total_amount");
                        String status = rs.getString("status");
                        
                        // Initialize with an empty list of items
                        order = new OrderHistoryModel(orderId, dateStr, totalAmount, status, new ArrayList<>());
                        orderMap.put(orderId, order);
                    }

                    // --- 2. Create and Add the Item Detail (OrderHistoryItemModel) ---
                    String menuName = rs.getString("menu_name");
                    int quantity = rs.getInt("quantity");
                    double subtotalPrice = rs.getDouble("subtotal_price");
                    
                    OrderHistoryItemModel item = new OrderHistoryItemModel(menuName, quantity, subtotalPrice);
                    order.getItems().add(item); // Add item to the list held by the OrderHistoryModel
                }
            }

        } catch (SQLException e) {
            System.err.println("!!! FATAL DAO ERROR: SQLException during Cashier order history data fetching!");
            e.printStackTrace();
            return Collections.emptyList();
        }
        
        // Convert the map values (the OrderHistoryModel objects) into a list
        return new ArrayList<>(orderMap.values());
    }
}