package com.searchemployeeservice.service;

import java.util.List;

import com.searchemployeeservice.bean.Customer;


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
	public List<Customer> searchEmployee(String criteria) throws Exception;

}
