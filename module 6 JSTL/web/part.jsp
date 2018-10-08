<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<c:out value="Меняю" />
<c:out value="${param.awl}" /> 
<c:set var="soap" value="мыло" scope="request"/>