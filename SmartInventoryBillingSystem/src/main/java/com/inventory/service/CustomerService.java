package com.inventory.service;
import com.inventory.dto.CustomerDto; import com.inventory.model.Customer; import com.inventory.repository.CustomerRepository; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.*;
import java.util.stream.*;
@Service public class CustomerService {
    @Autowired private CustomerRepository repo;
    public java.util.List<CustomerDto> getAll() { return repo.findAll().stream().map(this::toDto).collect(Collectors.toList()); }
    public CustomerDto create(CustomerDto d) { return toDto(repo.save(toEntity(d))); }
    private CustomerDto toDto(Customer c) { return new CustomerDto(c.getId(),c.getName(),c.getEmail(),c.getPhone(),c.getAddress()); }
    private Customer toEntity(CustomerDto d) { return new Customer(null,d.getName(),d.getEmail(),d.getPhone(),d.getAddress()); }
}