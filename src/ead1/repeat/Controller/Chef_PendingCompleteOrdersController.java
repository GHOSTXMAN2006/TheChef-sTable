package ead1.repeat.Controller;

import ead1.repeat.Model.Chef_OrderDAO;
import ead1.repeat.Model.OrderModel;
import ead1.repeat.View.Chef_OrderRowPanel;
import ead1.repeat.View.Chef_PendingCompleteOrdersPanel;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * Controller for the Chef_PendingCompleteOrdersPanel.
 * It loads all orders, handles the "Mark Complete" action, 
 * and updates the DAO and the View dynamically.
 */
public class Chef_PendingCompleteOrdersController 
    implements Chef_OrderRowPanel.OrderCompletionListener { 

    private final Chef_PendingCompleteOrdersPanel view;
    private final Chef_OrderDAO dao;

    public Chef_PendingCompleteOrdersController(Chef_PendingCompleteOrdersPanel view) {
        this.view = view;
        this.dao = new Chef_OrderDAO();
        
        loadOrdersToView();
    }
    
    /**
     * Fetches all orders from the DAO and populates the two sections in the view.
     */
    private void loadOrdersToView() {
        // Run in a separate thread to prevent blocking the UI during data fetch
        new Thread(() -> {
            List<OrderModel> allOrders = dao.fetchAllOrders();
            
            // UI updates must be done on the Event Dispatch Thread (EDT)
            SwingUtilities.invokeLater(() -> {
                for (OrderModel order : allOrders) {
                    // Pass 'this' (the controller) as the listener for the row panel
                    Chef_OrderRowPanel orderPanel = new Chef_OrderRowPanel(order, this); 
                    
                    if (order.getStatus().equalsIgnoreCase("Incomplete")) {
                        view.addPendingOrder(orderPanel);
                    } else {
                        view.addCompleteOrder(orderPanel);
                    }
                }
            });
        }).start();
    }

    /**
     * Called when the Chef confirms an order is complete via the checkbox.
     */
    @Override
    public void onOrderCompleted(OrderModel order, Chef_OrderRowPanel panel) {
        // 1. Update the status in the DAO (Database)
        boolean success = dao.updateOrderStatusToComplete(order.getOrderId());
        
        if (success) {
            // 2. Update the Model's status
            order.setStatus("Complete");
            
            // 3. Update the View dynamically
            
            // 3a. Remove the panel from the Pending section
            view.removePendingOrder(panel);
            
            // 3b. Create a *new* panel for the Complete section (passing 'null' for listener removes the checkbox)
            Chef_OrderRowPanel newCompletePanel = new Chef_OrderRowPanel(order, null);
            view.addCompleteOrder(newCompletePanel);

            System.out.println("CONTROLLER: Order #" + order.getOrderId() + " successfully moved to Completed.");
            
        } else {
            System.err.println("CONTROLLER: Failed to update order status in DAO.");
            // Optional: Show an error message to the user here
        }
    }
}