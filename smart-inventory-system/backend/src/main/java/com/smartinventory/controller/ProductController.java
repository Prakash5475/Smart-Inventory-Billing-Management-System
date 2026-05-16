package com.smartinventory.controller;

import com.smartinventory.entity.Product;
import com.smartinventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product) {

        return service.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }
}