// Admin_HomePanel.java (Updated)

package ead1.repeat.View;

import java.awt.BorderLayout;
// Import for the MessagePanel
import ead1.repeat.View.MessagePanel; 

/**
 *
 * @author ACER
 */
public class Admin_ChatPanel extends javax.swing.JPanel {

    /**
     * Creates new form HomePanel
     */
    public Admin_ChatPanel() {
        initComponents();
        // REMOVE: The MessagePanel creation is REMOVED from the constructor here.
        // It will be added later using the initializeChatPanel() method.
    }
    
    // --- NEW METHOD TO BE CALLED AFTER LOGIN ---
    /**
     * Initializes and loads the MessagePanel.
     * This must be called AFTER a successful login (UserSession is set).
     */
    public void initializeChatPanel() {
        // Only run this once to prevent multiple MessagePanels
        if (pnlMessage.getComponentCount() == 0) {
            System.out.println("--- ADMIN HOME PANEL DEBUG: Initializing MessagePanel and loading chat history. ---");
            pnlMessage.setLayout(new BorderLayout());
            pnlMessage.add(new MessagePanel(), BorderLayout.CENTER);
            pnlMessage.revalidate();
            pnlMessage.repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMessage = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1000, 750));
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        pnlMessage.setMaximumSize(new java.awt.Dimension(959, 618));
        pnlMessage.setMinimumSize(new java.awt.Dimension(959, 618));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlMessage;
    // End of variables declaration//GEN-END:variables
}
