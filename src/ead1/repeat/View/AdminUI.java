package ead1.repeat.View;

import ead1.repeat.controller.AdminController;

public class AdminUI extends javax.swing.JFrame {
    public AdminUI() {
        initComponents();
        this.setLocationRelativeTo(null); // center window

        // Background only (no controller logic here)
        this.getContentPane().setBackground(new java.awt.Color(204,204,204));
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
        pnlEmployees = new javax.swing.JPanel();
        lblEmployees = new javax.swing.JLabel();
        pnlMenus = new javax.swing.JPanel();
        lblMenus = new javax.swing.JLabel();
        pnlSales = new javax.swing.JPanel();
        lblSales = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin");
        setBackground(new java.awt.Color(204, 255, 255));
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
        lblPanelLoadedName.setText("Admin-Home");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 948, Short.MAX_VALUE)
                .addComponent(lblLogout)
                .addGap(22, 22, 22))
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
        jPanel1.setBounds(0, 0, 1253, 47);

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

        pnlEmployees.setBackground(new java.awt.Color(51, 51, 51));

        lblEmployees.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmployees.setForeground(new java.awt.Color(255, 255, 255));
        lblEmployees.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/employees3.png"))); // NOI18N
        lblEmployees.setText("EMPLOYEES");
        lblEmployees.setIconTextGap(8);

        javax.swing.GroupLayout pnlEmployeesLayout = new javax.swing.GroupLayout(pnlEmployees);
        pnlEmployees.setLayout(pnlEmployeesLayout);
        pnlEmployeesLayout.setHorizontalGroup(
            pnlEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmployeesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblEmployees)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        pnlEmployeesLayout.setVerticalGroup(
            pnlEmployeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmployees, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlEmployees, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        pnlMenus.setBackground(new java.awt.Color(51, 51, 51));

        lblMenus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMenus.setForeground(new java.awt.Color(255, 255, 255));
        lblMenus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/menus3.png"))); // NOI18N
        lblMenus.setText("MENUS");
        lblMenus.setIconTextGap(8);

        javax.swing.GroupLayout pnlMenusLayout = new javax.swing.GroupLayout(pnlMenus);
        pnlMenus.setLayout(pnlMenusLayout);
        pnlMenusLayout.setHorizontalGroup(
            pnlMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenusLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblMenus)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        pnlMenusLayout.setVerticalGroup(
            pnlMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlMenus, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 240, -1));

        pnlSales.setBackground(new java.awt.Color(51, 51, 51));

        lblSales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSales.setForeground(new java.awt.Color(255, 255, 255));
        lblSales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/payments3.png"))); // NOI18N
        lblSales.setText("SALES");
        lblSales.setIconTextGap(8);

        javax.swing.GroupLayout pnlSalesLayout = new javax.swing.GroupLayout(pnlSales);
        pnlSales.setLayout(pnlSalesLayout);
        pnlSalesLayout.setHorizontalGroup(
            pnlSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblSales)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        pnlSalesLayout.setVerticalGroup(
            pnlSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 240, -1));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 44, 240, 780);

        contentPanel.setBackground(new java.awt.Color(204, 204, 204));
        contentPanel.setLayout(new java.awt.CardLayout());
        getContentPane().add(contentPanel);
        contentPanel.setBounds(240, 50, 1000, 740);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AdminUI view = new AdminUI();
            new AdminController(view); // attach controller here
            view.setVisible(true);
        });
    }
    
    // --- Getters for Controller ---
    public javax.swing.JLabel getLblChat() { return lblChat; }
    public javax.swing.JLabel getLblEmployees() { return lblEmployees; }
    public javax.swing.JLabel getLblMenus() { return lblMenus; }
    public javax.swing.JLabel getLblSales() { return lblSales; }
    public javax.swing.JLabel getLblLogout() { return lblLogout; }

    public javax.swing.JPanel getPnlChat() { return pnlChat; }
    public javax.swing.JPanel getPnlEmployees() { return pnlEmployees; }
    public javax.swing.JPanel getPnlMenus() { return pnlMenus; }
    public javax.swing.JPanel getPnlSales() { return pnlSales; }

    public javax.swing.JPanel getContentPanel() { return contentPanel; }
    public javax.swing.JLabel getLblPanelLoadedName() { return lblPanelLoadedName; }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblChat;
    private javax.swing.JLabel lblEmployees;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblMenus;
    private javax.swing.JLabel lblPanelLoadedName;
    private javax.swing.JLabel lblSales;
    private javax.swing.JPanel pnlChat;
    private javax.swing.JPanel pnlEmployees;
    private javax.swing.JPanel pnlMenus;
    private javax.swing.JPanel pnlSales;
    // End of variables declaration//GEN-END:variables
}
