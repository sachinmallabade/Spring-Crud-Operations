package com.employee.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.Entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
