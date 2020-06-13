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
	
	
	<form action="receive.jsp" method="post">
		<input type="text" id="name" name="name">
		<input type="text" id="gender" name="gender">
		<button type="submit">Finish!</button>
	</form>
</body>
</html>