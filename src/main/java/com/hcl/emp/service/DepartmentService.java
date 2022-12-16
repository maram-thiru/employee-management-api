package com.hcl.emp.service;

import com.hcl.emp.entity.Department;
import com.hcl.emp.exception.DepartmentNotFoundException;
import com.hcl.emp.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

	private final DepartmentRepository departmentRepository;

	public Department saveDepartment(Department department) {
		log.info(getClass()+ "::saveDepartment");
		return departmentRepository.save(department);
	}
	public List<Department> getAllDepartments() {
		log.info(getClass()+ "::getAllDepartments");
		return departmentRepository.findAll();
	}
	public void deleteById(Long deptId) {
		departmentRepository.deleteById(deptId);
	}
	public Optional<Department> findDepartmentById(Long deptId) {
		return Optional
				.ofNullable(departmentRepository.findById(deptId)
						.orElseThrow(() -> new DepartmentNotFoundException("No Department found - " + deptId)));
	}
}
