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
	<jsp:forward page="receive.jsp">
		<jsp:param value="Teste do forward" name="test"/>	
	</jsp:forward>
</body>
</html>