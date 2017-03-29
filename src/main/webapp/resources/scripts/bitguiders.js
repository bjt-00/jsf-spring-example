showLoading = function(data) {
				//data.source.id;
				var progressbarId = "#form\\:loading";
				if (data.status === "begin")
					setTimeout(function(){$(progressbarId).fadeIn("slow");},1000);
				else if (data.status === "success")
				setTimeout(function(){$(progressbarId).fadeOut("slow");},5000);
				else if (data.status === "complete"){
					showAlert("Process Completed");
				}
				}
showError = function(data){
	showAlert(data.errorName+":"+data.errorMessage+"<p>"+data.description+"</p>");
}
function showAlert(message){
	$("#alert").fadeIn("slow");
	$("#message").html(message);
	setTimeout(function(){$("#alert").fadeOut("slow");},5000);
}

var builtinAjaxRequestFunction = jsf.ajax.request;
jsf.ajax.request = function(c,e,o) {
alert("hello");
builtinAjaxRequestFunction(c,e,o);
alert("bye");
}