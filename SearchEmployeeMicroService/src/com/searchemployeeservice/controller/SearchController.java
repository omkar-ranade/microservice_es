package com.searchemployeeservice.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.searchemployeeservice.bean.Customer;
import com.searchemployeeservice.service.IElasticSearchSaveService;
import com.searchemployeeservice.service.IElasticSearchService;
import com.searchemployeeservice.util.IApplicationConstant;

@RestController
public class SearchController {

	final Logger log = Logger.getLogger(SearchController.class);
	boolean isInfo = log.isInfoEnabled();
	
	@Autowired
	IElasticSearchService elasticSearchService;
	
	@Autowired
	IElasticSearchSaveService elasticSearchSaveService; 

	@CrossOrigin
	@RequestMapping(value = "/searchEmployee/{searchString}", method = RequestMethod.GET)
	public @ResponseBody
	List<Customer> getEmployee(@PathVariable("searchString") String searchString) {
		if(isInfo)
			log.info(IApplicationConstant.METHOD_STARTED);
		List<Customer> empLst = null;
		try {
			empLst = elasticSearchService.searchEmployee(searchString);
		} catch (Exception e1) {
			log.error("Error for EMPLOYEE SEARCH" + e1);
			throw new RuntimeException(e1);
		}

		if (empLst != null && !empLst.isEmpty()) {
			for (Customer e : empLst) {
				log.info("Employee Details ---- " + e.toString());
			}
		}
		
		if(isInfo)
			log.info(IApplicationConstant.METHOD_EXIT);
		return empLst;
	}

	@CrossOrigin
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.PUT)
	public void saveEmployee(@RequestBody Customer employee) {
		if(isInfo)
			log.info("Entering");
		try {
			elasticSearchSaveService.saveEmployeeDetails(employee);
		} catch (Exception e1) {
			log.error("Error for EMPLOYEE SEARCH" + e1);
			throw new RuntimeException(e1);
		}
		if(isInfo)
			log.info("Exiting");
	}
}
