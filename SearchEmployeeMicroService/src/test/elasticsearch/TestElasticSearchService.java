package test.elasticsearch;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.searchemployeeservice.bean.Customer;
import com.searchemployeeservice.service.ElasticSearchService;

public class TestElasticSearchService {

	final Logger log = Logger.getLogger(TestElasticSearchService.class);
	
	ElasticSearchService elasticSearchService = new ElasticSearchService();
	
	@Test
	public void searchEmployeeTest(){
		try {
			List<Customer> empList =  elasticSearchService.searchEmployee("010");
			List<Customer> empListExpected = new ArrayList<>();
			Customer e = new Customer();
			e.setEmpFirstName("Monica");
			e.setEmailID("monica.gellar@friends.com");
			e.setEmpId("010");
			e.setEmpLastName("Gellar");
			e.setPhoneNo(Long.parseLong("9890123456"));
			
			
			empListExpected.add(e);
			assertEquals(empListExpected.get(0).getEmpId(),empList.get(0).getEmpId());
			assertEquals(empListExpected.get(0).getEmpFirstName(),empList.get(0).getEmpFirstName());
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	@Test
	public void saveEmployeeDetails(){
		Customer expected = new Customer();
		expected.setEmpFirstName("Shardul");
		expected.setEmailID("Shardul.B@friends.com");
		expected.setEmpId("999");
		expected.setEmpLastName("B");
		expected.setPhoneNo(Long.parseLong("0000000001"));
		
		try {
			elasticSearchService.saveEmployeeDetails(expected);
			List<Customer> empList =  elasticSearchService.searchEmployee("999");
			assertEquals(expected.getEmpId(),empList.get(0).getEmpId());
			assertEquals(expected.getEmpFirstName(),empList.get(0).getEmpFirstName());
		} catch (Exception e1) {
			log.error(e1);
		}
	}
}
