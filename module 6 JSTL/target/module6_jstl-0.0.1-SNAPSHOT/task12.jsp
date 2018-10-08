<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" scope="page"/>
	<h1 align="center">
		Текущая дата и время:
		<fmt:formatDate value="${date}" pattern="d MMMM, yyyy, H:mm:ss" />

		<br> В Минске:
		<fmt:timeZone value="Europe/Minsk">
			<fmt:formatDate value="${date}" pattern="yyyy.MMM.dd hh:mm:ss" />
		</fmt:timeZone>

		<br> В Антарктиде:
		<fmt:timeZone value="Antarctica/Casey">
			<fmt:formatDate value="${date}" pattern="dd.MM.yyyy hh:mm:ss" />
		</fmt:timeZone>
	</h1>

	<p align="center">
		<c:url value="/index.jsp" var="inputURL" />
		<a href="${inputURL}">Ссылка на главную</a>
	</p>
</body>
</html>