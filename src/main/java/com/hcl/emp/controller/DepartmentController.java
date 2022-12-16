package com.hcl.emp.controller;

import com.hcl.emp.entity.Department;
import com.hcl.emp.service.DepartmentService;
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
@RequestMapping("/departments")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

	private DepartmentService departmentService;

	@GetMapping
	public List<Department> getAllDepartments(){
		log.info(getClass()+ "::getAllDepartments");
		return departmentService.getAllDepartments();
	}

	@PostMapping
	public ResponseEntity<Department> saveDepartment(@RequestBody Department Department) {
		log.info(getClass()+ "::saveDepartment");
		return new ResponseEntity<>(departmentService.saveDepartment(Department), HttpStatus.CREATED);
	}


	@PutMapping("/{deptId}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long deptId,
												   @RequestBody Department department) {
		log.info(getClass()+ "::updateDepartment");
		Optional<Department>  existingDepartment= departmentService.findDepartmentById(deptId);
		if(existingDepartment.isPresent()) {
			Department dbDepartment = existingDepartment.get();
			dbDepartment.setDeptName(department.getDeptName());
			return new ResponseEntity<>(departmentService.saveDepartment(dbDepartment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{empId}")
	public ResponseEntity<Department> deleteDepartment(@PathVariable Long empId) {
		try {
			log.info("Deleting Department by id :: " + empId);
			departmentService.deleteById(empId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	


}
