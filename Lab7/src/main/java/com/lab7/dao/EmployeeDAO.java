package com.lab7.dao;

import java.util.List;

import com.lab7.entity.Employee;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(String id);
    void create(Employee e);
    void update(Employee e);
    void deleteById(String id);
}
