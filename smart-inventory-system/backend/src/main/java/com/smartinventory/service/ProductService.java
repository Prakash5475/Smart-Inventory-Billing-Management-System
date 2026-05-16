package com.smartinventory.service;

import com.smartinventory.entity.Product;
import com.smartinventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product product) {

        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setSku(product.getSku());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStatus(product.getStatus());

        return repository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}