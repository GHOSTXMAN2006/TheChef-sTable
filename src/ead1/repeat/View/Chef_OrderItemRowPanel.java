package ead1.repeat.View;

import ead1.repeat.Model.OrderItemModel;
import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Custom JPanel component representing a single menu item in an order.
 * It's static, showing name, quantity, and price.
 */
public class Chef_OrderItemRowPanel extends JPanel {

    private final DecimalFormat priceFormatter = new DecimalFormat("0.00");

    public Chef_OrderItemRowPanel(OrderItemModel itemModel) {
        setLayout(new BorderLayout(5, 0));
        setBackground(new Color(235, 235, 235)); // Light background
        setBorder(new EmptyBorder(3, 15, 3, 15));
        
        // Left side: Item Name and Quantity
        JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        pnlLeft.setOpaque(false);
        
        JLabel lblName = new JLabel(itemModel.getMenu().getName());
        lblName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel lblQuantity = new JLabel("x" + itemModel.getQuantity());
        lblQuantity.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblQuantity.setForeground(Color.DARK_GRAY);
        
        pnlLeft.add(lblName);
        pnlLeft.add(lblQuantity);

        // Right side: Total Price
        JLabel lblPrice = new JLabel("Rs. " + priceFormatter.format(itemModel.getTotalPrice()));
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrice.setForeground(new Color(0, 100, 0)); // Green for price

        add(pnlLeft, BorderLayout.WEST);
        add(lblPrice, BorderLayout.EAST);
    }
}