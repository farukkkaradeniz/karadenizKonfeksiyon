/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.builder;

import com.faruk.karadenizkonfeksiyon.domain.Expenses;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.persistence.Column;

/**
 *
 * @author Faruk
 */
public class ExpenseBuilder {
    
    public String expenseType;
    
    public String description;
    
    public BigDecimal price;
    
    public ZonedDateTime expenseDate;
    
    public ExpenseBuilder expenseType(String expenseType){
    
        this.expenseType = expenseType;
        return this;
    }
    
    public ExpenseBuilder description(String description){
    
        this.description = description;
        return this;
    }
    
    public ExpenseBuilder price(BigDecimal price){
    
        this.price = price;
        return this;
        
    }
    
    public ExpenseBuilder price(double price){
    
        this.price = BigDecimal.valueOf(price);
        return this;
        
    }
    
    public ExpenseBuilder expenseDate(ZonedDateTime expenseDate){
    
        this.expenseDate = expenseDate;
        return this;
    }
    
    public Expenses build(){
    
        return new Expenses(this);
        
    }
    
}
