<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.util.Date, java.text.*"%>
	<div>Некоторые переменные из сервлета доступны в JSP:</div>
	<ul type="disc">
		<li>request : javax.servlet.http.HttpServletRequest</li>
		<li>response: javax.servlet.http.HttpServletResponse</li>
		<li>out: javax.servlet.jsp.JspWriter</li>
	</ul>
	<p>
		Request URI =
		<%=request.getRequestURI()%></p>
	<p>
		Header =
		<%=request.getHeader("Host")%></p>
	<p>
		Addres =
		<%=request.getRemoteHost()%></p>
	<%
		out.println("<h4>Now is " + new Date() + "</h4>");
	%>
</body>
</html>