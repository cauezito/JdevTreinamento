<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usu�rio</title>

<link href="vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="User/css/user.css" rel="stylesheet" media="all">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>Login</title>
</head>
<body style="background-color:#EBEBEB">
	<div class="container-fluid">
		<div class="row mb-4">
			<div class="col-12 text-center">
				<h4 class="display-4">Bem vindo, ${loginUser}!</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-md-1">
				<a href="manageUser?action=listAll"> 
				<img alt="Usu�rios"	title="Gerenciar usu�rios" src="vendor/img/team.png" width="64"
					height="64"> 
					Usu�rios
				</a>
			</div>
			<div class="col-sm-2 col-md-1">
				<a href="manageProduct?action=listAll"> <img alt="Produtos"
					title="Gerenciar Produtos" src="vendor/img/products.png">
					Produtos
				</a>
			</div>
		</div>
	</div>

</body>
</html>