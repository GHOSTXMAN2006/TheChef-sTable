package ead1.repeat.Model;

import ead1.repeat.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Collections; // For handling empty lists in case of fatal error

public class Admin_MenusDAO {

    // 🔹 Get all menus
    public List<MenusModel> getAllMenus() {
        List<MenusModel> menuList = new ArrayList<>();
        // Explicitly list columns to debug potential column name issues
        String sql = "SELECT menu_id, name, price, image_path FROM menus"; 

        System.out.println("--- DAO DEBUG: getAllMenus() started. SQL: " + sql);

        // Using try-with-resources to ensure connection, statement, and resultset are closed
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql); // Using PreparedStatement for robustness
             ResultSet rs = ps.executeQuery()) {

            System.out.println("DAO DEBUG: SQL Query prepared and executed successfully. Checking results...");
            int rowCount = 0;
            
            while (rs.next()) {
                System.out.println("DAO DEBUG: Processing row " + (rowCount + 1) + "...");
                MenusModel menu = new MenusModel();
                
                // --- DETAILED COLUMN RETRIEVAL DEBUGGING ---
                int menuId = rs.getInt("menu_id");
                menu.setId(menuId);
                System.out.println("DAO DEBUG: Retrieved ID: " + menuId);
                
                String name = rs.getString("name");
                menu.setName(name);
                System.out.println("DAO DEBUG: Retrieved Name: " + name);
                
                double price = rs.getDouble("price");
                menu.setPrice(price);
                System.out.println("DAO DEBUG: Retrieved Price: " + price);
                
                String imgPath = rs.getString("image_path");
                menu.setImagePath(imgPath);
                System.out.println("DAO DEBUG: Retrieved ImagePath: " + (imgPath != null ? imgPath : "null"));
                // ------------------------------------------

                menuList.add(menu);
                rowCount++;
            }
            
            System.out.println("DAO DEBUG: Finished retrieving " + rowCount + " rows.");

        } catch (SQLException e) {
            // This is the most likely place of failure (Wrong table/column names/types)
            System.err.println("!!! FATAL DAO ERROR: SQLException during data fetching!");
            System.err.println("!!! SQL Error Message: " + e.getMessage());
            System.err.println("!!! Check that table 'menus' exists and columns 'menu_id' (INT), 'name' (VARCHAR), 'price' (DOUBLE/DECIMAL), and 'image_path' (VARCHAR) are named correctly.");
            e.printStackTrace(); // PRINT THIS FULL STACK TRACE TO CONSOLE
            JOptionPane.showMessageDialog(null, "Error fetching menus: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return Collections.emptyList();
        } catch (Exception ex) {
            // Catch any other unexpected runtime exceptions (e.g., in MenusModel constructor/setter)
            System.err.println("!!! FATAL DAO ERROR: Unexpected Runtime Exception during getAllMenus()!");
            ex.printStackTrace(); // PRINT THIS FULL STACK TRACE TO CONSOLE
            JOptionPane.showMessageDialog(null, "Unexpected Error fetching menus: " + ex.getMessage(), "Application Error", JOptionPane.ERROR_MESSAGE);
            return Collections.emptyList();
        }

        System.out.println("DAO DEBUG: getAllMenus() returning list with size: " + menuList.size());
        System.out.println("--- DAO DEBUG: getAllMenus() finished ---");
        return menuList;
    }

    // 🔹 Add a menu
    public boolean addMenu(MenusModel menu) {
        System.out.println("--- DAO DEBUG: addMenu() started for: " + menu.getName());
        String sql = "INSERT INTO menus (name, price, image_path) VALUES (?, ?, ?)";
        boolean success = false;
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, menu.getName());
            ps.setDouble(2, menu.getPrice());
            ps.setString(3, menu.getImagePath());
            System.out.println("DAO DEBUG: Executing INSERT: Name=" + menu.getName() + ", Price=" + menu.getPrice());

            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("DAO DEBUG: Menu added successfully. Rows affected: " + rowsAffected);
                success = true;
            } else {
                System.err.println("DAO ERROR: INSERT executed but no rows affected.");
            }

        } catch (SQLException e) {
            System.err.println("DAO ERROR: Error adding menu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding menu: " + e.getMessage());
        } finally {
             System.out.println("--- DAO DEBUG: addMenu() finished. Success: " + success + " ---");
        }
        return success;
    }

    // 🔹 Update a menu
    public boolean updateMenu(MenusModel menu) {
        System.out.println("--- DAO DEBUG: updateMenu() started for ID: " + menu.getId());
        String sql = "UPDATE menus SET name = ?, price = ?, image_path = ? WHERE menu_id = ?";
        boolean success = false;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, menu.getName());
            ps.setDouble(2, menu.getPrice());
            ps.setString(3, menu.getImagePath());
            ps.setInt(4, menu.getId());
            System.out.println("DAO DEBUG: Executing UPDATE for ID=" + menu.getId());
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("DAO DEBUG: Menu updated successfully. Rows affected: " + rowsAffected);
                success = true;
            } else {
                System.err.println("DAO WARNING: UPDATE executed but no rows affected (ID may not exist).");
            }

        } catch (SQLException e) {
            System.err.println("DAO ERROR: Error updating menu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating menu: " + e.getMessage());
        } finally {
             System.out.println("--- DAO DEBUG: updateMenu() finished. Success: " + success + " ---");
        }
        return success;
    }

    // 🔹 Delete a menu
    public boolean deleteMenu(int menuId) {
        System.out.println("--- DAO DEBUG: deleteMenu() started for ID: " + menuId);
        String sql = "DELETE FROM menus WHERE menu_id = ?";
        boolean success = false;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, menuId);
            System.out.println("DAO DEBUG: Executing DELETE for ID=" + menuId);
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                 System.out.println("DAO DEBUG: Menu deleted successfully. Rows affected: " + rowsAffected);
                 success = true;
            } else {
                 System.err.println("DAO WARNING: DELETE executed but no rows affected (ID may not exist).");
            }


        } catch (SQLException e) {
            System.err.println("DAO ERROR: Error deleting menu: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting menu: " + e.getMessage());
        } finally {
            System.out.println("--- DAO DEBUG: deleteMenu() finished. Success: " + success + " ---");
        }
        return success;
    }
}