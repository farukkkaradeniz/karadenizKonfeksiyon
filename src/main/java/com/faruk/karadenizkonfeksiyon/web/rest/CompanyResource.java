/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.Company;
import com.faruk.karadenizkonfeksiyon.service.CompanyService;
import com.faruk.karadenizkonfeksiyon.service.GenericDatabaseServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faruk
 */
@CrossOrigin(origins = "*")
@RestController
public class CompanyResource {
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private GenericDatabaseServiceImpl<Company> genericDatabaseServiceImpl;
    
    @GetMapping("/company")
    public List<Company> getAllCompany(){
    
        return companyService.findAll();
        
    }
    
    @GetMapping("company/{id}")
    public Company getOneById(@PathVariable Long id){
    
        return companyService.findOneById(id);
        
    }
    
    @PostMapping("company")
    public Company createCompany(@RequestBody Company company){
    
        return companyService.save(company);
        
    }
    
    @PutMapping("company")
    public Company updateCompany(@RequestBody Company company){
        
        return companyService.save(company);
        
    }
    
    @DeleteMapping("company")
    public void deleteCompany(@RequestBody Company company){
    
        companyService.deleteCompany(company);
    
    }
    
    @DeleteMapping("company/{id}")
    public void deleteCompany(@PathVariable Long id){
    
        companyService.deleteCompany(id);
        
    }
    
    @GetMapping("/company/hibernate")
    public List<Company> getHibernateData(){
    
        return genericDatabaseServiceImpl.findAll(Company.class);
        
    }
    
    @GetMapping("/company/hibernate/{id}")
    public Company getHibernateOneById(@PathVariable Long id){
    
        return genericDatabaseServiceImpl.findOne(Company.class, id);
        
    }
    
    @GetMapping("/company/hibernate/save")
    public Company saveHiberbate(){
        Company company = new Company();
        
        company.setCompanyName("Karadeniz Konfeksiyon");
        
        return genericDatabaseServiceImpl.save(Company.class, company);
        
    }
    
}
