package com.searchemployeeservice.dataretriever;

import java.io.IOException;
import java.net.MalformedURLException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.searchemployeeservice.bean.Employee;
import com.searchemployeeservice.util.ElasticSearchUtil;
import org.apache.log4j.Logger;

public class EmployeeDetailsRetriever {
	final Logger log = Logger.getLogger(EmployeeDetailsRetriever.class);
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	private static IEmployeeDetailsRetriever retriever;

	public String fetchEmployeeDetails(String empId) throws JsonParseException,
			JsonMappingException, IOException {

		try {
			String emp = retriever.getEmployee(empId);
			//employee = ElasticSearchUtil.convertJSonDataToEmployees(emp);

		} catch (Exception e) {
			log.error(e);
			return "failure";
		}
		return "Success";
	}
}
