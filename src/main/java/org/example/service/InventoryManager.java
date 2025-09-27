package org.example.service;

import org.example.model.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class InventoryManager {
    private final Map<String, Product> productMap = new HashMap<>();

    public boolean addProduct(Product product) {
        if (product != null) {
            if (productMap.containsKey(product.getProductId())) {
                System.err.println("❌ Error: A product with ID '" + product.getProductId() + "' already exists. Use the update option instead.");
                return false;
            } else {
                productMap.put(product.getProductId(), product);
                return true;
            }
        }
        return false;
    }
    public Collection<Product> getAllProducts() {
        return productMap.values();
    }
    public List<Product> getSortedProducts(Comparator<Product> comparator) {
        List<Product> products = new ArrayList<>(productMap.values());

        products.sort(comparator);
        return products;
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
    public void saveToCsv() {
        String csvFilePath = "inventory_output.csv";
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
    public int getTotalProducts() {
        return productMap.size();
    }
    public int getTotalQuantity() {
        return productMap.values().stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }
    public double getTotalValue() {
        return productMap.values().stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();
    }
}
