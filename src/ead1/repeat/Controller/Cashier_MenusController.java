package ead1.repeat.Controller;

import ead1.repeat.Model.Cashier_MenusDAO;
import ead1.repeat.Model.MenusModel;
import ead1.repeat.View.Cashier_MenusPanel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JDialog; // New Import

/**
 * Controller for the Cashier's menu selection screen.
 * Responsible for loading menus and handling the selection confirmation.
 * The primary goal of this controller is to pass the selected menus back to its caller (Cashier_AddOrderController).
 */
public class Cashier_MenusController implements Cashier_MenusPanel.MenuSelectionListener {

    private final Cashier_MenusPanel view;
    private final Cashier_MenusDAO dao;
    private final JDialog dialog; // Reference to the dialog to close it upon selection
    private final MenuSelectionListener resultListener; // The listener from Cashier_AddOrderController

    // We modify the constructor to accept the dialog and a listener for the result
    public Cashier_MenusController(Cashier_MenusPanel view, Cashier_MenusDAO dao, JDialog dialog, MenuSelectionListener resultListener) {
        System.out.println("--- CONTROLLER DEBUG: Initializing Cashier_MenusController ---");
        
        if (view == null || dao == null || dialog == null || resultListener == null) {
            throw new IllegalArgumentException("View, DAO, Dialog, and Result Listener must not be null.");
        }
        
        this.view = view;
        this.dao = dao;
        this.dialog = dialog;
        this.resultListener = resultListener; // The listener in the parent controller

        // Set this controller as the listener for selection events on the view
        this.view.setMenuSelectionListener(this);

        // Load and display menus immediately
        loadMenus();
        System.out.println("--- CONTROLLER DEBUG: Initialized and menus loaded. ---");
    }

    /** 🔹 Interface to communicate the result (list of selected MenusModel) back to the caller. */
    public interface MenuSelectionListener {
        void onMenusConfirmed(List<MenusModel> selectedMenus);
    }
    
    /** 🔹 Load menus from DB and show in view */
    public void loadMenus() {
        System.out.println("CONTROLLER DEBUG: loadMenus() started.");
        
        List<MenusModel> menus = dao.getAllMenus();
        
        System.out.println("CONTROLLER DEBUG: Fetched list from DAO. Size: " + menus.size());
        
        // Pass the list to the view for display
        view.displayMenus(menus);
        
        System.out.println("CONTROLLER DEBUG: loadMenus() finished. View update called.");
    }

    /**
     * 🔹 Handles the event when the OK button is clicked in the view (from Cashier_MenusPanel).
     * This is the bridge method that calls the listener in the parent controller.
     * @param selectedMenus The list of menu items the cashier selected.
     */
    @Override
    public void onMenusSelected(List<MenusModel> selectedMenus) {
        System.out.println("CONTROLLER DEBUG: Menu selection confirmed!");
        
        if (selectedMenus.isEmpty()) {
            System.out.println("CONTROLLER DEBUG: No items selected.");
            JOptionPane.showMessageDialog(view, "Please select at least one menu item.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        System.out.println("CONTROLLER DEBUG: Selected " + selectedMenus.size() + " items. Passing to AddOrderController.");
        
        // 1. Pass the list back to the Cashier_AddOrderController
        resultListener.onMenusConfirmed(selectedMenus);
        
        // 2. Close the dialog window
        dialog.dispose();
    }
}
