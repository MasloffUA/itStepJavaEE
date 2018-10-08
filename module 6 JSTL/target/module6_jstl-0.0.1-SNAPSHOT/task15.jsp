<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>fn:containsIgnoreCase() example</title>
</head>
<body>
	<div align="center">
		<h3>Страница проверяет, содержится ли одна строка в другой</h3>
		<form method="post">
			<table>
				<tr>
					<td>string1</td>
					<td><input type="text" name="string1"></td>
				</tr>
				<tr>
					<td>string2</td>
					<td><input type="text" name="string2"></td>
				</tr>
				<tr>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>

		<c:if test="${pageContext.request.method=='POST'}">
			<c:if test="${fn:containsIgnoreCase(param.string1, param.string2)}">
				<c:out value="string1 содержит string2" />
				<br>
			</c:if>

			<c:if test="${fn:containsIgnoreCase(param.string2, param.string1)}">
				<c:out value="string2 содержит string1" />
			</c:if>
		</c:if>
	</div>
	   
    <div align="center">
        <c:url value="/index.jsp" var="inputURL" />
        <a href="${inputURL}">Ссылка на главную</a>
    </div>
</body>
</html>