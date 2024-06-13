<%
String message = request.getParameter("message");
String parameterFormat = request.getParameter("format");
String format = "";
String screenMessage = "";
String color = "black";
if ("passed".equals(message)) {
	screenMessage = "captcha validation passed";
	color = "green";
}
else if ("failed".equals(message)) {
	screenMessage = "captcha validation failed";
	color = "red";
}

String image = "jcaptcha.jpg";
if ("png".equalsIgnoreCase(parameterFormat)) {
	format = "png";
	image = "jcaptcha.png";
}
else {
	format = "jpg";
	image = "jcaptcha.jpg";
}

request.setAttribute("color", color);
request.setAttribute("image", image);
request.setAttribute("format", format);
request.setAttribute("screenMessage", screenMessage);

%>
<!DOCTYPE html>
<html>
<head>
	<style>
		h4 { 
			color: ${color};
		}
		img.captcha {
			border:none;
			width:200px;
			height:70px;
			margin-top: 20px;
			margin-bottom: 20px;
		}
		
	</style>
	<script>
	function focus() {
		var input = document.getElementById('jcaptcha');
		if (input) {
			input.focus();
		}
	}
	
	</script>
</head>
<body onload="focus()">
<h2>Simple Captcha Servlet sample</h2>
<h4>${screenMessage}</h4>
<form action="submit.action" method="post" autocomplete="off">
     <img class="captcha" src="${image}" >
     <br>
     <input type="text" id="jcaptcha" name="jcaptcha" value="" autocomplete="off" />
     <input type="hidden" name="format" value="${format}" />
     <input type="submit"/>
</form>
</body>
</html>
