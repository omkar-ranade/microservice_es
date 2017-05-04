package test.elasticsearch;

import com.searchemployeeservice.util.ElasticSearchUtil;

public class TestElasticSearchService {

	public static void main(String[] args) {
		
		try {
			String response ="";// ElasticSearchUtil.searchEmployee("10");
			
			System.out.println("Response is "+response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
