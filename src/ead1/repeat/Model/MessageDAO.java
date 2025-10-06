package ead1.repeat.Model;

import ead1.repeat.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    /**
     * Retrieves all messages, sorted by timestamp, along with sender details.
     * Uses LEFT JOIN to ensure all messages are included, even for sender_id=0 (Admin),
     * and manually injects Admin details if the join fails.
     * @return A list of MessageModel objects.
     */
    public List<MessageModel> loadMessages() {
        List<MessageModel> messages = new ArrayList<>();
        
        // Use LEFT JOIN to include ALL messages from the 'message' table, 
        // even if sender_id does not exist in the 'employee' table (which is the case for Admin/ID 0).
        String sql = "SELECT m.message_id, m.sender_id, m.content, m.timestamp, "
                   + "e.emp_name, e.emp_role "
                   + "FROM message m LEFT JOIN employee e ON m.sender_id = e.emp_id " 
                   + "ORDER BY m.timestamp ASC";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int senderId = rs.getInt("sender_id");
                
                String empName = rs.getString("emp_name");
                String empRole = rs.getString("emp_role");
                
                // 💡 FIX 1: If sender is the hardcoded Admin ID (0) 
                // AND the database employee name is NULL (because Admin is not in 'employee' table)
                if (senderId == 0 && (empName == null || empName.isEmpty())) {
                    empName = "admin"; // Manually set the name
                    empRole = "admin"; // Manually set the role
                }
                
                // Handle cases where a valid employee ID might have a missing name/role (shouldn't happen, but safe check)
                if (empName == null) {
                    empName = "Unknown User";
                }
                if (empRole == null) {
                    empRole = "Unknown Role";
                }
                
                MessageModel message = new MessageModel(
                    rs.getInt("message_id"),
                    senderId,
                    empName,
                    empRole, // This will now be "admin" for Admin messages
                    rs.getString("content"),
                    rs.getTimestamp("timestamp")
                );
                messages.add(message);
            }

        } catch (SQLException e) {
            System.err.println("Error loading messages: " + e.getMessage());
            e.printStackTrace();
        }
        return messages;
    }

    /**
     * Saves a new message to the database.
     * @param message The MessageModel containing sender ID and content.
     * @return true if save was successful, false otherwise.
     */
    public boolean saveMessage(MessageModel message) {
        String sql = "INSERT INTO message (sender_id, content) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, message.getSenderId());
            pst.setString(2, message.getContent());
            
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error saving message: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}