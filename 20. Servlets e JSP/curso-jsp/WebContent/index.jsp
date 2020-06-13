<jsp:useBean id="calculator" class="br.com.cauezito.beans.TestBean" type="br.com.cauezito.beans.TestBean" scope="page"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso JSP</title>
</head>
<body>
	<h1>Index</h1>
	<%= "Utilizando o método da classe TestBean: " + calculator.calc(10) %>
</body>
</html>