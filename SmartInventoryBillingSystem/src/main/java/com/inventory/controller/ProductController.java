package com.inventory.controller;
import com.inventory.dto.ProductDto; import com.inventory.service.ProductService; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/products") public class ProductController {
    @Autowired private ProductService service;
    @GetMapping public List<ProductDto> all() { return service.getAll(); }
    @GetMapping("/{id}") public ProductDto get(@PathVariable Long id) { return service.getById(id); }
    @GetMapping("/alerts/low-stock") public List<ProductDto> low() { return service.getLowStock(); }
    @PostMapping public ProductDto create(@RequestBody ProductDto d) { return service.create(d); }
    @PutMapping("/{id}") public ProductDto update(@PathVariable Long id, @RequestBody ProductDto d) { return service.update(id,d); }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id) { service.delete(id); }
}