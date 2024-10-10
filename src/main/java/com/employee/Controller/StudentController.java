package com.employee.Controller;
import java.util.List;
import java.util.Optional;

import com.employee.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.employee.Entities.Employee;

@RestController
public class StudentController {
	
	@Autowired
	EmployeeRepository emp;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees()
	{
		List<Employee> employees = emp.findAll();
		return employees;
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable int id)
	{
		Employee employee = emp.findById(id).get();
		return employee;
	}
	 
	@PostMapping("/employees/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addEmployee(@RequestBody Employee employee)
	{
		emp.save(employee);
	}
	
	@PutMapping("/employees/update/{id}")
	public String updateEmployeeById(@PathVariable int id,@RequestBody Employee employee)
	{
		Optional<Employee> emp1 = emp.findById(id);
		if(emp1.isPresent())
		{
			Employee getemp = emp1.get();	
			getemp.setName(employee.getName());
			getemp.setProfile(employee.getProfile());
			getemp.setSalary(employee.getSalary());
			emp.save(getemp);
			return "Employee has Been Updated with Id : "+id;
		}
		else
		{
			return "Employee details not found with Id : "+id;
		}
		
	}
	
	@DeleteMapping("employees/delete/{id}")
	public String deleteEmployeeById(@PathVariable int id)
	{
		Optional<Employee> emp1 = emp.findById(id);
		if(emp1.isPresent())
		{
			Employee employee  = emp1.get();
			emp.deleteById(employee.getId());
			return "Employee has Been Deleted with Id : "+id;
		}
		else
		{
			return "Employee details not found with Id : "+id;
		}
	}
	
	@DeleteMapping("/employees/delete")
	public String deleteAllEmployees()
	{
		emp.deleteAll();
		return "All Emplyees has been Deleted";
	}
	
}
