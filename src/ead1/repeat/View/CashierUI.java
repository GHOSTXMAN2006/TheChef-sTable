package ead1.repeat.View;

import ead1.repeat.Controller.CashierController;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class CashierUI extends javax.swing.JFrame {

    public CashierUI() {
        initComponents();
        
        // Background only (no controller logic here)
        this.getContentPane().setBackground(new java.awt.Color(204,204,204));
        
        // 💡 ADDED: Attach the CashierController to this View
        new CashierController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPanelLoadedName = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnlChat = new javax.swing.JPanel();
        lblChat = new javax.swing.JLabel();
        pnlOrders = new javax.swing.JPanel();
        lblOrders = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cashier");
        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(1253, 750));
        setResizable(false);
        setSize(new java.awt.Dimension(1253, 750));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(215, 145, 130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/menu1.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(4, 35));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        lblPanelLoadedName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPanelLoadedName.setForeground(new java.awt.Color(255, 255, 255));
        lblPanelLoadedName.setText("Cashier-Home");

        lblLogout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLogout.setForeground(new java.awt.Color(255, 255, 255));
        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/logout6.png"))); // NOI18N
        lblLogout.setText("LOGOUT");
        lblLogout.setIconTextGap(8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblPanelLoadedName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 930, Short.MAX_VALUE)
                .addComponent(lblLogout)
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPanelLoadedName, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1280, 47);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlChat.setBackground(new java.awt.Color(0, 0, 0));

        lblChat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblChat.setForeground(new java.awt.Color(215, 145, 130));
        lblChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/chat3.png"))); // NOI18N
        lblChat.setText("CHAT");
        lblChat.setIconTextGap(8);

        javax.swing.GroupLayout pnlChatLayout = new javax.swing.GroupLayout(pnlChat);
        pnlChat.setLayout(pnlChatLayout);
        pnlChatLayout.setHorizontalGroup(
            pnlChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChatLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblChat)
                .addContainerGap(143, Short.MAX_VALUE))
        );
        pnlChatLayout.setVerticalGroup(
            pnlChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 240, -1));

        pnlOrders.setBackground(new java.awt.Color(51, 51, 51));

        lblOrders.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOrders.setForeground(new java.awt.Color(255, 255, 255));
        lblOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/orders1.png"))); // NOI18N
        lblOrders.setText("ORDERS");
        lblOrders.setIconTextGap(8);

        javax.swing.GroupLayout pnlOrdersLayout = new javax.swing.GroupLayout(pnlOrders);
        pnlOrders.setLayout(pnlOrdersLayout);
        pnlOrdersLayout.setHorizontalGroup(
            pnlOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdersLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblOrders)
                .addContainerGap(125, Short.MAX_VALUE))
        );
        pnlOrdersLayout.setVerticalGroup(
            pnlOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 44, 240, 780);

        contentPanel.setBackground(new java.awt.Color(204, 204, 204));
        contentPanel.setLayout(new java.awt.CardLayout());
        getContentPane().add(contentPanel);
        contentPanel.setBounds(240, 50, 1020, 740);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new CashierUI().setVisible(true);
        });
    }

    // ------------------------------------------
    // 💡 ADDED GETTER METHODS FOR CONTROLLER ACCESS
    // ------------------------------------------

    public JPanel getContentPanel() {
        return contentPanel;
    }
    
    // Home Panel components
    public JPanel getPnlHome() {
        return pnlChat;
    }

    public JLabel getLblHome() {
        return lblChat;
    }

    // Orders Panel components
    public JPanel getPnlOrders() {
        return pnlOrders;
    }

    public JLabel getLblOrders() {
        return lblOrders;
    }

    // Top Bar components
    public JLabel getLblPanelLoadedName() {
        return lblPanelLoadedName;
    }
    
    public JLabel getLblLogout() {
        return lblLogout;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblChat;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblOrders;
    private javax.swing.JLabel lblPanelLoadedName;
    private javax.swing.JPanel pnlChat;
    private javax.swing.JPanel pnlOrders;
    // End of variables declaration//GEN-END:variables
}
