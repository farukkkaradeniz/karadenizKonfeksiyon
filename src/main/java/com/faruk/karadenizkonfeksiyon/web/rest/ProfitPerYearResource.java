/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.ProfitPerYear;
import com.faruk.karadenizkonfeksiyon.service.database.ProfitPerYearService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Faruk
 */
@RestController
public class ProfitPerYearResource {
    
    @Autowired
    private ProfitPerYearService profitPerYearService;
    
    @GetMapping("/profitperyear")
    public ResponseEntity<List<ProfitPerYear>> findAllProfitsPerYear(){
    
        return ResponseEntity.ok().body(profitPerYearService.findAll());
        
    }
    
    @GetMapping("/profitperyear/{id}")
    public ResponseEntity<ProfitPerYear> findOneProfitPerYear(@PathVariable Long id){
    
        return ResponseEntity.ok().body(profitPerYearService.findOne(id));
        
    }
    
    @GetMapping("/profitperyear/eager/{id}")
    public ResponseEntity<ProfitPerYear> findOneWithEagerProfit(@PathVariable Long id){
    
        return ResponseEntity.ok().body(profitPerYearService.findOneWithEager(id));
        
    }
    
}
