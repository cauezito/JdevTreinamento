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

$(function () {
	  $('[data-toggle="popover"]').popover()
	})