/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.service.database;

import com.faruk.karadenizkonfeksiyon.domain.Employee;
import java.util.List;

/**
 *
 * @author Faruk
 */
public interface EmployeeService {
    
    public Employee save(Employee employee);
    
    public Employee update(Employee employee);
    
    public void delete(Employee employee);
    
    public void delete(Long id);
    
    public List<Employee> findAll();
    
    public Employee findOneById(Long id);
    
}
