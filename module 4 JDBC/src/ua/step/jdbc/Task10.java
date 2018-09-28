package ua.step.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ua.step.jdbc.utils.ConnectionUrl;

/**
 * 
 * Удаление таблицы. 
 * Внимание после запуска все введеные данные будут потеряны.
 *
 */
public class Task10 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUrl.getConnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute("DROP TABLE Users");
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
