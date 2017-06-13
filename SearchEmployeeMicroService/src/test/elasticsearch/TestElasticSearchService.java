package test.elasticsearch;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.searchemployeeservice.bean.Employee;
import com.searchemployeeservice.service.ElasticSearchService;

@Component
public class TestElasticSearchService {

	ElasticSearchService elasticSearchService = new ElasticSearchService();
	
	@Test
	public void searchEmployeeTest(){
		try {
			List<Employee> empList =  elasticSearchService.searchEmployee("010");
			List<Employee> empListExpected = new ArrayList<Employee>();
			Employee e = new Employee();
			e.setEmpFirstName("Monica");
			e.setEmailID("monica.gellar@friends.com");
			e.setEmpId("010");
			e.setEmpLastName("Gellar");
			e.setPhoneNo(Long.parseLong("9890123456"));
			
			
			empListExpected.add(e);
			assertEquals(empListExpected.get(0).getEmpId(),empList.get(0).getEmpId());
			assertEquals(empListExpected.get(0).getEmpFirstName(),empList.get(0).getEmpFirstName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveEmployeeDetails(){
		Employee expected = new Employee();
		expected.setEmpFirstName("Shardul");
		expected.setEmailID("Shardul.B@friends.com");
		expected.setEmpId("999");
		expected.setEmpLastName("B");
		expected.setPhoneNo(Long.parseLong("0000000001"));
		
		try {
			elasticSearchService.saveEmployeeDetails(expected);
			List<Employee> empList =  elasticSearchService.searchEmployee("999");
			assertEquals(expected.getEmpId(),empList.get(0).getEmpId());
			assertEquals(expected.getEmpFirstName(),empList.get(0).getEmpFirstName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
