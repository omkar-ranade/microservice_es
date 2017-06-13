package com.searchemployeeservice.util;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.indices.IndicesExists;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.searchemployeeservice.bean.Customer;

public class ElasticSearchUtil {

	private static final String INDEX = "employees";

	private static JestClient client = null;
	
	static final Logger log = Logger.getLogger(ElasticSearchUtil.class);
	static boolean isInfo = log.isInfoEnabled();

	private static JestClient getClient() throws Exception {
		if(isInfo)
			log.info(IApplicationConstant.METHOD_STARTED);
		if (client == null) {

			JestClientFactory factory = new JestClientFactory();
			factory.setHttpClientConfig(new HttpClientConfig.Builder(
					"https://search-employee-uvtglb6ouhsrjk3spdscwjt7ha.us-west-2.es.amazonaws.com")
					.multiThreaded(true).build());
			JestClient jestClient = factory.getObject();

			boolean indexExists = jestClient.execute(
					new IndicesExists.Builder(IApplicationConstant.ELASTIC_SEARCH_INDEX).build()).isSucceeded();
			
			log.info("IndexExist : "+indexExists);
			
			if (!indexExists) {
				log.error("Index " + INDEX + " not found in elasticsearch.");	
				throw new Exception("Index " + INDEX
						+ " not found in elasticsearch.");
			}
			client = jestClient;
		}
		if(isInfo)
			log.info(IApplicationConstant.METHOD_EXIT);
		
		return client;
	}

	/**
	 * Util method to search the employee in elastic search based on the
	 * employee id given as parameter.
	 * 
	 * @param criteria
	 *            Employee id as search criteria
	 * @return {@link Customer} data as JSON Format
	 * @throws Exception
	 */
	public static List<Customer> searchEmployee(String criteria)
			throws Exception {
		if(isInfo)
			log.info(IApplicationConstant.METHOD_STARTED);
		if (client == null) {
			client = getClient();
		}

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		Map<String, Customer> employeeMap = new HashMap<>();

		// emp id search
		searchSourceBuilder.query(QueryBuilders.wildcardQuery("empId", criteria));
		employeeMap.putAll(getSearchResults(searchSourceBuilder));

		// emp first name search
		searchSourceBuilder.query(QueryBuilders.wildcardQuery("empFirstName",
				"*" + criteria + "*"));
		employeeMap.putAll(getSearchResults(searchSourceBuilder));

		// emp last name search
		searchSourceBuilder.query(QueryBuilders.wildcardQuery("empLastName",
				"*" + criteria + "*"));
		employeeMap.putAll(getSearchResults(searchSourceBuilder));

		// email id search
		searchSourceBuilder.query(QueryBuilders.wildcardQuery("emailId", "*"
				+ criteria + "*"));
		employeeMap.putAll(getSearchResults(searchSourceBuilder));

		List<Customer> list = new ArrayList<Customer>(employeeMap.values());
		
		if(isInfo)
			log.info(IApplicationConstant.METHOD_EXIT);
		
		return list;
	}

	private static Map<String, Customer> getSearchResults(
			SearchSourceBuilder searchSourceBuilder) throws IOException,
			ParseException {

		if(isInfo)
			log.info(IApplicationConstant.METHOD_STARTED);
		
		Search search = new Search.Builder(searchSourceBuilder.toString())
				.addIndex(IApplicationConstant.ELASTIC_SEARCH_INDEX).addType("employee").build();

		SearchResult result = client.execute(search);

		Map<String, Customer> employeeMap = new HashMap<>();
		for (Hit<Customer, Void> hit : result.getHits(Customer.class)) {
			Customer emp = new Customer();

			emp.setEmpId(hit.source.getEmpId());
			emp.setEmpFirstName(hit.source.getEmpFirstName());
			emp.setEmpLastName(hit.source.getEmpLastName());
			emp.setEmailID(hit.source.getEmailId());
			emp.setPhoneNo(hit.source.getPhoneNo());

			employeeMap.put(emp.getEmpId(), emp);
		}

		if(isInfo)
			log.info(IApplicationConstant.METHOD_EXIT);
		return employeeMap;
	}

	/**
	 * Method to save the employee data into Elastic Search database
	 * 
	 * @param employee
	 *            {@link Customer} object data
	 * @return status of the operation as success/failure
	 * @throws Exception
	 */
	public static void saveEmployee(Customer employee) throws Exception {
		if(isInfo)
			log.info(IApplicationConstant.METHOD_STARTED);
		
		if (client == null) {
			client = getClient();
		}

		Map<String, Object> source = createJsonDocument(employee);

		Index index = new Index.Builder(source).index(IApplicationConstant.ELASTIC_SEARCH_INDEX)
				.type("employee").build();
		DocumentResult result = client.execute(index);

		log.info("Response code -"+ result.getResponseCode());
		
		if(isInfo)
			log.info(IApplicationConstant.METHOD_EXIT);

	}

	/**
	 * Method to create {@link Map} of JSON fields and values for the
	 * {@link Customer} object
	 * 
	 * @param emp
	 *            {@link Customer} data
	 * @return {@link Map} of JSON fields and values
	 */
	public static Map<String, Object> createJsonDocument(Customer emp) {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		jsonDocument.put("empId", emp.getEmpId());
		jsonDocument.put("empFirstName", emp.getEmpFirstName());
		jsonDocument.put("empLastName", emp.getEmpLastName());
		jsonDocument.put("emailId", emp.getEmailId());
		jsonDocument.put("phoneNo", emp.getPhoneNo());
		return jsonDocument;
	}
}
