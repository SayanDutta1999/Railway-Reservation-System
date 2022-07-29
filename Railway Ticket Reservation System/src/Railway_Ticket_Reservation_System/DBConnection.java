package Railway_Ticket_Reservation_System;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 

	public static Connection connnectDB() { // make Driver class static for avoiding accessing huge memory.
		Connection con = null ;
		final String URL = "jdbc:mysql://localhost:3306/TRAIN_RESERVATION_SYSTEM";
		final String USER_NAME = "root";
		final String PASS = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Connection is a public interface which extends extends Wrapper, AutoCloseable
			// getConnection() is static method of DriverManager class
			con =  DriverManager.getConnection(URL, USER_NAME, PASS);
			
			
		} 
		catch (SQLException | ClassNotFoundException e) {
			System.out.println("Unable to load JDBC driver");
		}
		return con;
		
		
	}
}
