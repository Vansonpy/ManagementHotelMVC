var checkIsEmpty = document.querySelectorAll(".checkIsEmpty");
var save = document.querySelector("input[name=save]");
var pageName = document.querySelector("input[name=pageName]");
var select = document.querySelector("select[name=select]");
Array.from(checkIsEmpty).forEach(function(input){
	input.addEventListener("keyup",function(){
		Array.from(checkIsEmpty).forEach(function(input){
			if(input.value){
				save.removeAttribute("disabled");
			}else{
				save.setAttribute("disabled","disabled");
			}
		})
	})
})
save.addEventListener("click",function(){
	pageName.value = select.value; 
})