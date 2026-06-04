import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BasicJDBCConnection {
	
	static final String URL = "jdbc:mysql://localhost:3306/college_db";
	static final String USER = "root";
	static final String PASSWORD = "root123";
	
	public static void main(String[] args) {
		
		try {
			// load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// create connection
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection established successfully");
			
			// execute query
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM students");
			
			System.out.println("Students:");
			while(rs.next()) {
				System.out.println("ID: " + rs.getInt("student_id") + 
					" | Name: " + rs.getString("name") + 
					" | Age: " + rs.getInt("age"));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
}
