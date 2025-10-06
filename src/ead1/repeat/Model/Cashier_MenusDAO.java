package ead1.repeat.Model;

import ead1.repeat.DatabaseConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Handles database operations for the cashier's menu viewing functionality.
 * The logic is the same as the Admin's read logic, focusing only on fetching menus.
 */
public class Cashier_MenusDAO {

    /** 🔹 Retrieves all menu items from the database. */
    public List<MenusModel> getAllMenus() {
        List<MenusModel> menuList = new ArrayList<>();
        // Note: Assuming 'menus' table and column names 'menu_id', 'name', 'price', 'image_path' exist.
        String sql = "SELECT menu_id, name, price, image_path FROM menus";

        System.out.println("--- DAO DEBUG: Cashier getAllMenus() started.");
        
        // Add a check to confirm the number of rows for debugging purposes
        int rowsFound = 0;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                rowsFound++; // Increment counter for each row found
                MenusModel menu = new MenusModel();
                menu.setId(rs.getInt("menu_id"));
                menu.setName(rs.getString("name"));
                menu.setPrice(rs.getDouble("price"));
                menu.setImagePath(rs.getString("image_path"));
                
                menuList.add(menu);
            }

            System.out.println("DAO DEBUG: Query successful. Rows found: " + rowsFound + ". Menus added to list: " + menuList.size());

        } catch (SQLException e) {
            System.err.println("!!! FATAL DAO ERROR: SQLException during Cashier menu data fetching!");
            System.err.println("!!! SQL Error Message: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching menus: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return Collections.emptyList();
        }
        return menuList;
    }
}
