/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service;

import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import java.util.List;

/**
 *
 * @author Faruk
 */
public interface InvoiceService {
    
    public Invoice save(Invoice invoice);
    
    public Invoice update(Invoice invoice);
    
    public void delete(Invoice invoice);
    
    public void delete(Long id);
    
    public Invoice findOneById(Long id);
    
    public List<Invoice> findAllByCompanyId(Long companyId);
    
    public List<Invoice> findAll();
    
    
}
