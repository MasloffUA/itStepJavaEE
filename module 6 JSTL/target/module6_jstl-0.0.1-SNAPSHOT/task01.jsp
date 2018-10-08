<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <p align="center">Тэг c:out Используется для вставки данных в результат.</p>
	<h1 align="center"><c:out value="Hello World" /></h1>
	<h2 align="center"><c:out value="${(12+16)/3}" /></h2>
</body>
</html>