var cancelButtons = document.querySelectorAll("input[name=delete]");
Array.from(cancelButtons).forEach(function(cancelButton) {
	cancelButton.addEventListener("click", function() {
		if (confirm("You need delete room") == true) {
			cancelButton.type = "submit";
		}
	})
}) 

