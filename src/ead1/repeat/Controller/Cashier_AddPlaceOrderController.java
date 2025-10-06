package ead1.repeat.Controller;

import ead1.repeat.Model.MenusModel;
import ead1.repeat.Model.Cashier_MenusDAO;
import ead1.repeat.View.Cashier_AddPlaceOrderPanel;
import ead1.repeat.View.Cashier_MenusPanel;
import ead1.repeat.Model.OrderItemModel;
import ead1.repeat.View.Cashier_OrderItemRowPanel;
// 💡 NEW IMPORTS FOR DATABASE HANDLING
import ead1.repeat.Model.Cashier_OrderDAO; 
import ead1.repeat.Model.UserSession;
import javax.swing.JOptionPane; 
// 💡 END NEW IMPORTS

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

/**
 * Controller for Cashier_AddOrderPanel.
 * Manages order items, total calculation, and menu selection flow.
 */
public class Cashier_AddPlaceOrderController 
    // 🔹 IMPLEMENT THE LISTENER interfaces
    implements Cashier_MenusController.MenuSelectionListener, Cashier_OrderItemRowPanel.OrderItemRowListener { 

    private final Cashier_AddPlaceOrderPanel view;
    // 🔹 This list holds the actual order items currently added.
    private final List<OrderItemModel> orderItems = new ArrayList<>();
    
    private final DecimalFormat priceFormatter = new DecimalFormat("0.00");
    
    // 💡 NEW: Instance of OrderDAO for database operations
    private final Cashier_OrderDAO orderDAO = new Cashier_OrderDAO();

    public Cashier_AddPlaceOrderController(Cashier_AddPlaceOrderPanel view) {
        this.view = view;
        addMenuListeners();
        addPlaceOrderListener(); 
        calculateAndDisplayTotal();
        updateOrderListPlaceholder(); 
    }
    
    // ------------------------------------------
    // 🔹 MENU DIALOG HANDLING 🔹
    // ------------------------------------------
    
    private void addMenuListeners() {
        MouseAdapter menuDialogListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { 
                openMenusDialog();
            }
        };
        
        view.getPnlAddMenus().addMouseListener(menuDialogListener);
        view.getLblAddMenus().addMouseListener(menuDialogListener);
    }

    private void openMenusDialog() {
        JDialog dialog = new JDialog((java.awt.Frame)SwingUtilities.getWindowAncestor(view), "Select Menus", true);
        Cashier_MenusPanel menusPanel = new Cashier_MenusPanel();
        Cashier_MenusDAO menuDao = new Cashier_MenusDAO();
        
        new Cashier_MenusController(menusPanel, menuDao, dialog, this);

        dialog.getContentPane().add(menusPanel);
        dialog.pack(); 
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(view));
        dialog.setResizable(false);
        dialog.setVisible(true);
    }
    
    // ------------------------------------------
    // 🔹 PLACE ORDER IMPLEMENTATION 🔹
    // ------------------------------------------

    /** Attaches listeners to the "Place Order" button/panel. */
    private void addPlaceOrderListener() {
        MouseAdapter placeOrderAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                placeOrderAction(); // Call the saving logic
            }
        };

        view.getPnlPlaceOrder().addMouseListener(placeOrderAdapter);
        view.getLblPlaceOrder().addMouseListener(placeOrderAdapter);
    }
    
    /** Logic to process and save the order to the database. */
    private void placeOrderAction() {
        if (orderItems.isEmpty()) {
            JOptionPane.showMessageDialog(view, "The order list is empty. Add items before placing an order.", "Order Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int cashierId = UserSession.getEmployeeId();
        if (cashierId == 0) {
            JOptionPane.showMessageDialog(view, "Cashier ID not found. Please log in again.", "Session Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double grandTotal = 0.0;
        for (OrderItemModel item : orderItems) {
            grandTotal += item.getTotalPrice();
        }
        
        boolean success = orderDAO.placeOrder(cashierId, grandTotal, orderItems);
        
        if (success) {
            JOptionPane.showMessageDialog(view, "Order placed successfully! Order Total: Rs. " + priceFormatter.format(grandTotal), "Order Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Clear the current order
            orderItems.clear(); 
            view.getPnlOrderList().removeAll(); 
            view.getPnlOrderList().revalidate();
            view.getPnlOrderList().repaint();
            view.updateGrandTotal(0.0); // Reset total
            updateOrderListPlaceholder(); 
            
        } else {
            JOptionPane.showMessageDialog(view, "Failed to place order due to a database error. Check console for details.", "Order Failure", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // ------------------------------------------
    // 🔹 MENU SELECTION (FROM Cashier_MenusController)
    // ------------------------------------------

    /** Implementation of MenuSelectionListener. */
    @Override
    public void onMenusConfirmed(List<MenusModel> selectedMenus) {
        System.out.println("ADD ORDER CONTROLLER: Received " + selectedMenus.size() + " confirmed menu items.");
        
        List<String> alreadyAdded = new ArrayList<>();
        
        for (MenusModel menu : selectedMenus) {
            OrderItemModel existingItem = findExistingItem(menu);

            if (existingItem != null) {
                // If item exists, add to list for alert and SKIP.
                alreadyAdded.add(menu.getName());
            } else {
                // If new, create a new item model and add a new UI row
                OrderItemModel newItem = new OrderItemModel(menu);
                orderItems.add(newItem);

                // Create and configure the UI row panel
                Cashier_OrderItemRowPanel newRowPanel = new Cashier_OrderItemRowPanel(newItem, this);
                view.addOrderItemRow(newRowPanel); // Assuming view.addOrderItemRow exists
            }
        }
        
        // Display alert for duplicates
        if (!alreadyAdded.isEmpty()) {
            String message = "The following items were already on the order list and were NOT added again:\n";
            message += String.join("\n", alreadyAdded);
            JOptionPane.showMessageDialog(view, message, "Duplicate Items Skipped", JOptionPane.WARNING_MESSAGE);
        }
        
        calculateAndDisplayTotal();
        updateOrderListPlaceholder(); 
    }
    
    /** * Helper to find an existing OrderItemModel based on the selected MenusModel.
     * 💡 FIX: Compares the menu name (unique identifier) instead of relying on Object.equals().
     */
    private OrderItemModel findExistingItem(MenusModel menu) {
        for (OrderItemModel item : orderItems) {
            if (item.getMenu().getName().equals(menu.getName())) { 
                return item;
            }
        }
        return null;
    }


    // ------------------------------------------
    // 🔹 ORDER ITEM ROW LISTENERS (FROM OrderItemRowPanel)
    // ------------------------------------------

    /** * 🔹 Handles item removal request from the row panel.
     * NOTE: This is the method called by OrderItemRowPanel.
     */
    @Override 
    public void onDeleteItem(OrderItemModel item) { 
        System.out.println("CONTROLLER: Deleting item: " + item.getMenu().getName());
        
        // 1. Remove from the internal list
        orderItems.remove(item);
        
        // 2. Remove the row from the view and re-layout
        view.removeOrderItemRow(item); 

        // 3. Recalculate total
        calculateAndDisplayTotal();
        updateOrderListPlaceholder(); // Update placeholder visibility
    }
    
    /** 🔹 Handles quantity change from the row panel. */
    @Override
    public void onQuantityChanged(OrderItemModel item) {
        System.out.println("CONTROLLER: Quantity changed for: " + item.getMenu().getName() + " to " + item.getQuantity());
        calculateAndDisplayTotal();
    }
    
    // ------------------------------------------
    // 🔹 DYNAMIC UI MANAGEMENT 🔹
    // ------------------------------------------
    
    /** 💡 Manages the visibility of the "No items selected" placeholder text. */
    private void updateOrderListPlaceholder() {
        boolean isEmpty = orderItems.isEmpty();
        view.setOrderListPlaceholderVisibility(isEmpty);
    }


    // ------------------------------------------
    // 🔹 ORDER TOTAL CALCULATION
    // ------------------------------------------

    /** Calculates the total price of all items in the order and updates the view. */
    private void calculateAndDisplayTotal() {
        double grandTotal = 0.0;
        for (OrderItemModel item : orderItems) {
            grandTotal += item.getTotalPrice();
        }
        
        view.updateGrandTotal(grandTotal);
        
        System.out.println("CONTROLLER: Grand Total Updated to Rs. " + priceFormatter.format(grandTotal));
    }
    
    
}