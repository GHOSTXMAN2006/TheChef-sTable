package ead1.repeat.View;

import ead1.repeat.Model.OrderHistoryItemModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Displays a single item row within an order history detail block.
 */
public class Cashier_OrderHistoryItemRowPanel extends JPanel {
    
    private final DecimalFormat priceFormatter = new DecimalFormat("0.00");
    
    public Cashier_OrderHistoryItemRowPanel(OrderHistoryItemModel item) {
        setLayout(new GridLayout(1, 3, 10, 0)); // 3 columns for Name, Qty, Price
        setBorder(new EmptyBorder(2, 5, 2, 5));
        setBackground(new Color(245, 245, 245)); // Light background for detail row

        Font font = new Font("Segoe UI", Font.PLAIN, 12);
        
        JLabel lblName = new JLabel("  " + item.getMenuName());
        lblName.setFont(font);
        
        JLabel lblQuantity = new JLabel("x" + item.getQuantity());
        lblQuantity.setFont(font);
        
        JLabel lblPrice = new JLabel("Rs. " + priceFormatter.format(item.getSubtotalPrice()));
        lblPrice.setFont(font);
        lblPrice.setHorizontalAlignment(JLabel.RIGHT);

        add(lblName);
        add(lblQuantity);
        add(lblPrice);
    }
}