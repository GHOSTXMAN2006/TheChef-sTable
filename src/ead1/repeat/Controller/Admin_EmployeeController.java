package ead1.repeat.Controller;

import ead1.repeat.Model.EmployeeModel;
import ead1.repeat.Model.Admin_EmployeeDAO;
import java.util.List;

public class Admin_EmployeeController {
    private Admin_EmployeeDAO dao;

    public Admin_EmployeeController() {
        dao = new Admin_EmployeeDAO();
    }
    
    // ✅ NEW METHOD: Get employee by ID
    public EmployeeModel getEmployeeById(int id) {
        return dao.getEmployeeById(id);
    }

    // ✅ Get next employee ID
    public int getNextEmployeeID() {
        return dao.getNextEmployeeID();
    }

    // ✅ Get all employees
    public List<EmployeeModel> getEmployees() {
        return dao.getAllEmployees();
    }

    // ✅ Add new employee
    public void addEmployee(EmployeeModel emp) {
        dao.addEmployee(emp);
    }

    // 🔄 MODIFIED: updateEmployee now returns the number of rows updated (int)
    public int updateEmployee(EmployeeModel emp) {
        return dao.updateEmployee(emp);
    }

    // ✅ Delete employee by ID
    public void deleteEmployee(int id) {
        dao.deleteEmployee(id);
    }

    // ✅ Search employees by keyword
    public List<EmployeeModel> searchEmployees(String keyword) {
        return dao.searchEmployees(keyword);
    }
}