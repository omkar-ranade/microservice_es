package com.searchemployeeservice.service;

import com.searchemployeeservice.bean.Employee;

public interface IElasticSearchSaveService {

	/**
	 * Method to save the employee details in Elastic Search, the employee
	 * details are retrieved from Mongo DB. This method will return the employee
	 * id on successful save operation.
	 * 
	 * @param employee
	 *            {@link Employee} details retrieved from Mongo DB
	 * @return {@link String} employee id after saving the details in
	 *         ElasticSearch
	 * @throws Exception
	 */
	void saveEmployeeDetails(Employee employee) throws Exception;
}
