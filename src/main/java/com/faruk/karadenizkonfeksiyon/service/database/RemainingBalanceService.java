/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import com.faruk.karadenizkonfeksiyon.domain.RemainingBalance;
import java.util.List;

/**
 *
 * @author Faruk
 */
public interface RemainingBalanceService {

    public RemainingBalance save(RemainingBalance remainingBalance);

    public RemainingBalance update(RemainingBalance remainingBalance);

    public void delete(RemainingBalance remainingBalance);

    public void delete(Long id);
    
    public List<RemainingBalance> findAllByCompanyId(Long companyId);
    
    public RemainingBalance findOneById(Long id);
    
    public List<RemainingBalance> findAll();

}
