package ead1.repeat.Controller;

import ead1.repeat.Model.UserSession;
import ead1.repeat.View.ChefUI;
import ead1.repeat.View.LoginUI;
import ead1.repeat.View.Chef_ChatPanel;
import ead1.repeat.View.Chef_PendingCompleteOrdersPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class ChefController {

    private final ChefUI view;

    private final Color ACTIVE_PANEL_BG = new Color(0, 0, 0);       // Black
    private final Color ACTIVE_LABEL_FG = new Color(215, 145, 130); // Peach
    private final Color INACTIVE_PANEL_BG = new Color(51, 51, 51);  // Dark gray
    private final Color INACTIVE_LABEL_FG = new Color(255, 255, 255); // White

    public ChefController(ChefUI view) {
        System.out.println("--- CHEF CONTROLLER DEBUG: Initializing ChefController ---");
        this.view = view;

        // Initialize session for standalone testing (optional)
        if (UserSession.getEmployeeId() == 0) {
            UserSession.setUsername("chef");
            UserSession.setRole("chef");
            UserSession.setEmployeeId(0);
            System.out.println("--- CHEF CONTROLLER DEBUG: UserSession manually set for 'chef' (ID 0). ---");
        }

        // 1️⃣ Load all panels
        preloadPanels();

        // 2️⃣ Add sidebar listeners
        addMenuListeners();

        // 3️⃣ Show default panel → Chat
        loadPanel("Chat");

        // 4️⃣ Logout listener
        view.getLblLogout().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLogout();
            }
        });

        System.out.println("--- CHEF CONTROLLER DEBUG: Initialization Complete ---");
    }

    private void preloadPanels() {
        System.out.println("--- CHEF CONTROLLER DEBUG: preloadPanels() started ---");
        JPanel contentPanel = view.getContentPanel();
        contentPanel.removeAll();

        // Add Chef panels
        contentPanel.add(new Chef_ChatPanel(), "Chat");
        contentPanel.add(new Chef_PendingCompleteOrdersPanel(), "PendingOrders");

        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("--- CHEF CONTROLLER DEBUG: preloadPanels() finished ---");
    }

    private void addMenuListeners() {
        // CHAT
        view.getLblChat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Chat"); }
        });
        view.getPnlChat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Chat"); }
        });

        // PENDING ORDERS
        view.getLblPendingOrders().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("PendingOrders"); }
        });
        view.getPnlPendingOrders().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("PendingOrders"); }
        });
    }

    private void loadPanel(String panelName) {
        System.out.println("--- CHEF CONTROLLER DEBUG: loadPanel() called for: " + panelName + " ---");
        JPanel contentPanel = view.getContentPanel();
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);

        switch (panelName) {
            case "Chat":
                setActive(view.getPnlChat(), view.getLblChat());
                setInactive(view.getPnlPendingOrders(), view.getLblPendingOrders());
                Component panel = getActivePanel(contentPanel, panelName);
                if (panel instanceof Chef_ChatPanel) {
                    ((Chef_ChatPanel) panel).initializeChatPanel();
                    System.out.println("CHEF CONTROLLER DEBUG: Chat panel initialized.");
                }
                break;

            case "PendingOrders":
                setActive(view.getPnlPendingOrders(), view.getLblPendingOrders());
                setInactive(view.getPnlChat(), view.getLblChat());
                break;
        }

        view.getLblPanelLoadedName().setText("Chef - " + panelName);
        view.revalidate();
        view.repaint();
        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("--- CHEF CONTROLLER DEBUG: loadPanel() finished for: " + panelName + " ---");
    }

    private Component getActivePanel(JPanel parent, String panelName) {
        for (Component comp : parent.getComponents()) {
            if (comp.isVisible()) {
                if (comp instanceof Chef_ChatPanel && panelName.equals("Chat")) {
                    return comp;
                }
                if (comp instanceof Chef_PendingCompleteOrdersPanel && panelName.equals("PendingOrders")) {
                    return comp;
                }
            }
        }
        return null;
    }

    private void setActive(JPanel panel, JLabel label) {
        panel.setBackground(ACTIVE_PANEL_BG);
        label.setForeground(ACTIVE_LABEL_FG);
    }

    private void setInactive(JPanel panel, JLabel label) {
        panel.setBackground(INACTIVE_PANEL_BG);
        label.setForeground(INACTIVE_LABEL_FG);
    }

    private void handleLogout() {
        System.out.println("CHEF CONTROLLER DEBUG: Handling Logout.");
        UserSession.clearSession();
        view.dispose();
        LoginUI login = new LoginUI();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        System.out.println("CHEF CONTROLLER DEBUG: ChefUI disposed. LoginUI launched.");
    }
}
