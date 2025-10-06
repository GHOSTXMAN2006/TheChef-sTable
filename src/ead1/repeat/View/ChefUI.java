package ead1.repeat.View;

import ead1.repeat.Controller.ChefController; // 💡 ADDED IMPORT
import javax.swing.JPanel;
import javax.swing.JLabel;

public class ChefUI extends javax.swing.JFrame {

    public ChefUI() {
        initComponents();
        
        // Background color logic (optional, but good practice)
        this.getContentPane().setBackground(new java.awt.Color(204, 204, 204));
        
        // 💡 ADDED: Attach the ChefController to this View
        new ChefController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPanelLoadedName = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pnlChat = new javax.swing.JPanel();
        lblChat = new javax.swing.JLabel();
        pnlPendingOrders = new javax.swing.JPanel();
        lblPendingOrders = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chef");
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
        lblPanelLoadedName.setText("Chef-Home");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 956, Short.MAX_VALUE)
                .addComponent(lblLogout)
                .addGap(30, 30, 30))
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
        jPanel1.setBounds(0, 0, 1250, 47);

        contentPanel.setBackground(new java.awt.Color(204, 204, 204));
        contentPanel.setLayout(new java.awt.CardLayout());
        getContentPane().add(contentPanel);
        contentPanel.setBounds(243, 50, 1000, 740);

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
                .addContainerGap(153, Short.MAX_VALUE))
        );
        pnlChatLayout.setVerticalGroup(
            pnlChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlChat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 250, -1));

        pnlPendingOrders.setBackground(new java.awt.Color(51, 51, 51));

        lblPendingOrders.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPendingOrders.setForeground(new java.awt.Color(255, 255, 255));
        lblPendingOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/pendingOrders1.png"))); // NOI18N
        lblPendingOrders.setText("PENDING ORDERS");
        lblPendingOrders.setIconTextGap(8);

        javax.swing.GroupLayout pnlPendingOrdersLayout = new javax.swing.GroupLayout(pnlPendingOrders);
        pnlPendingOrders.setLayout(pnlPendingOrdersLayout);
        pnlPendingOrdersLayout.setHorizontalGroup(
            pnlPendingOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendingOrdersLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblPendingOrders)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        pnlPendingOrdersLayout.setVerticalGroup(
            pnlPendingOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPendingOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPendingOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlPendingOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 250, -1));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(-7, 44, 250, 780);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChefUI().setVisible(true);
            }
        });
    }
    
    // ------------------------------------------
    // 💡 ADDED GETTER METHODS FOR CONTROLLER ACCESS
    // ------------------------------------------

    public JPanel getContentPanel() {
        return contentPanel;
    }
    
    // Chat Panel components
    public JPanel getPnlChat() {
        return pnlChat;
    }

    public JLabel getLblChat() {
        return lblChat;
    }

    // Pending Orders Panel components
    public JPanel getPnlPendingOrders() {
        return pnlPendingOrders;
    }

    public JLabel getLblPendingOrders() {
        return lblPendingOrders;
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
    private javax.swing.JLabel lblPanelLoadedName;
    private javax.swing.JLabel lblPendingOrders;
    private javax.swing.JPanel pnlChat;
    private javax.swing.JPanel pnlPendingOrders;
    // End of variables declaration//GEN-END:variables
}
