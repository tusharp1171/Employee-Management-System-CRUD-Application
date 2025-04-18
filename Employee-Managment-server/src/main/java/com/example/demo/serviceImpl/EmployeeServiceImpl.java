package com.example.demo.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl {

	@Autowired
	private EmployeeRepo employeeRepo;

	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	public Employee getById(Long id) {
		return employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	}

	public Employee updateEmployeeById(Long id, Employee updatedEmployee) {
		return employeeRepo.findById(id).map(employee -> {
			// Update the employee's details
			employee.setName(updatedEmployee.getName());
			employee.setAge(updatedEmployee.getAge());
			employee.setSalary(updatedEmployee.getSalary());
			employee.setAddress(updatedEmployee.getAddress());
			employee.setDepartment(updatedEmployee.getDepartment());
			employee.setDesignation(updatedEmployee.getDesignation());
			employee.setEmail(updatedEmployee.getEmail());
			employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
			employee.setJoiningDate(updatedEmployee.getJoiningDate());

			// Save the updated employee
			return employeeRepo.save(employee);
		}).orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	}

	public Map<String, Object> deleteEmployeeById(Long id) {
		return employeeRepo.findById(id).map(employee -> {
			employeeRepo.delete(employee);
			// Success response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Employee deleted successfully");
			response.put("id", id);
			return response;
		}).orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	}

	public Employee SaveEmployee(Employee emp) {
		return employeeRepo.save(emp);
	}
}
