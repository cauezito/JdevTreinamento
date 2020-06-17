 $(document).ready(function() {
	
	function validateForm(){
		let form = document.getElementById("formUser");
		let isValid = true;
		[...form.elements].forEach(function(field){
			if(['name', 'lastName', 'login', 'password'].indexOf(field.name) > -1 && !field.value 
					|| field.value.indexOf(' ') >= 0){
				field.classList.add('invalid');
				isValid = false;
			} 
		});  
		return isValid;
	}
	
	$("#zipCode").blur(function() {
		let cep = $(this).val().replace(/\D/g, '');

		if (cep != "") {
	
			let validacep = /^[0-9]{8}$/;
	
			if(validacep.test(cep)) {
	
				$("#address").val("...");
				$("#area").val("...");
				$("#locality").val("...");
				$("#federatedUnit").val("...");
	
				$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
	
					if (!("erro" in dados)) {
						$("#address").val(dados.logradouro);
						$("#area").val(dados.bairro);
						$("#locality").val(dados.localidade);
						$("#federatedUnit").val(dados.uf);
					} //end if.
					else {
						alert("CEP não encontrado.");
					}
				});
			} 
			else {
				alert("Formato de CEP inválido.");
			}
		} 
		else {
		}
	});
	
	$(function () {
		$('[data-toggle="popover"]').popover()
	});
 });
