<jsp:useBean id="user" class="br.com.cauezito.beans.UserBean"
	type="br.com.cauezito.beans.UserBean" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=0.9">
<meta charset="ISO-8859-1">
<title>Entrar</title>
<link rel="icon" type="image/png" href="Login/images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="Login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="Login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="Login/css/util.css">
<link rel="stylesheet" type="text/css" href="Login/css/main.css">
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<c:if test="${msg != null}">
					<div class="alert alert-danger alert-dismissible fade show"
						role="alert">
						${msg}
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:if>
				<form class="login100-form validate-form flex-sb flex-w"
					action="Login" method="post">
					<span class="login100-form-title p-b-32"> Entrar no sistema
					</span> <span class="txt1 p-b-11"> Usu�rio </span>
					<div class="wrap-input100 validate-input m-b-36"
						data-validate="O usu�rio � necess�rio">
						<input class="input100" type="text" name="login" id="Login">
						<span class="focus-input100"></span>
					</div>

					<span class="txt1 p-b-11"> Senha </span>
					<div class="wrap-input100 validate-input m-b-12"
						data-validate="A senha � necess�ria!">
						<span class="btn-show-pass"> <i class="fa fa-eye"></i>
						</span> <input class="input100" type="password" name="password"
							id="password"> <span class="focus-input100"></span>
					</div>

					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox"
								name="remember-me"> <label class="label-checkbox100"
								for="ckb1"> Manter-me conectado </label>
						</div>

						<div>
							<a href="#" class="txt3"> Esqueceu a senha? </a>
						</div>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn" type="submit">Entrar</button>
					</div>

				</form>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendor/animsition/js/animsition.min.js"></script>
	<script src="vendor/bootstrap/js/popper.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="vendor/select2/select2.min.js"></script>
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
	<script src="vendor/countdowntime/countdowntime.js"></script>
	<script src="Login/js/main.js"></script>


</body>
</html>