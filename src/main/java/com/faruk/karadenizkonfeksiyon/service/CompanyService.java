/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service;

import com.faruk.karadenizkonfeksiyon.domain.Company;
import java.util.List;

/**
 *
 * @author Faruk
 */
public interface CompanyService {
    
    public Company save(Company company);
    
    public Company updateCompany(Company company);
    
    public void deleteCompany(Company company);
    
    public void deleteCompany(Long id);
    
    public List<Company> findAll();
    
    public Company findOneById(Long id);
    
    public List<Company> getAllRecordsFromDb();
    
}
