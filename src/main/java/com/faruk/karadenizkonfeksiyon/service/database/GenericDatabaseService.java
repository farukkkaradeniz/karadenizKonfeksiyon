/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Faruk
 * @param <T>
 */
public interface GenericDatabaseService<T> {
    
    public List<T> findAll(Class<T> type);
    
    public T findOne(Class<T> type,Long id);
    
    public T findOne(Class<T> type,UUID id);
    
    public T save(Class<T> type,T t);
    
    public T update(Class<T> type,T t);
    
    public void delete(Class<T> type,T t);
    
    public void delete(Class<T> type,Long id);
    
}
