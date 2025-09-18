package org.example.service;

import org.example.model.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InventoryManager {
    private final Map<String, Product> productMap = new HashMap<>();

    // ... (existing methods like addProduct, viewAllProducts, etc.)

    public int getTotalProducts() {
        return productMap.size();
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Product product : productMap.values()) {
            totalQuantity += product.getQuantity();
        }
        return totalQuantity;
    }

    public double getTotalValue() {
        double totalValue = 0.0;
        for (Product product : productMap.values()) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        return totalValue;
    }

    public void saveToCsv() {
        String csvFilePath = "inventory.csv";
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            // Write the CSV header
            writer.append("ID,Name,Price,Quantity,ManufacturingDate,Supplier\n");
            for (Product product : productMap.values()) {
                writer.append(product.getProductId()).append(",");
                writer.append(product.getProductName()).append(",");
                writer.append(String.valueOf(product.getPrice())).append(",");
                writer.append(String.valueOf(product.getQuantity())).append(",");
                writer.append(product.getManufacturingDate().toString()).append(",");
                writer.append(product.getSupplier()).append("\n");
            }

            System.out.println("✅ Inventory successfully saved to " + csvFilePath);
        } catch (IOException e) {
            System.err.println("❌ Error saving inventory to CSV: " + e.getMessage());
        }
    }


    public void addProduct(Product product) {
        if (product != null) {
            if (productMap.containsKey(product.getProductId())) {
                System.err.println("❌ Error: A product with ID '" + product.getProductId() + "' already exists. Use the update option instead.");
            } else {
                productMap.put(product.getProductId(), product);
                System.out.println("✅ Product added successfully: " + product.getProductName());
            }
        }
    }

    public void viewAllProducts() {
        if (productMap.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- All Products in Inventory ---");
        for (Product p : productMap.values()) {
            System.out.println("ID: " + p.getProductId() + " | Name: " + p.getProductName() +
                    " | Price: $" + p.getPrice() + " | Qty: " + p.getQuantity());
        }
    }

    public Optional<Product> searchProduct(String id) {
        Product product = productMap.get(id);
        return Optional.ofNullable(product);
    }

    public boolean updateProduct(String id, double newPrice, int newQuantity) {
        Product productToUpdate = productMap.get(id);
        if (productToUpdate != null) {
            productToUpdate.setPrice(newPrice);
            productToUpdate.setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    public boolean removeProduct(String id) {
        return productMap.remove(id) != null;
    }
}