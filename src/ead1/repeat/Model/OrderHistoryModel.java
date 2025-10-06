package ead1.repeat.Model;

import java.util.List;

/**
 * Model for a single historical order (header + all its items).
 */
public class OrderHistoryModel {
    private int orderId;
    private String orderDate; // Use String to hold formatted date/time
    private double totalAmount;
    private String status;
    private List<OrderHistoryItemModel> items;

    public OrderHistoryModel(int orderId, String orderDate, double totalAmount, String status, List<OrderHistoryItemModel> items) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.items = items;
    }

    // Getters
    public int getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public List<OrderHistoryItemModel> getItems() { return items; }
}