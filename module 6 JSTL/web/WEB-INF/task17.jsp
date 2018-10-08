<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>JSTL sql:query Tag</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<table class="table">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Фамилия</th>
				<th scope="col">Имя</th>
				<th scope="col">Департамент</th>
				<th scope="col">Зарплата</th>
			</tr>
			<c:forEach var="row" items="${employees}">
				<tr>
					<th scope="row"><c:out value="${row.id}" />
					<td><c:out value="${row.firstName}" />
					<td><c:out value="${row.lastName}" />
					<td><c:out value="${row.depart}" />
					<td><c:out value="${row.salary/100}" />
				</tr>
			</c:forEach>
			<tr> Список департаментов: </tr>
			<c:forEach var="row" items="${departs}">
				<tr>
					<th scope="row"><c:out value="${row.key}" />
					<td><c:out value="${row.value}" />

				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		Добавить департамент:
		 <form method="post">
			Введите новый департамент:<input type="text" name="dep"></input>
		<input type="submit" value="Добавить"></form>
		
	</div>
	
	<div align="center">
		<c:url value="/index.jsp" var="inputURL" />
		<a href="${inputURL}">Ссылка на главную</a>
	</div>
	
	
</body>
</html>