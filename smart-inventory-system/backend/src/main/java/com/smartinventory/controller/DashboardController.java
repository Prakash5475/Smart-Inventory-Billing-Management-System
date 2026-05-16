package com.smartinventory.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @GetMapping
    public Map<String, Object> getDashboardData() {

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put("products", 284);
        dashboard.put("revenue", 2400000);
        dashboard.put("lowStock", 17);
        dashboard.put("invoices", 1847);

        return dashboard;
    }
}