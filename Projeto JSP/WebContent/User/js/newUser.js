function validateForm(){
	let form = document.getElementById("formUser");
	var isValid = true;
	 [...form.elements].forEach(function(field){
         if(['name', 'lastName', 'login', 'password'].indexOf(field.name) > -1 && !field.value){
             field.classList.add('invalid');
             isValid = false;
         }        
         
         return isValid;
     });
	 
	 
}