const dropArea = document.querySelector(".drag-area");
const drapText = dropArea.querySelector("header");
//const button = dropArea.querySelector("button");
const bntFile = dropArea.querySelector(".bntFile");
const input = document.querySelector("input[name=multipartFile]");
bntFile.addEventListener("click", function() {
	input.click();
})

input.addEventListener("change", function(event) {
	const file = event.target.files[0];
	const fileReader = new FileReader();
	fileReader.readAsDataURL(file)
	fileReader.addEventListener("load", (event) => {
		const img = event.target.result;
		dropArea.innerHTML = `<img src = '${img}' style="width:100%" class="imgInner">
				<div class="file">
					<img
						src="https://cdn3.iconfinder.com/data/icons/file-management-system-outline-1/32/file_manager_icon_set-22-512.png"
						alt="" width="50%" height="50%">
						<p><button class="bntFile" type="button">Choose file</button> or Drag</p>
				</div>`;
	})

})
dropArea.addEventListener("dragover", (event) => {
	event.preventDefault();
	//drapText.textContent = "Upload File"
})

dropArea.addEventListener("dragleave", (event) => {
	event.preventDefault();
	//drapText.textContent = "Pull Upload File"
})

dropArea.addEventListener("drop", (event) => {
	event.preventDefault();
	const file = event.dataTransfer.files[0];
	input.files = event.dataTransfer.files
	const fileReader = new FileReader();
	fileReader.readAsDataURL(file)
	fileReader.addEventListener("load", (event) => {
		const img = event.target.result;
		dropArea.innerHTML = `<img src = '${img}' style="width:100%;" class="imgInner"> 
					<div class="file">
					<img
						src="https://cdn3.iconfinder.com/data/icons/file-management-system-outline-1/32/file_manager_icon_set-22-512.png"
						alt="" width="50%" height="50%">
						<p><button class="bntFile" type="button">Choose file</button> or Drag</p>
				</div>`;
	})
})
