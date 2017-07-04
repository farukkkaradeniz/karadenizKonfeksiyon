/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

@Service
public class GenericDatabaseServiceImpl<T> implements GenericDatabaseService<T> {

    @PersistenceContext 
    private EntityManager entityManager;
        
    @Override
    public List<T> findAll(Class<T> type) {
        
        Session session = entityManager.unwrap(Session.class);
        
        List<T> items =(List<T>) session.createCriteria(type).list();
        
        return items;
        
    }

    @Override
    public T findOne(Class<T> type, Long id) {
        
        Session session = entityManager.unwrap(Session.class);
        
        T result = (T)session.createCriteria(type).add(Restrictions.eq("id", id)).uniqueResult();
        
        return result;
        
    }

    @Override
    public T findOne(Class<T> type, UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T save(Class<T> type, T t) {
        
        Session session = entityManager.unwrap(Session.class);
        
        Long id = (Long)session.save(t);
        
        return findOne(type, id);
        
    }

    @Override
    public T update(Class<T> type, T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Class<T> type, T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Class<T> type, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
