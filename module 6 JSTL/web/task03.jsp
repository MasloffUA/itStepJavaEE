<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<p align="center">Тэг c:set Используется, чтобы задать переменную.</p>
	<p align="center">Параметр scope не обязательный, по-умолчанию
		используется scope=”page”.</p>

	<c:set var="name" scope="page">Вася на странице </c:set>
	<p align="center">
		<c:set var="name" scope="request">Вася в запросе</c:set>
	</p>
	<p align="center">
		<c:set var="name" scope="session"> Вася в сессии</c:set>
	</p>
	<p align="center">
		<c:set var="name" scope="application">Вася в приложении</c:set>
	</p>
	<p align="center">
		<c:out value="${sessionScope.name}" />
	</p>
	<p align="center">
		<c:out value="${name}" />
	</p>
	<c:remove var="name" />

	<!-- удаление переменной из всех контекстов -->
	<p align="center">
		<c:out value="${name}" />
	</p>
	<p align="center">
		<c:out value="${sessionScope.name}" />
	</p>


	<p align="center">Если контекст не указан, то работает правило
		поиска нужного контекста: ищем переменную внутри pageScope, если не
		найдено внутри requestScope, если опять не найдено внутри sessionScope
		и, наконец, внутри applicationScope.</p>

	<c:url value="/index.jsp" var="inputURL" />
	<p align="center">
		<a href="${inputURL}">Ссылка на главную</a>
	</p>

</body>
</html>