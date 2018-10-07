<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <h3>Choose an author:</h3>
  <form method="get">
    <input type="checkbox" name="author" value="James Gosling">James
    <input type="checkbox" name="author" value="Joshua J. Bloch">Joshua
    <input type="checkbox" name="author" value="Bruce Eckel">Bruce
        <input type="checkbox" name="author" value="Doug Lea">Doug
    <input type="submit" value="Query">
  </form>
  
  <!-- Скриплет -->
  <%
      String[] authors = request.getParameterValues("author");
      if (authors != null) {
  %>
    <h3>You have selected author(s):</h3>
    <ul>
  <%
      for (int i = 0; i < authors.length; ++i) {
  %>
        <li><%= authors[i] %></li>
  <%
      }
  %>
    </ul>
    <a href="<%= request.getRequestURI() %>">BACK</a>
  <%
  }
  %>
  
      <p></p>
    <p><a href="index.jsp">Home</a></p>
</body>
</html>