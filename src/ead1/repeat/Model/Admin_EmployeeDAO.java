package ead1.repeat.Model;

import ead1.repeat.DatabaseConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Admin_EmployeeDAO {

    // ✅ NEW METHOD: Get employee by ID for pre-update comparison
    public EmployeeModel getEmployeeById(int id) {
        String sql = "SELECT * FROM employee WHERE emp_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    EmployeeModel emp = new EmployeeModel(
                        rs.getString("emp_name"),
                        rs.getString("emp_email"),
                        rs.getString("emp_role"),
                        rs.getString("emp_password")
                    );
                    emp.setEmpId(rs.getInt("emp_id"));
                    return emp;
                }
                return null; // Return null if ID not found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching employee by ID " + id + ": " + e.getMessage(), e);
        }
    }

    // ✅ Get next available ID from DB
    public int getNextEmployeeID() {
        int nextId = 1;
        String sql = "SELECT MAX(emp_id) AS maxId FROM employee";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                nextId = rs.getInt("maxId") + 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching next employee ID: " + e.getMessage(), e);
        }
        return nextId;
    }

    // ✅ Get all employees (Exception handling updated)
    public List<EmployeeModel> getAllEmployees() {
        List<EmployeeModel> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EmployeeModel emp = new EmployeeModel(
                        rs.getString("emp_name"),
                        rs.getString("emp_email"),
                        rs.getString("emp_role"),
                        rs.getString("emp_password")
                );
                emp.setEmpId(rs.getInt("emp_id"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all employees: " + e.getMessage(), e);
        }
        return employees;
    }

    // ✅ Add employee (Exception handling updated)
    public void addEmployee(EmployeeModel emp) {
        String sql = "INSERT INTO employee(emp_name, emp_email, emp_role, emp_password) VALUES(?,?,?,?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, emp.getEmpName());
            pst.setString(2, emp.getEmpEmail());
            pst.setString(3, emp.getEmpRole());
            pst.setString(4, emp.getEmpPassword());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding new employee: " + e.getMessage(), e);
        }
    }

    // 🔄 MODIFIED: updateEmployee now returns the number of rows updated (int)
    public int updateEmployee(EmployeeModel emp) {
        String sql = "UPDATE employee SET emp_name=?, emp_email=?, emp_role=?, emp_password=? WHERE emp_id=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, emp.getEmpName());
            pst.setString(2, emp.getEmpEmail());
            pst.setString(3, emp.getEmpRole());
            pst.setString(4, emp.getEmpPassword());
            pst.setInt(5, emp.getEmpId());
            
            // Return the count of updated rows (0 if no change, 1 if updated)
            return pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating employee ID " + emp.getEmpId() + ": " + e.getMessage(), e);
        }
    }

    // ✅ Delete employee (Exception handling updated)
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE emp_id=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee ID " + id + ": " + e.getMessage(), e);
        }
    }

    // ✅ Search employees (Exception handling updated)
    public List<EmployeeModel> searchEmployees(String keyword) {
        List<EmployeeModel> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee WHERE emp_name LIKE ? OR emp_email LIKE ? OR emp_role LIKE ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            pst.setString(1, key);
            pst.setString(2, key);
            pst.setString(3, key);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    EmployeeModel emp = new EmployeeModel(
                            rs.getString("emp_name"),
                            rs.getString("emp_email"),
                            rs.getString("emp_role"),
                            rs.getString("emp_password")
                    );
                    emp.setEmpId(rs.getInt("emp_id"));
                    employees.add(emp);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching employees: " + e.getMessage(), e);
        }
        return employees;
    }
}