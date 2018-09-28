package ua.step.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Получние контекста для сервлетов.
 */
@WebServlet("/context")
public class Servlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Метод вызывается при get запросе на адрес
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		writer.append("<p>List context parameters:</p>");
		/*
		 * Информация о контексте сервера доступна через объект ServletContext. Сервлет
		 * может получить этот объект, вызывая метод getServletContext() объекта
		 * ServletConfig. Необходимо помнить, что этот объект передается сервлету во
		 * время инициализации в методе init().
		 */
		ServletContext context = getServletContext();

		Enumeration<String> parameters = context.getInitParameterNames();
		while (parameters.hasMoreElements()) {
			String name = (String) parameters.nextElement();

			writer.append("<p>");
			// получение значения параметра из контекста по его имени
			writer.append(name).append(" = ").append(context.getInitParameter(name));
			writer.append("</p>");
		}
	}
}