package com.searchemployeeservice.service;

import com.searchemployeeservice.bean.Customer;


public interface IElasticSearchSaveService {

	/**
	 * Method to save the employee details in Elastic Search, the employee
	 * details are retrieved from Mongo DB. This method will return the employee
	 * id on successful save operation.
	 * 
	 * @param employee
	 *            {@link Customer} details retrieved from Mongo DB
	 * @return {@link String} employee id after saving the details in
	 *         ElasticSearch
	 * @throws Exception
	 */
	void saveEmployeeDetails(Customer employee) throws Exception;
}
