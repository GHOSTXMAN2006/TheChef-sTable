package ead1.repeat.View;

import ead1.repeat.Controller.Chef_PendingCompleteOrdersController;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Chef_PendingCompleteOrdersPanel extends javax.swing.JPanel {

    // 1. Containers for the two dynamic lists
    private final JPanel pnlPendingOrders = createOrderContainer("PENDING ORDERS (INCOMPLETE)", new Color(255,51,51));
    private final JPanel pnlCompleteOrders = createOrderContainer("COMPLETED ORDERS", new Color(102,153,0));
    
    public Chef_PendingCompleteOrdersPanel() {
        initComponents();
        setupMainContainer();
        
        // 💡 MVC: Attach the controller to handle events
        new Chef_PendingCompleteOrdersController(this);
    }
    
    // --- Custom Setup Methods ---
    private JPanel createOrderContainer(String title, Color color) {
        JPanel panel = new JPanel();
        // Use BoxLayout for vertical stacking of order panels
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(220, 220, 220)); 
        
        // Add a titled border to clearly separate sections
        panel.setBorder(new CompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 2), 
                title, 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 18), 
                color
            ),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        return panel;
    }
    
    private void setupMainContainer() {
        // Main container to hold the two sections vertically
        JPanel pnlMaster = new JPanel();
        pnlMaster.setLayout(new BoxLayout(pnlMaster, BoxLayout.Y_AXIS));
        pnlMaster.setBackground(new Color(204, 204, 204));
        
        // Add both sections to the master panel
        pnlMaster.add(pnlPendingOrders);
        pnlMaster.add(Box.createVerticalStrut(20)); // Spacing between sections
        pnlMaster.add(pnlCompleteOrders);
        pnlMaster.add(Box.createVerticalGlue()); // Push content to the top
        
        // Set the master panel as the view for the JScrollPane
        scrollPendingCompeleOrders.setViewportView(pnlMaster);
        scrollPendingCompeleOrders.getVerticalScrollBar().setUnitIncrement(16); // Faster scrolling
    }

    // --- Public Methods for Controller Access ---
    
    public void addPendingOrder(Chef_OrderRowPanel orderPanel) {
        pnlPendingOrders.add(orderPanel);
        pnlPendingOrders.revalidate();
        pnlPendingOrders.repaint();
    }
    
    public void removePendingOrder(Chef_OrderRowPanel orderPanel) {
        pnlPendingOrders.remove(orderPanel);
        pnlPendingOrders.revalidate();
        pnlPendingOrders.repaint();
    }
    
    public void addCompleteOrder(Chef_OrderRowPanel orderPanel) {
        // Add at the top of the complete list (LIFO/reverse-chronological)
        pnlCompleteOrders.add(orderPanel, 0); 
        pnlCompleteOrders.revalidate();
        pnlCompleteOrders.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPendingCompeleOrders = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1000, 750));
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(scrollPendingCompeleOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(scrollPendingCompeleOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPendingCompeleOrders;
    // End of variables declaration//GEN-END:variables
}
