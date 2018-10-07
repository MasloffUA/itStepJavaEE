package ua.step.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserBean {
	public UserBean() {
	}
	public List<User> getUsers() {
		User user = null;
		List<User> users = new ArrayList<User>();
		try {

			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://10.2.210.17:3306/users";
			Connection con = DriverManager.getConnection(url, "admin", "adminadmin");
			Statement statement = con.createStatement();

			String requete1 = "SELECT id, login, password, admin FROM users";

			System.out.println(requete1);
			ResultSet resultset = statement.executeQuery(requete1);

			while (resultset.next()) {
				user = new User(resultset.getInt("id"), resultset.getString("login"), resultset.getString("password"),
						resultset.getBoolean("admin"));
				users.add(user);
			}
			con.close();

		}

		catch (ClassNotFoundException e) {
			System.err.println("Erreur lors du chargement du pilote : " + e);
		} catch (SQLException sqle) {
			System.err.print("Erreur SQL : " + sqle);
		}
		return users;
	}

	public static void main(String[] args) {
		UserBean bean = new UserBean();
		bean.getUsers().forEach(System.out::println);
	}
}
