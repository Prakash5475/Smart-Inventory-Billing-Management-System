package com.inventory.service;
import com.inventory.dto.ProductDto; import com.inventory.exception.ResourceNotFoundException; import com.inventory.model.Product; import com.inventory.repository.ProductRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.*;
import java.util.List; import java.util.stream.*;
@Service public class ProductService {
    @Autowired private ProductRepository repo;
    public List<ProductDto> getAll() { return repo.findAll().stream().map(this::toDto).collect(Collectors.toList()); }
    public ProductDto getById(Long id) { return toDto(repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found"))); }
    public ProductDto create(ProductDto d) { return toDto(repo.save(toEntity(d))); }
    public ProductDto update(Long id, ProductDto d) {
        Product p = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found"));
        p.setName(d.getName()); p.setSku(d.getSku()); p.setPrice(d.getPrice()); p.setStock(d.getStock()); p.setCategory(d.getCategory());
        return toDto(repo.save(p));
    }
    public void delete(Long id) { repo.deleteById(id); }
    public List<ProductDto> getLowStock() { return repo.findByStockLessThan(10).stream().map(this::toDto).collect(Collectors.toList()); }
    private ProductDto toDto(Product p) { return new ProductDto(p.getId(),p.getName(),p.getSku(),p.getDescription(),p.getCategory(),p.getPrice(),p.getStock()); }
    private Product toEntity(ProductDto d) { return new Product(null,d.getName(),d.getSku(),d.getDescription(),d.getCategory(),d.getPrice(),d.getStock()); }
}