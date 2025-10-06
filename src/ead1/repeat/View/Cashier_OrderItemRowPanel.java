package ead1.repeat.View;

import ead1.repeat.Model.OrderItemModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Custom JPanel component representing a single row in the order list.
 * Contains menu details, quantity spinner, total price, and a delete button.
 */
public class Cashier_OrderItemRowPanel extends JPanel {

    // 🔹 Interface for Controller communication (for deletion and price recalculation)
    public interface OrderItemRowListener {
        void onDeleteItem(OrderItemModel item);
        void onQuantityChanged(OrderItemModel item);
    }
    
    private final OrderItemModel itemModel;
    private final OrderItemRowListener listener;
    private final DecimalFormat priceFormatter = new DecimalFormat("0.00");

    // UI Components
    private final JLabel lblImage = new JLabel();
    private final JLabel lblName = new JLabel();
    private final JSpinner spinQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
    private final JLabel lblPrice = new JLabel();
    
    // 💡 REVERTED: Back to the simple 'X'
    private final JLabel lblDelete = new JLabel("X"); 

    public Cashier_OrderItemRowPanel(OrderItemModel itemModel, OrderItemRowListener listener) {
        this.itemModel = itemModel;
        this.listener = listener;
        
        setupLayout();
        setupComponents();
        loadInitialData();
        addListeners();
    }

    // ------------------------------------------
    // ## Layout Setup & Aesthetics
    // ------------------------------------------
    private void setupLayout() {
        // Use BorderLayout for the main panel to separate left content from the right controls
        setLayout(new BorderLayout(15, 0)); // 15px horizontal gap between WEST and CENTER/EAST
        setBackground(Color.WHITE); 
        setBorder(BorderFactory.createCompoundBorder(
            // FIX: Removed 'border:' parameter name
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            new EmptyBorder(5, 10, 5, 10) // Internal padding
        ));
        setPreferredSize(new Dimension(850, 70));
        setMaximumSize(new Dimension(Short.MAX_VALUE, 70));
    }

    private void setupComponents() {
        // --- 1. Component Styling & Sizing ---
        
        // Image
        lblImage.setPreferredSize(new Dimension(60, 60));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setVerticalAlignment(SwingConstants.CENTER);
        // FIX: Removed 'border:' parameter name and 'r:', 'g:', 'b:' parameter names
        lblImage.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        
        // Name (Increased from 16 to 18)
        // FIX: Removed 'name:' and 'style:' parameter names
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        // FIX: Removed 'r:', 'g:', 'b:' parameter names
        lblName.setForeground(new Color(50, 50, 50));
        
        // Quantity Spinner (Increased size, and its editor font is modified below)
        spinQuantity.setPreferredSize(new Dimension(70, 40));
        
        // Increase font inside the spinner's text field (editor)
        JComponent editor = spinQuantity.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            // FIX: Removed 'name:' and 'style:' parameter names
            textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        }

        // Price (Emphasized) (Increased from 16 to 20)
        // FIX: Removed 'name:' and 'style:' parameter names
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 20)); 
        // FIX: Removed 'r:', 'g:', 'b:' parameter names
        lblPrice.setForeground(new Color(0, 150, 0)); // Brighter Green
        lblPrice.setPreferredSize(new Dimension(100, 50));
        lblPrice.setHorizontalAlignment(SwingConstants.RIGHT); 
        
        // Delete Button (REVERTED STYLING) (Increased from 16 to 22)
        lblDelete.setText("X"); // Ensure it's 'X'
        lblDelete.setForeground(Color.RED); // Original RED color
        // FIX: Removed 'name:' and 'style:' parameter names
        lblDelete.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Increased font size
        lblDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDelete.setPreferredSize(new Dimension(30, 50));
        lblDelete.setHorizontalAlignment(SwingConstants.CENTER); // Center the 'X' in its component area

        // --- 2. Panel Grouping for Layout ---
        
        // 🔹 Group Left (Image + Name) -> Placed in BorderLayout.WEST
        // FIX: Removed 'layout:' parameter name
        JPanel pnlLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlLeft.setOpaque(false);
        pnlLeft.add(lblImage);
        pnlLeft.add(lblName);
        
        // 🔹 Group Center (Qty, Price) -> Placed in BorderLayout.CENTER
        // FIX: Removed 'layout:' parameter name
        JPanel pnlCenter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5)); // 30px gap between Qty and Price
        pnlCenter.setOpaque(false);
        
        // Quantity Section
        // FIX: Removed 'layout:' parameter name
        JPanel pnlQuantity = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlQuantity.setOpaque(false);
        JLabel lblQty = new JLabel("Qty:");
        // FIX: Removed 'name:' and 'style:' parameter names
        lblQty.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Increased font size for 'Qty:' label
        pnlQuantity.add(lblQty);
        pnlQuantity.add(spinQuantity);
        
        // Price Section
        // FIX: Removed 'layout:' parameter name
        JPanel pnlPriceContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlPriceContainer.setOpaque(false);
        JLabel lblTotal = new JLabel("Total:");
        // FIX: Removed 'name:' and 'style:' parameter names
        lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Increased font size for 'Total:' label
        pnlPriceContainer.add(lblTotal);
        pnlPriceContainer.add(lblPrice);
        
        pnlCenter.add(pnlQuantity);
        pnlCenter.add(pnlPriceContainer);
        
        // 🔹 Delete Panel -> Placed in BorderLayout.EAST (Always far right)
        // FIX: Removed 'layout:' parameter name
        JPanel pnlDelete = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 5)); 
        pnlDelete.setOpaque(false);
        pnlDelete.add(lblDelete);

        // --- 3. Add Panels to Main Panel ---
        add(pnlLeft, BorderLayout.WEST);
        add(pnlCenter, BorderLayout.CENTER); 
        add(pnlDelete, BorderLayout.EAST); 
    }

    // ------------------------------------------
    // ## Data Management Methods
    // ------------------------------------------

    /** Loads the initial data from the model into the components. */
    private void loadInitialData() {
        lblName.setText(itemModel.getMenu().getName());
        updateImageDisplay(itemModel.getMenu().getImagePath());
        updateDisplay();
    }

    /** Safely handles image loading. */
    private void updateImageDisplay(String imagePath) {
        if (imagePath != null && !imagePath.trim().isEmpty()) {
            java.net.URL location = getClass().getResource(imagePath);
            
            if (location != null) {
                ImageIcon icon = new ImageIcon(location); 
                Image image = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                lblImage.setIcon(new ImageIcon(image));
                lblImage.setText("");
            } else {
                lblImage.setText("No Img"); 
                lblImage.setIcon(null);
                System.err.println("!!! ERROR: Image resource not found at: " + imagePath);
            }
        } else {
            lblImage.setText("No Img");
            lblImage.setIcon(null);
        }
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /** Updates the displayed quantity and price from the model. */
    public void updateDisplay() {
        lblPrice.setText("Rs. " + priceFormatter.format(itemModel.getTotalPrice()));
        spinQuantity.setValue(itemModel.getQuantity());
    }

    private void addListeners() {
        // Listener for the Quantity Spinner
        spinQuantity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newQuantity = (Integer) spinQuantity.getValue();
                if (newQuantity > 0) {
                    itemModel.setQuantity(newQuantity);
                    updateDisplay();
                    if (listener != null) {
                        listener.onQuantityChanged(itemModel);
                    }
                }
            }
        });
        
        // Listener for the Delete Button
        lblDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (listener != null) {
                    listener.onDeleteItem(itemModel);
                }
            }
        });
    }
    
    // ------------------------------------------
    // ## Public Getter
    // ------------------------------------------
    /** Public getter to allow external classes to access the model. */
    public OrderItemModel getItemModel() {
        return itemModel;
    }
}