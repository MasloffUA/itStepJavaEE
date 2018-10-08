<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="alert alert-success" role="alert">Для оформления данной страницы используется bootstrap 3.3</div>
		<form method="post">
			<div class="form-group">
				<label for="text">Фамилия</label><input type="text" name="lastName"
					class="form-control">
			</div>
			<div class="form-group">
				<label for="text">Имя</label><input type="text" name="firstName"
					class="form-control">
			</div>
			<div class="form-group">
				<label for="text">Зарплата</label><input type="number" name="salary"
					class="form-control">
			</div>
			<input type="submit" class="btn btn-default">
		</form>

		<jsp:useBean id="controller" class="ua.step.beans.EmployeeController"
			scope="session" />

		<!-- проверка типа запроса -->
		<c:if test="${pageContext.request.method=='POST'}">
			<jsp:useBean id="employee" class="ua.step.beans.Employee" />
			<jsp:setProperty name="employee" property="lastName"
				value="${param.lastName}" />
			<jsp:setProperty name="employee" property="firstName"
				value="${param.firstName}" />
			<c:catch var="errormsg">
				<jsp:setProperty name="employee" property="salary"
					value="${param.salary}" />
			</c:catch>
        ${controller.addEmployee(employee)}

    </c:if>

		<table border="1" class="table table-striped">
			<thead>
				<tr>
					<th>Фамилия</th>
					<th>Имя</th>
					<th>Зарплата</th>
				</tr>
			</thead>
			<c:forEach items="${controller.employes}" var="employee">
				<tr>
					<td valign="top"><c:out value="${employee.firstName}" /></td>
					<td valign="top"><c:out value="${employee.lastName}" /></td>
					<td valign="top"><c:out value="${employee.salary}" /></td>
				
				</tr>
				${controller.addSalary(employee.salary)}
			</c:forEach>
		</table>
	</div>
	<div align="center" class="alert alert-info">Измени пример, чтобы
		выводилась ИТОГО по зароботной плате: <c:out value="${controller.getTotalSalary()}" /></div>
	<p align="center">
		<c:url value="/index.jsp" var="inputURL" />
		<a href="${inputURL}">Ссылка на главную</a>
	</p>
</body>
</html>