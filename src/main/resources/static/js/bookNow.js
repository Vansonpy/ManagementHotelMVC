bookButton = document.querySelector("input[name=book]");
formBook = document.querySelector(".formBook");
inputCheck = document.querySelectorAll(".inputcheck");
inputConfirm = document.querySelectorAll(".inputConfirm");
bookButton.addEventListener("click",function(){
	inputConfirm[0].value=inputCheck[0].value;
	inputConfirm[1].value=inputCheck[1].value;
	inputConfirm[2].value=inputCheck[2].value;
	inputConfirm[3].value=inputCheck[3].value;
	inputConfirm[4].value=inputCheck[4].value;
	formBook.style.display='block';
})