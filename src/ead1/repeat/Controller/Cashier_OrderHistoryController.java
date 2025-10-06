package ead1.repeat.Controller;

import ead1.repeat.Model.Cashier_OrderHistoryDAO;
import ead1.repeat.Model.OrderHistoryModel;
import ead1.repeat.Model.UserSession;
import ead1.repeat.View.Cashier_OrderHistoryPanel;
import ead1.repeat.View.Cashier_OrderHistoryRowPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for the Cashier_OrderHistoryPanel.
 * Fetches order history and populates the view dynamically.
 */
public class Cashier_OrderHistoryController {

    private final Cashier_OrderHistoryPanel view;
    private final Cashier_OrderHistoryDAO dao;
    private final JPanel pnlHistoryContainer; // The internal panel inside the JScrollPane

    public Cashier_OrderHistoryController(Cashier_OrderHistoryPanel view) {
        this.view = view;
        this.dao = new Cashier_OrderHistoryDAO();
        
        // The view must expose a method or field to get the internal panel 
        // where we will add dynamic components.
        this.pnlHistoryContainer = new JPanel();
        
        // Set up the container layout for vertical stacking
        this.pnlHistoryContainer.setLayout(new BoxLayout(this.pnlHistoryContainer, BoxLayout.Y_AXIS));
        this.pnlHistoryContainer.setBackground(new Color(153, 153, 153)); // Match view background
        
        // Update the view's JScrollPane to use our dynamic panel
        view.setDynamicOrderHistoryPanel(this.pnlHistoryContainer);
        
        loadOrderHistory();
    }

    /** Fetches data and updates the view. */
    public void loadOrderHistory() {
        int cashierId = UserSession.getEmployeeId();
        if (cashierId == 0) {
            JOptionPane.showMessageDialog(view, "Cashier session not active. Cannot load history.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 1. Fetch all orders and their items from the DAO
        List<OrderHistoryModel> allOrders = dao.getOrderHistoryByCashier(cashierId);
        
        // 2. Clear previous content
        pnlHistoryContainer.removeAll();

        if (allOrders.isEmpty()) {
            displayPlaceholder("No orders found for this cashier.");
            return;
        }
        
        // 3. Group the orders by date (extracting only the date part)
        Map<String, List<OrderHistoryModel>> groupedByDate = allOrders.stream()
            .collect(Collectors.groupingBy(
                order -> order.getOrderDate().substring(0, 10), // Group by YYYY-MM-DD
                LinkedHashMap::new, 
                Collectors.toList()
            ));

        // 4. Populate the UI dynamically
        for (Map.Entry<String, List<OrderHistoryModel>> entry : groupedByDate.entrySet()) {
            // Add a date header
            addDateHeader(entry.getKey());
            
            for (OrderHistoryModel order : entry.getValue()) {
                // Add the OrderHistoryRowPanel for each order
                Cashier_OrderHistoryRowPanel orderRow = new Cashier_OrderHistoryRowPanel(order);
                pnlHistoryContainer.add(orderRow);
                pnlHistoryContainer.add(Box.createVerticalStrut(5)); // Small gap between orders
            }
            pnlHistoryContainer.add(Box.createVerticalStrut(15)); // Larger gap between dates
        }

        pnlHistoryContainer.revalidate();
        pnlHistoryContainer.repaint();
    }
    
    /** Helper to add a date separator/header. */
    private void addDateHeader(String date) {
        JLabel lblDate = new JLabel("  ORDERS ON: " + date);
        lblDate.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblDate.setForeground(Color.BLACK);
        
        JPanel pnlDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDate.setBackground(new Color(220, 220, 220)); // Light background for date header
        pnlDate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlDate.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        pnlDate.add(lblDate);
        
        pnlHistoryContainer.add(pnlDate);
        pnlHistoryContainer.add(Box.createVerticalStrut(10)); // Gap after header
    }

    /** Helper to display a message when no orders are found. */
    private void displayPlaceholder(String message) {
        JLabel lblPlaceholder = new JLabel(message);
        lblPlaceholder.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblPlaceholder.setForeground(new Color(180, 180, 180));

        JPanel pnlCenter = new JPanel(new GridBagLayout()); // Use GridBagLayout to center in the container
        pnlCenter.setBackground(this.pnlHistoryContainer.getBackground());
        pnlCenter.add(lblPlaceholder);

        // Add the centered panel, forcing it to fill the available space
        pnlHistoryContainer.add(pnlCenter);
        pnlHistoryContainer.revalidate();
        pnlHistoryContainer.repaint();
    }
}