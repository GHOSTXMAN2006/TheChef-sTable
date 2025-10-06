package ead1.repeat.Controller;

import ead1.repeat.Model.UserSession;
import ead1.repeat.View.CashierUI;
import ead1.repeat.View.LoginUI;
import ead1.repeat.View.Cashier_ChatPanel;
import ead1.repeat.View.Cashier_OrdersPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class CashierController {

    private final CashierUI view;

    // 🎨 Sidebar color constants
    private final Color ACTIVE_PANEL_BG = new Color(0, 0, 0);
    private final Color ACTIVE_LABEL_FG = new Color(215, 145, 130);
    private final Color INACTIVE_PANEL_BG = new Color(51, 51, 51);
    private final Color INACTIVE_LABEL_FG = new Color(255, 255, 255);

    public CashierController(CashierUI view) {
        System.out.println("--- CASHIER CONTROLLER DEBUG: Initializing CashierController ---");
        this.view = view;
        
        // 💡 Ensure user session is valid
        if (UserSession.getEmployeeId() == 0) {
            System.out.println("--- CASHIER CONTROLLER DEBUG: User session not set. Proceeding as generic user. ---");
        }

        // 🔹 Load all panels
        preloadPanels();

        // 🔹 Add sidebar listeners
        addMenuListeners();

        // 🔹 Show default panel (Chat)
        loadPanel("Chat");

        // 💬 Initialize chat after loading
        Component panel = getActivePanel(view.getContentPanel(), "Chat");
        if (panel instanceof Cashier_ChatPanel) {
            ((Cashier_ChatPanel) panel).initializeChatPanel();
        }

        // 🔹 Logout button
        view.getLblLogout().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLogout();
            }
        });

        System.out.println("--- CASHIER CONTROLLER DEBUG: Initialization Complete ---");
    }

    // --- Preload all panels ---
    private void preloadPanels() {
        System.out.println("--- CASHIER CONTROLLER DEBUG: preloadPanels() started ---");
        JPanel contentPanel = view.getContentPanel();

        contentPanel.removeAll();
        System.out.println("CASHIER CONTROLLER DEBUG: ContentPanel cleared.");

        contentPanel.add(new Cashier_ChatPanel(), "Chat"); 
        contentPanel.add(new Cashier_OrdersPanel(), "Orders");

        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("--- CASHIER CONTROLLER DEBUG: preloadPanels() finished ---");
    }

    // --- Sidebar listeners ---
    private void addMenuListeners() {
        // CHAT
        view.getLblHome().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Chat"); }
        });
        view.getPnlHome().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Chat"); }
        });

        // ORDERS
        view.getLblOrders().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Orders"); }
        });
        view.getPnlOrders().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Orders"); }
        });
    }

    // --- Load a panel into view ---
    private void loadPanel(String panelName) {
        System.out.println("--- CASHIER CONTROLLER DEBUG: loadPanel() called for: " + panelName + " ---");
        JPanel contentPanel = view.getContentPanel();
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);

        switch (panelName) {
            case "Chat":
                setActive(view.getPnlHome(), view.getLblHome());
                setInactive(view.getPnlOrders(), view.getLblOrders());
                Component panel = getActivePanel(contentPanel, panelName);
                if (panel instanceof Cashier_ChatPanel) {
                    ((Cashier_ChatPanel) panel).initializeChatPanel(); 
                    System.out.println("CASHIER CONTROLLER DEBUG: ChatPanel initialized.");
                }
                break;

            case "Orders":
                setActive(view.getPnlOrders(), view.getLblOrders());
                setInactive(view.getPnlHome(), view.getLblHome());
                break;
        }

        // 🔹 Update title label
        view.getLblPanelLoadedName().setText("Cashier - " + panelName);

        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("--- CASHIER CONTROLLER DEBUG: loadPanel() finished for: " + panelName + " ---");
    }

    // --- Helper to find active panel ---
    private Component getActivePanel(JPanel parent, String panelName) {
        for (Component comp : parent.getComponents()) {
            if (comp.isVisible()) {
                if (comp instanceof Cashier_ChatPanel && panelName.equals("Chat")) {
                    return comp;
                }
                if (comp instanceof Cashier_OrdersPanel && panelName.equals("Orders")) {
                    return comp;
                }
            }
        }
        return null; 
    }

    // --- Sidebar style helpers ---
    private void setActive(JPanel panel, JLabel label) {
        panel.setBackground(ACTIVE_PANEL_BG);
        label.setForeground(ACTIVE_LABEL_FG);
    }

    private void setInactive(JPanel panel, JLabel label) {
        panel.setBackground(INACTIVE_PANEL_BG);
        label.setForeground(INACTIVE_LABEL_FG);
    }

    // --- Logout logic ---
    private void handleLogout() {
        System.out.println("CASHIER CONTROLLER DEBUG: Handling Logout.");
        UserSession.clearSession();
        view.dispose();
        LoginUI login = new LoginUI();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        System.out.println("CASHIER CONTROLLER DEBUG: CashierUI disposed. LoginUI launched.");
    }
}
