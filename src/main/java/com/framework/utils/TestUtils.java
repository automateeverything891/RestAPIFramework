package com.framework.utils;

import java.util.Hashtable;



public class TestUtils {
	
	public static boolean isTestcasesExecutable(String testcases, Xls_Reader xls){
		
		for(int rNum=2; rNum<=xls.getRowCount("Validation"); rNum++){
			
			if(testcases.equals(xls.getCellData("Validation", "PAGES", rNum))){
				
				//System.out.println(xls.getCellData("Test Cases", "TCID", rNum));
				if(xls.getCellData("Validation", "RUNMODE",rNum).equals("Y")){
					//System.out.println("test cases is runnable");
					return true;
				}
				else{
					return false;
				}
			}
		}
		return false;
	}
	
	// Read the test data based on the test cases form need
		public static Object[][] getData( Xls_Reader xls) {
			int testStartsRowNum = 0;

			System.out.println("Test cases started row number :" + testStartsRowNum);

			int columnStartNum = testStartsRowNum + 1;
			int cols = 0;

			while (!xls.getCellData("Validation", cols, columnStartNum).equals("")) {
				cols++;
			}

			System.out.println("Test cases total column count are :" + cols);

			int rowStartNum = testStartsRowNum + 2;
			int rows = 0;

			while (!xls.getCellData("Validation", 0, (rowStartNum + rows)).equals("")) {
				rows++;
			}

			System.out.println("Test cases total rows count are :" + rows);

			Object[][] data = new Object[rows][1];
			// System.out.println(data.length);
			Hashtable<String, String> table = null;

			for (int rNum = rowStartNum; rNum < (rowStartNum + rows); rNum++) {
				table = new Hashtable<String, String>();
				for (int cNum = 0; cNum < cols; cNum++) {
					table.put(xls.getCellData("Validation", cNum, columnStartNum), xls.getCellData("Validation", cNum, rNum));

					System.out.print(xls.getCellData("Validation", cNum, columnStartNum) + "--"
							+ xls.getCellData("Validation", cNum, rNum) + " --");
				}
				System.out.println();
				data[rNum - rowStartNum][0] = table;
			}
			return data;

		}

		public static int getNum(String testcases, Xls_Reader xls,String columnName) {
			int testStartsRowNum = 0;

			for (int rNum = 1; rNum <= xls.getRowCount("Validation"); rNum++) {
				if (testcases.equals(xls.getCellData("Validation", 0, rNum))) {
					testStartsRowNum = rNum;
					break;
				}
			}
			//System.out.println("Test cases started row number :" + testStartsRowNum);

			int columnStartNum = testStartsRowNum + 1;
			int cols = 0;

			while (!xls.getCellData("Validation", cols, columnStartNum).equals("")) {
				cols++;
			}

			//System.out.println("Test cases total column count are :" + cols);

			int rowStartNum = testStartsRowNum + 2;
			int rows = 0;

			while (!xls.getCellData("Validation", 0, (rowStartNum + rows)).equals("")) {
				rows++;
			}

			//System.out.println("Test cases total rows count are :" + rows);

			Object[][] data = new Object[rows][1];
			//System.out.println(data.length);
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int rNum = rowStartNum; rNum < (rowStartNum + rows); rNum++) {
				for (int cNum = 0; cNum < cols; cNum++) {
					table.put(xls.getCellData("Validation", cNum, columnStartNum), xls.getCellData("Validation", cNum, rNum));
					//System.out.print(xls.getCellData("Test Data", cNum, columnStartNum) + "--"+ xls.getCellData("Test Data", cNum, rNum) + " --");
					//System.out.println(xls.getCellData("Test Data", cNum, columnStartNum));
					//System.out.println(xls.getCellData("Test Data", cNum, rNum) + " --");
					if(xls.getCellData("Validation", cNum, columnStartNum).equalsIgnoreCase(columnName)){
						if(xls.getCellData("Validation", cNum, rNum).equals("")){
							return rNum-1;
							
						}
					}
				}
				//System.out.println();
				data[rNum - rowStartNum][0] = table;
			}

			return 0;
		}
		
		
	public static void main(String[] args) {
		
		
	}

}
