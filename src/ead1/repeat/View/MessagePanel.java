package ead1.repeat.View;

import ead1.repeat.Controller.MessageController;
import ead1.repeat.Model.MessageModel;
import ead1.repeat.Model.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * UI Panel for displaying chat messages (WhatsApp-like interface).
 * Features a fixed input bar and a scrollable message area with dynamic styling.
 */
public class MessagePanel extends JPanel {

    private final MessageController controller;
    private final JPanel pnlMessageHistory;
    private final JTextField txtMessageInput;
    private final JButton btnSend;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");

    // Custom Colors
    // Logged-in user's message (WhatsApp Green)
    private static final Color COLOR_SELF = new Color(37, 211, 102); 
    // Other users' messages (Gray)
    private static final Color COLOR_OTHER = new Color(150, 150, 150); 
    // Admin's messages background (Red)
    private static final Color COLOR_ADMIN = new Color(255, 51, 51); 

    public MessagePanel() {
        // 1. Setup Controller and Main Panel Layout
        this.controller = new MessageController();
        this.setLayout(new BorderLayout(5, 5));
        this.setBackground(new Color(240, 240, 240)); // Light background for the overall panel

        // 2. Setup Message History Panel (Top/Center)
        pnlMessageHistory = new JPanel();
        // Use BoxLayout for vertical stacking of messages
        pnlMessageHistory.setLayout(new BoxLayout(pnlMessageHistory, BoxLayout.Y_AXIS)); 
        pnlMessageHistory.setBackground(new Color(230, 230, 230));

        // Wrap the history panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(pnlMessageHistory);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // 3. Setup Input Panel (Bottom)
        JPanel pnlInput = new JPanel(new BorderLayout(5, 5));
        pnlInput.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Padding
        pnlInput.setBackground(Color.WHITE);

        txtMessageInput = new JTextField(40);
        txtMessageInput.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        btnSend = new JButton("Send");
        btnSend.setBackground(new Color(0, 150, 255)); // Blue color for send button
        btnSend.setForeground(Color.WHITE);
        btnSend.setFocusPainted(false);
        btnSend.setFont(new Font("Segoe UI", Font.BOLD, 14));

        pnlInput.add(txtMessageInput, BorderLayout.CENTER);
        pnlInput.add(btnSend, BorderLayout.EAST);

        // 4. Add Components to Main Panel
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pnlInput, BorderLayout.SOUTH);

        // 5. Add Event Listener and Load Messages
        btnSend.addActionListener(this::handleSendMessage);
        txtMessageInput.addActionListener(this::handleSendMessage); // Send on Enter key

        loadAndDisplayMessages();
    }

    /**
     * Handles the action of sending a new message.
     */
    private void handleSendMessage(ActionEvent e) {
        String content = txtMessageInput.getText();
        if (content.trim().isEmpty()) {
            return; // Do nothing if message is empty
        }

        boolean success = controller.sendMessage(content);

        if (success) {
            // Optional: Reload all messages to see the new one (in a real app, 
            // you'd typically only append the new message for performance)
            loadAndDisplayMessages();
            txtMessageInput.setText("");
        } else {
            // Show custom error panel instead of alert()
            JOptionPane.showMessageDialog(this, 
                "Failed to send message or user session error.", 
                "Send Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Fetches all messages from the database and renders them in the history panel.
     */
    private void loadAndDisplayMessages() {
        pnlMessageHistory.removeAll(); // Clear previous messages
        List<MessageModel> messages = controller.loadAllMessages();

        for (MessageModel message : messages) {
            pnlMessageHistory.add(createMessageBubble(message));
        }

        // Add a vertical glue to push messages to the top
        pnlMessageHistory.add(Box.createVerticalGlue()); 

        // Repaint and scroll to the bottom of the chat
        revalidate();
        repaint();
        scrollToBottom();
    }

    /**
     * Scrolls the chat view to the bottom (latest message).
     */
    private void scrollToBottom() {
        JScrollBar verticalBar = ((JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, pnlMessageHistory)).getVerticalScrollBar();
        verticalBar.setValue(verticalBar.getMaximum());
    }

    /**
     * Creates a single message bubble JPanel with role-based styling and alignment.
     * @param message The message data model.
     * @return A JPanel representing the formatted message bubble.
     */
    private JPanel createMessageBubble(MessageModel message) {
        int loggedInUserId = UserSession.getEmployeeId();
        
        // 🛑 FIX: Check if the message is from the logged-in user (ID 0) 
        // OR if the logged-in user is Admin (ID 0) AND the message was saved with the old Admin ID (1).
        boolean isSelf = message.getSenderId() == loggedInUserId || 
                         (loggedInUserId == 0 && message.getSenderId() == 1); 

        // --- 1. Determine Alignment and Color ---

        // Alignment: LEFT for others, RIGHT for self
        int align = isSelf ? FlowLayout.RIGHT : FlowLayout.LEFT;
        
        // Background color for the bubble itself
        Color bubbleColor;

        // Determine the correct bubble color based on role
        String senderRole = isSelf ? UserSession.getRole() : message.getSenderRole();
        if ("admin".equalsIgnoreCase(senderRole)) {
            bubbleColor = COLOR_ADMIN; // Red for Admin
        } else if (isSelf) {
            bubbleColor = COLOR_SELF; // Green for self (non-admin employee)
        } else {
            bubbleColor = COLOR_OTHER; // Gray for other employees
        }

        // --- 2. Message Content Panel (The Bubble) ---

        // Using a Panel with a BoxLayout for vertical stacking of the 3 required components:
        // [Username/Role]
        // [Message Content]
        // [Date/Time]
        JPanel pnlBubble = new JPanel();
        pnlBubble.setLayout(new BoxLayout(pnlBubble, BoxLayout.Y_AXIS));
        pnlBubble.setBackground(bubbleColor);
        pnlBubble.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        pnlBubble.setMaximumSize(new Dimension(350, Integer.MAX_VALUE)); // Limit bubble width

        // Labels for components

        // Panel 1: Username and Role (Top)
        String userInfoText;
        
        // 💡 FIX: Use UserSession data if it's the current user (to override DB name, i.e. "Sandeep Kumar (Chef)")
        if (isSelf) {
            userInfoText = UserSession.getUsername() + " (" + UserSession.getRole() + ")";
        } else {
            // Use the data retrieved from the database for messages from others
            userInfoText = message.getSenderUsername() + " (" + message.getSenderRole() + ")";
        }
        
        JLabel lblUserInfo = new JLabel(userInfoText);
        lblUserInfo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUserInfo.setForeground(Color.WHITE);
        lblUserInfo.setAlignmentX(isSelf ? Component.RIGHT_ALIGNMENT : Component.LEFT_ALIGNMENT);
        pnlBubble.add(lblUserInfo);

        // Panel 2: Message Content (Middle)
        JLabel lblContent = new JLabel("<html><p style='width: 200px;'>" + message.getContent() + "</p></html>");
        lblContent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblContent.setForeground(Color.BLACK);
        lblContent.setAlignmentX(isSelf ? Component.RIGHT_ALIGNMENT : Component.LEFT_ALIGNMENT);
        pnlBubble.add(lblContent);

        // Panel 3: Date and Time (Bottom)
        JLabel lblTimestamp = new JLabel(dateFormat.format(message.getTimestamp()));
        lblTimestamp.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        lblTimestamp.setForeground(new Color(255, 255, 255, 180)); // Semi-transparent white
        lblTimestamp.setAlignmentX(isSelf ? Component.RIGHT_ALIGNMENT : Component.LEFT_ALIGNMENT);
        pnlBubble.add(lblTimestamp);

        // --- 3. Outer Alignment Panel ---

        // This outer panel uses FlowLayout to push the bubble to the left or right edge.
        JPanel pnlAlignment = new JPanel(new FlowLayout(align, 10, 5)); // 10px horizontal, 5px vertical spacing
        pnlAlignment.setBackground(new Color(230, 230, 230)); // Match history panel background
        pnlAlignment.add(pnlBubble);
        pnlAlignment.setMaximumSize(new Dimension(Integer.MAX_VALUE, pnlBubble.getPreferredSize().height + 10));

        return pnlAlignment;
    }
}