package org.taxiforsure.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.SkipException;

public class ExcelUtils {
	public static String getTestExecutionStatus(String sheetName, String testName) 
	{
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "/TestSet.xlsx";
		File excel = new File(excelPath);
		FileInputStream fis = null;
		Workbook workBook = null;
		String cellValue = null;
		try {
			fis = new FileInputStream(excel);
			workBook = WorkbookFactory.create(fis);
			Sheet workSheet = workBook.getSheet(sheetName);
			int totalRows = workSheet.getLastRowNum();
			Row row = null;
			int testCaseRowNo = 0;
			for (int rowNo = 1; rowNo <= totalRows; rowNo++) {
				row = workSheet.getRow(rowNo);
				testCaseRowNo = testCaseRowNo + 1;
				if (row.getCell(0).getStringCellValue()
						.equalsIgnoreCase(testName)) {

					cellValue = workSheet.getRow(testCaseRowNo).getCell(1)
							.getStringCellValue();
					break;
				}
			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (cellValue == null) {
			throw new SkipException("Test Case was not found in the excel");
		}
		return cellValue;
	}

	public List<String> getDataFromAColumn(String filePath, String sheetName, int columnNumber)
	{
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "//src//main//resources" + filePath;
		File excel = new File(excelPath);
		FileInputStream fis = null;
		List<String> cellValues = new ArrayList<String>();
		Workbook workBook = null;
		try
		{
			fis = new FileInputStream(excel);
			workBook = WorkbookFactory.create(fis);
			Sheet workSheet = workBook.getSheet(sheetName);
			int totalRows = workSheet.getLastRowNum();
			for(int i =0;i<=totalRows; i++)
			{
				cellValues.add(workSheet.getRow(i).getCell(columnNumber).getStringCellValue().trim());
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			try {
				fis.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return cellValues;
	}
}












