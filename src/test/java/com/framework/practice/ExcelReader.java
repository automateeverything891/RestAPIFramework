package com.framework.practice;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	
	public static String getXlData(String path, String sheet, int r, int c) {
		String s = "";
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			
			// If You are reading the cell type is String this is OK..
			//s = wb.getSheet(sheet).getRow(r).getCell(c).toString();
			
			Cell cell = wb.getSheet(sheet).getRow(r).getCell(c);
			if(cell!=null)                                           // If that cell is empty You will get the Null Pointer Exception
				s = cell.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static void main(String[] args) {
		
		
		String path ="D:\\Jakay\\RestAPIFramework\\src\\main\\java\\com\\framework\\testdata\\TestDataWithValidation.xlsx";
		
		System.out.println(Integer.parseInt(getXlData(path, "Validation", 2, 6)));
		
	}

}
