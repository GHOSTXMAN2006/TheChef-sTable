package ead1.repeat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane; // Used for simple error notifications

public class DatabaseConfig {

    // 1. JDBC Driver and Connection details (Adjust these values)
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // NOTE: Replace 'restaurant_db' with the actual name of your MySQL database.
    private static final String DB_URL = "jdbc:mysql://localhost:3307/ead1-repeat-fix";
    
    // NOTE: Use your local MySQL username and password. 'root' and '' (empty) are common defaults for XAMPP/WAMP.
    private static final String USER = "root"; 
    private static final String PASS = ""; 

    /**
     * Establishes and returns a connection to the MySQL database.
     * @return Connection object if successful, otherwise null.
     */
    public static Connection getConnection() {
        System.out.println("--- DB DEBUG: getConnection() started ---");
        Connection conn = null;
        try {
            // 2. Register JDBC driver (Optional since JDBC 4.0, but good practice)
            System.out.println("DB DEBUG: Attempting to load JDBC Driver: " + JDBC_DRIVER);
            Class.forName(JDBC_DRIVER);
            System.out.println("DB DEBUG: JDBC Driver loaded successfully.");

            // 3. Open a connection
            System.out.println("DB DEBUG: Connecting to database at " + DB_URL + " with user " + USER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            if (conn != null) {
                System.out.println("DB DEBUG: Database connection established successfully!");
            }
            
        } catch (SQLException se) {
            // 4. Handle errors for JDBC (Database errors: wrong URL, port, credentials, DB not running)
            System.err.println("FATAL DB ERROR: SQLException during connection attempt!");
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Failed: " + se.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException cnfe) {
            // Handle error for Class.forName (Driver not found, usually missing JAR)
            System.err.println("FATAL DB ERROR: JDBC Driver class not found!");
            cnfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Loading JDBC Driver: " + cnfe.getMessage() + ". Check if MySQL Connector JAR is in the build path.", "Driver Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Catch any other unexpected runtime exceptions
            System.err.println("FATAL DB ERROR: Unexpected Exception during connection!");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unexpected Connection Error: " + e.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("--- DB DEBUG: getConnection() finished. Connection object is: " + (conn != null ? "VALID" : "NULL") + " ---");
        return conn;
    }

    /**
     * Simple method to close the connection to manage resources.
     * @param conn The Connection object to close.
     */
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                System.out.println("DB DEBUG: Closing connection.");
                conn.close();
                System.out.println("DB DEBUG: Connection closed.");
            }
        } catch (SQLException se) {
            System.err.println("DB ERROR: Error closing connection.");
            se.printStackTrace();
        }
    }
}