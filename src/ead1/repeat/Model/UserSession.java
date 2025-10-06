package ead1.repeat.Model;

// Simple static class to hold global session data
public class UserSession {
    private static int employeeId = 0; // Default to 0 (invalid ID)
    private static String username = null; // 💡 NEW: To store the username
    private static String role = null; // 💡 NEW: To store the role

    // --- Employee ID Getters/Setters ---
    public static int getEmployeeId() {
        return employeeId;
    }

    public static void setEmployeeId(int id) {
        employeeId = id;
    }
    
    // --- Username Getters/Setters ---
    public static String getUsername() { // 💡 NEW
        return username;
    }

    public static void setUsername(String user) { // 💡 NEW
        username = user;
    }
    
    // --- Role Getters/Setters ---
    public static String getRole() { // 💡 NEW
        return role;
    }

    public static void setRole(String userRole) { // 💡 NEW
        role = userRole;
    }

    // Optional: Add clear session method
    public static void clearSession() {
        employeeId = 0;
        username = null; // 💡 UPDATED
        role = null; // 💡 UPDATED
    }
}