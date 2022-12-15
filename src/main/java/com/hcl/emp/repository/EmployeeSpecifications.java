package com.hcl.emp.repository;

import com.hcl.emp.entity.Employee;

import com.hcl.emp.entity.Employee_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSpecifications {

	public static Specification<Employee> findByLastName(String lastName) {
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get(Employee_.LAST_NAME), "%" + lastName + "%");
		});
	}

	public static Specification<Employee> findByGender(String gender) {
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Employee_.GENDER), gender);
		});
	}

}
