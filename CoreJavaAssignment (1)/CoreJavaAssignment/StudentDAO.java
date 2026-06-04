import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StudentDAO {
	
	static final String URL = "jdbc:mysql://localhost:3306/college_db";
	static final String USER = "root";
	static final String PASSWORD = "root123";
	
	static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	void insertStudent(int id, String name, int age, String city, String course) {
		String sql = "INSERT INTO students(student_id, name, age, city, course_name) VALUES(?, ?, ?, ?, ?)";
		try(Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, city);
			ps.setString(5, course);
			
			int rows = ps.executeUpdate();
			System.out.println(rows + " row(s) inserted successfully");
			
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	void updateStudent(int id, String newCity) {
		String sql = "UPDATE students SET city=? WHERE student_id=?";
		try(Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, newCity);
			ps.setInt(2, id);
			
			int rows = ps.executeUpdate();
			System.out.println(rows + " row(s) updated successfully");
			
		}catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		StudentDAO dao = new StudentDAO();
		dao.insertStudent(101, "Ravi", 21, "Hyderabad", "CSE");
		dao.updateStudent(101, "Bangalore");
	}
	
}
