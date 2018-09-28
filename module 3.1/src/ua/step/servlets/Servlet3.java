package ua.step.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCicle
 */
@WebServlet("/life") // анотация для обозначения класса как сервлет
public class Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор сервлета
	 */
	public Servlet3() {
		System.out.println("Create");
	}

	/**
	 * Метод инициализации сервлета
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	    System.out.println("Версия сервлета : " + config.getInitParameter("version")); 
		super.init();
	}
	
	/**
	 * Метод вызывается при запросе на адрес сервлета 
	 */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service");
		super.service(arg0, arg1);
	}

	/**
	 * Метод вызывается при get запросе
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do GET");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Метод вызывается при post запросе
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do POST");
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * Метод вызывается при уничтожении сервлета
	 */
	@Override
	public void destroy() {
		System.out.println("destroy");
		super.destroy();
	}
}