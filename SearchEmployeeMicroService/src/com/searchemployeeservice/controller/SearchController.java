package com.searchemployeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searchemployeeservice.bean.Employee;
import com.searchemployeeservice.service.IElasticSearchSaveService;
import com.searchemployeeservice.service.IElasticSearchService;

@RestController
public class SearchController {

	@Autowired
	IElasticSearchService elasticSearchService;
	
	@Autowired
	IElasticSearchSaveService elasticSearchSaveService; 

	@RequestMapping(value = "/searchEmployee/{searchString}", method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getEmployee(@PathVariable("searchString") String searchString) {
		System.out.println("URI parameter :" + searchString);
		List<Employee> empLst = null;
		try {
			empLst = elasticSearchService.searchEmployee(searchString);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Error for EMPLOYEE SEARCH" + e1);
		}

		if (empLst != null && !empLst.isEmpty()) {
			for (Employee e : empLst) {
				System.out.println("Employee Details ---- " + e.toString());
			}
		}

		return empLst;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.PUT)
	public void saveEmployee(@RequestBody Employee employee) {
		System.out.println("Employee id " + employee.getEmpId());
		try {
			elasticSearchSaveService.saveEmployeeDetails(employee);
		} catch (Exception e1) {
			System.out.println("Error for EMPLOYEE SEARCH" + e1);
			throw new RuntimeException(e1);
		}
		System.out.println("Employee saved.");

	}
}
