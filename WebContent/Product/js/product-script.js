function validateForm(){
	let form = document.getElementById("formProduct");
	let isValid = true;
	 [...form.elements].forEach(function(field){
         if(['name', 'desc', 'value'].indexOf(field.name) > -1 && !field.value || field.value.indexOf(' ') >= 0){
             field.classList.add('invalid');
             isValid = false;
         } 
     });	
	 
	 
	 return isValid;
}