package com.framework.httpmethods;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.json.JSONObject;
import org.testng.Assert;


import com.framework.basetest.TestBase;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import com.mongodb.util.JSON;


public class GET extends TestBase{
	
	
	public void response_field_validations(String url, String path, String expected) {
		
		printout("EXECUTING THE GET RECORD METHOD WITH FIELD VALIDATION !");
		
		String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().get(url).jsonPath().get(path);
		
		System.out.println(actual);
		System.out.println(expected);
		Assert.assertEquals(actual, expected);
	}
	
	public void response_body_validations(String url, String expected) {
		
		printout("EXECUTING THE GET RECORD METHOD WITH BODY VALIDATION !");
		
		//String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().get(url).jsonPath().toString();
		
		String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().get(url).getBody().asString();
		
		JsonParser jsonparser = new JsonParser();
		JsonElement json1 = jsonparser.parse(actual);
		JsonElement json2 = jsonparser.parse(expected);
		
		if(!expected.equalsIgnoreCase("")) {
			Assert.assertEquals(json1, json2);
		}
		
	}
	
	public void response_status_code_validations(String url, String expected) {
		
		printout("EXECUTING THE GET RECORD METHOD WITH STATUS CODE VALIDATION !");
		
		int actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().get(url).getStatusCode();
		
		Assert.assertEquals(actual, Integer.parseInt(expected));
	}
	
	public void response_header_validations(String url, String expected) {
		
		printout("EXECUTING THE GET RECORD METHOD WITH HEADER VALIDATION !");
		
		String actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().get(url).getHeader("Content-Type");
		
		Assert.assertEquals(actual, expected);
	}

	public void response_time_validations(String url, long expected) {
		
		printout("EXECUTING THE GET RECORD METHOD WITH RESPONSE TIME VALIDATION !");
		
		long actual = given().header("Content-Type", "application/json").header("Accept", "application/json").when().get(url).getTime();
		
		Assert.assertTrue(actual<=expected, "RESPONSE TIME LIMIT IS EXCEED!!!");
	}
	
}
