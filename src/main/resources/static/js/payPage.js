var priceTotal = document.querySelectorAll(".totalPrice");


let checkinDate = convertDate(priceTotal[2].innerHTML);
let checkoutDate = convertDate(priceTotal[3].innerHTML);
let price =  Number(priceTotal[0].innerHTML);
let dateOfStay = Number((checkoutDate - checkinDate)/86400000).toFixed(0);
priceTotal[1].innerHTML= price*dateOfStay;
function convertDate(date){	
	let arr = [];
	arr = date.split(" ");
	let dateConvert =new Date(arr[5]+arr[1]+arr[2]);
	return dateConvert;
}
