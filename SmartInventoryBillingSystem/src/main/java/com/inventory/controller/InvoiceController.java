package com.inventory.controller;
import com.inventory.dto.InvoiceDto; import com.inventory.service.InvoiceService; import com.inventory.util.PdfGenerator; import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired; import org.springframework.web.bind.annotation.*;
import java.io.IOException; import java.util.List;
@RestController @RequestMapping("/api/invoices") public class InvoiceController {
    @Autowired private InvoiceService service; @Autowired private PdfGenerator pdf;
    @GetMapping public List<InvoiceDto> all() { return service.getAll(); }
    @PostMapping public InvoiceDto create(@RequestBody InvoiceDto d) { return service.create(d); }
    @GetMapping("/{id}/download") public void download(@PathVariable Long id, HttpServletResponse r) throws IOException {
        InvoiceDto inv = service.getAll().stream().filter(i->i.getId().equals(id)).findFirst().orElseThrow();
        r.setContentType("application/pdf"); r.setHeader("Content-Disposition","attachment; filename=invoice_"+inv.getInvoiceNumber()+".pdf");
        pdf.generate(inv, r.getOutputStream());
    }
}