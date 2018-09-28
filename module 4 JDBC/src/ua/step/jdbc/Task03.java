package ua.step.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ua.step.jdbc.utils.ConnectionUrl;

/**
 * 
 * Добавление записей в таблицу
 *
 */
public class Task03 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionUrl.getConnection(); 
		try {
		
			Statement statement = connection.createStatement();
			Scanner scanner = new Scanner(System.in);
			String text = null;
			do {
				System.out.println("Введеите логин и пароль через пробел или пустую строку для окончания");
				text = scanner.nextLine();
				String[] data = text.split(" ");
				if (data.length != 2)
				{
					System.out.println("Введите два значения");
					continue;
				}
				String query = String.format("INSERT INTO Users (login, password) VALUES ('%s', '%s')", data[0], data[1]);
				System.out.println(query);
				statement.execute(query);
				System.out.println("Сохранено");
			}while(!text.isEmpty());
			scanner.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	}
}
