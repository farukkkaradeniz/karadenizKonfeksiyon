/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.repository;

import com.faruk.karadenizkonfeksiyon.domain.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Faruk
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    public Employee findOneByEnabled(Long id, Boolean enabled);

    public List<Employee> findAllByEnabled(Boolean enabled);
    
}
