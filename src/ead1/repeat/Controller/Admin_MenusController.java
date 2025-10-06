package ead1.repeat.Controller;

import ead1.repeat.Model.MenusModel;
import ead1.repeat.Model.Admin_MenusDAO;
import ead1.repeat.View.Admin_MenusPanel;
import ead1.repeat.View.Admin_MenuAddEditPanel; 
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JDialog; 
import javax.swing.SwingUtilities; 
import java.awt.Frame; // Import Frame for casting the dialog owner
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 

public class Admin_MenusController {

    private Admin_MenusPanel view;
    private Admin_MenusDAO dao;

    public Admin_MenusController(Admin_MenusPanel view, Admin_MenusDAO dao) {
        System.out.println("--- CONTROLLER DEBUG: Initializing Admin_MenusController ---");
        
        if (view == null || dao == null) {
            System.err.println("CONTROLLER FATAL ERROR: View or DAO is null during initialization.");
            throw new IllegalArgumentException("MenusPanel view and Admin_MenusDAO must not be null.");
        }
        
        this.view = view;
        this.dao = dao;

        // Set menu actions for MVC
        System.out.println("CONTROLLER DEBUG: Setting up MenuActions interface...");
        this.view.setMenuActions(new Admin_MenusPanel.MenuActions() {
            @Override
            public void onAdd() {
                System.out.println("CONTROLLER DEBUG: MenuActions.onAdd() triggered.");
                openAddDialog();
            }

            @Override
            public void onEdit(MenusModel menu) {
                System.out.println("CONTROLLER DEBUG: MenuActions.onEdit() triggered for ID: " + menu.getId());
                openEditDialog(menu);
            }

            @Override
            public void onDelete(MenusModel menu) {
                System.out.println("CONTROLLER DEBUG: MenuActions.onDelete() triggered for ID: " + menu.getId());
                deleteMenu(menu);
            }
        });
        System.out.println("CONTROLLER DEBUG: MenuActions setup complete.");


        loadMenus();
        System.out.println("--- CONTROLLER DEBUG: Admin_MenusController initialized and loadMenus() called ---");
    }

    /** 🔹 Load menus from DB and show in view */
    public void loadMenus() {
        // ... (implementation unchanged)
        System.out.println("--- CONTROLLER DEBUG: loadMenus() started ---");
        
        List<MenusModel> menus = dao.getAllMenus();
        
        System.out.println("CONTROLLER DEBUG: Fetched list from DAO. Size: " + menus.size());
        
        view.displayMenus(menus);
        
        System.out.println("--- CONTROLLER DEBUG: loadMenus() finished. View update called. ---");
    }

    /** 🔹 Open Add Menu dialog */
    private void openAddDialog() {
        System.out.println("--- CONTROLLER DEBUG: openAddDialog() started (Using MenuAddEditPanel) ---");
        
        Admin_MenuAddEditPanel formPanel = new Admin_MenuAddEditPanel();
        
        // 1. Set up the JDialog with CRITICAL FIX (Casting to Frame)
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(view), "Add New Menu Item", true);
        dialog.setContentPane(formPanel);
        
        // ✅ FIX: Use pack() to size to the constrained component's preferred size, and then disable resizing.
        dialog.pack(); 
        dialog.setResizable(false);
        
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(view));

        // 2. Listen for the Save click on the panel
        formPanel.getPnlSave().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    MenusModel menu = formPanel.getMenuData();
                    System.out.println("CONTROLLER DEBUG: Attempting to add menu: " + menu.getName());
                    
                    if (dao.addMenu(menu)) {
                        System.out.println("CONTROLLER DEBUG: Menu added successfully. Reloading menus.");
                        JOptionPane.showMessageDialog(dialog, "Menu item added successfully!");
                        dialog.dispose();
                        loadMenus();
                    } else {
                        System.out.println("CONTROLLER DEBUG: Failed to add menu.");
                        JOptionPane.showMessageDialog(dialog, "Failed to add menu item.", "DB Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("CONTROLLER DEBUG: Input error: " + ex.getMessage());
                    JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        dialog.setVisible(true);
        System.out.println("--- CONTROLLER DEBUG: openAddDialog() finished ---");
    }

    /** 🔹 Open Edit Menu dialog */
    private void openEditDialog(MenusModel menu) {
        System.out.println("--- CONTROLLER DEBUG: openEditDialog() started for ID: " + menu.getId() + " (Using MenuAddEditPanel) ---");
        
        Admin_MenuAddEditPanel formPanel = new Admin_MenuAddEditPanel();
        formPanel.setMenuData(menu); // Load existing data into the form

        // 1. Set up the JDialog with CRITICAL FIX (Casting to Frame)
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(view), "Edit Menu Item: " + menu.getName(), true); 
        dialog.setContentPane(formPanel);
        
        // ✅ FIX: Use pack() to size to the constrained component's preferred size, and then disable resizing.
        dialog.pack(); 
        dialog.setResizable(false);

        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(view));

        // 2. Listen for the Save click on the panel
        formPanel.getPnlSave().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    MenusModel updatedMenu = formPanel.getMenuData();
                    System.out.println("CONTROLLER DEBUG: Attempting to update menu: " + updatedMenu.getName());
                    
                    if (dao.updateMenu(updatedMenu)) {
                        System.out.println("CONTROLLER DEBUG: Menu updated successfully. Reloading menus.");
                        JOptionPane.showMessageDialog(dialog, "Menu item updated successfully!");
                        dialog.dispose();
                        loadMenus();
                    } else {
                        System.out.println("CONTROLLER DEBUG: Failed to update menu.");
                        JOptionPane.showMessageDialog(dialog, "Failed to update menu item.", "DB Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("CONTROLLER DEBUG: Input error: " + ex.getMessage());
                    JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        dialog.setVisible(true);
        System.out.println("--- CONTROLLER DEBUG: openEditDialog() finished ---");
    }

    /** 🔹 Delete menu */
    private void deleteMenu(MenusModel menu) {
        // ... (implementation unchanged)
        System.out.println("--- CONTROLLER DEBUG: deleteMenu() started for ID: " + menu.getId());
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete \"" + menu.getName() + "\"?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) {
            System.out.println("CONTROLLER DEBUG: Delete cancelled by user.");
            return;
        }
        
        System.out.println("CONTROLLER DEBUG: Attempting to delete menu ID: " + menu.getId());

        if (dao.deleteMenu(menu.getId())) {
            System.out.println("CONTROLLER DEBUG: Menu deleted successfully. Reloading menus.");
            JOptionPane.showMessageDialog(null, "Menu deleted!");
            loadMenus();
        } else {
            System.out.println("CONTROLLER DEBUG: Failed to delete menu.");
            JOptionPane.showMessageDialog(null, "Failed to delete menu.");
        }
        System.out.println("--- CONTROLLER DEBUG: deleteMenu() finished ---\n");
    }
}