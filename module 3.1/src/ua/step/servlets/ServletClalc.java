package ua.step.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class ServletClalc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int a;
	int b;
	int c;
	
	public void sum() {
		c = a+b;
	}
	
	public void min() {
		c = a-b;
	}
	
	public void mul() {
		c = a+b;
	}
	
	public void del() {
		c = a+b;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		a = Integer.parseInt(req.getParameter("a"));
		b = Integer.parseInt(req.getParameter("b"));
		String work = req.getParameter("work");
		if (work.equals("plus")) {
			sum();
		}
		else if (work.equals("minus")) {
			min();
		}
		else if (work.equals("multi")) {
			mul();
		}
		else if (work.equals("del")) {
			del();
		}
		
		PrintWriter pw = resp.getWriter();
		pw.append("Result: " + Integer.toString(c));
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("servlet2.html");
		dispatcher.forward(req, resp);
	}

	
	
	
	
	
	
	
}
