package com.taxiforsure.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUtils {
	private static Connection conn = null;
	private static Statement statement = null;
	private static String connectionURL = "jdbc:mysql://10.3.7.138:3372/";
	private static String dbName = "elearning_uat?allowMultiQueries=true";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "elearning_uat";
	private static String password = "uat@arse";

	public static void executeScriptToClearBkuttyData() {
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connectionURL + dbName,
					userName, password);
			System.out.println("Connected to the database");
			statement = conn.createStatement();
			System.out.println("Executing query");
			String relativePath = System.getProperty("user.dir");
			String filePath = relativePath
					+ "\\src\\main\\resources\\DbQueries\\ClearBkuttyDB.txt";
			statement.execute(new ReadTextFile().getText(filePath).replace(
					"null", ""));
			System.out.println("Completed query execution");
			conn.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			System.out.println("Exception message: " + e.getMessage());
			System.out.println("NO CONNECTION =(");
			try {
				conn.close();
			} catch (SQLException e1) {
			}
		}
	}

	public static void executeScriptToClearDBandonData() {
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(connectionURL + dbName,
					userName, password);
			System.out.println("Connected to the database");
			statement = conn.createStatement();
			System.out.println("Executing query");
			String relativePath = System.getProperty("user.dir");
			String filePath = relativePath
					+ "\\src\\main\\resources\\DbQueries\\ClearDBandonData.txt";
			statement.execute(new ReadTextFile().getText(filePath).replace(
					"null", ""));
			System.out.println("Completed query execution");
			conn.close();
			System.out.println("Disconnected from database");
		} catch (Exception e) {
			System.out.println("Exception message: " + e.getMessage());
			System.out.println("NO CONNECTION =(");
			try {
				conn.close();
			} catch (SQLException e1) {
			}
		}
	}
}
