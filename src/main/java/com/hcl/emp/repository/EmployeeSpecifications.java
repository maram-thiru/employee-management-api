package com.hcl.emp.repository;

import com.hcl.emp.entity.Employee;

import com.hcl.emp.entity.Employee_;
import com.hcl.emp.entity.Gender;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeSpecifications {

	public static Specification<Employee> findByLastName(String lastName) {
		log.info("Searching for employees based on lastName :: " + lastName);
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get(Employee_.LAST_NAME), "%" + lastName + "%");
		});
	}

	public static Specification<Employee> findByGender(String gender) {
		log.info("Searching for employees based on gender of type :: " + gender);
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Employee_.GENDER), Gender.fromValue(gender));
		});
	}

	public static Specification<Employee> findByBirthDate(String birthDate) {
		log.info("Searching for employees based on birth date :: " + birthDate);
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Employee_.BIRTH_DATE), LocalDate.parse(birthDate));
		});
	}
}
