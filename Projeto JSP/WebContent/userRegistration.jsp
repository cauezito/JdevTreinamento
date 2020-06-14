<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>
</head>
<body>

<h1>Cadastro</h1>

<form action="manageUser" method="post">
	<table>
		<tr>
			<td>Id:</td>
			<td>
				<input type="text" id="id" name="id" value="${user.id}">
			</td>
		</tr>	
		<tr>
			<td>Login:</td>
			<td>
				<input type="text" id="login" name="login" value="${user.login}">
			</td>
		</tr>	
		<tr>
			<td>Senha:</td>
			<td>
				<input type="password" id="password" name="password" value="${user.password}">
			</td>
		</tr>	
	</table>
	<button type="submit">Salvar</button>
</form>

	<table>
		<c:forEach items="${users}" var="user">
			<tr> 
				<td>
					<c:out value="${user.id}"></c:out>
				</td>
				<td>
					<c:out value="${user.login}"></c:out>
				</td>
				<td>
					<c:out value="${user.password}"></c:out>
				</td>
				<td>
					<a href="manageUser?action=delete&id=${user.id}">Excluir</a>
				</td>
				<td>
					<a href="manageUser?action=update&id=${user.id}">Atualizar</a>
				</td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>