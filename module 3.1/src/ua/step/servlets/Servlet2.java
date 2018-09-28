package ua.step.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервлет объявленный с использование аннотации.
 */
@WebServlet("/user")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("userName");
		PrintWriter writer = response.getWriter();
		writer.append("Hello ");
		String sex = request.getParameter("sex");
		if (sex.equals("male")) {
			sex = "mister";
		}
		else if (sex.equals("female")) {
			sex = "madama";
		}
		else {
			sex = "childko";
		}
		writer.append(sex+" " + user);
	}
}