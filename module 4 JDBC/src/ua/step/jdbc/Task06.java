package ua.step.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import ua.step.jdbc.utils.ConnectionUrl;

/**
 * 
 * Подготовленный запрос с параметрами
 *
 */
public class Task06 {
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Connection connection = ConnectionUrl.getConnection();
		String query = "SELECT login, password FROM Users WHERE login=? AND password=?";
		PreparedStatement statement = null;
		Scanner scanner = new Scanner(System.in);
		String login = "";
		String password = "";
		try {
			statement = connection.prepareStatement(query);
			do {
				System.out.println("Введите Login");
				login = scanner.nextLine();
				statement.setString(1, login); // установка параметра
				System.out.println("Введите password:");
				password = scanner.nextLine();
				statement.setString(2, password);
				ResultSet rs = statement.executeQuery(); // переиспользование запроса

				if (rs.next()) {
					System.out.println("Есть такое");
				}
				else {
					System.out.println("Нету такого");
				}
				/*while (rs.next()) {
					System.out.println("Есть такое");
					//System.out.println(rs.getString(1) + " " + rs.getString(2));
				}*/
			} while (!login.isEmpty());
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
