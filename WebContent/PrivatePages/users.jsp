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
<link href="User/css/user.css" rel="stylesheet" media="all">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous">
	
</script>
<script src="vendor/jquery/jquery.js"></script>
</head>
<body style="background-color: #63707e;">
	<div class="container-fluid">
		<ul class="nav"
			style="padding-top: 10px; height: 70px; margin: 0;">
			<li class="nav-item"> 
				<img alt="Voltar" src="vendor/img/back.png" class="nav-link" onclick="goBack();" title="voltar">
			</li>
			<li class="nav-item">
				<a class="nav-link" href="manageUser?action=logout"> 
				<img alt="sair" src="vendor/img/logout.png" title="sair">
				</a>
			</li>
			<li class="nav-item">
				<form class="form-inline" method="POST" action="search">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Buscar um usuário..." aria-label="Search"
						name="description" id="description">
					<button class="btn btn-outline-light my-2 my-sm-0" type="submit">Pesquisar</button>
				</form>
			</li>
		</ul>
		<div class="row mt-2">
			<div class="col-2">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-light mt-4 mb-3"
					data-toggle="modal" data-target="#modalUser">Novo usuário</button>
			</div>
			<div class="col-6 search"
				style="margin: 25px 0 0 -70px; height: 45px;">
				<!-- Search form -->

			</div>
		</div>

		<div class="row">
			<div class="col-12">
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
			</div>
		</div>

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
								<form method="POST" action="manageUser"
									onsubmit="return validateForm() ? true : false" id="formUser"
									enctype="multipart/form-data">
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
											<div class="input-group ">
												<label class="label">Nome </label> <input
													class="input--style-4" type="text" name="name"
													value="${user.name}">
											</div>
										</div>
										<div class="col-6">
											<div class="input-group">
												<label class="label">Sobrenome</label> <input
													class="input--style-4" type="text" name="lastName"
													value="${user.lastName}">
											</div>
										</div>
									</div>
									<div class="row row-space">
										<div class="col-7">
											<div class="input-group">
												<label class="label">Telefone</label> <input
													class="input--style-4" type="text" name="phone"
													value="${user.phone}">
											</div>
										</div>
										<div class="col-4">
											<div class="input-group">
												<label class="label">Gênero </label>
												<div class="p-t-8">
													<label class="radio-container m-r-25">Masculino <input
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
									</div>

									<div class="row row-space">
										<div class="col-6">
											<div class="input-group">
												<label class="label">Nickname</label> <input
													class="input--style-4" type="text" name="login"
													value="${user.login}">
											</div>
										</div>
										<div class="col-6">
											<div class="input-group">
												<label class="label">Senha </label> <input
													class="input--style-4 " type="password" name="password"
													value="${user.password}">
											</div>
										</div>
									</div>

									<!-- Address -->
									<h6>Endereço</h6>
									<hr>
									<div class="row row-space">
										<div class="col-3">
											<div class="input-group">
												<label class="label">Cep</label> <input
													class="input--style-4" type="text" name="zipCode"
													value="${user.address.zipCode}" id="zipCode">
											</div>
										</div>
										<div class="col-9">
											<div class="input-group">
												<label class="label">Rua</label> <input
													class="input--style-4" type="text" name="address"
													value="${user.address.address}" id="address" readonly>
											</div>
										</div>
									</div>

									<div class="row row-space">
										<div class="col-5">
											<div class="input-group">
												<label class="label">Bairro </label> <input
													class="input--style-4" type="text" name="area"
													value="${user.address.area}" id="area" readonly>
											</div>
										</div>
										<div class="col-5">
											<div class="input-group">
												<label class="label">Cidade </label> <input
													class="input--style-4" type="text" name="locality"
													value="${user.address.locality}" id="locality" readonly>
											</div>
										</div>
										<div class="col-2">
											<div class="input-group">
												<label class="label">UF</label> <input
													class="input--style-4" type="text" name="federatedUnit"
													value="${user.address.federatedUnit}" id="federatedUnit"
													readonly>
											</div>
										</div>
									</div>

									<!-- /// -->

									<!-- PHOTO AND PDF -->
									<div class="row row-space">
										<div class="col-6">
											<div class="input-group">
												<label class="label">Foto</label> <input
													class="input--style-4" type="file" name="photo" id="photo">
											</div>
										</div>

									</div>

									<!-- /// -->


									<div class="p-t-15">
										<button class="btn btn--radius-2 btn--green" type="submit">Salvar
										</button>
										<a href="manageUser?action=listAll">
											<button type="button" class="btn btn--radius-2 btn--blue">Cancelar</button>
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

		<div class="card">
			<div class="card-body">
				<!-- Table users -->
				<table class="table table-striped table-responsive-md">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Foto</th>
							<th scope="col">Nome</th>
							<th scope="col">Sobrenome</th>
							<th scope="col">Gênero</th>
							<th scope="col">Telefone</th>
							<th scope="col">Endereço</th>
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
								<td><img src="${user.photo.tempPhoto}" width="32px"
									height="32px" /></td>
								<td>${user.name}</td>
								<td>${user.lastName}</td>
								<td>${user.gender}</td>
								<td>${user.phone}</td>
								<td>
									<button type="button" class="btn
								data-container="
										body" data-toggle="popover" data-placement="right"
										data-html="true"
										data-content="
								<b>Cep:</b> ${user.address.zipCode} <br/>
								<b>Rua:</b> ${user.address.address} <br/>
								<b>Bairro:</b> ${user.address.area} <br/>
								<b>Cidade:</b> ${user.address.locality} <br/>
								<b>UF: </b> ${user.address.federatedUnit}">
										<img alt="ver" src="vendor/img/eye.png"
											style="margin-top: -14px">
									</button>
								</td>
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
		</div>
	</div>


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
	<script src="User/js/user.js">
        </script>
	<!-- Main JS-->
	<c:if test="${update}">
		<script>
	        $('#modalUser').modal('show')
	      
	    </script>
	</c:if>

</body>
</html>
