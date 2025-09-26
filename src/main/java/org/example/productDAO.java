package org.example;

import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public interface productDAO {
    boolean addProduct(Product product);

    Optional<Product> getProductById(String id);

    List<Product> getAllProducts();

    boolean updateProduct(String id, double newPrice, int newQuantity);

    boolean deleteProduct(String id);
}
