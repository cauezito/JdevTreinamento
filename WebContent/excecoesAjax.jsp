<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Capturando exceções com Ajax</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" 
crossorigin="anonymous"></script>
</head>
<body>

	<input type="text" value="Cauê Santos" id="name">
	<button type="button" onclick="check();">Enviar</button>
 
	
</body>

	<script>
		function check(){
			let nameForm = $('#name').val();
			
			$.ajax({
				method: "POST",
				url: "catchExceptions", //servlet
				data: {name : nameForm} // key, value
			}).done(function(response){ //Ok
				alert("Sucesso: " + response);
				//to do
			}).fail(function(xhr, status, errorThrown){
				alert("Falha: " + xhr.responseText);
				//to do
			});
		}
	</script>

</html>