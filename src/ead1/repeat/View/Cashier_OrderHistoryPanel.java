package ead1.repeat.View;

import ead1.repeat.Controller.Cashier_OrderHistoryController;
import javax.swing.*;

public class Cashier_OrderHistoryPanel extends javax.swing.JPanel {

    // 💡 NEW: Declare the container panel where dynamic rows will be added
    private JPanel pnlHistoryContainer;

    // 💡 NEW: Declare the controller field
    private Cashier_OrderHistoryController controller; 

    public Cashier_OrderHistoryPanel(){
        initComponents();
        // Corrected background color syntax: no 'r:', 'g:', 'b:' prefixes are needed.
        scrollOrderHistory.getViewport().setBackground(new java.awt.Color(104, 104, 104));

        // 💡 MVC: Initialize the controller AND ASSIGN IT TO THE FIELD
        this.controller = new Cashier_OrderHistoryController(this); // <-- FIXED LINE
    }

    // 💡 NEW PUBLIC METHOD: To allow the Controller to set its dynamic panel
    public void setDynamicOrderHistoryPanel(JPanel dynamicPanel) {
        this.pnlHistoryContainer = dynamicPanel;
        scrollOrderHistory.setViewportView(pnlHistoryContainer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollOrderHistory = new javax.swing.JScrollPane();

        setBackground(new java.awt.Color(153, 153, 153));
        setMaximumSize(new java.awt.Dimension(965, 553));
        setMinimumSize(new java.awt.Dimension(965, 553));

        scrollOrderHistory.setBackground(new java.awt.Color(104, 104, 104));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(scrollOrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(scrollOrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollOrderHistory;
    // End of variables declaration//GEN-END:variables
}
