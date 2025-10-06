package ead1.repeat.Model;

/**
 * A simple Plain Old Java Object (POJO) to hold one row of aggregated sales data.
 */
public class SalesModel {
    private String monthYear;
    private String menuItemName;
    private double totalSales;

    // Constructor
    public SalesModel(String monthYear, String menuItemName, double totalSales) {
        this.monthYear = monthYear;
        this.menuItemName = menuItemName;
        this.totalSales = totalSales;
    }

    // Getters
    public String getMonthYear() {
        return monthYear;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public double getTotalSales() {
        return totalSales;
    }
}