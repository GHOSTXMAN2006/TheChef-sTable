package ead1.repeat.View;

import ead1.repeat.Controller.SalesController;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class Admin_SalesPanel extends javax.swing.JPanel {

    private SalesController controller; // Declare the controller

    public Admin_SalesPanel() {
        // 1. Instantiate the Controller
        this.controller = new SalesController();
        
        initComponents();
        
        // 2. Load the chart using the Controller
        loadSalesChart(); 
        
        // 3. Add the refresh listener
        addRefreshListener();
        
        // 4. ADD THE NEW PRINT REPORT LISTENER
        addPrintReportListener();
    }
      
    /**
     * **FIXED METHOD:** Adds the MouseListener to BOTH the JPanel and the JLabel 
     * inside it to ensure the click event is captured reliably.
     */
    private void addPrintReportListener() {
        
        // 1. Create a single listener instance
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("DEBUG VIEW: Print Report click detected. Calling controller..."); // NEW DEBUG TRACE
                // Call the controller method to generate and display the report
                controller.generateMonthlyReport(); 
            }
        };
        
        // 2. Attach the listener to the container JPanel
        this.pnlPrintReport.addMouseListener(listener);
        
        // 3. Attach the listener to the contained JLabel (most common fix)
        this.lblPrintReport.addMouseListener(listener);
    }
    
    private void addRefreshListener() {
        // Use MouseAdapter to handle the click event on the JLabel
        lblRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Call the chart loading method to re-fetch data and redraw the chart
                loadSalesChart(); 
            }
        });
    }

    /**
     * Retrieves the chart from the Controller and places it inside pnlChartContainer.
     */
    private void loadSalesChart() {
        
        // Request the fully built JFreeChart object from the Controller
        JFreeChart chart = controller.createSalesChart();
        
        if (chart == null) {
            // Add custom error handling here (e.g., set an error message in the panel)
            System.err.println("Failed to load chart: Chart object is null.");
            return;
        }

        // 3. Wrap the chart in the ChartPanel
        ChartPanel chartPanel = new ChartPanel(chart);
        
        // Clear any previous components and add the new chart
        this.pnlChartContainer.removeAll(); 
        
        // 4. Add the ChartPanel to the pnlChartContainer
        this.pnlChartContainer.add(chartPanel, BorderLayout.CENTER);
        
        // Refresh the panel
        this.pnlChartContainer.revalidate(); 
        this.pnlChartContainer.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlChartContainer = new javax.swing.JPanel();
        lblRefresh = new javax.swing.JLabel();
        pnlPrintReport = new javax.swing.JPanel();
        lblPrintReport = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));
        setMaximumSize(new java.awt.Dimension(1000, 750));
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setPreferredSize(new java.awt.Dimension(1000, 750));

        pnlChartContainer.setLayout(new java.awt.BorderLayout());

        lblRefresh.setBackground(new java.awt.Color(215, 145, 130));
        lblRefresh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRefresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRefresh.setText("Refresh");
        lblRefresh.setOpaque(true);

        pnlPrintReport.setBackground(new java.awt.Color(0, 0, 0));
        pnlPrintReport.setToolTipText("<html>\n  <b>Report Details:</b>\n  <ul>\n    <li><b>Purpose:</b> Provides a <b>Detailed Menu Item Sales Report</b>.</li>\n    <li><b>Function:</b> Calculates the total sales revenue for each menu item (`item_sales`) and breaks down this information by month and year (`month_year`).</li>\n  </ul>\n  <b>Data Source Columns:</b>\n  <ul>\n    <li><b>orders (o):</b> <code>order_date</code>, <code>order_id</code></li>\n    <li><b>order_items (oi):</b> <code>subtotal_price</code>, <code>order_id</code>, <code>menu_id</code></li>\n    <li><b>menus (m):</b> <code>name</code>, <code>menu_id</code></li>\n  </ul>\n</html>");

        lblPrintReport.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPrintReport.setForeground(new java.awt.Color(215, 145, 130));
        lblPrintReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ead1/repeat/Resources/add1.png"))); // NOI18N
        lblPrintReport.setText("Print Report");
        lblPrintReport.setToolTipText("<html>\n  <b>Report Details:</b>\n  <ul>\n    <li><b>Purpose:</b> Provides a <b>Detailed Menu Item Sales Report</b>.</li>\n    <li><b>Function:</b> Calculates the total sales revenue for each menu item (`item_sales`) and breaks down this information by month and year (`month_year`).</li>\n  </ul>\n  <b>Data Source Columns:</b>\n  <ul>\n    <li><b>orders (o):</b> <code>order_date</code>, <code>order_id</code></li>\n    <li><b>order_items (oi):</b> <code>subtotal_price</code>, <code>order_id</code>, <code>menu_id</code></li>\n    <li><b>menus (m):</b> <code>name</code>, <code>menu_id</code></li>\n  </ul>\n</html>");
        lblPrintReport.setIconTextGap(8);

        javax.swing.GroupLayout pnlPrintReportLayout = new javax.swing.GroupLayout(pnlPrintReport);
        pnlPrintReport.setLayout(pnlPrintReportLayout);
        pnlPrintReportLayout.setHorizontalGroup(
            pnlPrintReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrintReportLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblPrintReport)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pnlPrintReportLayout.setVerticalGroup(
            pnlPrintReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrintReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChartContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlPrintReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlChartContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblPrintReport;
    private javax.swing.JLabel lblRefresh;
    private javax.swing.JPanel pnlChartContainer;
    private javax.swing.JPanel pnlPrintReport;
    // End of variables declaration//GEN-END:variables
}
