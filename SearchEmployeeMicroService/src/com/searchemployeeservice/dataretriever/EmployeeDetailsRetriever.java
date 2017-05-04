package com.searchemployeeservice.dataretriever;

import java.io.IOException;
import java.net.MalformedURLException;

//import com.caucho.hessian.client.HessianProxyFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.searchemployeeservice.bean.Employee;
import com.searchemployeeservice.util.ElasticSearchUtil;

public class EmployeeDetailsRetriever {

	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	private static IEmployeeDetailsRetriever retriever;

	/**
	 * Method to perform initial configuration and create the private instance
	 * to call remote method. The method then initializes the
	 * {@link EmployeeDetailsRetriever} and returns it.
	 * 
	 * @return {@link EmployeeDetailsRetriever}
	 */
	/*	public static EmployeeDetailsRetriever initialiseConnectionAndReturnRetriever() {
		String url = "http://192.168.62.148:8890/SpringRemoting/EmployeeService";
		HessianProxyFactory factory = new HessianProxyFactory();

		if (retriever == null) {
			try {
				retriever = (IEmployeeDetailsRetriever) factory.create(
						IEmployeeDetailsRetriever.class, url);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
		}
		EmployeeDetailsRetriever retriever = new EmployeeDetailsRetriever();

		return retriever;
	}*/

	public String fetchEmployeeDetails(String empId) throws JsonParseException,
			JsonMappingException, IOException {

		try {
			String emp = retriever.getEmployee(empId);
			//employee = ElasticSearchUtil.convertJSonDataToEmployees(emp);

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "Success";
	}
}
