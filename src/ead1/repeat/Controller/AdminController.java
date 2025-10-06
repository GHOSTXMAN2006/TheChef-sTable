package ead1.repeat.controller;

import ead1.repeat.View.*;
import ead1.repeat.Model.Admin_MenusDAO;
import ead1.repeat.Controller.Admin_MenusController;
import ead1.repeat.Model.UserSession;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class AdminController {

    private final AdminUI view;
    private Admin_MenusPanel menusPanel;
    private Admin_ChatPanel chatPanel;    

    private final Color ACTIVE_PANEL_BG = new Color(0, 0, 0);       
    private final Color ACTIVE_LABEL_FG = new Color(215, 145, 130);   
    private final Color INACTIVE_PANEL_BG = new Color(51, 51, 51);      
    private final Color INACTIVE_LABEL_FG = new Color(255, 255, 255);  

    public AdminController(AdminUI view) {
        System.out.println("--- ADMIN CONTROLLER DEBUG: Initializing AdminController ---");
        this.view = view;

        // 💡 Initialize session if empty
        if (UserSession.getEmployeeId() == 0) {
            UserSession.setUsername("admin");
            UserSession.setRole("admin");
            UserSession.setEmployeeId(0); 
            System.out.println("--- ADMIN CONTROLLER DEBUG: UserSession manually set for 'admin'. ---");
        }

        preloadPanels();
        addMenuListeners();

        // 🔹 Show default panel (Chat)
        loadPanel("Chat");
        
        // 💬 Initialize chat panel
        if (this.chatPanel != null) {
            this.chatPanel.initializeChatPanel(); 
        }
        
        System.out.println("ADMIN CONTROLLER DEBUG: All listeners and panels loaded. Initial panel is Chat.");

        // 🔹 Logout listener
        view.getLblLogout().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLogout();
            }
        });
        System.out.println("--- ADMIN CONTROLLER DEBUG: AdminController Initialization Complete ---");
    }

    private void preloadPanels() {
        System.out.println("--- ADMIN CONTROLLER DEBUG: preloadPanels() started ---");
        JPanel contentPanel = view.getContentPanel();
        contentPanel.removeAll();
        System.out.println("ADMIN CONTROLLER DEBUG: ContentPanel cleared.");

        // 💬 Add Chat panel
        this.chatPanel = new Admin_ChatPanel();
        contentPanel.add(this.chatPanel, "Chat");
        
        contentPanel.add(new Admin_EmployeesPanel(), "Employees");

        this.menusPanel = new Admin_MenusPanel();
        System.out.println("ADMIN CONTROLLER DEBUG: Initializing MenusPanel MVC components...");
        Admin_MenusDAO menusDAO = new Admin_MenusDAO();
        new Admin_MenusController(this.menusPanel, menusDAO);
        System.out.println("ADMIN CONTROLLER DEBUG: MenusController initialized. loadMenus() was called.");
        
        contentPanel.add(this.menusPanel, "Menus");
        contentPanel.add(new Admin_SalesPanel(), "Sales");

        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("--- ADMIN CONTROLLER DEBUG: preloadPanels() finished ---");
    }

    private void addMenuListeners() {
        // CHAT
        view.getLblChat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { 
                loadPanel("Chat"); 
                if (chatPanel != null) {
                    chatPanel.initializeChatPanel();
                }
            }
        });
        view.getPnlChat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { 
                loadPanel("Chat"); 
                if (chatPanel != null) {
                    chatPanel.initializeChatPanel();
                }
            }
        });

        // EMPLOYEES
        view.getLblEmployees().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Employees"); }
        });
        view.getPnlEmployees().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Employees"); }
        });

        // MENUS
        view.getLblMenus().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Menus"); }
        });
        view.getPnlMenus().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Menus"); }
        });

        // Sales
        view.getLblSales().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Sales"); }
        });
        view.getPnlSales().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { loadPanel("Sales"); }
        });
    }

    private void loadPanel(String panelName) {
        System.out.println("--- ADMIN CONTROLLER DEBUG: loadPanel() called for: " + panelName + " ---");
        JPanel contentPanel = view.getContentPanel();
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, panelName);

        switch (panelName) {
            case "Chat":
                setActive(view.getPnlChat(), view.getLblChat());
                setInactive(view.getPnlEmployees(), view.getLblEmployees());
                setInactive(view.getPnlMenus(), view.getLblMenus());
                setInactive(view.getPnlSales(), view.getLblSales());
                break;

            case "Employees":
                setActive(view.getPnlEmployees(), view.getLblEmployees());
                setInactive(view.getPnlChat(), view.getLblChat());
                setInactive(view.getPnlMenus(), view.getLblMenus());
                setInactive(view.getPnlSales(), view.getLblSales());
                break;

            case "Menus":
                setActive(view.getPnlMenus(), view.getLblMenus());
                setInactive(view.getPnlChat(), view.getLblChat());
                setInactive(view.getPnlEmployees(), view.getLblEmployees());
                setInactive(view.getPnlSales(), view.getLblSales());
                break;

            case "Sales":
                setActive(view.getPnlSales(), view.getLblSales());
                setInactive(view.getPnlChat(), view.getLblChat());
                setInactive(view.getPnlEmployees(), view.getLblEmployees());
                setInactive(view.getPnlMenus(), view.getLblMenus());
                break;
        }

        // 💬 Update top bar label
        view.getLblPanelLoadedName().setText("Admin - " + panelName);

        view.revalidate();
        view.repaint();
        contentPanel.revalidate();
        contentPanel.repaint();
        System.out.println("--- ADMIN CONTROLLER DEBUG: loadPanel() finished for: " + panelName + " ---");
    }

    private void setActive(JPanel panel, javax.swing.JLabel label) {
        panel.setBackground(ACTIVE_PANEL_BG);
        label.setForeground(ACTIVE_LABEL_FG);
    }

    private void setInactive(JPanel panel, javax.swing.JLabel label) {
        panel.setBackground(INACTIVE_PANEL_BG);
        label.setForeground(INACTIVE_LABEL_FG);
    }

    private void handleLogout() {
        System.out.println("ADMIN CONTROLLER DEBUG: Handling Logout.");
        UserSession.clearSession();
        view.dispose();
        LoginUI login = new LoginUI();    
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        System.out.println("ADMIN CONTROLLER DEBUG: AdminUI disposed. Logout complete.");
    }
}
