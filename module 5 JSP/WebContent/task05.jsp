<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>О счастливчик.</title>
</head>
<body>
	<%! int win = 0; %>
	<%! int lose = 0; %>
	<%
		
		double num = Math.random();
		if (num > 0.95) {
			win++;
	%>
	<h2>Вам сегодня повезло!</h2>
	<p>
		(<%=num%>)
	</p>
	<%
		} else {
			lose++;
	%>
	<h2>На этот раз не повезло, но игра продолжается ...</h2>
	<p>
		(<%=num%>)
	</p>
	<%
		}
	%>
	<a href="<%=request.getRequestURI()%>"><h3>Попробуй ещё</a>
	<h5> Вы выиграли:  <%=win%> раз. </h5>
	<h5> Вы проиграли:  <%=lose%> раз. </h5>
	<p></p>
	<p>
		<a href="index.jsp">Вернуться на главную</a>
	</p>
</body>
</html>