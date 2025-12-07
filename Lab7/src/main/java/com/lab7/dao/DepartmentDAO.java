package com.lab7.dao;

import java.util.List;

import com.lab7.entity.Department;

public interface DepartmentDAO {
    List<Department> findAll();
    Department findById(String id);
    List<Department> findByKeyword(String keyword); // nâng cao: search
    void create(Department item);
    void update(Department item);
    void deleteById(String id);
}
