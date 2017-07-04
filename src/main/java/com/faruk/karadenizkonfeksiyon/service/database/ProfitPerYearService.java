/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import com.faruk.karadenizkonfeksiyon.domain.ProfitPerYear;
import java.util.List;

/**
 *
 * @author Faruk
 */
public interface ProfitPerYearService {
    
    public List<ProfitPerYear> findAll();
    
    public ProfitPerYear save(ProfitPerYear profitPerYear);
    
    public ProfitPerYear findOne(Long id);
    
    public ProfitPerYear findOneWithEager(Long id);
    
    public ProfitPerYear findOneByValue(Long value);
    
}
