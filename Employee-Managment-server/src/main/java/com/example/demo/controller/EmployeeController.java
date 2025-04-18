package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.serviceImpl.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return new ResponseEntity<>(employeeServiceImpl.getById(id), HttpStatus.FOUND);
	}

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee() {
		return new ResponseEntity<List<Employee>>(employeeServiceImpl.getAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		// TODO: process POST request

		return new ResponseEntity<Employee>(employeeServiceImpl.SaveEmployee(employee), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee entity) {
		// TODO: process PUT request

		return new ResponseEntity<Employee>(employeeServiceImpl.updateEmployeeById(id, entity), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteEmployee(@PathVariable Long id) {
		return ResponseEntity.ok(employeeServiceImpl.deleteEmployeeById(id));
	}

}
