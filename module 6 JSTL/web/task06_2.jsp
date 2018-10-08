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
	<jsp:useBean id="nameBean" class="ua.step.beans.NameBean" />

	<c:set var="namesArray" value="${nameBean.namesAsArray}" />

	<c:forEach items="${namesArray}" var="name">
		<h3 align="center">
			<c:out value="${name}" />
		</h3>
	</c:forEach>

	<p align="center">
		<c:url value="/index.jsp" var="inputURL" />
		<a href="${inputURL}">Ссылка на главную</a>
	</p>
</body>
</html>