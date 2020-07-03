package com.framework.basetest;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.framework.mail.SendMail;
import com.framework.utils.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	
	
	public static ExtentReports report;
	
	public static ExtentTest test;
	
	public static Logger logger = Logger.getLogger(TestBase.class);
	
	public static Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\main\\java\\com\\framework\\testdata\\TestDataWithValidation.xlsx");
	
	static {
		
		PropertyConfigurator.configure("log4j.properties");

		report = new ExtentReports(System.getProperty("user.dir") + "\\src\\main\\java\\com\\framework\\report\\extentreport.html",true);
		try {
			report.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName())
					.addSystemInfo("USER NAME", "TESTING TEAM").addSystemInfo("PROJECT NAME", "FREE CRM");
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@AfterClass
	public void afterClass() {
		
		SendMail.custom_Mail();
	}

	public static void printout(String data) {
		
		logger.info(data);
		test.log(LogStatus.INFO, data);
	}

}
