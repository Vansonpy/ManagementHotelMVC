editButtonsBorder = document.querySelectorAll(".editButton-border");
editButtons = document.querySelectorAll(".editButton");
boxUpdates = document.querySelectorAll(".text");
//boxs = document.querySelectorAll(".box");
//files = document.querySelectorAll(".file");
//fileImgs = document.querySelectorAll(".fileImg");

Array.from(editButtonsBorder).forEach(function(editBtn, index) {
	editBtn.addEventListener("click", function() {
		if (editBtn.innerText == "🖊") {
			let inputs = boxUpdates[index].querySelectorAll("input");
			let textareas = boxUpdates[index].querySelector("textarea");
			Array.from(inputs).forEach(function(input) {
				input.removeAttribute("readonly");
			})
			editBtn.innerText = "+";
			textareas.removeAttribute("readonly");
			
		} else {
			let inputs = boxUpdates[index].querySelectorAll("input");
			let textareas = boxUpdates[index].querySelector("textarea");
			Array.from(inputs).forEach(function(input) {
				input.setAttribute("readonly","readonly");
			})
			textareas.setAttribute("readonly","readonly");
			editBtn.innerText = "🖊";
			editBtn.type = "submit";
		}

	})
})

