<jsp:useBean id="calculator" class="br.com.cauezito.beans.TestBean" type="br.com.cauezito.beans.TestBean" scope="page"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Receber parâmetro</title>
</head>
<body>

	<h1>Receive</h1>
	<!-- Setando todas os atributos que vieram do formulário e que pertencem ao Bean "TestBean" -->
	<jsp:setProperty property="*" name="calculator"/>
	
	<!--  Recuperando os atributos -->
	Name: ${param.name}
	Gender: ${param.gender}
	
</body>
</html>