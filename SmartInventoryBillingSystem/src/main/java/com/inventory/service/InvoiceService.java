package com.inventory.service;
import com.inventory.dto.*; import com.inventory.exception.*; import com.inventory.model.*; import com.inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.*; import org.springframework.transaction.annotation.*;
import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;
@Service public class InvoiceService {
    @Autowired private InvoiceRepository invRepo; @Autowired private CustomerRepository custRepo; @Autowired private ProductRepository prodRepo;
    public List<InvoiceDto> getAll() { return invRepo.findAll().stream().map(this::toDto).collect(java.util.stream.Collectors.toList()); }
    @Transactional
    public InvoiceDto create(InvoiceDto dto) {
        Customer c = custRepo.findById(dto.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Customer not found"));
        Invoice i = new Invoice(); i.setInvoiceNumber("INV-"+System.currentTimeMillis()); i.setCustomer(c); i.setStatus("PAID"); i.setCreatedAt(LocalDateTime.now());
        List<InvoiceItem> items = new ArrayList<>(); BigDecimal total = BigDecimal.ZERO;
        for(InvoiceItemDto itemDto : dto.getItems()) {
            Product p = prodRepo.findById(itemDto.getProductId()).orElseThrow(()->new ResourceNotFoundException("Product not found"));
            if(p.getStock() < itemDto.getQuantity()) throw new RuntimeException("Insufficient stock: "+p.getName());
            p.setStock(p.getStock()-itemDto.getQuantity()); prodRepo.save(p);
            InvoiceItem item = new InvoiceItem(); item.setInvoice(i); item.setProduct(p); item.setQuantity(itemDto.getQuantity());
            item.setUnitPrice(p.getPrice()); item.setTotalPrice(p.getPrice().multiply(new BigDecimal(itemDto.getQuantity())));
            items.add(item); total = total.add(item.getTotalPrice());
        }
        i.setItems(items); i.setTotalAmount(total); i.setTaxAmount(total.multiply(new BigDecimal("0.10")));
        return toDto(invRepo.save(i));
    }
    private InvoiceDto toDto(Invoice i) {
        InvoiceDto d = new InvoiceDto(); d.setId(i.getId()); d.setInvoiceNumber(i.getInvoiceNumber()); d.setCustomerId(i.getCustomer().getId());
        d.setCustomerName(i.getCustomer().getName()); d.setTotalAmount(i.getTotalAmount()); d.setStatus(i.getStatus()); d.setCreatedAt(i.getCreatedAt());
        List<InvoiceItemDto> items = i.getItems().stream().map(it->{
            InvoiceItemDto id = new InvoiceItemDto(); id.setProductId(it.getProduct().getId()); id.setProductName(it.getProduct().getName());
            id.setQuantity(it.getQuantity()); id.setUnitPrice(it.getUnitPrice()); id.setTotalPrice(it.getTotalPrice()); return id;
        }).collect(Collectors.toList());
        d.setItems(items); return d;
    }
}