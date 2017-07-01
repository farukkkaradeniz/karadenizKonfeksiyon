/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service;

import com.faruk.karadenizkonfeksiyon.domain.Expenses;
import com.faruk.karadenizkonfeksiyon.repository.ExpensesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    
    @Autowired
    private ExpensesRepository expensesRepository;

    @Override
    public Expenses save(Expenses expenses) {
        return expensesRepository.save(expenses);
    }

    @Override
    public Expenses update(Expenses expenses) {
        return expensesRepository.save(expenses);
    }

    @Override
    public void delete(Expenses expenses) {
        expensesRepository.delete(expenses);
    }

    @Override
    public void delete(Long id) {
        expensesRepository.delete(id);
    }

    @Override
    public List<Expenses> findAllExpenses() {
        return expensesRepository.findAll();
    }

    @Override
    public Expenses findOneExpensesById(Long id) {
        return expensesRepository.findOne(id);
    }
    
}
