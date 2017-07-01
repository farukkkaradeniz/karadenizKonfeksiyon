/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.RemainingBalance;
import com.faruk.karadenizkonfeksiyon.service.RemainingBalanceService;
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
public class RemainingBalanceResource {

    @Autowired
    private RemainingBalanceService remainingBalanceService;

    @GetMapping("/balance/all/{companyId}")
    public List<RemainingBalance> getAllByCompanyId(@PathVariable Long companyId) {

        return remainingBalanceService.findAllByCompanyId(companyId);

    }
    
    @GetMapping("/balance")
    public List<RemainingBalance> getAll(){
        
        return remainingBalanceService.findAll();
        
    }

    @GetMapping("/balance/one/{id}")
    public RemainingBalance getOneById(@PathVariable Long id)
    {
        return remainingBalanceService.findOneById(id);
    }
    
    @PostMapping("/balance")
    public RemainingBalance createRemainingBalance(@RequestBody RemainingBalance remainingBalance){
    
        return remainingBalanceService.save(remainingBalance);
        
    }
    
    @PutMapping("/balance")
    public RemainingBalance updateRemainingBalance(@RequestBody RemainingBalance remainingBalance){
    
        return remainingBalanceService.update(remainingBalance);
        
    }
    
    @DeleteMapping("/balance")
    public void delete(@RequestBody RemainingBalance remainingBalance){
    
         remainingBalanceService.delete(remainingBalance);
        
    }
    
    @DeleteMapping("/balance/{id}")
    public void deleteById(@PathVariable Long id){
    
        remainingBalanceService.delete(id);
        
    }
    

}
