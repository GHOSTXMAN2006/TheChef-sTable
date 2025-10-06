package ead1.repeat.Model;

/**
 * Model class representing a menu item added to the current order.
 * It contains the base menu data (MenusModel) plus mutable order-specific
 * properties like quantity and calculated total price.
 */
public class OrderItemModel {
    
    private final MenusModel menu;
    private int quantity;
    private double totalPrice;

    /**
     * CONSTRUCTOR 1: Used when adding a NEW item (e.g., in Cashier).
     * Sets default quantity to 1.
     */
    public OrderItemModel(MenusModel menu) {
        if (menu == null) {
            throw new IllegalArgumentException("MenusModel cannot be null.");
        }
        this.menu = menu;
        this.quantity = 1; // Default quantity is 1 upon selection
        updateTotalPrice();
    }
    
    /**
     * CONSTRUCTOR 2: Used when LOADING an existing order (e.g., in DAO/Chef).
     */
    public OrderItemModel(MenusModel menu, int quantity) {
        if (menu == null) {
            throw new IllegalArgumentException("MenusModel cannot be null.");
        }
        this.menu = menu;
        // Uses the setter to ensure validation (quantity < 1) is applied
        setQuantity(quantity); 
    }

    // --- Getters ---
    public MenusModel getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    // --- Setters ---
    public void setQuantity(int quantity) {
        if (quantity < 1) {
            this.quantity = 1; // Prevent setting quantity less than 1
        } else {
            this.quantity = quantity;
        }
        updateTotalPrice(); // Recalculate price whenever quantity changes
    }

    /** Calculates and sets the totalPrice based on current quantity and base menu price. */
    public void updateTotalPrice() {
        // Use the base price from MenusModel for calculation
        this.totalPrice = this.menu.getPrice() * this.quantity;
    }
}