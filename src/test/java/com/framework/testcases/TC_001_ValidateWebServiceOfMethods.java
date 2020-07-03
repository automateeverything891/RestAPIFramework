package com.framework.testcases;

import java.util.Hashtable;

import org.apache.commons.lang3.math.NumberUtils;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.basetest.TestBase;
import com.framework.httpmethods.DELETE;
import com.framework.httpmethods.GET;
import com.framework.httpmethods.PATCH;
import com.framework.httpmethods.POST;
import com.framework.httpmethods.PUT;
import com.framework.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class TC_001_ValidateWebServiceOfMethods extends TestBase{

	GET get = new GET();
	
	POST post = new POST();
	
	PUT put = new PUT();
	
	DELETE delete = new DELETE();

	PATCH patch = new PATCH();
	
	int rowNum=2;
	
	public String testCases_name;
	
	public int rownum =1;
	
	@Test(dataProvider = "getData")
	public void checks_APIs(Hashtable<String, String> data) {
		
		if(!TestUtils.isTestcasesExecutable(data.get("PAGES"), xls)) {
			
			throw new SkipException("THIS TEST CASE RUNMODE IS NO !!!!!!!!!!!");
		}

		//************************************************GET******************************************************************//
		if (data.get("HTTPMETHODS").equalsIgnoreCase("GET")) {
			
				
				get.response_status_code_validations(data.get("URL"), data.get("RESPONSE STATUS CODE"));
				get.response_time_validations(data.get("URL"), Long.parseLong(data.get("RESPONSE TIME")));
				get.response_body_validations(data.get("URL"), data.get("RESPONSE BODY"));
				get.response_field_validations(data.get("URL"), data.get("PATH"), data.get("RESPONSE FIELD VALUE"));
				get.response_header_validations(data.get("URL"),data.get("RESPONSE HEADER"));
				
		
		}
		//************************************************POST******************************************************************//
		else if (data.get("HTTPMETHODS").equalsIgnoreCase("POST")) {
			
				post.response_status_code_validations(data.get("URL"), data.get("BODY"), data.get("RESPONSE STATUS CODE"));
				post.response_body_validations(data.get("URL"), data.get("BODY"), data.get("RESPONSE BODY"));
				post.response_time_validations(data.get("URL"), data.get("BODY"), Long.parseLong(data.get("RESPONSE TIME")));
				post.response_field_validations(data.get("URL"), data.get("BODY"), data.get("PATH"), data.get("RESPONSE FIELD VALUE"));
				post.response_header_validations(data.get("URL"), data.get("BODY"),data.get("RESPONSE HEADER"));
		
		}
		
		//************************************************PUT******************************************************************//
				else if (data.get("HTTPMETHODS").equalsIgnoreCase("PUT")) {
					
					put.response_body_validations(data.get("URL"), data.get("BODY"), data.get("RESPONSE BODY"));
					put.response_status_code_validations(data.get("URL"), data.get("BODY"), data.get("RESPONSE STATUS CODE"));
					put.response_field_validations(data.get("URL"), data.get("BODY"), data.get("PATH"), data.get("RESPONSE FIELD VALUE"));
					put.response_time_validations(data.get("URL"), data.get("BODY"), Long.parseLong(data.get("RESPONSE TIME")));
					put.response_header_validations(data.get("URL"), data.get("BODY"),data.get("RESPONSE HEADER"));
				}
		//************************************************DELETE******************************************************************//
				else if (data.get("HTTPMETHODS").equalsIgnoreCase("DELETE")) {
					
							delete.response_status_code_validations(data.get("URL"),data.get("RESPONSE STATUS CODE"));
							delete.response_time_validations(data.get("URL"), Long.parseLong(data.get("RESPONSE TIME")));
							delete.response_header_validations(data.get("URL"), data.get("RESPONSE HEADER"));
				}
				
		//************************************************PATCH******************************************************************//
				else if(data.get("HTTPMETHODS").equalsIgnoreCase("PATCH")) {
					
					patch.response_body_validations(data.get("URL"), data.get("BODY"), data.get("RESPONSE BODY"));
					patch.response_status_code_validations(data.get("URL"), data.get("BODY"), data.get("RESPONSE STATUS CODE"));
					patch.response_time_validations(data.get("URL"), data.get("BODY"), Long.parseLong(data.get("REPONSE TIME")));
					patch.response_field_validations(data.get("URL"), data.get("BODY"), data.get("PATH"), data.get("RESPONSE FIELD VALUE"));
					patch.response_header_validations(data.get("URL"), data.get("BODY"),data.get("RESPONSE HEADER"));
				}
	}
	
	@DataProvider
	public Object[][] getData(){
		
		 return TestUtils.getData(xls);
	}
	
	@BeforeMethod
	public void beforeMethod() {

		testCases_name = xls.getCellData("Validation", "PAGES", rowNum++);
		test = report.startTest(testCases_name);
	}
	
	@AfterMethod
	public void endTest(ITestResult result) {
		
		
		if(ITestResult.SUCCESS==result.getStatus()) {
			
			xls.setCellData("Validation", "STATUS", rownum++, "PASS");
			test.log(LogStatus.PASS, "TEST CASES IS PASSED!!!");
		}
		else if(ITestResult.FAILURE==result.getStatus()) {
			
			xls.setCellData("Validation", "STATUS", rownum++, "FAIL");
			test.log(LogStatus.FAIL, "TEST CASES IS FAILED!!!");
		}
		else if(ITestResult.SKIP==result.getStatus()) {
			
			xls.setCellData("Validation", "STATUS", rownum++, "SKIP");
			test.log(LogStatus.SKIP, "TEST CASES IS SKIPPED!!!");
		}
		
		report.endTest(test);
		report.flush();
		
	}
}
