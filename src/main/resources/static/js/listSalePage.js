var salePrices = document.querySelectorAll("input[name=salePrice]");
var statusSale = document.querySelectorAll("input[name=statuses]");
var statusCheck = document.querySelectorAll("input[name=buttonStatus]");
var totalPrices = document.querySelector("input[name=totalPrice]");
var checkinDates = document.querySelectorAll("input[name=checkinDate]");
var checkoutDates = document.querySelectorAll("input[name=checkoutDate]");
var prices = document.querySelectorAll("input[name=price]");
var cancelButtons = document.querySelectorAll("input[name=cancel]");
var aLink = document.querySelectorAll(".link");
var totalPrice = 0;
var search = document.querySelector("input[name=search]");
var statusSearch = document.querySelector("input[name=status]");
var selectSearch = document.querySelector("select[name=select]");
Array.from(salePrices).forEach(function(salePrice, index) {
	let checkoutDate = convertDate(checkoutDates[index].value);
	let checkinDate = convertDate(checkinDates[index].value);

	let dateOfStay = Number((checkoutDate- checkinDate)/86400000).toFixed(0);
	if (dateOfStay == 0) {
		salePrice.value = prices[index].value;
	} else {
		salePrice.value = prices[index].value * dateOfStay;
	}
	//if(statusSale[index].value==1){
		totalPrices.value = (Number(totalPrices.value) + Number(salePrice.value)).toFixed(0);
	//}
	if(statusSale[index].value==0){
		statusCheck[index].value = "Processing";
		statusCheck[index].classList.add("processing");
	}else if(statusSale[index].value==1){
		statusCheck[index].value = "Complete";
		statusCheck[index].classList.add("complete");
		aLink[index].style.display='none';
		cancelButtons[index].value="Checked";
		cancelButtons[index].setAttribute("disabled","disabled")
	}else{
		statusCheck[index].value = "Cancel";
		statusCheck[index].classList.add("cancel");
		aLink[index].style.display='none';
		cancelButtons[index].value="Checked";
		cancelButtons[index].setAttribute("disabled","disabled")
	}
}) 


function convertDate(date){
	let arr = [];
	arr = date.split("-");
	let dateConvert =new Date();
	dateConvert.setDate(arr[2]);
	dateConvert.setMonth(arr[1]);
	dateConvert.setYear(arr[0]);
	return dateConvert;
}

search.addEventListener("click", function(){
	if(selectSearch.value=="Processing"){
		statusSearch.value = 0;
		search.type = "submit";
		
	}else if(selectSearch.value=="Complete"){
		statusSearch.value = 1;
		search.type = "submit";
	}
	else if(selectSearch.value=="Cancel"){
		statusSearch.value = 2;
		search.type = "submit";
	}else if(selectSearch.value=="All"){
		statusSearch.value = 3;
		search.type = "submit";
	}
})

Array.from(cancelButtons).forEach(function(cancelButton,index){
	cancelButton.addEventListener("click",function(){
		if (confirm("You need delete Sale") == true) {
			cancelButton.type = "submit";
		}
	})
})