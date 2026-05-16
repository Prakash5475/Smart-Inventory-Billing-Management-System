package com.inventory.repository;
import com.inventory.model.Customer; import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {}