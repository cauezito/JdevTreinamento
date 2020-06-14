<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>
</head>
<body>

<h1>Cadastro</h1>

<form action="saveUser" method="post">
	<table>
		<tr>
			<td>Login:</td>
			<td><input type="text" id="login" name="login"></td>
		</tr>	
		<tr>
			<td>Senha:</td>
			<td><input type="password" id="password" name="password"></td>
		</tr>	
	</table>
	<button type="submit">Salvar</button>
</form>
</body>
</html>