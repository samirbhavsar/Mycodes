package com.taxiforsure.commonLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DbConnections {
	String url;// = "jdbc:mysql://5.9.154.138:3306/";
	String dbName;// = "taxi_dispatch";
	String mySqlDriver;// = "com.mysql.jdbc.Driver";
	String userName;// = "rtfs";
	String password;// = "qaAccessOnly";
	String myData[][] = null;

	public DbConnections(String url, String dbName, String mySqlDriver,
			String userName, String password) {

		this.url = url;
		this.dbName = dbName;
		this.mySqlDriver = mySqlDriver;
		this.userName = userName;
		this.password = password;

	}

	public Object[][] mySQL_Data(String sqlQuery) {
		// Connect to the database.

		int rowCount = 0;
		int columnCount = 0;

		try {
			Class.forName(mySqlDriver).newInstance();

			Connection con = DriverManager.getConnection(url + dbName,
					userName, password);

			// Execute the SQL statement
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(sqlQuery);

			// Get Column count
			ResultSetMetaData resultSet_metaData = resultSet.getMetaData();
			columnCount = resultSet_metaData.getColumnCount();

			// Get Row Count
			while (resultSet.next())
				rowCount++;

			// Initialize data structure
			myData = new String[rowCount][columnCount];

			resultSet.beforeFirst();

			// populate data structure
			for (int row = 0; row < rowCount; row++) {
				resultSet.next();

				for (int col = 1; col <= columnCount; col++)
					myData[row][col - 1] = resultSet.getString(col);
			}

			stmt.close();
			con.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return myData;

	}

	public Object[][] OracleDb_Data(String sqlQuery) {
		//code for oracle DB connection
		return myData;
	}
}
