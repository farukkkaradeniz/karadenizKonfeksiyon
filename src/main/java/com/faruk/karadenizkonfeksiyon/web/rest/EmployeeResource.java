/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faruk.karadenizkonfeksiyon.web.rest;

import com.faruk.karadenizkonfeksiyon.domain.Employee;
import com.faruk.karadenizkonfeksiyon.service.EmployeeService;
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
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employee")
    public List<Employee> getAll() {

        return employeeService.findAll();

    }

    @GetMapping("employee/{id}")
    public Employee getOneById(@PathVariable Long id) {

        return employeeService.findOneById(id);

    }
    
    @PostMapping("employee")
    public Employee crateEmployee(@RequestBody Employee employee){
        
        return employeeService.save(employee);
    }
    
    @PutMapping("employee")
    public Employee updateEmployee(@RequestBody Employee employee){
        
        return employeeService.update(employee);
    }
    
    @DeleteMapping("employee")
    public void deleteEmployee(@RequestBody Employee employee){
    
        employeeService.delete(employee);
    
    }
    
    @DeleteMapping("employee/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
    
        employeeService.delete(id);
        
    }
    

}
