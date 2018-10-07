<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- обявление бина с использованием Действия -->
	<jsp:useBean id="test" class="ua.step.example.SimpleBean" />
	<%-- <jsp:setProperty name="test" property="message" value="Привет, WWW" /> --%>
	<jsp:getProperty name="test" property="message" />

	<div></div>
	<table class="src" style="border-collapse: collapse; width: 100%"
		cellspacing="0" cellpadding="5" border="1">
		<tbody>
			<tr>
				<th style="width: 30%">Syntax</th>
				<th>Purpose</th>
			</tr>
			<tr>
				<td>jsp:include</td>
				<td>Includes a file at the time the page is requested</td>
			</tr>
			<tr>
				<td>jsp:useBean</td>
				<td>Finds or instantiates a JavaBean</td>
			</tr>
			<tr>
				<td>jsp:setProperty</td>
				<td>Sets the property of a JavaBean</td>
			</tr>
			<tr>
				<td>jsp:getProperty</td>
				<td>Inserts the property of a JavaBean into the output</td>
			</tr>
			<tr>
				<td>jsp:forward</td>
				<td>Forwards the requester to a new page</td>
			</tr>
			<tr>
				<td>jsp:plugin</td>
				<td>Generates browser-specific code that makes an OBJECT or
					EMBED tag for the Java plugin</td>
			</tr>
			<tr>
				<td>jsp:element</td>
				<td>Defines XML elements dynamically.</td>
			</tr>
			<tr>
				<td>jsp:attribute</td>
				<td>The <tt>jsp:attribute</tt> element allows you to define the
					value of a tag attribute in the <strong>body</strong> of an XML
					element instead of in the value of an XML attribute.
				</td>
			</tr>
			<tr>
				<td>jsp:body</td>
				<td>Defines dynamically defined XML element's body.</td>
			</tr>
			<tr>
				<td>jsp:text</td>
				<td>Use to write template text in JSP pages and documents.</td>
			</tr>
		</tbody>
	</table>
</body>
</html>