var selectSearch = document.querySelector("select[name=select]");
var check = document.querySelector("input[name=check]");
var type = document.querySelector("input[name=type]");
check.addEventListener("click", function(){
	type.value = selectSearch.value;
	check.type = "submit";
})