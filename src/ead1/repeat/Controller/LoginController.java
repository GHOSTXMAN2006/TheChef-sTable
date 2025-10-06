package ead1.repeat.Controller;

import ead1.repeat.DatabaseConfig;
import ead1.repeat.Model.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    public String login(String username, String password) {
        // First, check for hardcoded admin credentials
        if (username.equals("admin") && password.equals("admin123")) {
            // Requirement: for admin, save just their role and username

            // 💡 FIX: Save username and role for admin
            UserSession.setUsername(username);
            UserSession.setRole("admin");

            // 💡 FIX: Set a placeholder ID (e.g., ID 0) for admin as requested. 
            // This is crucial for the MessageController to validate the user session 
            // and allow sending messages (since '0' is now the designated ID for admin).
            UserSession.setEmployeeId(0); 

            return "admin";
        }

        // SQL query to check login details and retrieve the ID and ROLE
        String sql = "SELECT emp_id, emp_role FROM employee WHERE emp_name=? AND emp_password=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Login successful for database employee (cashier/chef)
                int employeeId = rs.getInt("emp_id");
                String role = rs.getString("emp_role");

                // Requirement: for cashier/chef, employee name (username), role, employee id should be saved

                // Save ALL required details to the session for non-admin users
                UserSession.setUsername(username);
                UserSession.setRole(role);
                UserSession.setEmployeeId(employeeId);

                return role;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Login failed
        return null;
    }
}