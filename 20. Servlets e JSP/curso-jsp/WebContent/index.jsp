<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso JSP</title>
</head>
<body>
	<form action="receive.jsp" method="get">
		<input type="text" id="name" name="name">
		<button type="Submit">Save!</button>
	</form>
	
	<%= application.getInitParameter("State") %>
	<% session.setAttribute("course", "Java WEB"); %>
</body>
</html>