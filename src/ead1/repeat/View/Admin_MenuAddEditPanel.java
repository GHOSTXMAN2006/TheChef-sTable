package ead1.repeat.View;

import ead1.repeat.Model.MenusModel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File; // 💡 NEW
import java.nio.file.Files; // 💡 NEW
import java.nio.file.Path; // 💡 NEW
import java.nio.file.StandardCopyOption; // 💡 NEW

public class Admin_MenuAddEditPanel extends javax.swing.JPanel {

    private int menuId = 0;
    // CRITICAL FIX: The base path for the runtime must include the package.
    private static final String PACKAGE_PATH = "/ead1/repeat";
    // This is the physical folder name used for copying the file locally.
    private static final String RESOURCE_DIR_NAME = "Resources"; 

    public Admin_MenuAddEditPanel() {
        initComponents();
        
        // 🚀 FIX FOR DIALOG RESIZING (Workaround for not changing initComponents):
        // We constrain the width of the image path text field to prevent a long path 
        // from making the entire dialog too wide when pack() is called.
        Dimension preferredSize = txtImagePath.getPreferredSize();
        txtImagePath.setPreferredSize(new Dimension(250, preferredSize.height));
        txtImagePath.setMaximumSize(new Dimension(250, preferredSize.height));
        
        setupBrowseButton();
        
        // Ensure components are enabled
        txtMenuName.setEnabled(true);
        txtMenuPrice.setEnabled(true);
        pnlSave.setEnabled(true);
    }
    
    // Renamed method to better reflect its function
    private void setupBrowseButton() {
        // Add the Browse button logic (now cleaner)
        btnBrowse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                JFileChooser chooser = new JFileChooser(); 
                
                // Set the filter to only show images
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image Files (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif");
                chooser.setFileFilter(filter);
                
                int result = chooser.showOpenDialog(Admin_MenuAddEditPanel.this);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the absolute path and set it in the text field
                    txtImagePath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
    }

    // --- Public Methods ---

    public MenusModel getMenuData() throws IllegalArgumentException {
        MenusModel menu = new MenusModel();
        menu.setId(this.menuId);

        String name = txtMenuName.getText().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Menu Name cannot be empty.");
        }
        menu.setName(name);

        try {
            double price = Double.parseDouble(txtMenuPrice.getText().trim());
            if (price <= 0) {
                 throw new IllegalArgumentException("Price must be a positive number.");
            }
            menu.setPrice(price);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Price must be a valid number (e.g., 12.50).");
        }

        String imagePath = txtImagePath.getText().trim();
        if (!imagePath.isEmpty()) {
            // Check if it's an existing relative path (Edit mode) or a new absolute path (Add/Edit mode)
            if (imagePath.startsWith(PACKAGE_PATH)) {
                // Existing relative path (User didn't change image).
                menu.setImagePath(imagePath);
            } else {
                // New absolute system path (Image selection occurred).
                String relativePath = saveImageFile(imagePath);
                menu.setImagePath(relativePath);
            }
        } else {
            // Path is empty
            menu.setImagePath(null);
        }

        return menu;
    }

    public void setMenuData(MenusModel menu) {
        this.menuId = menu.getId();
        txtMenuName.setText(menu.getName());
        txtMenuPrice.setText(String.valueOf(menu.getPrice()));
        // Display the relative path for viewing/editing
        txtImagePath.setText(menu.getImagePath() != null ? menu.getImagePath() : "");
    }

    public javax.swing.JPanel getPnlSave() {
        return pnlSave;
    }

    // --- File Copy Logic (Updated with correct resource path) ---
    private String saveImageFile(String absolutePath) throws IllegalArgumentException {
        File sourceFile = new File(absolutePath);

        if (!sourceFile.exists()) {
             throw new IllegalArgumentException("Image file not found at the specified path.");
        }

        try {
            // Use the simple directory name for creating the physical folder
            // RESOURCE_DIR_NAME should be "Resources"
            File destDir = new File(RESOURCE_DIR_NAME); 
            if (!destDir.exists()) {
                destDir.mkdirs();
            }

            String originalFileName = sourceFile.getName();
            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

            Path destPath = destDir.toPath().resolve(uniqueFileName);

            // This copies the file from the user's system to your project's 'Resources' folder
            Files.copy(sourceFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

            // 🎯 CRITICAL FIX: Return a simple DISK-RELATIVE path (e.g., Resources/123_image.jpg) 
            // This path is correct for file system loading at runtime.
            return RESOURCE_DIR_NAME + "/" + uniqueFileName; 

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to save image file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAddEditContent = new javax.swing.JPanel();
        lblMenuName = new javax.swing.JLabel();
        txtMenuName = new javax.swing.JTextField();
        lblMenuPrice = new javax.swing.JLabel();
        txtMenuPrice = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        pnlSave = new javax.swing.JPanel();
        lblSave = new javax.swing.JLabel();
        txtImagePath = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();

        pnlAddEditContent.setBackground(new java.awt.Color(215, 145, 130));

        lblMenuName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMenuName.setText("Menu Name");

        txtMenuName.setBackground(java.awt.SystemColor.control);
        txtMenuName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMenuName.setToolTipText("");
        txtMenuName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtMenuName.setEnabled(false);
        txtMenuName.setSelectionColor(new java.awt.Color(204, 204, 204));

        lblMenuPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMenuPrice.setText("Menu Price");

        txtMenuPrice.setBackground(java.awt.SystemColor.control);
        txtMenuPrice.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMenuPrice.setToolTipText("");
        txtMenuPrice.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtMenuPrice.setEnabled(false);
        txtMenuPrice.setSelectionColor(new java.awt.Color(204, 204, 204));

        lblImage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblImage.setText("Image");

        pnlSave.setBackground(new java.awt.Color(0, 0, 0));
        pnlSave.setEnabled(false);

        lblSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSave.setForeground(new java.awt.Color(102, 153, 0));
        lblSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/save1.png"))); // NOI18N
        lblSave.setText("Save");
        lblSave.setIconTextGap(8);

        javax.swing.GroupLayout pnlSaveLayout = new javax.swing.GroupLayout(pnlSave);
        pnlSave.setLayout(pnlSaveLayout);
        pnlSaveLayout.setHorizontalGroup(
            pnlSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaveLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblSave)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pnlSaveLayout.setVerticalGroup(
            pnlSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtImagePath.setBackground(java.awt.SystemColor.control);
        txtImagePath.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtImagePath.setToolTipText("");
        txtImagePath.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtImagePath.setEnabled(false);
        txtImagePath.setSelectionColor(new java.awt.Color(204, 204, 204));

        btnBrowse.setText("Browse");

        javax.swing.GroupLayout pnlAddEditContentLayout = new javax.swing.GroupLayout(pnlAddEditContent);
        pnlAddEditContent.setLayout(pnlAddEditContentLayout);
        pnlAddEditContentLayout.setHorizontalGroup(
            pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddEditContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMenuPrice)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddEditContentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtImagePath, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlAddEditContentLayout.createSequentialGroup()
                        .addGroup(pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMenuName)
                            .addComponent(txtMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMenuPrice)
                            .addGroup(pnlAddEditContentLayout.createSequentialGroup()
                                .addComponent(lblImage)
                                .addGap(18, 18, 18)
                                .addComponent(btnBrowse)))
                        .addGap(0, 113, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAddEditContentLayout.setVerticalGroup(
            pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddEditContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMenuName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMenuName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMenuPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMenuPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImage)
                    .addComponent(btnBrowse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtImagePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAddEditContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAddEditContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblMenuName;
    private javax.swing.JLabel lblMenuPrice;
    private javax.swing.JLabel lblSave;
    private javax.swing.JPanel pnlAddEditContent;
    private javax.swing.JPanel pnlSave;
    private javax.swing.JTextField txtImagePath;
    private javax.swing.JTextField txtMenuName;
    private javax.swing.JTextField txtMenuPrice;
    // End of variables declaration//GEN-END:variables
}
