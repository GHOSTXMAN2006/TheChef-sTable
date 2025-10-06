package ead1.repeat.View;

import ead1.repeat.Model.MenusModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class Cashier_MenusPanel extends javax.swing.JPanel {

    // 🔹 Interface for Controller communication
    public interface MenuSelectionListener {
        void onMenusSelected(List<MenusModel> selectedMenus);
    }
    
    private MenuSelectionListener listener;
    private final Map<MenusModel, JPanel> cardMap = new HashMap<>(); // Maps MenuModel to its Panel for selection tracking
    private final List<MenusModel> selectedMenus = new ArrayList<>();
    
    // Custom Colors
    private final Color COLOR_DEFAULT = new Color(255, 230, 230);
    private final Color COLOR_SELECTED = new Color(215, 145, 130);
    
    // 🔹 Setter for the Listener
    public void setMenuSelectionListener(MenuSelectionListener listener) {
        this.listener = listener;
    }

    public Cashier_MenusPanel() {
        initComponents();
        setupUI();
        setupOKButton();
    }
    
    /** 🔹 Custom setup for the menu display area. */
    private void setupUI() {
        // Clear size constraints for pnlMenus to allow GridLayout to function
        pnlMenus.setPreferredSize(null);
        pnlMenus.setMinimumSize(null);
        pnlMenus.setMaximumSize(null);

        // Set GridLayout: 0 rows (as many as needed), 4 columns, 15px gap
        pnlMenus.setLayout(new GridLayout(0, 4, 15, 15));
        
        // Set scrollbar increment
        scrollMenus.getVerticalScrollBar().setUnitIncrement(16);
    }

    /** 🔹 Logic executed when the OK action is triggered. */
    private void confirmSelection() {
        System.out.println("VIEW DEBUG: OK button clicked. Notifying controller.");
        if (listener != null) {
            // Pass a copy of the selected list to the controller
            listener.onMenusSelected(new ArrayList<>(selectedMenus));
        }
    }

    /** 🔹 Sets up the listener for the OK button (pnlOk) and its label (lblOk). */
    private void setupOKButton() {
        MouseAdapter okClickListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                confirmSelection();
            }
        };
        
        // Attach the listener to both the panel and the label to ensure a click on either works.
        pnlOk.addMouseListener(okClickListener);
        lblOk.addMouseListener(okClickListener);
    }
    
    /** 🔹 Renders the menu items in the panel */
    public void displayMenus(List<MenusModel> menus) {
        System.out.println("VIEW DEBUG: displayMenus() started. Received " + menus.size() + " menus.");
        
        // Clear previous items and state
        pnlMenus.removeAll();
        cardMap.clear();
        selectedMenus.clear();
        
        if (menus.isEmpty()) {
            JLabel emptyLabel = new JLabel("No menus found.", SwingConstants.CENTER);
            emptyLabel.setFont(new java.awt.Font("Segoe UI", 0, 18));
            pnlMenus.add(emptyLabel);
        } else {
            for (MenusModel menu : menus) {
                JPanel card = createMenuCard(menu);
                pnlMenus.add(card);
                cardMap.put(menu, card); // Store map for selection tracking
            }
        }
        
        // Redraw the panel
        pnlMenus.revalidate();
        pnlMenus.repaint();
        System.out.println("VIEW DEBUG: displayMenus() finished. Panel redrawn.");
    }
    
    /** 🔹 Creates a single card view for a menu item with selection logic. */
    private JPanel createMenuCard(MenusModel menu) {
        JPanel card = new JPanel(new java.awt.BorderLayout(10, 10));
        card.setBackground(COLOR_DEFAULT);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(), // Simple border for cashier panel
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // --- Content Labels ---
        JLabel lblName = new JLabel(menu.getName(), SwingConstants.CENTER);
        lblName.setFont(new java.awt.Font("Segoe UI", 1, 15));

        JLabel lblPrice = new JLabel("RS." + String.format("%.2f", menu.getPrice()), SwingConstants.CENTER);
        lblPrice.setFont(new java.awt.Font("Segoe UI", 0, 13));
        
        // Image Placeholder
        JLabel lblImage = new JLabel();
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setPreferredSize(new Dimension(100, 100));
        
        // Image Loading Logic (Simplified for brevity, reusing Admin logic structure)
        if (menu.getImagePath() != null && !menu.getImagePath().trim().isEmpty()) {
            ImageIcon icon = null;
            String imagePath = menu.getImagePath();
            
            try {
                // Try classpath first
                java.net.URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    icon = new ImageIcon(imageUrl);
                } else {
                    // Fallback to file system (assuming a path like "Resources/...")
                    File imageFile = new File(imagePath);
                    if (imageFile.exists()) {
                        icon = new ImageIcon(imagePath);
                    }
                }

                if (icon != null && icon.getImage() != null) {
                    Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    lblImage.setIcon(new ImageIcon(scaledImage));
                } else {
                    lblImage.setText("[No Image]");
                }
            } catch (Exception ex) {
                lblImage.setText("[Image Error]");
            }
        } else {
             lblImage.setText("[No Image]");
        }
        
        // --- Assemble Card ---
        card.add(lblName, java.awt.BorderLayout.NORTH);
        card.add(lblImage, java.awt.BorderLayout.CENTER);
        card.add(lblPrice, java.awt.BorderLayout.SOUTH);

        // --- Selection Logic ---
        MouseAdapter selectionListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleSelection(menu, card);
            }
        };
        card.addMouseListener(selectionListener);
        lblName.addMouseListener(selectionListener); // Make labels clickable too
        lblImage.addMouseListener(selectionListener);
        lblPrice.addMouseListener(selectionListener);
        
        return card;
    }
    
    /** 🔹 Toggles the selection state of a menu card. */
    private void toggleSelection(MenusModel menu, JPanel card) {
        if (selectedMenus.contains(menu)) {
            // Deselect
            selectedMenus.remove(menu);
            card.setBackground(COLOR_DEFAULT);
            System.out.println("VIEW DEBUG: Deselected " + menu.getName());
        } else {
            // Select
            selectedMenus.add(menu);
            card.setBackground(COLOR_SELECTED);
            System.out.println("VIEW DEBUG: Selected " + menu.getName());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollMenus = new javax.swing.JScrollPane();
        pnlMenus = new javax.swing.JPanel();
        pnlOk = new javax.swing.JPanel();
        lblOk = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1101, 700));
        setMinimumSize(new java.awt.Dimension(1101, 700));
        setPreferredSize(new java.awt.Dimension(1101, 700));

        pnlMenus.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout pnlMenusLayout = new javax.swing.GroupLayout(pnlMenus);
        pnlMenus.setLayout(pnlMenusLayout);
        pnlMenusLayout.setHorizontalGroup(
            pnlMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
        );
        pnlMenusLayout.setVerticalGroup(
            pnlMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );

        scrollMenus.setViewportView(pnlMenus);

        pnlOk.setBackground(new java.awt.Color(0, 0, 0));

        lblOk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblOk.setForeground(new java.awt.Color(153, 204, 0));
        lblOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/ok1.png"))); // NOI18N
        lblOk.setText("Ok");
        lblOk.setIconTextGap(8);

        javax.swing.GroupLayout pnlOkLayout = new javax.swing.GroupLayout(pnlOk);
        pnlOk.setLayout(pnlOkLayout);
        pnlOkLayout.setHorizontalGroup(
            pnlOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOkLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblOk)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        pnlOkLayout.setVerticalGroup(
            pnlOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Select menus to add to the order");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollMenus, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollMenus))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblOk;
    private javax.swing.JPanel pnlMenus;
    private javax.swing.JPanel pnlOk;
    private javax.swing.JScrollPane scrollMenus;
    // End of variables declaration//GEN-END:variables
}
