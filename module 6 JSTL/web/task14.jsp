<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<img src="https://www.stihi.ru/pics/2014/02/04/8008.jpg">
		<c:set value="Мама мыла Раму, Рама теперь весь чистый и довольный"
			var="boy" />
		<c:forEach items="${fn:split(boy, ' ')}" var="token">
			<div>
				<c:out value="${token}" />
			</div>
		</c:forEach>

		<c:if test="${fn:contains(boy, 'рама')}">
			<!-- регистро зависимый -->
			<h1>Харе кришна, харе Рама</h1>
		</c:if>
		
		<c:if test="${fn:containsIgnoreCase(boy, 'рама')}">
			<!-- регистро зависимый -->
			<h1>Рама не деревянный, просто у него плохая пластика.</h1>
		</c:if>
	</div>
	
	<p align="center">
		<c:url value="/index.jsp" var="inputURL" />
		<a href="${inputURL}">Ссылка на главную</a>
	</p>
</body>
</html>