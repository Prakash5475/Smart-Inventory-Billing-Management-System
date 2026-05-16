package com.inventory.util;
import com.inventory.dto.*; import com.itextpdf.text.*; import com.itextpdf.text.pdf.*; import org.springframework.stereotype.Component;
import java.io.*; import java.text.NumberFormat; import java.util.Locale;
@Component public class PdfGenerator {
    public void generate(InvoiceDto inv, OutputStream out) {
        try { Document doc = new Document(); PdfWriter.getInstance(doc, out); doc.open();
        doc.add(new Paragraph("INVOICE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK)));
        doc.add(Chunk.NEWLINE); doc.add(new Paragraph("Invoice #: "+inv.getInvoiceNumber())); doc.add(new Paragraph("Customer: "+inv.getCustomerName()));
        doc.add(Chunk.NEWLINE);
        PdfPTable t = new PdfPTable(4); t.setWidthPercentage(100);
        t.addCell("Product"); t.addCell("Qty"); t.addCell("Price"); t.addCell("Total");
        NumberFormat c = NumberFormat.getCurrencyInstance(Locale.US);
        for(InvoiceItemDto i : inv.getItems()) {
            t.addCell(i.getProductName()); t.addCell(""+i.getQuantity()); t.addCell(c.format(i.getUnitPrice())); t.addCell(c.format(i.getTotalPrice()));
        } doc.add(t); doc.add(new Paragraph("Total: "+c.format(inv.getTotalAmount()), FontFactory.getFont(FontFactory.HELVETICA_BOLD,14)));
        doc.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}