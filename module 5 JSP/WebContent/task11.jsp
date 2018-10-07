<%@page import="com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="errorPage.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>

    <body>
    <jsp:useBean id="test" class="ua.step.example.UserBean" />
    <%@ page import="java.util.*"%>
    <%@ page import="ua.step.example.User"%>
    <%
    List <User> users = test.getUsers();
    for (User u : users){
   		out.append(u.getId() + " " + u.getLogin() + " " + u.getPassword()+"<br>");
 
    }
    %>	
    </body>
</body>
</html>