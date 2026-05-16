package com.inventory.dto; import lombok.Data; import java.math.BigDecimal; @Data public class ProductDto { private Long id; private String name, sku, description, category; private BigDecimal price; private Integer stock; }package com.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String sku;
    private String category;
    private String status;
    private BigDecimal price;
    private Integer stock;
}