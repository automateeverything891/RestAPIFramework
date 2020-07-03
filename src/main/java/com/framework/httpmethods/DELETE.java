package com.framework.httpmethods;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import com.framework.basetest.TestBase;

public class DELETE extends TestBase{
	
	
	
	public void response_status_code_validations(String url, String expected) {
		
		printout("EXECUTING THE DELETE RECORD METHOD");
		
		int actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().delete(url).getStatusCode();
		
		Assert.assertEquals(actual, Integer.parseInt(expected));
	}
	public void response_header_validations(String url, String expected) {
		
		printout("EXECUTING THE DELETE RECORD METHOD WITH HEADER VALIDATION !");
		
		String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().delete(url).getHeader("Content-Type");
		
		if(!expected.equalsIgnoreCase(""))
			Assert.assertEquals(actual, expected);
	}

	public void response_time_validations(String url, long expected) {
		
		printout("EXECUTING THE DELETE RECORD METHOD WITH RESPONSE TIME VALIDATION !");
		
		long actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().delete(url).getTime();
		
		Assert.assertTrue(actual<=expected, "RESPONSE TIME LIMIT IS EXCEED!!!");
	}


}
