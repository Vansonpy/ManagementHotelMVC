const sliderMain = document.querySelector(".slider-wrapper");
const sliderItems = document.querySelectorAll(".slider-item");
const nextBtn = document.querySelector(".slider-next");
const prevBtn = document.querySelector(".slider-prev");
const dotsItem = document.querySelectorAll(".slider-bnt");
const imageButton = document.querySelectorAll(".image-button");
const sliderItemWidth = 1320;
let postionX = 0;
let indexX = 0;

function slideShow() {
	if (indexX >= dotsItem.length) {
		indexX = 0;
	}
	postionX = -1 * indexX * sliderItemWidth;
	sliderMain.style = `transform: translateX(${postionX}px)`;
	Array.from(dotsItem).forEach((re) => re.classList.remove("active"));
	dotsItem[indexX].classList.add("active");
	indexX++;
	setTimeout(slideShow, 5000);
}
slideShow();
Array.from(imageButton).forEach(function(dot, index) {
	dot.addEventListener('click', function() {
		let valueDot = Number(dot.value);

		if (valueDot == -1) {
			if (indexX <= 0) {
				indexX = dotsItem.length;
			}
			indexX--;
			console.log(indexX)
			postionX = -1 * indexX * sliderItemWidth;
			sliderMain.style = `transform: translateX(${postionX}px)`;
		} else if (valueDot >= 1) {
			if (indexX >= dotsItem.length - 1) {
				indexX = -1;
			}
			indexX++;
			console.log(indexX)
			postionX = -1 * indexX * sliderItemWidth;
			sliderMain.style = `transform: translateX(${postionX}px)`
		}
		Array.from(dotsItem).forEach((re) => re.classList.remove("active"));
		dotsItem[indexX].classList.add("active");
	})
});
Array.from(dotsItem).forEach(function(dot, index) {
	if (index == 0) {
		dot.classList.add('active');
	}
	dot.addEventListener('mouseenter', function() {
		postionX = -1 * index * sliderItemWidth;
		sliderMain.style = `transform:translateX(${postionX}px)`;
		Array.from(dotsItem).forEach(function(dot) {
			dot.classList.remove("active");
		})
		indexX = index;
		console.log(indexX)
		dot.classList.add('active');
	})

})
//--------------------------------------------------------------
const editBtns = document.querySelectorAll(".bntEdit");
const editBtnBorders = document.querySelectorAll(".bntEdit-border");
const text = document.querySelectorAll(".text");
Array.from(editBtnBorders).forEach(function(editBtn, index) {
	editBtn.addEventListener("click", function() {
		if (editBtn.innerText == "🖊") {
			let inputs = text[index].querySelectorAll("input");
			let textareas = text[index].querySelectorAll("textarea");
			Array.from(inputs).forEach(function(input) {
				input.removeAttribute("readonly");
			})
			textareas[index].removeAttribute("readonly");
			editBtn.innerText = "+";
		} else {
			let inputs = text[index].querySelectorAll("input");
			let textareas = text[index].querySelectorAll("textarea");
			Array.from(inputs).forEach(function(input) {
				input.setAttribute("readonly", "readonly");
			})
			textareas[index].setAttribute("readonly", "readonly");
			editBtn.innerText = "🖊";
			editBtn.type = "submit";
		}

	})
})

Array.from(editBtns).forEach(function(editBtn, index) {
	editBtn.addEventListener("click", function() {
		if (editBtn.innerText == "🖊") {
			let inputs = text[index].querySelectorAll("input");
			let textareas = text[index].querySelectorAll("textarea");
			Array.from(inputs).forEach(function(input) {
				input.removeAttribute("readonly");
			})
			textareas[index].removeAttribute("readonly");
			editBtn.innerText = "+";
		} else {
			let inputs = text[index].querySelectorAll("input");
			let textareas = text[index].querySelectorAll("textarea");
			Array.from(inputs).forEach(function(input) {
				input.setAttribute("readonly", "readonly");
			})
			textareas[index].setAttribute("readonly", "readonly");
			editBtn.innerText = "🖊";
			editBtn.type = "submit";
		}

	})
})