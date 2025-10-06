package ead1.repeat;

import ead1.repeat.View.LoginUI;

public class Main {
    public static void main(String[] args) {
        // Run on Event Dispatch Thread (Swing best practice)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginUI login = new LoginUI();
                login.setLocationRelativeTo(null); // 🎯 Center on screen
                login.setVisible(true);            // ✅ Show window
            }
        });
    }
}
