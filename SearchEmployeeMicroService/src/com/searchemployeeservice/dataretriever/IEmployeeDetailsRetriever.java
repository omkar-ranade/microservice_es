package com.searchemployeeservice.dataretriever;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IEmployeeDetailsRetriever {

	/**
	 * Method to retrieve the employee details from Mongo DB based on the
	 * employee id received from message bus.
	 * 
	 * @param empID
	 *            Employee ID read from message bus
	 * @return Status of the operation, "Success"/"failure"
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public String getEmployee(String empID) throws JsonParseException,
			JsonMappingException, IOException;
}
