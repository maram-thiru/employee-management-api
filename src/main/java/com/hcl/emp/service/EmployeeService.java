package com.hcl.emp.service;

import com.hcl.emp.entity.Employee;
import com.hcl.emp.repository.EmployeeRepository;
import com.hcl.emp.repository.EmployeeSpecifications;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		log.info(getClass()+ "::saveEmployee");
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		log.info(getClass()+ "::getAllEmployees");
		return employeeRepository.findAll();
	}

	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> getEmployeeById(Long empId) {
		return employeeRepository.findById(empId);
	}

	public void deleteById(Long empId) {
		employeeRepository.deleteById(empId);
	}

	public List<Employee> searchEmployee(String key) {
		Specification<Employee> specification = null;
		if (key.length() == 1) {
			specification = Specification
					.where(EmployeeSpecifications.findByGender(key));
			return employeeRepository.findAll(specification);
		} else {
			specification = Specification
					.where(EmployeeSpecifications.findByLastName(key));
			return employeeRepository.findAll(specification);
		}
	}
}
