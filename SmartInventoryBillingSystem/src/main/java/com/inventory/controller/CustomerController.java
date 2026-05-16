package com.inventory.controller;
import com.inventory.dto.CustomerDto; import com.inventory.service.CustomerService; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/customers") public class CustomerController {
    @Autowired private CustomerService service;
    @GetMapping public List<CustomerDto> all() { return service.getAll(); }
    @PostMapping public CustomerDto create(@RequestBody CustomerDto d) { return service.create(d); }
}