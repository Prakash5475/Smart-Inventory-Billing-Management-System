package com.inventory.repository;
import com.inventory.model.Invoice; import org.springframework.data.jpa.repository.JpaRepository;
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {}