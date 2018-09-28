package ua.step.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Спсиок параметров из запроса
 */
@WebServlet("/view")
public class Servlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Метод вызывается при get запросе на адрес
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<p>List request parameters:</p>");
		// получение списка имен параметров
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			writer.append("<p>");
			// получение значения параметра по его имени
			writer.append(name).append(" = ").append(request.getParameter(name));
			writer.append("</p>");
		}
	}

	/**
	 * Метод вызывается при post запросе на адрес
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}