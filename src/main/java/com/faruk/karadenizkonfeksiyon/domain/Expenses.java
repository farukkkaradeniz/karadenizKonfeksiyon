/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.domain;

import com.faruk.karadenizkonfeksiyon.builder.ExpenseBuilder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Faruk
 */
@Entity
public class Expenses implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "expense_type")
    private String expenseType;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private BigDecimal price;
    
    private ZonedDateTime expenseDate;
    
    public Expenses(){
    }

    public Expenses(ExpenseBuilder expenseBuilder) {
        this.description = expenseBuilder.description;
        this.expenseDate = expenseBuilder.expenseDate;
        this.price = expenseBuilder.price;
        this.expenseType = expenseBuilder.expenseType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZonedDateTime getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(ZonedDateTime expenseDate) {
        this.expenseDate = expenseDate;
    }
    
}
