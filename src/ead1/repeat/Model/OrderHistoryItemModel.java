package ead1.repeat.Model;

/**
 * Model for a single item within a historical order (details).
 */
public class OrderHistoryItemModel {
    private String menuName;
    private int quantity;
    private double subtotalPrice;

    public OrderHistoryItemModel(String menuName, int quantity, double subtotalPrice) {
        this.menuName = menuName;
        this.quantity = quantity;
        this.subtotalPrice = subtotalPrice;
    }

    // Getters
    public String getMenuName() { return menuName; }
    public int getQuantity() { return quantity; }
    public double getSubtotalPrice() { return subtotalPrice; }
}