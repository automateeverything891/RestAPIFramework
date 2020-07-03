package com.framework.testcases;

import java.util.Hashtable;

import org.apache.commons.lang3.math.NumberUtils;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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
import com.framework.utils.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class TC_001_ValidateWebServiceMethods extends TestBase {
	
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
			
			if(data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&!data.get("PATH").equalsIgnoreCase("")
					&&!data.get("EXPECTED").equalsIgnoreCase("")) {
				get.response_field_validations(data.get("URL"), data.get("PATH"), data.get("EXPECTED"));
			}
			else if(data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&data.get("PATH").equalsIgnoreCase("")
					&&!data.get("EXPECTED").equalsIgnoreCase("")) {
				if(NumberUtils.isNumber(data.get("EXPECTED")))
					get.response_status_code_validations(data.get("URL"),data.get("EXPECTED"));
				else 
					get.response_body_validations(data.get("URL"),data.get("EXPECTED"));
			}
		
		}
		//************************************************POST******************************************************************//
		else if (data.get("HTTPMETHODS").equalsIgnoreCase("POST")) {
			
			if(!data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&!data.get("PATH").equalsIgnoreCase("")
					&&!data.get("EXPECTED").equalsIgnoreCase("")) {
				post.response_field_validations(data.get("URL"), data.get("BODY"), data.get("PATH"), data.get("EXPECTED"));
			}
			else if(!data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&data.get("PATH").equalsIgnoreCase("")
					&&!data.get("EXPECTED").equalsIgnoreCase("")) {
			
				if(!NumberUtils.isNumber(data.get("EXPECTED")))
					post.response_body_validations(data.get("URL"), data.get("BODY"), data.get("EXPECTED"));
				else 
					post.response_status_code_validations(data.get("URL"), data.get("BODY"), data.get("EXPECTED"));
			}
			
		}
		
		//************************************************PUT******************************************************************//
				else if (data.get("HTTPMETHODS").equalsIgnoreCase("PUT")) {
					
					if(!data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&!data.get("PATH").equalsIgnoreCase("")
							&&!data.get("EXPECTED").equalsIgnoreCase("")) {
						put.response_field_validations(data.get("URL"), data.get("BODY"), data.get("PATH"), data.get("EXPECTED"));
					}
					else if(!data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&data.get("PATH").equalsIgnoreCase("")
							&&!data.get("EXPECTED").equalsIgnoreCase("")) {
					
						if(!NumberUtils.isNumber(data.get("EXPECTED")))
							put.response_body_validations(data.get("URL"), data.get("BODY"), data.get("EXPECTED"));
						else 
							put.response_status_code_validations(data.get("URL"), data.get("BODY"), data.get("EXPECTED"));
					}
					
				}
		//************************************************DELETE******************************************************************//
				else if (data.get("HTTPMETHODS").equalsIgnoreCase("DELETE")) {
					
							delete.response_status_code_validations(data.get("URL"),data.get("EXPECTED"));
				}
				
		//************************************************PATCH******************************************************************//
				else if(data.get("HTTPMETHODS").equalsIgnoreCase("PATCH")) {
					
					if(!data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&!data.get("PATH").equalsIgnoreCase("")
							&&!data.get("EXPECTED").equalsIgnoreCase("")) {
							patch.response_field_validations(data.get("URL"), data.get("BODY"), data.get("PATH"), data.get("EXPECTED"));
					}
					else if(!data.get("BODY").equalsIgnoreCase("")&&!data.get("URL").equalsIgnoreCase("")&&data.get("PATH").equalsIgnoreCase("")
							&&!data.get("EXPECTED").equalsIgnoreCase("")) {
					
						if(!NumberUtils.isNumber(data.get("EXPECTED")))
							patch.response_body_validations(data.get("URL"), data.get("BODY"), data.get("EXPECTED"));
						else 
							patch.response_status_code_validations(data.get("URL"), data.get("BODY"), data.get("EXPECTED"));
					}
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
