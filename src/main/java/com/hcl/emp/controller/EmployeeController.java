package com.hcl.emp.controller;

import com.hcl.emp.entity.Employee;
import com.hcl.emp.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping
	public List<Employee> getAllEmployees(){
		log.info(getClass()+ "::getAllEmployees");
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{empId}")
	public Optional<Employee> findEmployeeById(@PathVariable Long empId){
		log.info(getClass()+ "::findEmployeeById");
		return employeeService.findEmployeeById(empId);
	}

	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		log.info(getClass()+ "::saveEmployee");
		return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
	}


	@PutMapping("/{empId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId,
												   @RequestBody Employee employee) {
		log.info(getClass()+ "::updateEmployee");
		Optional<Employee>  existingEmployee= employeeService.getEmployeeById(empId);
		if(existingEmployee.isPresent()) {
			Employee dbEmployee = existingEmployee.get();
			dbEmployee.setFirstName(employee.getFirstName());
			dbEmployee.setLastName(employee.getLastName());
			dbEmployee.setGender(employee.getGender());
			dbEmployee.setBirthDate(employee.getBirthDate());
			dbEmployee.setHireDate(employee.getHireDate());
			return new ResponseEntity<>(employeeService.saveEmployee(dbEmployee), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{empId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable Long empId) {
		try {
			log.info("Deleting employee by id :: " + empId);
			employeeService.deleteById(empId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/searchBy/{key}")
	public List<Employee> searchBy(@PathVariable("key") String key) {
		log.info("Searching started ");
		return employeeService.searchEmployee(key);
	}

}
