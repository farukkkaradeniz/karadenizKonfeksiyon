/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import com.faruk.karadenizkonfeksiyon.domain.Company;
import com.faruk.karadenizkonfeksiyon.repository.CompanyRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompanyServiceImpl implements CompanyService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        
        return companyRepository.save(company);
    
    }

    @Override
    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.delete(id);
    }

    @Override
    public List<Company> findAll() {
        List<Company> companies = companyRepository.findAll();
//        for (Company company : companies) {
//            company.getInvoices();
//            company.getRemainingBalances();
//        }
        return companies;
    }

    @Override
    public Company findOneById(Long id) {
        Company company = companyRepository.findOne(id);
        company.getInvoices();
        company.getRemainingBalances();
        return company;
    }
    
    @Override
    public List<Company> getAllRecordsFromDb(){
    
        Session session = entityManager.unwrap(Session.class);
        
        List<Company> company =(List<Company>) session.createCriteria(Company.class).list();
        
        return company;
        
    }

    @Override
    public List<Company> findAllLikeCompanyName(String companyName) {
        
        Session session = entityManager.unwrap(Session.class);
        
        return session.createCriteria(Company.class)
                .add(Restrictions.like("companyName", companyName)).list();
        
    }
    
}
