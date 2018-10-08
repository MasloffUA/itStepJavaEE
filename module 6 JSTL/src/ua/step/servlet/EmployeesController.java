package ua.step.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.step.beans.Employee;

/**
 * Сервлет загружается при старте приложения
 */
@WebServlet(urlPatterns = "/employees", loadOnStartup = 1)
public class EmployeesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	public EmployeesController() {
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = config.getServletContext();
		try {
			Class.forName(context.getInitParameter("dbDriverName"));
			connection = DriverManager.getConnection(context.getInitParameter("dbUri"),
					context.getInitParameter("login"), context.getInitParameter("password"));
			Statement statement = connection.createStatement();
			/*statement.execute(
					"CREATE TABLE IF NOT EXISTS departments (id INT NOT NULL, name VARCHAR(45) NULL, PRIMARY KEY (id));");
			statement.execute(
					"CREATE TABLE IF NOT EXISTS employees (id int(11) NOT NULL AUTO_INCREMENT, first varchar(45) DEFAULT NULL, last varchar(45) DEFAULT NULL,  salary int(11) NOT NULL DEFAULT '0',   department_id int(11) NOT NULL,   PRIMARY KEY (id),   UNIQUE KEY `id_UNIQUE` (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8");
			statement.execute("TRUNCATE departments");
			statement.execute("TRUNCATE employees");
			PreparedStatement prepared = connection
					.prepareStatement("INSERT INTO departments (id, name) VALUES (?, ?)");
			prepared.setInt(1, 1);
			prepared.setString(2, "Обслуживание клиентов");
			prepared.executeUpdate();
			prepared.setInt(1, 2);
			prepared.setString(2, "Произодство");
			prepared.executeUpdate();
			prepared.setInt(1, 3);
			prepared.setString(2, "Снабжение");
			prepared.executeUpdate();
			prepared = connection.prepareStatement(
					"INSERT INTO employees (id, first, last, salary, department_id) VALUES (?,?,?,?,?)");
			prepared.setInt(1, 1);
			prepared.setString(2, "Иванов");
			prepared.setString(3, "Иван");
			prepared.setInt(4, 659045);
			prepared.setInt(5, 1);
			prepared.executeUpdate();
			prepared.setInt(1, 2);
			prepared.setString(2, "Сидоров");
			prepared.setString(3, "Григорий");
			prepared.setInt(4, 654964);
			prepared.setInt(5, 2);
			prepared.executeUpdate();
			prepared.setInt(1, 3);
			prepared.setString(2, "Петров");
			prepared.setString(3, "Петр");
			prepared.setInt(4, 876941);
			prepared.setInt(5, 3);
			prepared.executeUpdate();
			prepared.setInt(1, 4);
			prepared.setString(2, "Степанов");
			prepared.setString(3, "Степан");
			prepared.setInt(4, 456546);
			prepared.setInt(5, 1);
			prepared.executeUpdate();
*/		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> employees = new ArrayList<>();
		Map<Integer, String> departs = new HashMap<>();
		
		try (Statement statament = connection.createStatement()) {
			ResultSet rs = statament.executeQuery("SELECT * FROM departments");
			while (rs.next()) {
				departs.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Map.Entry<Integer, String> d : departs.entrySet()) {
			System.out.println(d.getKey() + "   " + d.getValue());
		}
		
		
		
		try (Statement statament = connection.createStatement()) {
			ResultSet rs = statament.executeQuery("SELECT * FROM employees");
			while (rs.next()) {
				employees.add(new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), departs.get(rs.getInt(5))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("employees", employees);
		request.setAttribute("departs", departs);
		// чтобы forward (перенаправить) запрос к странице task017.jsp
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/task17.jsp");

		// Forward (Перенаправить) запрос, чтобы отобразить страницу JSP.
		dispatcher.forward(request, response);
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String s = req.getParameter("dep");
			System.out.println(s);
			PreparedStatement prepared = connection.prepareStatement("INSERT INTO departments (id, name) VALUES (1101, \""+s+"\")");
			prepared.execute();
		} catch (SQLException e) {
			System.out.println("Не удалось");
			e.printStackTrace();
		}
		doGet(req, resp);

	}

	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
