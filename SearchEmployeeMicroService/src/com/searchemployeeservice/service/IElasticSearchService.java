package com.searchemployeeservice.service;

import java.util.List;

import com.searchemployeeservice.bean.Employee;


public interface IElasticSearchService {

	/**
	 * Method to search employee based on the search criteria given from UI.
	 * This method will return the details in JSON format.
	 * 
	 * @param criteria
	 *            String criteria entered at UI
	 * @return JSON format String employee search results
	 * @throws Exception 
	 */
	public List<Employee> searchEmployee(String criteria) throws Exception;

}
