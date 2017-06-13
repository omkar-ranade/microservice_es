package com.searchemployeeservice.amqp.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeSaveQueueListener implements MessageListener {

	public static void main(String[] args) {
		// Initialize Spring IOC Container
		new ClassPathXmlApplicationContext(
				"resources/amqp/listener/employee-save-queue-listener-context.xml");
	}

	public void onMessage(Message message) {}
}
