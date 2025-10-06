package ead1.repeat.Controller;

import ead1.repeat.Model.MessageDAO;
import ead1.repeat.Model.MessageModel;
import ead1.repeat.Model.UserSession;
import java.util.List;

/**
 * Controller class for message handling.
 * Manages fetching messages and processing new messages for sending.
 */
public class MessageController {
    
    private final MessageDAO messageDAO;

    public MessageController() {
        this.messageDAO = new MessageDAO();
    }

    /**
     * Loads all historical messages from the database.
     * @return A list of MessageModel objects.
     */
    public List<MessageModel> loadAllMessages() {
        return messageDAO.loadMessages();
    }

    /**
     * Creates a new MessageModel, saves it to the database, and returns the result.
     * @param content The text content of the message.
     * @return true if the message was successfully saved, false otherwise.
     */
    public boolean sendMessage(String content) {
        // Retrieve logged-in user details from UserSession
        int senderId = UserSession.getEmployeeId();
        String senderUsername = UserSession.getUsername();
        String senderRole = UserSession.getRole();
        
        // 💡 FIX: Check if username is null or content is empty. 
        // senderId=0 is now valid for Admin, so we only need to check if the session is properly initialized.
        if (senderUsername == null || content.trim().isEmpty()) {
            System.err.println("Cannot send message: User not logged in or content is empty.");
            return false;
        }
        
        // Create a new MessageModel instance
        MessageModel newMessage = new MessageModel(
            0, // messageId is auto-generated
            senderId, // This will correctly save 0 for Admin
            senderUsername,
            senderRole,
            content.trim(),
            null // Timestamp is set by the database
        );
        
        // Save to database
        return messageDAO.saveMessage(newMessage);
    }
}