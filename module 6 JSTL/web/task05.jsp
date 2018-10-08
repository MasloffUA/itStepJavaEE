<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form>
			<input type="number" name="age" /> <input type="submit"
				value="Отправить" />
		</form>
	</div>
	<h3 align="center">
		<c:choose>
			<c:when test="${param.age le 0}">
      Вы еще не родились
  </c:when>
			<c:when test="${param.age lt 10}">
      Ребенок
  </c:when>
			<c:when test="${param.age lt 16}">
      Подросток
  </c:when>
			<c:when test="${param.age lt 50}">
      Взрослый
  </c:when>
			<c:when test="${param.age lt 120}">
      Пожилой
  </c:when>
			<c:otherwise>
          Ну здравствуй Дункан.
        </c:otherwise>
		</c:choose>
	</h3>
	<c:url value="/index.jsp" var="inputURL" />
	<p align="center"><a href="${inputURL}">Ссылка на главную</a></p>
</body>
</html>