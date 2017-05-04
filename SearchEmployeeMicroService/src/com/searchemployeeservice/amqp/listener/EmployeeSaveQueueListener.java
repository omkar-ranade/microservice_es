package com.searchemployeeservice.amqp.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.searchemployeeservice.dataretriever.EmployeeDetailsRetriever;
import com.searchemployeeservice.service.ElasticSearchService;
import com.searchemployeeservice.service.IElasticSearchSaveService;

public class EmployeeSaveQueueListener implements MessageListener {

	public static void main(String[] args) {
		// Initialize Spring IOC Container
		new ClassPathXmlApplicationContext(
				"resources/amqp/listener/employee-save-queue-listener-context.xml");
	}

	public void onMessage(Message message) {/*

		String empId = new String(message.getBody());
		System.out.println("Employee ID received = " + empId);

		// Method to fetch employee details from Mongo DB based on the emp
		// id
		EmployeeDetailsRetriever retriever = EmployeeDetailsRetriever
				.initialiseConnectionAndReturnRetriever();

		String opStatus = "failure";
		try {
			opStatus = retriever.fetchEmployeeDetails(empId);
		} catch (IOException e) {
			opStatus = "failure";
			e.printStackTrace();
		}
		System.out.println("Status of getting employee details from Mongo DB: "
				+ opStatus);

		if (opStatus.equals("Success")) {
			// Method to save the employee details in Elastic Search
			IElasticSearchSaveService saveService = ElasticSearchService
					.getElasticSearchSaveService();

			String saveStatus = null;
			try {
				saveStatus = saveService.saveEmployeeDetails(retriever
						.getEmployee());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out
					.println("Status of saving employee details in elastic search: "
							+ saveStatus);
		}
	*/}
}
