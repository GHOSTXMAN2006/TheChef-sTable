package ead1.repeat.View;

import ead1.repeat.Model.MenusModel;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.*;

public class Admin_MenusPanel extends javax.swing.JPanel {

    // 🔹 Listener interfaces for MVC
    public interface MenuActions {
        void onAdd();
        void onEdit(MenusModel menu);
        void onDelete(MenusModel menu);
    }

    private MenuActions menuActions;

    public void setMenuActions(MenuActions actions) {
        this.menuActions = actions;
        System.out.println("VIEW DEBUG: MenuActions listener set.");
    }

    public Admin_MenusPanel() {
        System.out.println("--- VIEW DEBUG: MenusPanel constructor started ---");
        initComponents();
        setupUI(); // Last successful debug log
        setupAddButton(); 
        System.out.println("--- VIEW DEBUG: MenusPanel constructor finished successfully ---");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAdd = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        scrollMenus = new javax.swing.JScrollPane();
        pnlMenus = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1000, 750));
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        pnlAdd.setBackground(new java.awt.Color(0, 0, 0));

        lblAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAdd.setForeground(new java.awt.Color(255, 255, 255));
        lblAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/add1.png"))); // NOI18N
        lblAdd.setText("Add");
        lblAdd.setIconTextGap(8);

        javax.swing.GroupLayout pnlAddLayout = new javax.swing.GroupLayout(pnlAdd);
        pnlAdd.setLayout(pnlAddLayout);
        pnlAddLayout.setHorizontalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblAdd)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlAddLayout.setVerticalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlMenus.setBackground(new java.awt.Color(153, 153, 153));
        pnlMenus.setPreferredSize(null);
        scrollMenus.setViewportView(pnlMenus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollMenus, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /** 🔹 Sets up the listener for the Add button */
    private void setupAddButton() {
        System.out.println("--- VIEW DEBUG: setupAddButton() started (Checking for component nulls) ---");
        
        // Check both components
        if (lblAdd == null || pnlAdd == null) { // Check pnlAdd as well
            System.err.println("VIEW FATAL ERROR: lblAdd or pnlAdd component is null! Check initComponents() or your .form file.");
            return;
        }

        System.out.println("VIEW DEBUG: lblAdd and pnlAdd components found. Adding common MouseListener.");
        
        // 1. Define the common MouseListener that triggers the onAdd action
        java.awt.event.MouseAdapter addClickListener = new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("VIEW DEBUG: Add button/panel clicked."); // Updated debug message
                if (menuActions != null) {
                    menuActions.onAdd();
                } else {
                    System.err.println("VIEW ERROR: MenuActions listener not set when Add button was clicked.");
                    JOptionPane.showMessageDialog(null, "Controller is not set up correctly.");
                }
            }
        };

        // 2. Attach the listener to the label (lblAdd)
        lblAdd.addMouseListener(addClickListener);

        // 3. Attach the same listener to the panel (pnlAdd)
        pnlAdd.addMouseListener(addClickListener); // ⬅️ NEW LINE

        System.out.println("VIEW DEBUG: MouseListener added to lblAdd and pnlAdd successfully.");
        System.out.println("--- VIEW DEBUG: setupAddButton() finished ---");
    }


    /** 🔹 Custom setup */
    private void setupUI() {
        // 1. IMPORTANT FIX: Clear size constraints set by the GUI builder to allow GridLayout to function.
        pnlMenus.setPreferredSize(null);
        pnlMenus.setMinimumSize(null);
        pnlMenus.setMaximumSize(null);

        // 2. Set the correct Layout Manager: Changed 3 to 5 columns
        // '0' for rows means: "use as many rows as needed".
        pnlMenus.setLayout(new java.awt.GridLayout(0, 5, 15, 15));
        
        // 3. Set scrollbar increment
        scrollMenus.getVerticalScrollBar().setUnitIncrement(16);
    }
    
    /** 🔹 Renders the menu items in the panel */
    public void displayMenus(List<MenusModel> menus) {
        System.out.println("--- VIEW DEBUG: displayMenus() started. Received " + menus.size() + " menus. ---");
        
        // Clear previous items
        pnlMenus.removeAll();
        System.out.println("VIEW DEBUG: pnlMenus cleared.");
        
        if (menus.isEmpty()) {
            // Handle empty list case
            JLabel emptyLabel = new JLabel("No menus found in the database.", SwingConstants.CENTER);
            emptyLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
            pnlMenus.add(emptyLabel);
            System.out.println("VIEW DEBUG: Added 'No menus found' label.");
        } else {
            for (MenusModel menu : menus) {
                System.out.println("VIEW DEBUG: Creating card for menu ID: " + menu.getId());
                pnlMenus.add(createMenuCard(menu));
            }
        }
        
        // Redraw the panel
        pnlMenus.revalidate();
        pnlMenus.repaint();
        System.out.println("--- VIEW DEBUG: displayMenus() finished. Panel redrawn. ---");
    }

    /** 🔹 Creates a single card view for a menu item */
    private JPanel createMenuCard(MenusModel menu) {

        JPanel card = new JPanel(new java.awt.BorderLayout(10, 10));

        // 🔹 VISUAL ENHANCEMENT: Light coral color
        card.setBackground(new java.awt.Color(255, 230, 230));

        // 🔹 VISUAL ENHANCEMENT: Border
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblName = new JLabel(menu.getName(), SwingConstants.CENTER);
        lblName.setFont(new java.awt.Font("Segoe UI", 1, 16));

        JLabel lblPrice = new JLabel("RS." + String.format("%.2f", menu.getPrice()), SwingConstants.CENTER);
        lblPrice.setFont(new java.awt.Font("Segoe UI", 0, 14));

        JPanel btnPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        btnPanel.setOpaque(false);

        // Edit Button
        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(e -> {
            if (menuActions != null) menuActions.onEdit(menu);
        });

        // Delete Button
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            if (menuActions != null) menuActions.onDelete(menu);
        });

        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        // Image Placeholder
        JLabel lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setPreferredSize(new java.awt.Dimension(150, 150));

        // 💡 IMAGE LOADING LOGIC FIX: Handle both classpath and file system paths
        if (menu.getImagePath() != null && !menu.getImagePath().trim().isEmpty()) {
            ImageIcon icon = null;
            String imagePath = menu.getImagePath();
            String finalLoadPath = imagePath;

            try {
                // 1. Try to load as a Classpath Resource (for built-in images, starts with '/')
                if (imagePath.startsWith("/")) {
                    java.net.URL imageUrl = getClass().getResource(imagePath);
                    if (imageUrl != null) {
                        icon = new ImageIcon(imageUrl);
                    } else {
                        // 🎯 FIX for broken uploaded images (like ID 13): Classpath load failed.
                        // Strip the full prefix to get the disk-relative path (e.g., "Resources/...")
                        if (imagePath.startsWith("/ead1/repeat/")) {
                             finalLoadPath = imagePath.substring("/ead1/repeat/".length());
                             System.out.println("VIEW DEBUG: Correcting broken classpath path for ID " + menu.getId() + " to disk path: " + finalLoadPath);
                        }
                    }
                } 

                // 2. Try to load from the File System (for corrected broken paths AND new, correct disk-relative paths)
                if (icon == null) {
                    // Ensure the path exists on the disk.
                    File imageFile = new File(finalLoadPath);
                    if (imageFile.exists()) {
                        icon = new ImageIcon(finalLoadPath); 
                    } else {
                        System.err.println("VIEW WARNING: File not found on disk at path: " + finalLoadPath);
                    }
                }

                if (icon != null && icon.getImage() != null) {
                    Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    lblImage.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("VIEW ERROR: Image resource not found for ID " + menu.getId() + " at path: " + menu.getImagePath());
                    lblImage.setText("[File Missing]");
                }
            } catch (Exception ex) {
                System.err.println("VIEW ERROR: General failure loading image for ID " + menu.getId());
                ex.printStackTrace();
                lblImage.setText("[Image Error]");
            }
        } else {
            lblImage.setText("[No Image]");
        }

        card.add(lblName, java.awt.BorderLayout.NORTH);
        card.add(lblImage, java.awt.BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new java.awt.BorderLayout());
        southPanel.setOpaque(false);
        southPanel.add(lblPrice, java.awt.BorderLayout.NORTH);
        southPanel.add(btnPanel, java.awt.BorderLayout.SOUTH);
        card.add(southPanel, java.awt.BorderLayout.SOUTH);

        return card;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAdd;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlMenus;
    private javax.swing.JScrollPane scrollMenus;
    // End of variables declaration//GEN-END:variables
}
