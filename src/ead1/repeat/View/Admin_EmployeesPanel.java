package ead1.repeat.View;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;

import ead1.repeat.Controller.Admin_EmployeeController;
import ead1.repeat.Model.EmployeeModel;
import java.util.List;

public class Admin_EmployeesPanel extends javax.swing.JPanel {
    
    private Admin_EmployeeController controller;
    private String currentMode = "View";
    
    public Admin_EmployeesPanel() {
        // 🚨 CRITICAL FIX: Initialize controller here
        controller = new Admin_EmployeeController(); 
        
        initComponents();
        loadEmployeeData(); 
        applyTableStyles(); 
        addSearchFunctionality(); 
        addActionListeners(); 
    }

    
    // --- Enable or disable all fields except ID ---
    private void setFieldsEnabled(boolean enabled) {
        txtEmployeeName.setEnabled(enabled);
        txtGmail.setEnabled(enabled); // Gmail
        cmbEmployeeRole.setEnabled(enabled);
        txtEmployeePassword.setEnabled(enabled);
    }

    // --- Clear all text fields ---
    private void clearFields() {
        txtEmployeeID.setText("");
        txtEmployeeName.setText("");
        txtGmail.setText("");
        cmbEmployeeRole.setSelectedIndex(0);
        txtEmployeePassword.setText("");
    }

    // --- Load selected row data into fields ---
    private void loadSelectedEmployee() {
        int selectedRow = tblEmployees.getSelectedRow();
        if (selectedRow == -1) return;

        txtEmployeeID.setText(tblEmployees.getValueAt(selectedRow, 0).toString());
        txtEmployeeName.setText(tblEmployees.getValueAt(selectedRow, 1).toString());
        txtGmail.setText(tblEmployees.getValueAt(selectedRow, 2).toString());
        cmbEmployeeRole.setSelectedItem(tblEmployees.getValueAt(selectedRow, 3).toString());
        txtEmployeePassword.setText(tblEmployees.getValueAt(selectedRow, 4).toString());
    }
    
    // New loadEmployeeData() method
    private void loadEmployeeData() {
        try {
            // --- START MVC CHANGE ---
            List<EmployeeModel> employees = controller.getEmployees(); 

            // Setup table model with Password column
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Email", "Role", "Password"}, 0
            );

            for (EmployeeModel emp : employees) { 
                int id = emp.getEmpId();
                String name = emp.getEmpName();
                String email = emp.getEmpEmail();
                String role = emp.getEmpRole();
                String password = emp.getEmpPassword();

                model.addRow(new Object[]{id, name, email, role, password});
            }
            // --- END MVC CHANGE ---

            tblEmployees.setModel(model);
            applyTableStyles();

        } catch (Exception e) { // Catches RuntimeException from Controller/DAO
            JOptionPane.showMessageDialog(this, "Failed to load employees: " + e.getMessage());
            e.printStackTrace();
        } 
    }
    
    // 🌟 New method to apply visual styles to the table
    private void applyTableStyles() {
        // 1. Set Row Height to make rows larger (e.g., 30 pixels)
        tblEmployees.setRowHeight(30);

        // 2. Improve row readability
        tblEmployees.setSelectionBackground(new Color(153, 204, 255)); // Lighter blue selection
        tblEmployees.setSelectionForeground(Color.BLACK);
        tblEmployees.setGridColor(new Color(230, 230, 230)); // Lighter grid lines
        tblEmployees.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Slightly larger row font

        // 3. Style the Table Header
        JTableHeader header = tblEmployees.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 15)); // Bold and larger font for header
        header.setBackground(new Color(215,145,130)); // Header background color (Rust/Terracotta)
        header.setForeground(Color.WHITE); // Header text color
        header.setReorderingAllowed(false); // Prevent column reordering
        header.setResizingAllowed(true); // Allow column resizing

        // 4. Adjust Column Widths for ID and Email visibility
        // Column Index mapping: 0=ID, 1=Name, 2=Email, 3=Role, 4=Password
        if (tblEmployees.getColumnModel().getColumnCount() > 0) {
            // ID (Index 0): Reduced width for small numbers
            tblEmployees.getColumnModel().getColumn(0).setPreferredWidth(50);

            // Name (Index 1): Standard width
            tblEmployees.getColumnModel().getColumn(1).setPreferredWidth(150);

            // Email (Index 2): Increased width to accommodate full email address
            tblEmployees.getColumnModel().getColumn(2).setPreferredWidth(270);

            // Role (Index 3): Standard width
            tblEmployees.getColumnModel().getColumn(3).setPreferredWidth(90);

            // Password (Index 4): Standard width
            tblEmployees.getColumnModel().getColumn(4).setPreferredWidth(130);
        }
    }
    
    // 🌟 New method for search functionality
    private void addSearchFunctionality() {
        // 🔹 Click on search icon
        lblSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchEmployee(txtSearch.getText().trim());
            }
        });

        // 🔹 Press Enter in search field
        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployee(txtSearch.getText().trim());
            }
        });
    }

    // 🌟 Search employees by name/email/role
    // New searchEmployee() method
    private void searchEmployee(String keyword) {
        if (keyword.isEmpty()) {
            loadEmployeeData();
            return;
        }
        
        try {
            // --- START MVC CHANGE ---
            List<EmployeeModel> employees = controller.searchEmployees(keyword); 

            DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Name", "Email", "Role", "Password"}, 0
            );

            boolean hasData = false;
            for (EmployeeModel emp : employees) { 
                hasData = true;
                model.addRow(new Object[]{
                    emp.getEmpId(),
                    emp.getEmpName(),
                    emp.getEmpEmail(),
                    emp.getEmpRole(),
                    emp.getEmpPassword()
                });
            }
            // --- END MVC CHANGE ---

            if (hasData) {
                tblEmployees.setModel(model);
                applyTableStyles();
            } else {
                JOptionPane.showMessageDialog(this, "No search result for: " + keyword, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) { // Catches RuntimeException from Controller/DAO
            JOptionPane.showMessageDialog(this, "Search failed: " + e.getMessage());
        }
    }    
    
    // --- Add Click Events ---
    private void addActionListeners() {
        // Add
        MouseAdapter addAction = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pnlAddEditContent.setBackground(new Color(215,145,130));
                clearFields();

                // ✅ FIX: Call Controller method to get the next ID
                try {
                    txtEmployeeID.setText(String.valueOf(controller.getNextEmployeeID())); 
                } catch (Exception ex) {
                    // Handle potential DB connection error when getting ID
                    JOptionPane.showMessageDialog(null, "Error setting new Employee ID: " + ex.getMessage());
                    txtEmployeeID.setText("Error");
                }

                setFieldsEnabled(true);
                txtEmployeeID.setEnabled(false); // ID is read-only

                lblAdd.setForeground(new Color(215,145,130)); // Active
                lblEdit.setForeground(Color.WHITE); // Inactive

                // 🎯 FIX 1: Set currentMode to "Add"
                currentMode = "Add"; 

                pnlSave.setEnabled(true);
                lblSave.setEnabled(true);
            }
        };
        lblAdd.addMouseListener(addAction);
        pnlAdd.addMouseListener(addAction); 

        // Edit
        MouseAdapter editAction = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblEmployees.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to edit!");
                    return;
                }
                pnlAddEditContent.setBackground(new Color(215,145,130));
                loadSelectedEmployee();
                setFieldsEnabled(true);
                txtEmployeeID.setEnabled(false); // ID is read-only when editing

                lblEdit.setForeground(new Color(215,145,130)); // Active
                lblAdd.setForeground(Color.WHITE);

                // 🎯 FIX 2: Set currentMode to "Edit"
                currentMode = "Edit"; 

                pnlSave.setEnabled(true);
                lblSave.setEnabled(true);
            }
        };
        lblEdit.addMouseListener(editAction);
        pnlEdit.addMouseListener(editAction); 

        // Delete
        MouseAdapter deleteAction = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tblEmployees.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete!");
                    return;
                }
                int id = Integer.parseInt(tblEmployees.getValueAt(selectedRow, 0).toString());
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete Employee ID: " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteEmployee(id); 
                    loadEmployeeData();
                }
            }
        };
        lblDelete.addMouseListener(deleteAction);
        pnlDelete.addMouseListener(deleteAction); 

        // Save
        MouseAdapter saveAction = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                saveEmployee(); 
            }
        };
        lblSave.addMouseListener(saveAction);
        pnlSave.addMouseListener(saveAction); 

        // Refresh
        MouseAdapter refreshAction = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                loadEmployeeData();
            }
        };
        lblRefresh.addMouseListener(refreshAction);
    }

    // --- Save Employee (Insert or Update) ---
    private void saveEmployee() {
        String name = txtEmployeeName.getText().trim();
        String email = txtGmail.getText().trim();
        String password = txtEmployeePassword.getText().trim();
        String role = cmbEmployeeRole.getSelectedItem().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String idText = txtEmployeeID.getText().trim();
            // 🎯 FIX 3: Base logic on the explicit currentMode, not the presence of ID
            boolean isUpdate = "Edit".equals(currentMode); 

            EmployeeModel emp = new EmployeeModel(name, email, role, password);

            if (isUpdate) {
                // UPDATE Logic
                int id = Integer.parseInt(idText);
                emp.setEmpId(id); 
                
                EmployeeModel oldEmp = controller.getEmployeeById(id);
                
                // Perform the comparison ONLY in EDIT mode
                if (oldEmp != null && 
                    oldEmp.getEmpName().equals(emp.getEmpName()) &&
                    oldEmp.getEmpEmail().equals(emp.getEmpEmail()) &&
                    oldEmp.getEmpRole().equals(emp.getEmpRole()) &&
                    oldEmp.getEmpPassword().equals(emp.getEmpPassword())) 
                {
                    // Show message and skip update
                    JOptionPane.showMessageDialog(this, "No difference detected. Update was canceled.", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    // Difference detected: Proceed with update
                    int rowsAffected = controller.updateEmployee(emp); 
                    
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Updated Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Update attempted, but no record was modified. Record may be missing.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                // ADD/INSERT Logic (currentMode is "Add")
                controller.addEmployee(emp); 
                // No comparison needed for Add
                JOptionPane.showMessageDialog(this, "Added Successfully! (ID will be visible on refresh)");
            }

            loadEmployeeData(); // Refresh table

            // Reset UI state
            pnlAddEditContent.setBackground(new Color(153,153,153));
            setFieldsEnabled(false);
            clearFields();
            pnlSave.setEnabled(false);
            lblSave.setEnabled(false);
            lblAdd.setForeground(Color.WHITE);
            lblEdit.setForeground(Color.WHITE);
            currentMode = "View"; // 🎯 FIX 4: Reset mode after operation

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Employee ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(this, "Save failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // --- Delete Employee ---
    private void deleteEmployee(int id) {
        try {
            controller.deleteEmployee(id); 
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
        } catch (Exception e) { // Catches RuntimeException from Controller/DAO
            JOptionPane.showMessageDialog(null, "Delete failed: " + e.getMessage());
            e.printStackTrace();
        } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        pnlAdd = new javax.swing.JPanel();
        lblAdd = new javax.swing.JLabel();
        pnlEdit = new javax.swing.JPanel();
        lblEdit = new javax.swing.JLabel();
        pnlDelete = new javax.swing.JPanel();
        lblDelete = new javax.swing.JLabel();
        lblRefresh = new javax.swing.JLabel();
        pnlAddEditContent = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtEmployeeID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmployeeName = new javax.swing.JTextField();
        txtEmployeeGmail = new javax.swing.JLabel();
        txtGmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmployeePassword = new javax.swing.JTextField();
        pnlSave = new javax.swing.JPanel();
        lblSave = new javax.swing.JLabel();
        cmbEmployeeRole = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1000, 750));
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblEmployees);

        lblSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/search5.png"))); // NOI18N

        txtSearch.setBackground(java.awt.SystemColor.control);
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearch.setToolTipText("");
        txtSearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtSearch.setSelectionColor(new java.awt.Color(204, 204, 204));

        pnlAdd.setBackground(new java.awt.Color(0, 0, 0));

        lblAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAdd.setForeground(new java.awt.Color(255, 255, 255));
        lblAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/add1.png"))); // NOI18N
        lblAdd.setText("Add");
        lblAdd.setIconTextGap(8);

        javax.swing.GroupLayout pnlAddLayout = new javax.swing.GroupLayout(pnlAdd);
        pnlAdd.setLayout(pnlAddLayout);
        pnlAddLayout.setHorizontalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblAdd)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pnlAddLayout.setVerticalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlEdit.setBackground(new java.awt.Color(0, 0, 0));

        lblEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEdit.setForeground(new java.awt.Color(255, 255, 255));
        lblEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/edit1.png"))); // NOI18N
        lblEdit.setText("Edit");
        lblEdit.setIconTextGap(8);

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblEdit)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDelete.setBackground(new java.awt.Color(51, 51, 51));

        lblDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblDelete.setForeground(new java.awt.Color(255, 51, 51));
        lblDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/delete1.png"))); // NOI18N
        lblDelete.setText("Delete");
        lblDelete.setIconTextGap(8);

        javax.swing.GroupLayout pnlDeleteLayout = new javax.swing.GroupLayout(pnlDelete);
        pnlDelete.setLayout(pnlDeleteLayout);
        pnlDeleteLayout.setHorizontalGroup(
            pnlDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblDelete)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlDeleteLayout.setVerticalGroup(
            pnlDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/refresh2.png"))); // NOI18N

        pnlAddEditContent.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Employee ID");

        txtEmployeeID.setBackground(java.awt.SystemColor.control);
        txtEmployeeID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmployeeID.setToolTipText("");
        txtEmployeeID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtEmployeeID.setEnabled(false);
        txtEmployeeID.setSelectionColor(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Employee Name");

        txtEmployeeName.setBackground(java.awt.SystemColor.control);
        txtEmployeeName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmployeeName.setToolTipText("");
        txtEmployeeName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtEmployeeName.setEnabled(false);
        txtEmployeeName.setSelectionColor(new java.awt.Color(204, 204, 204));

        txtEmployeeGmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmployeeGmail.setText("Employee Gmail");

        txtGmail.setBackground(java.awt.SystemColor.control);
        txtGmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtGmail.setToolTipText("");
        txtGmail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtGmail.setEnabled(false);
        txtGmail.setSelectionColor(new java.awt.Color(204, 204, 204));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Employee Role");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Employee Password");

        txtEmployeePassword.setBackground(java.awt.SystemColor.control);
        txtEmployeePassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtEmployeePassword.setToolTipText("");
        txtEmployeePassword.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        txtEmployeePassword.setEnabled(false);
        txtEmployeePassword.setSelectionColor(new java.awt.Color(204, 204, 204));

        pnlSave.setBackground(new java.awt.Color(0, 0, 0));
        pnlSave.setEnabled(false);

        lblSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSave.setForeground(new java.awt.Color(102, 153, 0));
        lblSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/save1.png"))); // NOI18N
        lblSave.setText("Save");
        lblSave.setEnabled(false);
        lblSave.setIconTextGap(8);

        javax.swing.GroupLayout pnlSaveLayout = new javax.swing.GroupLayout(pnlSave);
        pnlSave.setLayout(pnlSaveLayout);
        pnlSaveLayout.setHorizontalGroup(
            pnlSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaveLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblSave)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pnlSaveLayout.setVerticalGroup(
            pnlSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbEmployeeRole.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbEmployeeRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cashier", "Chef" }));
        cmbEmployeeRole.setEnabled(false);

        javax.swing.GroupLayout pnlAddEditContentLayout = new javax.swing.GroupLayout(pnlAddEditContent);
        pnlAddEditContent.setLayout(pnlAddEditContentLayout);
        pnlAddEditContentLayout.setHorizontalGroup(
            pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddEditContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmployeeName)
                    .addComponent(txtGmail)
                    .addComponent(txtEmployeePassword)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAddEditContentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAddEditContentLayout.createSequentialGroup()
                        .addGroup(pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEmployeeRole, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmployeeGmail)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAddEditContentLayout.setVerticalGroup(
            pnlAddEditContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddEditContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmployeeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmployeeGmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(8, 8, 8)
                .addComponent(cmbEmployeeRole, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmployeePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlAddEditContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch)
                    .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(pnlDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(pnlAddEditContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbEmployeeRole;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblEdit;
    private javax.swing.JLabel lblRefresh;
    private javax.swing.JLabel lblSave;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlAddEditContent;
    private javax.swing.JPanel pnlDelete;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlSave;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JLabel txtEmployeeGmail;
    private javax.swing.JTextField txtEmployeeID;
    private javax.swing.JTextField txtEmployeeName;
    private javax.swing.JTextField txtEmployeePassword;
    private javax.swing.JTextField txtGmail;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
