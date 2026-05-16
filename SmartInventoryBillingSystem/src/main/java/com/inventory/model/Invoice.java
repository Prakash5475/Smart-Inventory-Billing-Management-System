package com.inventory.model;
import jakarta.persistence.*; import lombok.*; import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.ArrayList; import java.util.List;
@Entity @Table(name="invoices") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Invoice {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String invoiceNumber;
    @ManyToOne @JoinColumn(name="customer_id") private Customer customer;
    private BigDecimal totalAmount, taxAmount, discountAmount, status;
    @CreationTimestamp private LocalDateTime createdAt;
    @OneToMany(mappedBy="invoice", cascade=CascadeType.ALL, orphanRemoval=true) private List<InvoiceItem> items = new ArrayList<>();
}