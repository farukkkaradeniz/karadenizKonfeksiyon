/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service;

import com.faruk.karadenizkonfeksiyon.domain.Expenses;
import java.util.List;

/**
 *
 * @author Faruk
 */
public interface ExpensesService {
    
    public Expenses save(Expenses expenses);
    
    public Expenses update(Expenses expenses);
    
    public void delete(Expenses expenses);
    
    public void delete(Long id);
    
    public List<Expenses> findAllExpenses();
    
    public Expenses findOneExpensesById(Long id);
    
}
