/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import com.faruk.karadenizkonfeksiyon.domain.ProfitPerYear;
import com.faruk.karadenizkonfeksiyon.repository.ProfitPerYearRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfitPerYearServiceImpl implements ProfitPerYearService {

    @Autowired
    private ProfitPerYearRepository profitPerYearRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProfitPerYear> findAll() {
        return profitPerYearRepository.findAll();
    }

    @Override
    public ProfitPerYear save(ProfitPerYear profitPerYear) {
        return profitPerYearRepository.save(profitPerYear);
    }

    @Override
    public ProfitPerYear findOne(Long id) {
        return profitPerYearRepository.findOne(id);
    }

    @Override
    public ProfitPerYear findOneWithEager(Long id) {

        Session session = entityManager.unwrap(Session.class);
        Criteria profitPerYearCriteria = session.createCriteria(ProfitPerYear.class);
        profitPerYearCriteria.createCriteria("profitPerMonths");
        return (ProfitPerYear) profitPerYearCriteria.add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public ProfitPerYear findOneByValue(Long value) {
        Session session = entityManager.unwrap(Session.class);
        return (ProfitPerYear) session.createCriteria(ProfitPerYear.class)
                .add(Restrictions.eq("value", value))
                .uniqueResult();
    }

}
