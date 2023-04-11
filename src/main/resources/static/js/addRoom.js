const dropArea = document.querySelector(".drag-area");
const drapText = dropArea.querySelector("header");
const button = dropArea.querySelector("button");
const input = document.querySelector("input[name=multipartFile]");

button.addEventListener("click", function() {
	input.click();
})

input.addEventListener("change", function(event) {
	const fileReader = new FileReader();
	const files = event.target.files;
	fileReader.readAsDataURL(files[0])
	fileReader.addEventListener("load", (event) => {
		const img = event.target.result;
		dropArea.innerHTML = `<img src = '${img}' style="width:100%" class="imgInner">`;
	})

})
dropArea.addEventListener("dragover", (event) => {
	event.preventDefault();
	drapText.textContent = "Upload File"
})

dropArea.addEventListener("dragleave", (event) => {
	event.preventDefault();
	drapText.textContent = "Pull Upload File"
})

dropArea.addEventListener("drop", (event) => {
	event.preventDefault();
	const file = event.dataTransfer.files[0];
	const fileReader = new FileReader();
	input.attributes.class.ownerElement.files[0];
	//const files = event.target.files;
	fileReader.readAsDataURL(file)
	fileReader.addEventListener("load", (event) => {
		const img = event.target.result;
		dropArea.innerHTML = `<img src = '${img}' style="width:100%" class="imgInner">`;
	})
})
