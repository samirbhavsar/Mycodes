package org.taxiforsure.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;

public class CSVReader {

	

	public String getValue(String fileName, String rowNum, int colHeader) {
		String returnVal = null;
		String relativePath = System.getProperty("user.dir");
		String csvPath = relativePath + "\\src\\main\\resources\\CSV\\"
				+ fileName + ".csv";
		CsvReader r;
		try {
			r = new CsvReader(csvPath);
			while (r.readRecord()) {
				String row = r.get(0);
				if (row.equalsIgnoreCase(rowNum)) {
					returnVal = r.get(colHeader);
					break;
				}
			}
			r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnVal;

	}

	public static boolean getTestExecutionStatus(String Filename,
			String testName) {
		boolean executionStatus = false;
		String relativePath = System.getProperty("user.dir");
		String csvPath = relativePath + "\\src\\main\\resources\\CSV\\"
				+ Filename + ".csv";
		CsvReader r;
		try {
			r = new CsvReader(csvPath);
			while (r.readRecord()) {
				String row = r.get(0);
				if (row.equalsIgnoreCase(testName)) {
					String status = r.get(1);
					if (status.equalsIgnoreCase("yes")) {
						executionStatus = true;
					}
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return executionStatus;

	}

}
