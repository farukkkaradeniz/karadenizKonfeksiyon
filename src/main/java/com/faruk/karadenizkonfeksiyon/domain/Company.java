package com.faruk.karadenizkonfeksiyon.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Faruk
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "jsonSerializationCompanyId")
public class Company implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String companyName;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "company")
    @Basic(fetch = FetchType.LAZY)
    private List<Invoice> invoices = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "company")
    private Set<RemainingBalance> remainingBalances = new HashSet<>();

    public Company() {
    }

    public Company(String companyName) {
        this.companyName = companyName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<RemainingBalance> getRemainingBalances() {
        return remainingBalances;
    }

    public void setRemainingBalances(Set<RemainingBalance> remainingBalances) {
        this.remainingBalances = remainingBalances;
    }
    
}
