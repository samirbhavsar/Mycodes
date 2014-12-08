package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {
	 Workbook wbWorkbook;
	 org.apache.poi.ss.usermodel.Sheet shSheet;
	FileInputStream fs;
	public void openSheet(String filePath) throws IOException {
		
		try {
			fs = new FileInputStream(filePath);
			try {
				wbWorkbook = WorkbookFactory.create(fs);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shSheet = wbWorkbook.getSheetAt(0);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			fs.close();
		}
	}

	public String getValueFromCell(int iColNumber, int iRowNumber) throws IOException {

		Cell cell = shSheet.getRow(iRowNumber).getCell(iColNumber);
		int type = cell.getCellType();
		Object result=null;
		try{
		
		switch (type) {
		case Cell.CELL_TYPE_NUMERIC: // 0
			result = new BigDecimal(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 1
			result = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA: // 2
			//throw new RuntimeException("Can't evaluate formulas ");
			FormulaEvaluator formulaEval = wbWorkbook.getCreationHelper().createFormulaEvaluator();
			result=formulaEval.evaluate(cell).formatAsString();
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
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result.toString();
	}

	public int getRowCount() {
		return shSheet.getLastRowNum();
	}

	public int getColumnCount() {
		return shSheet.getRow(0).getPhysicalNumberOfCells();
	}
	
}
