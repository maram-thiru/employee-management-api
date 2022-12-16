package com.hcl.emp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.emp.entity.Employee;
import com.hcl.emp.entity.Gender;
import com.hcl.emp.service.EmployeeService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

	MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(employeeService)).build();
	}

	@Test
	void test_get_all_employees() throws Exception {

		List<Employee> employees = Stream.of(
				new Employee(1L, "Thirupathi", "Maram", LocalDate.of(1986, 8, 10), LocalDate.of(2022, 3, 2), Gender.MALE), new Employee(2L, "Shruthi", "Maram", LocalDate.of(1990, 1, 8), LocalDate.of(2022, 3, 2), Gender.FEMALE)).collect(Collectors.toList());

		when(employeeService.getAllEmployees()).thenReturn(employees);

		mockMvc.perform(get("/employees"))
				.andExpect(jsonPath("$[0].lastName").isNotEmpty())
				.andExpect(jsonPath("$[0].firstName").value("Thirupathi"))
				.andExpect(jsonPath("$[0].gender").value("M"));

	}


	@Test
	void test_save_employee_with_valid_data() throws Exception {
		Employee employee = new Employee(100L,"Thirupathi", "Maram",
				LocalDate.of(1986,8,10),
				LocalDate.of(2022,3,2),
				Gender.MALE);

		mockMvc.perform(post("/employees")
						.contentType("application/json")
						.param("sendWelcomeMail", "true")
						.content(pojoToJSON(employee)))
				.andExpect(status().isCreated());
	}

	@Test
	void updateEmployee() {
	}

	@Test
	void deleteEmployee() {
	}

	@Test
	void test_search_employee_by_lastname() throws Exception {
		String searchKey = "Maram";

		List<Employee> employees = Stream.of(
				new Employee(1L, "Thirupathi", "Maram", LocalDate.of(1986, 8, 10), LocalDate.of(2022, 3, 2), Gender.MALE), new Employee(2L, "Shruthi", "Maram", LocalDate.of(1990, 1, 8), LocalDate.of(2022, 3, 2), Gender.FEMALE)).collect(Collectors.toList());

		when(employeeService.searchEmployee(searchKey)).thenReturn(employees);

		mockMvc.perform(get("/employees/searchBy/{lastName}",searchKey))
				.andExpect(jsonPath("$[0].lastName").value("Maram"))
				.andExpect(jsonPath("$[0].gender").value("M"));
	}


	public static String pojoToJSON(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}