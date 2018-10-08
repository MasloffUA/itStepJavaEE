<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>

<c:set var="i" value="1" />
<body>
	<div align="center" class="alert alert-info">
		<b>JSTL</b> является частью Java EE API, и большинства контейнеров
		servlet. Но чтобы использовать JSTL в ваших страницах JSP, вам
		необходимо скачать библиотеки JSTL для вашего контейнера Servlet
		(container servlet). Вам нужно поместить библиотеки в WEB-INF / Lib
		проекта. Если ваш проект является проектом Maven, вам нужно добавить
		зависимость на эти библиотеки в pom.xml
	</div>
	<div class="container-fluid">
		<h2 align="center">JSTL Core Tags</h2>
		<p align="center">
			<a href="task00.jsp">Task 0</a> Ссылки и параметры
		</p>
		<p align="center">
			<a href="task01.jsp">Task <c:out value="${i}" /></a> Вывод данных
		</p>
		<p align="center">
			<a href="task02.jsp?name=Vasya">Task 2</a> Вывод параметров из
			запроса
		</p>
		<p align="center">
			<a href="task03.jsp">Task 3</a> Объявление и удаление параметров в
			различных контекста приложения
		</p>
		<p align="center">
			<a href="task04.jsp?age=33">Task 4</a> Условный оператор
		</p>
		<p align="center">
			<a href="task05.jsp?age=33">Task 5</a> Условный выбор
		</p>
		<p align="center">
			<a href="task06_1.jsp">Task 6.1</a> Цикл
		</p>
		<p align="center">
			<a href="task06_2.jsp">Task 6.2</a> Цикл с массивом
		</p>
		<p align="center">
			<a href="task07.jsp">Task 7</a> Цикл от и до
		</p>
		<p align="center">
			<a href="task08.jsp">Task 8</a> Цикл с шагом
		</p>
		<p align="center">
			<a href="task09.jsp">Task 9</a> Импорт страниц
		</p>
		<p align="center">
			<a href="task10.jsp">Task 10</a> Перехват исключений
		</p>
		<p align="center">
			<a href="task11.jsp">Task 11</a> Ввод данных из формы
		</p>
		<h2 align="center">JSTL Functions</h2>
		<p align="center">
			<a href="task12.jsp">Task 12</a> Форматирование даты и времени
		</p>
		<p align="center">
			<a href="task13.jsp">Task 13</a> Форматирование чисел
		</p>
		<p align="center">
			<a href="task14.jsp">Task 14</a> Функции 1
		</p>
		<p align="center">
			<a href="task15.jsp">Task 15</a> Функции 2
		</p>
		<h2 align="center">JSTL SQL</h2>
		<p align="center">
			<a href="task16.jsp">Task 16</a> SQL
		</p>
	</div>
</body>
</html>