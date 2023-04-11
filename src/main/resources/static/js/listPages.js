var buttonEdits = document.querySelectorAll("input[name=edit]");
var buttonDeletes = document.querySelectorAll("input[name=delete]");
var pageIds = document.querySelectorAll("input[name=pageId]");
var pageDelete = document.querySelector("input[name=pageDelete]");
Array.from(buttonDeletes).forEach(function(buttonDelete) {
	buttonDelete.addEventListener("click", function() {
		if (confirm("You need delete Page") == true) {
			buttonDelete.type = "submit";
		}
	})
}) 