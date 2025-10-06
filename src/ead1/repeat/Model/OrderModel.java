package ead1.repeat.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderModel {
    
    private final int orderId;
    private double totalAmount; // This will now hold the value from the DB
    private String status;
    private final LocalDateTime orderDateTime; 
    private final List<OrderItemModel> items;
    
    // CONSTRUCTOR 1: For Cashier (New Order) - Total calculated here
    public OrderModel(int orderId, List<OrderItemModel> items) {
        this.orderId = orderId;
        this.status = "incomplete";
        this.items = items;
        this.orderDateTime = LocalDateTime.now(); 
        calculateTotalAmount(); // Keep calculation for NEW orders
    }
    
    /**
     * CONSTRUCTOR 2 (FIXED): Used for DAO/Chef (Loading Existing Order).
     * Now accepts the totalAmount directly from the database to ensure correctness.
     */
    public OrderModel(int orderId, String status, List<OrderItemModel> items, LocalDateTime orderDateTime, double totalAmount) {
        this.orderId = orderId;
        this.status = status;
        this.items = items;
        this.orderDateTime = orderDateTime;
        this.totalAmount = totalAmount; // <-- SET DIRECTLY FROM DB COLUMN
        // IMPORTANT: DO NOT call calculateTotalAmount() here.
    }

    // --- Utility ---
    // This is ONLY used by Constructor 1 for new orders.
    private void calculateTotalAmount() {
        // Ensures the total amount is accurately calculated from the items.
        this.totalAmount = items.stream().mapToDouble(OrderItemModel::getTotalPrice).sum();
    }

    // --- Getters ---
    public int getOrderId() { return orderId; }
    // Returns the total amount set by the constructor (either calculated or from DB)
    public double getTotalAmount() { return totalAmount; } 
    public String getStatus() { return status; }
    public List<OrderItemModel> getItems() { return items; }
    
    public String getFormattedDateTime() {
        // Uses the actual date/time stored in the model
        return orderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")); 
    }

    // --- Setter ---
    public void setStatus(String status) { 
        this.status = status;
    }
}