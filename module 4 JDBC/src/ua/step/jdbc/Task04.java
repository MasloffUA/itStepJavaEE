package ua.step.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ua.step.jdbc.utils.ConnectionUrl;

/**
 * 
 * Добавление записей в таблицу используя пакетный режим
 *
 */
public class Task04 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<String[]> users = new ArrayList<String[]>();
		Scanner scanner = new Scanner(System.in);
		String text ="";
		do {
			System.out.println("Введеите логин и пароль через пробел или пустую строку для окончания");
			text = scanner.nextLine();
			String[] data = text.split(" ");
			if (data.length != 2)
			{
				System.out.println("Введите два значения");
				continue;
			}
			users.add(data);
		}while(!text.isEmpty());
		scanner.close();
		Connection connection = ConnectionUrl.getConnection(); 
		connection.setAutoCommit(false);
		try {
		
			Statement statement = connection.createStatement();
			for (String[] user: users) {
				String query = String.format("INSERT INTO Users (login, password) VALUES ('%s', '%s')", user[0], user[1]);
				statement.addBatch(query); // добавление в пакет
			}
			statement.executeBatch(); // добавление данных из пакета в базу данных
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
		}
	}
}
