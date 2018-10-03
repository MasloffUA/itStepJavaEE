package ua.step.jdbc;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Регистрация драйвера
 *
 */
public class Task01 {
	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
/*		try {
			DriverManager.registerDriver(new org.sqlite.JDBC());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
}
