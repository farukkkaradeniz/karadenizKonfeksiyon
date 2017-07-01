/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.service.CompanyService;
import com.faruk.karadenizkonfeksiyon.service.GenericDatabaseService;
import com.faruk.karadenizkonfeksiyon.service.GenericDatabaseServiceImpl;
import com.faruk.karadenizkonfeksiyon.service.InvoiceService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faruk
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class InvoiceResource {

    @Autowired
    private InvoiceService invoiceService;
    
    @Autowired
    private GenericDatabaseService<Invoice> invoiceGenericImp;

    @GetMapping("/invoice")
    public List<Invoice> getAllInvoices(){
        List<Invoice> invoices = invoiceGenericImp.findAll(Invoice.class);
        return invoiceGenericImp.findAll(Invoice.class);
        
    }

    @GetMapping("/invoice/all/{companyId}")
    public List<Invoice> getAllByCompanyId(@PathVariable Long companyId) {
        List<Invoice> data = invoiceService.findAllByCompanyId(companyId);
        return invoiceService.findAllByCompanyId(
                companyId);

    }

    @GetMapping("invoice/one/{id}")
    public Invoice getOneByInvoiceID(@PathVariable Long id) {

        return invoiceService.findOneById(id);

    }

    @PostMapping("/invoice")
    public Invoice createInvoice(@RequestBody Invoice invoice) {

        return invoiceService.save(invoice);

    }

    @PutMapping("/invoice")
    public Invoice updateInvoice(@RequestBody Invoice invoice) {

        return invoiceService.update(invoice);

    }

    @DeleteMapping("/invoice")
    public void delete(@RequestBody Invoice invoice) {

        invoiceService.delete(invoice);

    }

    @DeleteMapping("/invoice/{id}")
    public void deleteById(@PathVariable Long id) {

        invoiceService.delete(id);

    }

}
