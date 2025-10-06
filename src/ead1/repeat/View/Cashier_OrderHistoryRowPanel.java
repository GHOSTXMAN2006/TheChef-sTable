package ead1.repeat.View;

import ead1.repeat.Model.OrderHistoryModel;
import ead1.repeat.Model.OrderHistoryItemModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Displays the header and all items for a single historical order.
 */
public class Cashier_OrderHistoryRowPanel extends JPanel {
    
    private final DecimalFormat priceFormatter = new DecimalFormat("0.00");

    public Cashier_OrderHistoryRowPanel(OrderHistoryModel order) {
        // Main panel setup
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // --- Header Panel (Order ID, Total, Status) ---
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setOpaque(false);
        pnlHeader.setBorder(new EmptyBorder(0, 0, 5, 0)); // Bottom padding
        
        // Left side: ID and Status
        JPanel pnlLeftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        pnlLeftHeader.setOpaque(false);
        
        JLabel lblId = new JLabel("Order ID: " + order.getOrderId());
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel lblStatus = new JLabel("Status: " + order.getStatus().toUpperCase());
        lblStatus.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblStatus.setForeground(order.getStatus().equalsIgnoreCase("incomplete") ? Color.RED : new Color(0, 150, 0));
        
        pnlLeftHeader.add(lblId);
        pnlLeftHeader.add(lblStatus);
        
        // Right side: Total Amount
        JLabel lblTotal = new JLabel("TOTAL: Rs. " + priceFormatter.format(order.getTotalAmount()));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setForeground(new Color(0, 100, 0));
        
        pnlHeader.add(pnlLeftHeader, BorderLayout.WEST);
        pnlHeader.add(lblTotal, BorderLayout.EAST);
        
        add(pnlHeader, BorderLayout.NORTH);

        // --- Details Panel (The items) ---
        // Use BoxLayout for vertical stacking of item rows
        JPanel pnlDetails = new JPanel();
        pnlDetails.setLayout(new BoxLayout(pnlDetails, BoxLayout.Y_AXIS));
        pnlDetails.setBackground(new Color(230, 230, 230)); // Slightly darker background for item list
        pnlDetails.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Add all item rows
        for (OrderHistoryItemModel item : order.getItems()) {
            pnlDetails.add(new Cashier_OrderHistoryItemRowPanel(item));
        }

        add(pnlDetails, BorderLayout.CENTER);
        
        // Ensure consistent width
        setPreferredSize(new Dimension(800, getPreferredSize().height));
        setMaximumSize(new Dimension(Short.MAX_VALUE, getPreferredSize().height));
    }
}