// File: src/main/java/org/example/service/InventoryManager.java
package org.example.service;

import org.example.DAO.ProductDAO;
import org.example.model.Product;
import java.util.List;
import java.util.Optional;

public class InventoryManager {
    private final ProductDAO productDAO;

    public InventoryManager() {
        // Instantiate the specific DAO implementation
        this.productDAO = new productDAOImpl();
    }

    public void addProduct(Product product) {
        Optional<Product> existingProduct = productDAO.getProductById(product.getProductId());
        if (existingProduct.isPresent()) {
            System.err.println("❌ Error: A product with ID '" + product.getProductId() + "' already exists. Use the update option instead.");
        } else {
            if (productDAO.addProduct(product)) {
                System.out.println("✅ Product added successfully: " + product.getProductName());
            }
        }
    }

    public void viewAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- All Products in Inventory ---");
        for (Product p : products) {
            System.out.println("ID: " + p.getProductId() + " | Name: " + p.getProductName() +
                    " | Price: $" + p.getPrice() + " | Qty: " + p.getQuantity());
        }
    }

    public Optional<Product> searchProduct(String id) {
        return productDAO.getProductById(id);
    }

    public boolean updateProduct(String id, double newPrice, int newQuantity) {
        return productDAO.updateProduct(id, newPrice, newQuantity);
    }

    public boolean removeProduct(String id) {
        return productDAO.deleteProduct(id);
    }

    // The report methods now rely on the DAO to fetch all data
    public int getTotalProducts() {
        return productDAO.getAllProducts().size();
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Product product : productDAO.getAllProducts()) {
            totalQuantity += product.getQuantity();
        }
        return totalQuantity;
    }

    public double getTotalValue() {
        double totalValue = 0.0;
        for (Product product : productDAO.getAllProducts()) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        return totalValue;
    }
}