package com.inventory.model;
import jakarta.persistence.*; import lombok.*; import java.math.BigDecimal;
@Entity @Table(name="invoice_items") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class InvoiceItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name="invoice_id") private Invoice invoice;
    @ManyToOne @JoinColumn(name="product_id") private Product product;
    private Integer quantity;
    private BigDecimal unitPrice, totalPrice;
}