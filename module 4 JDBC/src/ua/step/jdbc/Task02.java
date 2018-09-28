package ua.step.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.ResultSetMetaData;

import ua.step.jdbc.utils.ConnectionUrl;

/**
 * 
 * Получение соеденение 
 *
 */
public class Task02 {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		
		try {
			Connection connection = DriverManager.getConnection(ConnectionUrl.URL, "admin", "adminadmin");
			//System.out.println(connection.getMetaData());
			DatabaseMetaData md = (DatabaseMetaData) connection.getMetaData();
			//System.out.println(md.getDatabaseProductName());
			String[] types = {"TABLE"};
			ResultSet rs = md.getTables(null, null, "%", types);
			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				System.out.println(tableName);
				for (int i=1; i<=rs.getMetaData().getColumnCount(); i++) {
					System.out.print(rs.getMetaData().getColumnName(i)+"\t");
				}
				System.out.println();
				
				Statement st = connection.createStatement();
				int jMax= rs.getMetaData().getColumnCount();
				
				String req = ("SELECT * FROM " + tableName);
				ResultSet resultSet = st.executeQuery(req);
	
				
				while (resultSet.next()) {
					for (int j=1; j<jMax; j++) {
						System.out.print(rs.getString(j)+"\t");
					}
					System.err.println();
				}
				
				System.out.println();
				System.out.println();
			}
				

			



			
			
			//Statement st = connection.createStatement();
			
/*			System.out.println(connection.getMetaData().getURL());
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'login' text, 'password' text);");
			System.out.println("Table users created");
			statement.execute("CREATE TABLE if not exists 'comments' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'id_user' INTEGER, 'comment' text);");
			System.out.println("Table Comments created");*/

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
