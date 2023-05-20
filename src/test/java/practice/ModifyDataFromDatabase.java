package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ModifyDataFromDatabase {
	public static void main(String[] args) throws SQLException 
	{
		//step 1: create instance of driver
		Driver dbDriver = new Driver();
		
		//step 2: Register driver to JDBC
		DriverManager.registerDriver(dbDriver);
		
		//step 3: Establish database connection
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advsel","root","root");
		
		//step 4: create statement
		Statement statement = connection.createStatement();
	
		//step 5: Execute Update query
		int result = statement.executeUpdate("insert into student(id,name,address) values (7,'manoj','gurgaon');");
		System.out.println(result);
		
		//step 6: close database connection
		connection.close();
	}
}
