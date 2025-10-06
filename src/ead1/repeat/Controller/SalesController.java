package ead1.repeat.Controller;

import ead1.repeat.DatabaseConfig; // Required for connection handling
import ead1.repeat.Model.SalesDAO;
import ead1.repeat.Model.SalesModel; 
import java.util.List;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

// JasperReports Imports
import java.sql.Connection;
import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class SalesController {

    private SalesDAO salesDAO;

    public SalesController() {
        this.salesDAO = new SalesDAO();
    }

    // ... (createSalesChart() and createDataset() methods remain unchanged) ...

    /**
     * Fetches data from the DAO and builds the JFreeChart object.
     * @return A JFreeChart object ready to be displayed.
     */
    public JFreeChart createSalesChart() {
        
        List<SalesModel> dataList = salesDAO.getMonthlyMenuSales();
        
        DefaultCategoryDataset dataset = createDataset(dataList);

        JFreeChart chart = ChartFactory.createBarChart(
            "Monthly Sales Breakdown by Menu Item (LKR Rs.)", // Chart Title
            "Month and Year",                           
            "Total Sales Amount (LKR Rs.)",         
            dataset,                                
            PlotOrientation.VERTICAL,
            true,                                   
            true,                                   
            false                                   
        );

        return chart;
    }

    /**
     * Converts the List of SalesModel objects into a DefaultCategoryDataset.
     */
    private DefaultCategoryDataset createDataset(List<SalesModel> dataList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (SalesModel data : dataList) {
            // JFreeChart adds data as: (Value, Series/Legend Key, Category/X-Axis Key)
            dataset.addValue(
                data.getTotalSales(),  
                data.getMenuItemName(),  
                data.getMonthYear()
            );
        }
        return dataset;
    }

    // ----------------------------------------------------------------------
    // NEW REPORT GENERATION LOGIC
    // ----------------------------------------------------------------------
    
    /**
     * Generates the monthly sales report, displays it in a JasperViewer, and allows printing/exporting.
     */
    public void generateMonthlyReport() {
        Connection conn = null;
        try {
            // 1. Get Database Connection (using DatabaseConfig as seen in SalesDAO)
            conn = DatabaseConfig.getConnection(); 
            
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed. Check your DatabaseConfig class.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Load the JRXML file from resources
            // IMPORTANT: Ensure "report.jrxml" is the correct path and file name
            InputStream inputStream = getClass().getResourceAsStream("/ead1/repeat/Resources/report.jrxml");

            if (inputStream == null) {
                JOptionPane.showMessageDialog(null, "Report file (report.jrxml) not found in Resources.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Compile the report
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // 4. Fill the report with data using the database connection
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, 
                    new HashMap<>(), // Empty HashMap for parameters
                    conn // This passes the DB connection to the report's SQL query
            );

            // 5. View the report in a new window
            JasperViewer viewer = new JasperViewer(jasperPrint, false); 
            viewer.setTitle("Monthly Menu Item Sales Report");
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error generating report: " + e.getMessage(), "Report Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // 6. Close the connection safely
            DatabaseConfig.closeConnection(conn);
        }
    }
}