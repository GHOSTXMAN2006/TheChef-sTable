package ead1.repeat.Model;

import java.sql.Timestamp;

/**
 * Model class representing a single chat message.
 * It includes content, timestamp, and details about the sender (ID, username, role).
 */
public class MessageModel {
    private int messageId;
    private int senderId;
    private String senderUsername;
    private String senderRole;
    private String content;
    private Timestamp timestamp;

    public MessageModel(int messageId, int senderId, String senderUsername, String senderRole, String content, Timestamp timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.senderUsername = senderUsername;
        this.senderRole = senderRole;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Getters
    public int getMessageId() { return messageId; }
    public int getSenderId() { return senderId; }
    public String getSenderUsername() { return senderUsername; }
    public String getSenderRole() { return senderRole; }
    public String getContent() { return content; }
    public Timestamp getTimestamp() { return timestamp; }

    // Setters (useful for new messages before saving)
    public void setSenderUsername(String senderUsername) { this.senderUsername = senderUsername; }
    public void setSenderRole(String senderRole) { this.senderRole = senderRole; }
    public void setSenderId(int senderId) { this.senderId = senderId; }
    public void setContent(String content) { this.content = content; }
}
