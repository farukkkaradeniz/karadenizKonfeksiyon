/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.Expenses;
import com.faruk.karadenizkonfeksiyon.service.ExpensesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faruk
 */
@CrossOrigin(origins = "*")
@RestController
public class ExpensesResource {
    
    @Autowired
    private ExpensesService expensesService;
    
    @GetMapping("/expenses")
    public List<Expenses> getAll(){
    
        return expensesService.findAllExpenses();
        
    }
    
    @GetMapping("/expenses/{id}")
    public Expenses getOneById(@PathVariable Long id){
    
        return expensesService.findOneExpensesById(id);
        
    }
    
    @PostMapping("/expenses")
    public Expenses createExpenses(@RequestBody Expenses expenses){
    
        return expensesService.save(expenses);
        
    }
    
    @PutMapping("/expenses/")
    public Expenses updateExpenses(@RequestBody Expenses expenses){
    
        return expensesService.update(expenses);
        
    }
    
    @DeleteMapping("/expenses")
    public void delete(@RequestBody Expenses expenses){
    
        expensesService.delete(expenses);
        
    }
    
    @DeleteMapping("/expenses/{id}")
    public void delete(@PathVariable Long id){
    
        expensesService.delete(id);
        
    }
    
}
