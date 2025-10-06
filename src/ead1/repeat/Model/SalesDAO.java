package ead1.repeat.Model;

import ead1.repeat.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {

    /**
     * Fetches aggregated sales data grouped by month and menu item.
     * @return A List of SalesModel objects.
     */
    public List<SalesModel> getMonthlyMenuSales() {
        
        System.out.println("DEBUG: Starting getMonthlyMenuSales() data fetch."); // DEBUG TRACE
        List<SalesModel> salesList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        // SQL query to join orders, order_items, and menus, then group and sum the sales.
        String sql = "SELECT " +
                     "    DATE_FORMAT(o.order_date, '%Y-%m') AS month_year, " +
                     "    m.name AS menu_item_name, " +
                     "    SUM(oi.subtotal_price) AS total_sales " +
                     "FROM orders o " +
                     "JOIN order_items oi ON o.order_id = oi.order_id " +
                     "JOIN menus m ON oi.menu_id = m.menu_id " +
                     // NOTE: If you need to filter by status, add WHERE o.status = 'complete' or similar here.
                     "GROUP BY 1, 2 " +
                     "ORDER BY 1 ASC, 3 DESC";

        try {
            // 1. Get Connection
            conn = DatabaseConfig.getConnection();
            if (conn == null) {
                System.err.println("DB ERROR: Connection is null. Check DatabaseConfig settings."); // DEBUG ERROR
                return salesList;
            }
            System.out.println("DEBUG: Connection established successfully."); // DEBUG TRACE
            
            // 2. Prepare Statement and Execute Query
            System.out.println("DEBUG: Executing SQL query:\n" + sql); // DEBUG TRACE: Print the query!
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // 3. Process Results
            int rowCount = 0; // DEBUG counter
            while (rs.next()) {
                String monthYear = rs.getString("month_year");
                String menuItemName = rs.getString("menu_item_name");
                double totalSales = rs.getDouble("total_sales");
                
                // DEBUG: Print the first few records to confirm data retrieval
                if (rowCount < 5) {
                     System.out.println("DEBUG: Row " + (rowCount + 1) + ": " + monthYear + " | " + menuItemName + " | Rs." + totalSales);
                } else if (rowCount == 5) {
                    System.out.println("DEBUG: ... (More rows being processed) ...");
                }
                
                // Populate the SalesModel object
                salesList.add(new SalesModel(monthYear, menuItemName, totalSales));
                rowCount++;
            }
            System.out.println("DEBUG: Data retrieval complete. Total records fetched: " + rowCount); // DEBUG TRACE

        } catch (SQLException se) {
            System.err.println("FATAL SQL ERROR in getMonthlyMenuSales(): " + se.getMessage()); // DEBUG ERROR
            se.printStackTrace();
        } finally {
            // 4. Close Resources
            try { 
                if (rs != null) rs.close(); 
                System.out.println("DEBUG: ResultSet closed."); // DEBUG TRACE
            } catch (SQLException e) { 
                System.err.println("ERROR: Could not close ResultSet: " + e.getMessage());
            }
            try { 
                if (stmt != null) stmt.close(); 
                System.out.println("DEBUG: PreparedStatement closed."); // DEBUG TRACE
            } catch (SQLException e) { 
                System.err.println("ERROR: Could not close PreparedStatement: " + e.getMessage());
            }
            
            DatabaseConfig.closeConnection(conn);
            System.out.println("DEBUG: Connection closed via DatabaseConfig."); // DEBUG TRACE
        }

        System.out.println("DEBUG: Exiting getMonthlyMenuSales(). Total list size: " + salesList.size());
        return salesList;
    }
}