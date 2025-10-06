package ead1.repeat.Controller;

import ead1.repeat.View.Cashier_AddPlaceOrderPanel;
import ead1.repeat.View.Cashier_OrderHistoryPanel;
import ead1.repeat.View.Cashier_OrdersPanel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cashier_OrdersController {

    private final Cashier_OrdersPanel view;

    // Panels to be loaded into pnlOrderAddOrHistory
    private final Cashier_AddPlaceOrderPanel addOrderPanel = new Cashier_AddPlaceOrderPanel();
    private final Cashier_OrderHistoryPanel historyPanel = new Cashier_OrderHistoryPanel();

    // Color constants for active/inactive button styling
    private final Color ACTIVE_PANEL_BG = new Color(51, 51, 51);      // dark gray
    private final Color ACTIVE_LABEL_FG = new Color(215, 145, 130);   // peach
    private final Color INACTIVE_PANEL_BG = new Color(0, 0, 0);       // black
    private final Color INACTIVE_LABEL_FG = new Color(255, 255, 255); // white

    public Cashier_OrdersController(Cashier_OrdersPanel view) {
        this.view = view;

        // 1️⃣ Show Add Orders panel first
        initPanelContent();

        // 2️⃣ Add mouse listeners for buttons
        addListeners();
    }

    // --- Initial Setup ---
    private void initPanelContent() {
        // Set default content to Add Orders panel
        view.getPnlOrderAddOrHistory().removeAll();
        view.getPnlOrderAddOrHistory().add(addOrderPanel, java.awt.BorderLayout.CENTER);
        view.getPnlOrderAddOrHistory().revalidate();
        view.getPnlOrderAddOrHistory().repaint();

        // Highlight Add Orders button
        setActive(view.getPnlAdd(), view.getLblAdd());
        setInactive(view.getPnlOrderHistory(), view.getLblOrderHistory());
    }

    // --- Listeners for switching panels ---
    private void addListeners() {
        // 🟢 Add Orders button
        MouseAdapter addListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ✅ Load the existing Add Order panel (don’t refresh)
                loadPanel(addOrderPanel,
                          view.getPnlAdd(), view.getLblAdd(),
                          view.getPnlOrderHistory(), view.getLblOrderHistory());
            }
        };
        view.getPnlAdd().addMouseListener(addListener);
        view.getLblAdd().addMouseListener(addListener);

        // 🔵 Order History button
        MouseAdapter historyListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // ✅ Create a *new* history panel each time to refresh it
                Cashier_OrderHistoryPanel newHistoryPanel = new Cashier_OrderHistoryPanel();

                loadPanel(newHistoryPanel,
                          view.getPnlOrderHistory(), view.getLblOrderHistory(),
                          view.getPnlAdd(), view.getLblAdd());
            }
        };
        view.getPnlOrderHistory().addMouseListener(historyListener);
        view.getLblOrderHistory().addMouseListener(historyListener);
    }

    // --- Panel Loading + Color Update ---
    private void loadPanel(JPanel panelToLoad,
                           JPanel activePanel, JLabel activeLabel,
                           JPanel inactivePanel, JLabel inactiveLabel) {

        // Change the center content
        view.getPnlOrderAddOrHistory().removeAll();
        view.getPnlOrderAddOrHistory().add(panelToLoad, java.awt.BorderLayout.CENTER);
        view.getPnlOrderAddOrHistory().revalidate();
        view.getPnlOrderAddOrHistory().repaint();

        // Update button colors
        setActive(activePanel, activeLabel);
        setInactive(inactivePanel, inactiveLabel);
    }

    // --- Active / Inactive Button Styles ---
    private void setActive(JPanel panel, JLabel label) {
        panel.setBackground(ACTIVE_PANEL_BG);
        label.setForeground(ACTIVE_LABEL_FG);
    }

    private void setInactive(JPanel panel, JLabel label) {
        panel.setBackground(INACTIVE_PANEL_BG);
        label.setForeground(INACTIVE_LABEL_FG);
    }
}
