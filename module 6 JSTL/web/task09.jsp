<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">
		<!-- передача параметра в импортируюмую страницу -->
		<c:import url="part.jsp" charEncoding="utf-8">
			<c:param name="awl" value="шило" />
		</c:import>
		<c:out value="на " />
		
		<!-- получение значения параметра из импортируемой страницы -->
		<c:out value="${soap}" />
	</h1>

    <p align="center">
        <c:url value="/index.jsp" var="inputURL" />
        <a href="${inputURL}">Ссылка на главную</a>
    </p>
</body>
</html>