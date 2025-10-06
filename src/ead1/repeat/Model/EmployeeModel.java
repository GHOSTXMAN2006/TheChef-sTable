package ead1.repeat.Model;

public class EmployeeModel {
    private int empId;
    private String empName;
    private String empEmail;
    private String empRole;
    private String empPassword;

    // Constructor
    public EmployeeModel(String empName, String empEmail, String empRole, String empPassword) {
        this.empName = empName;
        this.empEmail = empEmail;
        this.empRole = empRole;
        this.empPassword = empPassword;
    }

    // Getters & Setters
    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }

    public String getEmpRole() { return empRole; }
    public void setEmpRole(String empRole) { this.empRole = empRole; }

    public String getEmpPassword() { return empPassword; }
    public void setEmpPassword(String empPassword) { this.empPassword = empPassword; }
}
