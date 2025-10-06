package ead1.repeat.View;

import ead1.repeat.Controller.Cashier_AddPlaceOrderController;
import ead1.repeat.Model.OrderItemModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Cashier_AddPlaceOrderPanel extends javax.swing.JPanel {

    // 🔹 UI Components for Order Display 🔹
    private final JPanel pnlOrderList = new JPanel(); // Container for the dynamic rows (inside JScrollPane)
    // 💡 REMOVED: private final JLabel lblGrandTotal = new JLabel("Total: $0.00"); 
    // 💡 Using existing lblTotalPrice instead.
    private final DecimalFormat priceFormatter = new DecimalFormat("0.00");
    
    // ... declare a private JLabel for the placeholder
    private final JLabel lblPlaceholder = new JLabel("No items selected yet. Click 'Add Menus' to begin.");
    
    public Cashier_AddPlaceOrderPanel() {
        initComponents();
        setupPlaceholderLabel(); // 💡 NEW: Call to setup the look and feel
        setupOrderListPanel(); // Custom setup
        
        // 💡 MVC: Attach the controller to handle events
        new Cashier_AddPlaceOrderController(this);
    }
    
    // Add to Cashier_AddPlaceOrderPanel.java

    // 1. Method to remove the specific row panel
    public void removeOrderItemRow(OrderItemModel itemModel) {
        // Iterate through all components in pnlOrderList
        for (java.awt.Component comp : pnlOrderList.getComponents()) {
            if (comp instanceof Cashier_OrderItemRowPanel) {
                Cashier_OrderItemRowPanel rowPanel = (Cashier_OrderItemRowPanel) comp;
                // Check if the OrderItemModel in the row matches the one passed for deletion
                if (rowPanel.getItemModel().equals(itemModel)) {
                    pnlOrderList.remove(rowPanel);
                    break;
                }
            }
        }
        pnlOrderList.revalidate();
        pnlOrderList.repaint();
    }

    // 2. Method to manage the placeholder
    // (Assuming you declared 'private final JLabel lblPlaceholder = new JLabel("...");' 
    // in Cashier_AddPlaceOrderPanel as suggested previously)
    // 2. Method to manage the placeholder (FIXED)
    public void setOrderListPlaceholderVisibility(boolean visible) {
        if (visible) {
            if (lblPlaceholder.getParent() == null) {
                pnlOrderList.removeAll();

                // 💡 FIX 1: Set layout to BorderLayout and add placeholder to CENTER
                pnlOrderList.setLayout(new BorderLayout()); 
                pnlOrderList.add(lblPlaceholder, BorderLayout.CENTER);
            }
        } else {
            // Only remove the placeholder if it's there
            pnlOrderList.remove(lblPlaceholder);

            // When hiding, switch the layout back to BoxLayout for stacking items
            // This is done here to prepare for the *next* item add operation, 
            // though it will also be checked in addOrderItemRow.
            pnlOrderList.setLayout(new BoxLayout(pnlOrderList, BoxLayout.Y_AXIS));
        }
        pnlOrderList.revalidate();
        pnlOrderList.repaint();
    }
    
    // 💡 1. New method to configure the label size and alignment
    private void setupPlaceholderLabel() {
        // Set font size large and color for contrast
        lblPlaceholder.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Increased Font Size
        lblPlaceholder.setForeground(new Color(180, 180, 180)); // Light Gray
        // Center the text within the label's bounds
        lblPlaceholder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlaceholder.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
    }
    
    /** 🔹 Custom setup for the panel that holds the dynamic OrderItemRowPanels. */
    private void setupOrderListPanel() {
        // 1. Set background color (Initial layout setting should be avoided here)
        pnlOrderList.setBackground(new Color(102,102,102)); 

        // 2. Place the dynamic panel into your JScrollPane
        scrollMenusDisplay.setViewportView(pnlOrderList);

        // 💡 IMPORTANT: Ensure the placeholder is visible initially (since the order is empty)
        setOrderListPlaceholderVisibility(true); 

        // 3. Configure the existing lblTotalPrice component
        lblTotalPrice.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalPrice.setText("Total: Rs. 0.00"); // Initialize the text
    }
    
    // ------------------------------------------
    // 🔹 PUBLIC METHODS FOR CONTROLLER MANIPULATION 🔹
    // ------------------------------------------

    /** Adds a new OrderItemRowPanel to the display. */
    public void addOrderItemRow(Cashier_OrderItemRowPanel row) {
        // 💡 FIX 2: Ensure layout is BoxLayout (Y_AXIS) for stacking when adding rows
        if (!(pnlOrderList.getLayout() instanceof BoxLayout)) {
            // If layout is BorderLayout (from placeholder), switch it back to BoxLayout
            pnlOrderList.setLayout(new BoxLayout(pnlOrderList, BoxLayout.Y_AXIS));
        }

        pnlOrderList.add(row);
        // Important for BoxLayout: add a small vertical space between rows
        pnlOrderList.add(javax.swing.Box.createVerticalStrut(5));
        pnlOrderList.revalidate();
        pnlOrderList.repaint();
    }
    
    /** Updates the displayed grand total price using the existing lblTotalPrice. */
    public void updateGrandTotal(double total) {
        lblTotalPrice.setText("Total: Rs. " + priceFormatter.format(total));
    }

    /** Helper method to get the dynamic container panel. */
    public JPanel getPnlOrderList() {
        return pnlOrderList;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAddMenus = new javax.swing.JPanel();
        lblAddMenus = new javax.swing.JLabel();
        scrollMenusDisplay = new javax.swing.JScrollPane();
        pnlPlaceOrder = new javax.swing.JPanel();
        lblPlaceOrder = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 153));
        setMaximumSize(new java.awt.Dimension(965, 553));
        setMinimumSize(new java.awt.Dimension(965, 553));
        setPreferredSize(new java.awt.Dimension(965, 553));

        pnlAddMenus.setBackground(new java.awt.Color(0, 0, 0));

        lblAddMenus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAddMenus.setForeground(new java.awt.Color(255, 255, 255));
        lblAddMenus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/menus3.png"))); // NOI18N
        lblAddMenus.setText("Add Menus");
        lblAddMenus.setIconTextGap(8);

        javax.swing.GroupLayout pnlAddMenusLayout = new javax.swing.GroupLayout(pnlAddMenus);
        pnlAddMenus.setLayout(pnlAddMenusLayout);
        pnlAddMenusLayout.setHorizontalGroup(
            pnlAddMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddMenusLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblAddMenus)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlAddMenusLayout.setVerticalGroup(
            pnlAddMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddMenusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPlaceOrder.setBackground(new java.awt.Color(0, 0, 0));

        lblPlaceOrder.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPlaceOrder.setForeground(new java.awt.Color(153, 204, 0));
        lblPlaceOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/placeOrder2.png"))); // NOI18N
        lblPlaceOrder.setText("Place Order");
        lblPlaceOrder.setIconTextGap(8);

        javax.swing.GroupLayout pnlPlaceOrderLayout = new javax.swing.GroupLayout(pnlPlaceOrder);
        pnlPlaceOrder.setLayout(pnlPlaceOrderLayout);
        pnlPlaceOrderLayout.setHorizontalGroup(
            pnlPlaceOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlaceOrderLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblPlaceOrder)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        pnlPlaceOrderLayout.setVerticalGroup(
            pnlPlaceOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlaceOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPlaceOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblTotalPrice.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTotalPrice.setForeground(new java.awt.Color(255, 255, 255));
        lblTotalPrice.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAddMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlPlaceOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollMenusDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlAddMenus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlPlaceOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(scrollMenusDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
    }// </editor-fold>//GEN-END:initComponents

    // ------------------------------------------
    // PUBLIC GETTER METHODS FOR CONTROLLER ACCESS
    // ------------------------------------------
    
    public JPanel getPnlAddMenus() {
        return pnlAddMenus;
    }

    public JLabel getLblAddMenus() {
        return lblAddMenus;
    }
    
    public JScrollPane getScrollMenusDisplay() {
        return scrollMenusDisplay;
    }
    
    public JPanel getPnlPlaceOrder() {
        return pnlPlaceOrder;
    }
    
    public JLabel getLblPlaceOrder() {
        return lblPlaceOrder;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAddMenus;
    private javax.swing.JLabel lblPlaceOrder;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JPanel pnlAddMenus;
    private javax.swing.JPanel pnlPlaceOrder;
    private javax.swing.JScrollPane scrollMenusDisplay;
    // End of variables declaration//GEN-END:variables
}
