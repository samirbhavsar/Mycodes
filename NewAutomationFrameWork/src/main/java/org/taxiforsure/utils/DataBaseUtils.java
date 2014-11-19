package org.taxiforsure.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class DataBaseUtils 
{
	
	private  Connection conn = null;
	private  Statement statement = null;
	private  String connectionURL = "jdbc:mysql://localhost/"; 
	private  String dbName = "taxi_dispatch?allowMultiQueries=true";
	private  String driver = "com.mysql.jdbc.Driver";
	private  String userName = "vinay.prasanna";
	private  String password = "vin_1984";
	private  ResultSet results;

	public synchronized ArrayList<Object> executeQuery(String query, String columnName) 
	{
		ArrayList<Object> list = new ArrayList<Object>();
		try 
		{
			statement = createConnection();
			System.out.println("Satement " + statement);
			results = statement.executeQuery(query);
			while(results.next())
			{
				list.add(results.getString(columnName));
			}
				
			
		} catch (Exception e) 
		{
			throw new RuntimeException(e.getLocalizedMessage());
		}finally
		{
			closeConnection();
		}
		return list;
		
	}
	
	public synchronized void updateValueInDb(String query)
	{
	   createConnection();
	   try
      {
	     PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
	     preparedStmt.executeUpdate();
      } catch (SQLException e)
      {
	     System.out.println("Unable to update the table to show more number of cabs");
	     e.printStackTrace();
      }
	   closeConnection();
	}
	
	private synchronized Statement createConnection()
	{
		System.out.println("Creating connection");
		try {
			Class.forName(driver).newInstance();
			System.out.println("url " + connectionURL);
			System.out.println("dbName " + dbName);
			System.out.println("username " + userName);
			System.out.println("pass " + password);
			conn = DriverManager.getConnection(connectionURL + dbName, userName, password);
			
			statement = conn.createStatement();
			System.out.println("Created connection");
		} catch (InstantiationException e) {
		   throw new RuntimeException(e.getLocalizedMessage());
		   
		} catch (IllegalAccessException e) {
		   throw new RuntimeException(e.getLocalizedMessage());
		   
		} catch (ClassNotFoundException e) {
		   throw new RuntimeException(e.getLocalizedMessage());
		   
		} catch (SQLException e) {
		   throw new RuntimeException(e.getLocalizedMessage());
		}
		return statement;
		
		
	}
	
	private synchronized void closeConnection()
	{
		try {
			if(!(conn.isClosed()))
			conn.close();
			
		} catch (SQLException e) 
		{
		   throw new RuntimeException(e.getLocalizedMessage());
		}
	}
}
