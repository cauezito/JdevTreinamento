<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Receber parâmetro</title>
</head>
<body>
	<%= request.getParameter("name") %>
	<%! int cont = 2; %>
	<%= cont %>
	<%= session.getAttribute("course") %>
</body>
</html>