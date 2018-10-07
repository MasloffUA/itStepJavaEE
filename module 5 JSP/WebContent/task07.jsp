<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<CENTER>
		<TABLE BORDER=5 BGCOLOR="#EF8429">
			<TR>
				<TH CLASS="TITLE">Использование JavaServer Pages
		</TABLE>
	</CENTER>
	<P>Некое динамическое содержание созданное с использованием
		различных механизмов JSP:
	<UL>
		<LI><B>Выражение.</B><BR> Имя вашего хоста: <%=request.getRemoteHost()%>.
		
		<LI><B>Scriptlet.</B><BR> <%
 	out.println("Дополнительные данные запроса: " + request.getQueryString());
 %>
		<LI><B>Объявление (совместно с выражением).</B><BR> <%!private int accessCount = 0;%>
			Количество обращений к странице с момента загрузки сервера: <%=++accessCount%>
		<LI><B>Директива (совместно с выражением).</B><BR> <%@ page
				import="java.util.*"%> Текущая дата: <%=new Date()%>
	</UL>

</body>
</html>