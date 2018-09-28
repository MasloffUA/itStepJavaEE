package ua.step.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ua.step.jdbc.utils.ConnectionUrl;

/**
 * 
 * Обнавление данных в таблице
 *
 */
public class Task07 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUrl.getConnection();
		String query = "UPDATE Users SET password = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, "admin11");
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
			connection.close();
		}
	}
}
