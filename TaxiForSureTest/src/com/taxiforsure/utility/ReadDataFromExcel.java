package com.taxiforsure.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

@SuppressWarnings("unused")
public class ReadDataFromExcel {
	// String
	Properties props=new Properties();
	
	
	static String filePath=System.getProperty("user.dir")+"/testdata/testdata.xlsx";
	
	//InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\com\\taxiforsure\\testdata\\testdata.xlsx"));
	public static Workbook ExcelWBook;
	public static Sheet ExcelWSheet;
	public static Cell cell;
	public static Row Row;

	public Object[][] testData( String SheetName)
			throws Exception {

		FileInputStream fis = new FileInputStream(filePath);
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

				// Cell cell =
				//arr[ci][cj] = getCellData(i, j);
				arr[ci][cj]=getCellData(i,j);
				// System.out.println(arr[ci][cj]);
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
			/*int dataType = cell.getCellType();
			if (dataType == 3) {
				return "";
			} else {
				 CellData = cell.getStringCellValue();
				return CellData;
			}*/
			
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
				CellData=cell.getStringCellValue();
				break;
			default:
				//CellData = cell.getStringCellValue();

				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					CellData = String.valueOf(cal.get(Calendar.MONTH) + 1)
							+ "/"
							+ String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
							+ "/" + String.valueOf(cal.get(Calendar.YEAR));
					System.out.println(CellData);
				}
                        break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
		return CellData;

			
			/*
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
			case Cell.CELL_TYPE_ERROR:
				// ignore all blank or error cells
				break;
			case Cell.CELL_TYPE_NUMERIC:
				CellData = Double.toString(cell.getNumericCellValue());
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
		*/

	}
	
	public  static String getExcelData(String sheetName,int RowNum, int ColNum) throws InvalidFormatException, IOException {

		
		FileInputStream fis = new FileInputStream(filePath);
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
	
	
	
	public static List<String> getExcelData(String sheetName) throws InvalidFormatException, IOException{
		List<String> sheetData = new ArrayList<String>();
		 FileInputStream fis = new FileInputStream(filePath);
		 try{
		 
		 Workbook wb = WorkbookFactory.create(fis);
         Sheet sheet = wb.getSheet(sheetName);
         Iterator<Row> rows = sheet.rowIterator();
         while (rows.hasNext()) {
             Row row = (Row) rows.next();
             Iterator<Cell> cells = row.cellIterator();

            // List<String> data = new ArrayList<String>();
             while (cells.hasNext()) {
                 Cell cell = (Cell) cells.next();
                 sheetData.add(cell.getStringCellValue());
             }

             
         }
     } catch (IOException e) {
         e.printStackTrace();
     }finally{
    	 if (fis != null) {
             fis.close();
         }
     }
		 return sheetData;
	}
	/*
	public static String getExcelData(String sheetName, int rowNum, int colNum)
			throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(colNum).getStringCellValue();
		return data;
	}*/

	public static int getRowCount(String sheetName)
			throws InvalidFormatException, IOException {

		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum() + 1;
		return rowCount;
	}

	@SuppressWarnings("static-access")
	public static void setExcelData(String sheetName, int rowNum, int colNum,
			String data) throws InvalidFormatException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(colNum);
		cel.setCellType(cel.CELL_TYPE_STRING);
		cel.setCellValue(data);

		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);

	}

}
