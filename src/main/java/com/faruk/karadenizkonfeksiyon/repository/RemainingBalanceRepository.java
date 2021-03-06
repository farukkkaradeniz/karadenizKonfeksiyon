/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.repository;

import com.faruk.karadenizkonfeksiyon.domain.Company;
import com.faruk.karadenizkonfeksiyon.domain.RemainingBalance;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Faruk
 */
public interface RemainingBalanceRepository extends JpaRepository<RemainingBalance, Long> {

    public List<RemainingBalance> findAllByCompany(Company company);
    
}
