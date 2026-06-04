import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TransactionHandling {
	
	static final String URL = "jdbc:mysql://localhost:3306/bank_db";
	static final String USER = "root";
	static final String PASSWORD = "root123";
	
	static void createAccountsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS accounts(" +
			"account_id INT PRIMARY KEY, " +
			"holder_name VARCHAR(50), " +
			"balance DOUBLE)";
		try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sql);
			stmt.executeUpdate("INSERT IGNORE INTO accounts VALUES(1, 'Alice', 10000)");
			stmt.executeUpdate("INSERT IGNORE INTO accounts VALUES(2, 'Bob', 5000)");
			System.out.println("Accounts table ready");
		}catch(Exception e) {
			System.out.println("Setup error: " + e.getMessage());
		}
	}
	
	static void transfer(int fromId, int toId, double amount) {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn.setAutoCommit(false);
			
			// debit
			String debit = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
			PreparedStatement ps1 = conn.prepareStatement(debit);
			ps1.setDouble(1, amount);
			ps1.setInt(2, fromId);
			ps1.executeUpdate();
			
			// credit
			String credit = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
			PreparedStatement ps2 = conn.prepareStatement(credit);
			ps2.setDouble(1, amount);
			ps2.setInt(2, toId);
			ps2.executeUpdate();
			
			conn.commit();
			System.out.println("Transfer of " + amount + " successful");
			
			ps1.close();
			ps2.close();
			conn.close();
			
		}catch(Exception e) {
			System.out.println("Transaction failed: " + e.getMessage());
			try {
				if(conn != null) {
					conn.rollback();
					System.out.println("Transaction rolled back");
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		createAccountsTable();
		transfer(1, 2, 2000);
	}
	
}
