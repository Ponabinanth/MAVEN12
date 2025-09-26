package org.example.service;

import org.example.DAO.ProductDAO;
import org.example.model.Product;

import java.util.List;
import java.util.Optional;

public class productDAOImpl implements ProductDAO {
    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public boolean updateProduct(String id, double newPrice, int newQuantity) {
        return false;
    }

    @Override
    public boolean deleteProduct(String id) {
        return false;
    }
}
