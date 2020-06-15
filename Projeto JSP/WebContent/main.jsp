<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de usuário</title>

<link href="vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="Main/css/mainNewUser.css" rel="stylesheet" media="all">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid">

		<!-- Button trigger modal -->
		<button type="button" class="btn btn-light mt-4 mb-3"
			data-toggle="modal" data-target="#modalUser">Novo usuário</button>
		<!-- Alert success -->
		<c:if test="${msgSuccess != null}">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				${msgSuccess}
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</c:if>
		<!-- /Alert success -->

		<!-- Modal for new/edit user -->
		<div class="modal fade" id="modalUser" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Cadastrar usuário</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times; </span>
						</button>
					</div>
					<div class="modal-body">
						<div class="page-wrapper p-t-10 p-b-20 font-poppins">
							<div class="wrapper wrapper--w680">
								<form method="POST" action="manageUser">
									<div class="row row-space">
										<div class="col-12 mb-4">
											<!-- Alert error -->
											<c:forEach var="msg" items="${msgValidation}">
												<div class="alert alert-danger alert-dismissible fade show"
													role="alert">
													${msg}
													<button type="button" class="close" data-dismiss="alert"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
											</c:forEach>
											<!-- /Alert error -->
										</div>
										<div class="col-3">
											<div class="input-group">
												<input class="input--style-4 form-control" readonly
													type="text" name="id" value="${user.id}" placeholder="ID">
											</div>
										</div>
									</div>
									<div class="row row-space">
										<div class="col-6">
											<div class="input-group">
												<label class="label">Nome </label> <input
													class="input--style-4" type="text" name="name"
													value="${user.name}">
											</div>
										</div>
										<div class="col-6">
											<div class="input-group">
												<label class="label">Sobrenome </label> <input
													class="input--style-4" type="text" name="lastName"
													value="${user.lastName}">
											</div>
										</div>
									</div>
									<div class="row row-space">
										<div class="col-2">
											<div class="input-group">
												<label class="label">Gênero </label>
												<div class="p-t-10">
													<label class="radio-container m-r-45">Masculino <input
														type="radio" checked="checked" name="gender"
														value="Masculino"> <span class="checkmark">
													</span>
													</label> <label class="radio-container">Feminino <input
														type="radio" name="gender" value="Feminino"> <span
														class="checkmark"> </span>
													</label> <label class="radio-container">Outro <input
														type="radio" name="gender" value="Outro"> <span
														class="checkmark"> </span>
													</label>
												</div>
											</div>
										</div>
										<div class="col-8">
											<div class="input-group">
												<label class="label">Telefone </label> <input
													class="input--style-4" type="text" name="phone"
													value="${user.phone}">
											</div>
										</div>
									</div>
									<div class="row row-space">
										<div class="col-6">
											<div class="input-group">
												<label class="label">Nickname </label> <input
													class="input--style-4" type="text" name="login"
													value="${user.login}">
											</div>
										</div>
										<div class="col-6">
											<div class="input-group">
												<label class="label">Senha </label> <input
													class="input--style-4" type="password" name="password"
													value="${user.password}">
											</div>
										</div>
									</div>
									<div class="p-t-15">
										<button class="btn btn--radius-2 btn--blue" type="submit">Salvar
										</button>
										<a href="manageUser?action=listAll">
											<button>Cancelar</button>
										</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /Modal for new/edit user -->

		<!-- Table users -->
		<table class="table table-striped table-responsive-md">
			<thead class="thead-dark">
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">Sobrenome</th>
					<th scope="col">Gênero</th>
					<th scope="col">Telefone</th>
					<th scope="col">Login</th>
					<th scope="col">Senha</th>
					<th scope="col">Deletar</th>
					<th scope="col">Editar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<th scope="row">${user.id}</th>
						<td>${user.name}</td>
						<td>${user.lastName}</td>
						<td>${user.gender}</td>
						<td>${user.phone}</td>
						<td>${user.login}</td>
						<td>${user.password}</td>
						<td><a class="ml-3"
							href="manageUser?action=delete&id=${user.id}"> <img
								alt="Deletar" src="vendor/img/delete.png">
						</a></td>
						<td><a class="ml-2"
							href="manageUser?action=update&id=${user.id}"> <img
								alt="Editar" src="vendor/img/edit.png">
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- /Table users -->
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous">
        </script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous">
        </script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous">
        </script>
	<script src="vendor/select2/select2.min.js">
        </script>
	<script src="Main/js/newUser.js">
        </script>
	<!-- Main JS-->
	<c:if test="${update}">
		<script>
	        $('#modalUser').modal('show')
	        </script>
	</c:if>
</body>
</html>
