package ead1.repeat.View;

import ead1.repeat.Controller.Cashier_OrdersController;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Cashier_OrdersPanel extends javax.swing.JPanel {

    public Cashier_OrdersPanel() {
        initComponents();
        
        pnlOrderAddOrHistory.setLayout(new java.awt.BorderLayout());
        
        // 💡 MVC SEPARATION: Initialize the controller for this panel
        new Cashier_OrdersController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlOrderHistory = new javax.swing.JPanel();
        lblOrderHistory = new javax.swing.JLabel();
        pnlAdd = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        pnlOrderAddOrHistory = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1000, 750));
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        pnlOrderHistory.setBackground(new java.awt.Color(0, 0, 0));

        lblOrderHistory.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrderHistory.setForeground(new java.awt.Color(255, 255, 255));
        lblOrderHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/orderHistory1.png"))); // NOI18N
        lblOrderHistory.setText("Order History");
        lblOrderHistory.setIconTextGap(8);

        javax.swing.GroupLayout pnlOrderHistoryLayout = new javax.swing.GroupLayout(pnlOrderHistory);
        pnlOrderHistory.setLayout(pnlOrderHistoryLayout);
        pnlOrderHistoryLayout.setHorizontalGroup(
            pnlOrderHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderHistoryLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblOrderHistory)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlOrderHistoryLayout.setVerticalGroup(
            pnlOrderHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAdd.setBackground(new java.awt.Color(51, 51, 51));

        lblAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAdd.setForeground(new java.awt.Color(215, 145, 130));
        lblAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/add1.png"))); // NOI18N
        lblAdd.setText("Add Orders");
        lblAdd.setIconTextGap(8);

        javax.swing.GroupLayout pnlAddLayout = new javax.swing.GroupLayout(pnlAdd);
        pnlAdd.setLayout(pnlAddLayout);
        pnlAddLayout.setHorizontalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblAdd)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnlAddLayout.setVerticalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlOrderAddOrHistoryLayout = new javax.swing.GroupLayout(pnlOrderAddOrHistory);
        pnlOrderAddOrHistory.setLayout(pnlOrderAddOrHistoryLayout);
        pnlOrderAddOrHistoryLayout.setHorizontalGroup(
            pnlOrderAddOrHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
        );
        pnlOrderAddOrHistoryLayout.setVerticalGroup(
            pnlOrderAddOrHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOrderAddOrHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlOrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlOrderHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlOrderAddOrHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pnlOrderAddOrHistory.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    // ------------------------------------------
    // PUBLIC GETTER METHODS FOR CONTROLLER ACCESS
    // ------------------------------------------
    public JPanel getPnlOrderAddOrHistory() {
        return pnlOrderAddOrHistory;
    }

    public JPanel getPnlOrderHistory() {
        return pnlOrderHistory;
    }

    public JLabel getLblOrderHistory() {
        return lblOrderHistory;
    }

    public JPanel getPnlAdd() {
        return pnlAdd;
    }

    public JLabel getLblAdd() {
        return lblAdd;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblOrderHistory;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlOrderAddOrHistory;
    private javax.swing.JPanel pnlOrderHistory;
    // End of variables declaration//GEN-END:variables
}
