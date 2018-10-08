<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<input type="number" name="age" /> <input type="submit"
			value="Отправить" />
	</form>

	<c:if test="${param.age gt 12}">
        Возраст более 12 лет
    </c:if>

	<c:if test="${param.age lt 25}">
        и менее 25 лет
    </c:if>
	<ul>
		<li>eq – проверка на равенство</li>
		<li>ne – проверка на неравенство</li>
		<li>lt – строго менее чем</li>
		<li>gt – строго более чем</li>
		<li>le – меньше либо равно чему-то</li>
		<li>ge – больше или равно чему-то</li>
	</ul>
	<p>Тэг c:if не предпологает наличие else, таким образом можно
		сделать несколько вариантов поведения, нельзя сделать некоторое
		значение по-умолчанию.</p>

	<c:url value="/index.jsp" var="inputURL" />
	<a href="${inputURL}">Ссылка на главную</a>
</body>
</html>