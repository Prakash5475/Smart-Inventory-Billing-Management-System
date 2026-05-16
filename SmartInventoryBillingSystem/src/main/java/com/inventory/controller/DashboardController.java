package com.inventory.controller;
import com.inventory.repository.*; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/dashboard") public class DashboardController {
    @Autowired private ProductRepository prepo; @Autowired private InvoiceRepository irepo;
    @GetMapping("/stats") public Map<String,Object> stats() {
        Map<String,Object> m = new HashMap<>();
        m.put("totalProducts", prepo.count()); m.put("lowStockCount", prepo.findByStockLessThan(10).size());
        m.put("totalRevenue", irepo.findAll().stream().mapToDouble(i->i.getTotalAmount().doubleValue()).sum());
        m.put("totalInvoices", irepo.count()); return m;
    }
}