package ead1.repeat.View;

import ead1.repeat.Model.OrderModel;
import ead1.repeat.Model.OrderItemModel;
import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays the header and all items for a single order in the Chef view.
 * Includes a checkbox if the order is "Incomplete".
 */
public class Chef_OrderRowPanel extends JPanel {
    
    // Interface to communicate the completion action back to the Controller
    public interface OrderCompletionListener {
        void onOrderCompleted(OrderModel order, Chef_OrderRowPanel panel);
    }

    private final OrderModel orderModel;
    private final DecimalFormat priceFormatter = new DecimalFormat("0.00");
    private final JCheckBox chkComplete = new JCheckBox("Mark Complete");
    private final OrderCompletionListener listener;

    public Chef_OrderRowPanel(OrderModel order, OrderCompletionListener listener) {
        this.orderModel = order;
        this.listener = listener;
        
        // Main panel setup
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // --- Header Panel (Checkbox/ID, Date, Total) ---
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setOpaque(false);
        pnlHeader.setBorder(new EmptyBorder(0, 0, 5, 0)); // Add space below header

        // Left side: Checkbox (for Incomplete) or Order ID (for Complete)
        JPanel pnlLeftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        pnlLeftHeader.setOpaque(false);
        
        JLabel lblOrderId = new JLabel("ORDER #" + order.getOrderId());
        lblOrderId.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JLabel lblDateTime = new JLabel("(" + order.getFormattedDateTime() + ")");
        lblDateTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        pnlLeftHeader.add(lblOrderId);
        pnlLeftHeader.add(lblDateTime);

        if (order.getStatus().equalsIgnoreCase("Incomplete")) {
            // Add Checkbox for Pending Orders
            chkComplete.setOpaque(false);
            chkComplete.setFont(new Font("Segoe UI", Font.BOLD, 14));
            chkComplete.setForeground(new Color(255,51,51));
            pnlLeftHeader.add(chkComplete);
            addListeners();
        } else {
            // For Complete Orders, show a status indicator instead of checkbox
            JLabel lblStatus = new JLabel("COMPLETED");
            lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lblStatus.setForeground(new Color(102,153,0));
            pnlLeftHeader.add(lblStatus);
        }
        
        // Right side: Total Amount
        JLabel lblTotal = new JLabel("TOTAL: Rs. " + priceFormatter.format(order.getTotalAmount()));
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setForeground(new Color(102,153,0));
        
        pnlHeader.add(pnlLeftHeader, BorderLayout.WEST);
        pnlHeader.add(lblTotal, BorderLayout.EAST);
        
        add(pnlHeader, BorderLayout.NORTH);

        // --- Details Panel (The items) ---
        JPanel pnlDetails = new JPanel();
        pnlDetails.setLayout(new BoxLayout(pnlDetails, BoxLayout.Y_AXIS));
        pnlDetails.setBackground(new Color(230, 230, 230)); 
        pnlDetails.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Add all item rows
        for (OrderItemModel item : order.getItems()) {
            // NOTE: Assuming you have a Chef_OrderItemRowPanel class implemented
            pnlDetails.add(new Chef_OrderItemRowPanel(item)); 
        }

        add(pnlDetails, BorderLayout.CENTER);
    }
    
    private void addListeners() {
        // Listener for the Mark Complete Checkbox
        chkComplete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If the user checks the box, prompt for confirmation
                if (chkComplete.isSelected()) {
                    
                    // 💡 FIX: Get the top-level parent window (JFrame) to center the dialog
                    Component parentFrame = SwingUtilities.getWindowAncestor(Chef_OrderRowPanel.this);
                    
                    int response = JOptionPane.showConfirmDialog(
                        parentFrame, // Use the main frame as the parent component
                        "Are you sure you want to mark Order #" + orderModel.getOrderId() + " as Complete?",
                        "Confirm Order Completion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );
                    
                    if (response == JOptionPane.YES_OPTION) {
                        if (listener != null) {
                            // Notify Controller to handle DAO update and panel move
                            listener.onOrderCompleted(orderModel, Chef_OrderRowPanel.this);
                        }
                    } else {
                        // If user cancels, uncheck the box
                        chkComplete.setSelected(false);
                    }
                }
            }
        });
    }

    public OrderModel getOrderModel() {
        return orderModel;
    }
}