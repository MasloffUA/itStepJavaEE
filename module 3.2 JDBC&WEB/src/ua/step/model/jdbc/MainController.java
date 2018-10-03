package ua.step.model.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/*")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
	}

	public void init() throws ServletException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Class.mySQL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String query = "CREATE TABLE Users (" + "  id integer, " + "  login character(50), "
				+ "  password character(50), " + "  isAdmin boolean, " + " CONSTRAINT user_id PRIMARY KEY(id)" + ");";
		try {

			connection = DriverManager.getConnection("jdbc:mysql://10.2.210.17:3306/users", "admin", "adminadmin");
			//connection.createStatement().execute(query); // 
			System.out.println("Connected");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyLocalDB");
			
			connection = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	
	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void updating(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
	
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		boolean isAdmin = true;
		
		String query = "UPDATE Users SET login=\""+ login + "\" password=\"" +password + "\" isadmin=" + isAdmin + " WHERE id = " + id;
		Statement statement;
		try {
			statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			response.sendRedirect(request.getContextPath() +"/view");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	protected void remove(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String query = "DELETE FROM Users WHERE id = " + id;
		Statement statement;
		try {
			statement = connection.createStatement();
			int result = statement.executeUpdate(query);
			response.sendRedirect(request.getContextPath() +"/view");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer url = request.getRequestURL();
		System.out.println("DO GET" + url.toString());
		if (url.toString().endsWith("update")) {
			//edit(request, response);
		}
		else if (url.toString().endsWith("delete")) {
			System.out.println("WANT DELETE");
			remove(request, response);
		}
		else if (url.toString().endsWith("updating")) {
			updating(request, response);
		}
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		//writer.append(url);
		writer.append("<html><body>");
		Statement statement = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * from Users";
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			ResultSetMetaData metadata = rs.getMetaData();
			writer.append("<table border=1><tr>");
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				writer.append("<th>");
				writer.append(metadata.getColumnName(i));
				writer.append("</th>");
			}
			writer.append("</tr>");

			while (rs.next()) {
				writer.append("<tr>");
				for (int i = 1; i <= metadata.getColumnCount(); i++) {
					writer.append("<td>");
					int type = metadata.getColumnType(i);
					//System.out.println(i + " " + type);
					switch (type) {
					case Types.CHAR:
					case Types.NCHAR:
					case Types.VARCHAR:
					case Types.NVARCHAR:
						writer.append(rs.getString(i).trim());
						break;
					case Types.INTEGER:
					case Types.DECIMAL:
						writer.format("%d", rs.getInt(i));
						break;
					case Types.BOOLEAN:
					case Types.BIT:{
						boolean bool = rs.getBoolean(i);
						if (bool)
						{
							writer.append("<input type=\"checkbox\" checked=\"checked\" disabled=\"disabled\"/>");							
						}
						else
						{
							writer.append("<input type=\"checkbox\" disabled=\"disabled\"/>");
						}
					}
					default:
						break;
					}

					writer.append("</td>");
				}
				writer.append("<td><a href=\"delete?id=" + rs.getInt("id") + "\">Delete</a></td>");
				writer.append("<td><a href=\"update?id=" + rs.getInt("id") + "\">Edit</a></td>");
				writer.append("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		writer.append("</table>");

		writer.append("<form action=\"addperson\" method=\"post\" id=\"add\">");
		writer.append("</form>");			
		writer.append("<button type=\"submit\" form=\"add\" value=\"Submit\">Add user</button>");
		
		writer.append("<body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<html><body>");
		StringBuffer url = request.getRequestURL(); // получение строки запроса
		System.out.println(url.toString());
		if (url.toString().endsWith("addperson") || url.toString().endsWith("updating"))
		{
			writer.append("<form action=\"new\" method=\"post\" id=\"new\">");
			writer.append("<table>");
			writer.append("<tr><td><label for=\"login\">Login </label></td>");
			writer.append("<td><input type=\"login\" name=\"login\" required></td>");
			writer.append("<tr><td><label for=\"password\">Password </label></td>");
			writer.append("<td><input type=\"password\" name=\"password\" placeholder=\"password\" required></td>");
			writer.append("<tr><td><label for=\"isAdmin\">Is Admin</label></td>");
			writer.append("<td><input name = \"isadmin\"type=\"checkbox\"/></td></tr>");
			writer.append("</table>");
			if (url.toString().endsWith("addperson")) {
				writer.append("<input type=\"submit\" value=\"New\"></li>");
			}
			else {
				writer.append("<input type=\"submit\" value=\"" + request.getParameter("id") + "\"></li>");
			}

			writer.append("</form>");			
			System.out.println("IF" + url.toString());
			
			
		}
		else if (url.toString().endsWith("new"))
		{
			// FIXME измени код для добавления пользователей
			
			
			System.out.println("Новый");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			boolean isAdmin = true;
			if (request.getParameter("isadmin") == null) {
				isAdmin = false;
			}
			System.out.println(request.getParameter("isadmin"));
			String query = "INSERT INTO Users (login, password, admin) VALUES (\'" + login + "\', \'"+password+"\', "+isAdmin+")";
			
			try {
				Statement statement = connection.createStatement();
				int result = statement.executeUpdate(query);
					
				
				//System.out.println(result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() +"/view");
			//System.out.println(request.getRequestURI());
		}
		else if (url.toString().endsWith("updating")) {
			updating(request, response);
		}
		writer.append("<body></html>");
	}		
}
