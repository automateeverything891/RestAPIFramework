package com.framework.practice;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class GetDetailsTest {
	
	
	
	public Response listOfUsersDetails() {
		
	    return given().when().get("https://reqres.in/api/users?page=2");
	}
	
	public Response verifyDetails() {
		
	    return given().when().get("https://reqres.in/api/users?page=2");
	}
	
	@Test
	public void getDetailsURLs() {
		
		System.out.println(listOfUsersDetails().getBody().asString());
		
		JsonPath jsonpath = listOfUsersDetails().jsonPath();
		System.out.println(jsonpath.get("total_pages"));
		
	}

}
