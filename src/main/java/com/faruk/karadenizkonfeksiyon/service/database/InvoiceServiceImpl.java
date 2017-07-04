/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import com.faruk.karadenizkonfeksiyon.service.database.CompanyService;
import com.faruk.karadenizkonfeksiyon.domain.Invoice;
import com.faruk.karadenizkonfeksiyon.repository.InvoiceRepository;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CompanyService companyService;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice update(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    @Override
    public void delete(Long id) {
        invoiceRepository.delete(id);
    }

    @Override
    public Invoice findOneById(Long id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    public List<Invoice> findAllByCompanyId(Long companyId) {
        return invoiceRepository.findAllByCompany(companyService.findOneById(companyId));
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> findAllBeetweenDates(ZonedDateTime startDate, ZonedDateTime endDate) {

        Session session = entityManager.unwrap(Session.class);

        Criteria invoiceCriteria = session.createCriteria(Invoice.class);

        return invoiceCriteria.add(Restrictions.between("date", startDate, endDate)).list();

    }

}
