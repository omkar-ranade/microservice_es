package com.searchemployeeservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.searchemployeeservice.bean.Employee;
import com.searchemployeeservice.util.ElasticSearchUtil;

@Service
public class ElasticSearchService implements IElasticSearchService,
		IElasticSearchSaveService {

	private static IElasticSearchService service;

	private static IElasticSearchSaveService saveService;

	/**
	 * Private Constructor.
	 */
	private ElasticSearchService() {

	}

	/**
	 * Method to get static {@link IElasticSearchService} instance.
	 * 
	 * @return {@link IElasticSearchService}
	 */
	public static IElasticSearchService getElasticSearchService() {
		service = new ElasticSearchService();
		return service;
	}

	/**
	 * Method to get static {@link IElasticSearchSaveService} instance.
	 * 
	 * @return {@link IElasticSearchSaveService}
	 */
	public static IElasticSearchSaveService getElasticSearchSaveService() {
		saveService = new ElasticSearchService();
		return saveService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * elasticsearch.service.IElasticSearchService#searchEmployee(java.lang.
	 * String)
	 */
	public List<Employee> searchEmployee(String criteria) throws Exception {
		List<Employee> empList;
		empList = ElasticSearchUtil.searchEmployee(criteria);
		return empList;
	}

	/* (non-Javadoc)
	 * @see elasticsearch.service.IElasticSearchSaveService#saveEmployeeDetails(bean.Employee)
	 */
	public void saveEmployeeDetails(Employee employee) throws Exception {

		ElasticSearchUtil.saveEmployee(employee);
	}

}
