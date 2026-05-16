package com.inventory.model;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal;
@Entity @Table(name="products") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name, sku, description, category;
    private BigDecimal price;
    private Integer stock;
}