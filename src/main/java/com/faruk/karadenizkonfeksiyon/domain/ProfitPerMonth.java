/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Month;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Faruk-pc
 */
@Entity
public class ProfitPerMonth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Month month;

    @NotNull
    @Column(nullable = false)
    private BigDecimal unitSpendings;

    @NotNull
    @Column(nullable = false)
    private BigDecimal unitEarnings;

    @ManyToOne
    private ProfitPerYear profitPerYear;

    public ProfitPerMonth() {
    }

    public ProfitPerMonth(Month month, BigDecimal unitSpendings, BigDecimal unitEarnings,ProfitPerYear profitPerYear) {
        this.month = month;
        this.unitSpendings = unitSpendings;
        this.unitEarnings = unitEarnings;
        this.profitPerYear = profitPerYear;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public BigDecimal getUnitSpendings() {
        return unitSpendings;
    }

    public void setUnitSpendings(BigDecimal unitSpendings) {
        this.unitSpendings = unitSpendings;
    }

    public BigDecimal getUnitEarnings() {
        return unitEarnings;
    }

    public void setUnitEarnings(BigDecimal unitEarnings) {
        this.unitEarnings = unitEarnings;
    }

    public ProfitPerYear getProfitPerYear() {
        return profitPerYear;
    }

    public void setProfitPerYear(ProfitPerYear profitPerYear) {
        this.profitPerYear = profitPerYear;
    }

}
