/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import com.faruk.karadenizkonfeksiyon.service.database.CompanyService;
import com.faruk.karadenizkonfeksiyon.domain.RemainingBalance;
import com.faruk.karadenizkonfeksiyon.repository.RemainingBalanceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemainingBalanceServiceImpl implements RemainingBalanceService {

    @Autowired
    private RemainingBalanceRepository remainingBalanceRepository;
    
    @Autowired
    private CompanyService companyService;

    @Override
    public RemainingBalance save(RemainingBalance remainingBalance) {
        return remainingBalanceRepository.save(remainingBalance);
    }

    @Override
    public RemainingBalance update(RemainingBalance remainingBalance) {
        return remainingBalanceRepository.save(remainingBalance);
    }

    @Override
    public void delete(RemainingBalance remainingBalance) {
        remainingBalanceRepository.delete(remainingBalance);
    }

    @Override
    public void delete(Long id) {
        remainingBalanceRepository.delete(id);
    }

    @Override
    public List<RemainingBalance> findAllByCompanyId(Long companyId) {
        return remainingBalanceRepository.findAllByCompany(companyService.findOneById(companyId));
    }

    @Override
    public RemainingBalance findOneById(Long id) {
        return remainingBalanceRepository.findOne(id);
    }

    @Override
    public List<RemainingBalance> findAll() {
        return remainingBalanceRepository.findAll();
    }

}
