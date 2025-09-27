package org.example.model;
import java.time.LocalDate;
public class Product {
    private final String productId;
    private String productName;
    private double price;
    private int quantity;
    private LocalDate manufacturingDate;
    private String supplier;

    public Product(String productId, String productName, double price, int quantity, LocalDate manufacturingDate, String supplier) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.manufacturingDate = manufacturingDate;
        this.supplier = supplier;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public LocalDate getManufacturingDate() { return manufacturingDate; }
    public String getSupplier() { return supplier; }

    // Setters (used by the updateProduct method)
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Additional setters (optional, but good practice)
    public void setProductName(String productName) { this.productName = productName; }
    public void setManufacturingDate(LocalDate manufacturingDate) { this.manufacturingDate = manufacturingDate; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
}
