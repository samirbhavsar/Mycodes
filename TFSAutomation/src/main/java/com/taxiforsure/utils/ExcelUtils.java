package com.taxiforsure.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.SkipException;

public class ExcelUtils {
	public static Workbook ExcelWBook;
	public static Sheet ExcelWSheet;
	public static Cell cell;
	public static Row Row;
	public static String getTestExecutionStatus(String sheetName, String testName) 
	{
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "/testdata/testdata.xls";
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
	
	public static Object[][] testData( String SheetName)
			throws Exception {
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath + "/testdata/testdata.xls";
		FileInputStream fis = new FileInputStream(excelPath);
		ExcelWBook = WorkbookFactory.create(fis);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int totalRows = ExcelWSheet.getLastRowNum();
		int ci, cj;
		// Get total number of columns
		int noOfColumns = ExcelWSheet.getRow(0).getLastCellNum();// getPhysicalNumberOfCells()
		String[][] arr = new String[totalRows][noOfColumns];
		ci = 0;

		for (int i = 1; i <= totalRows; i++, ci++) {
			cj = 0;
			for (int j = 0; j < noOfColumns; j++, cj++) {

				
				arr[ci][cj]=getCellData(i,j);
				
			}
		}
		return arr;

	}

	//@SuppressWarnings("static-access")
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		
		
		String CellData=null;
		cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		try {
			cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
						
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
			case Cell.CELL_TYPE_ERROR:
				// ignore all blank or error cells
				break;
			case Cell.CELL_TYPE_NUMERIC:
				
				//CellData=CellReference.convertNumToColString((int) cell.getNumericCellValue());
				CellData =new BigDecimal(cell.getNumericCellValue()).toPlainString();  
				//CellData= Double.toString(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				CellData = Boolean.toString(cell.getBooleanCellValue());
				break;
			
			case Cell.CELL_TYPE_STRING:
			default:
				CellData = cell.getStringCellValue();
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
		return CellData;
	}
	
public  static String getExcelData(String sheetName,int RowNum, int ColNum) throws InvalidFormatException, IOException {

		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/testdata/testdata.xls");
		ExcelWBook = WorkbookFactory.create(fis);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		// This function will convert an object of type excel cell to a string
		// value
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case Cell.CELL_TYPE_NUMERIC: // 0
			result = new BigDecimal(cell.getNumericCellValue());  
			break;
		case Cell.CELL_TYPE_STRING: // 1
			result = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA: // 2
			throw new RuntimeException("Can't evaluate formulas ");
		case Cell.CELL_TYPE_BLANK: // 3
			result = "-";
			break;
		case Cell.CELL_TYPE_BOOLEAN: // 4
			result = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_ERROR: // 5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type: "
					+ type);
		}
		return result.toString();
	}
	
}












