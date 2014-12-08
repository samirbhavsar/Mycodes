import java.sql.Statement;


public class MySqlDBConnector {
	
	
	 private  Statement stmt;
     // Constant for Database URL
     public  String DB_URL = "jdbc:mysql://localhost:3306/user";   
     // Constant for Database Username
     public  String DB_USER = "root";
     // Constant for Database Password
     public  String DB_PASSWORD = "";
	
	public MySqlDBConnector(String DB_URL,String DB_USER,String DB_PASSWORD) {
		
		
	}

}
