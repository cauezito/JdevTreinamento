<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
<link href="vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="Product/css/product.css" rel="stylesheet" media="all">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body style="background-color: #63707e;">
	<div class="container-fluid">
		<ul class="nav" style="padding-top: 10px; height: 70px; margin: 0;">
			<li class="nav-item"> 
				<img alt="Voltar" src="vendor/img/back.png" class="nav-link" onclick="goBack();" title="voltar">
			</li>
			<li class="nav-item"><a class="nav-link" href="PublicPages/login.jsp"> <img
					alt="sair" src="vendor/img/logout.png" title="sair">
			</a></li>
		</ul>

		<!-- Button trigger modal -->
		<button type="button" class="btn btn-light mt-4 mb-3"
			data-toggle="modal" data-target="#modalProduct">Novo produto</button>
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

		<!-- Modal for new/edit product -->
		<div class="modal fade" id="modalProduct" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Cadastrar Produto</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times; </span>
						</button>
					</div>
					<div class="modal-body">
						<div class="page-wrapper p-t-10 p-b-20 font-poppins">
							<div class="wrapper wrapper--w680">
								<form method="POST" action="manageProduct" id="formProduct"
									onsubmit="return validateForm() ? true : false">
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
									</div>
									<div class="col-3">
										<div class="input-group">
											<input class="input--style-4 form-control" readonly
												type="text" name="id" value="${product.id}" placeholder="ID">
										</div>
									</div>
									<div class="col-12">
										<div class="input-group">
											<label class="label">Nome </label> <input
												class="input--style-4" type="text" name="name"
												value="${product.name}">
										</div>
									</div>
									<div class="col-12">
										<div class="input-group">
											<label class="label">Descrição</label> <input
												class="input--style-4" type="text" name="desc"
												value="${product.desc}">
										</div>
									</div>
									<div class="row row-space">
										<div class="col-3">
											<div class="input-group">
												<label class="label">Quantidade</label> <input
													class="input--style-4" type="number" name="quantity"
													value="${product.quantity}" min="1">
											</div>
										</div>
										<div class="col-3">
											<div class="input-group">
												<label class="label">Valor</label> <input
													class="input--style-4" type="number" name="value"
													value="${product.value}">
											</div>
										</div>
										<div class="col-6">
											<label class="label">Categoria</label> <select
												id="categories" class="form-control" name="category_id">

												<c:forEach items="${categories}" var="category">
													<option value="${category.id}" id="${category.id}"
														<c:if test="${category.id == product.category.id}">
        													<c:out value="selected=selected"/>
        													</c:if>>
														${category.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="p-t-15">
										<button class="btn btn--radius-2 btn--blue" type="submit">Salvar
										</button>
										<a href="manageProduct?action=listAll">
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
		<!-- /Modal for new/edit product -->

		<!-- Table product -->
		<div class="card">
			<div class="card-body">
				<table class="table table-striped table-responsive-md">
					<thead class="thead-dark">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nome</th>
							<th scope="col">Descrição</th>
							<th scope="col">Quantidade</th>
							<th scope="col">Valor</th>
							<th scope="col">Categoria</th>
							<th scope="col">Deletar</th>
							<th scope="col">Editar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${products}" var="product">
							<tr>
								<th scope="row">${product.id}</th>
								<td>${product.name}</td>
								<td>${product.desc}</td>
								<td>${product.quantity}</td>
								<td>${product.value}</td>
								<td>${product.category.name}</td>
								<td><a class="ml-3"
									href="manageProduct?action=delete&id=${product.id}"> <img
										alt="Deletar" src="vendor/img/delete.png">
								</a></td>
								<td><a class="ml-2"
									href="manageProduct?action=update&id=${product.id}"> <img
										alt="Editar" src="vendor/img/edit.png">
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- /Table products -->
			</div>
		</div>
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
	<!-- Main JS-->
	<c:if test="${update}">
		<script>
	        $('#modalProduct').modal('show')
	        </script>
	</c:if>
	<script src="Product/js/product.js">
        </script>
</body>
</html>