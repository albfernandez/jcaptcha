<%
String message = request.getParameter("message");
String format = request.getParameter("format");
String msg = "";
String color = "black";
if ("passed".equals(message)) {
	msg = "captcha validation passed";
	color = "green";
}
else if ("failed".equals(message)) {
	msg = "captcha validation failed";
	color = "red";
}

String image = "jcaptcha.jpg";
if ("png".equals(format)) {
	format = "png";
	image = "jcaptcha.png";
}
else if ("jpeg".equals(format) || "jpg".equals(format)) {
	format = "jpg";
	image = "jcaptcha.jpg";
}
else {
	format = "jpg";
	image = "jcaptcha.jpg";
}


%>
<!DOCTYPE html>
<html>
<head>
	<style>
		h4 { color: <%= color %>; }
	</style>
</head>
<body>
<h2>Simple Captcha Servlet sample</h2>
<br/>
<br/>
<h4><%= msg %></h4>	

<br/>

<form action="submit.action" method="post">
     <img src="<%= image %>" /> <input type="text" name="jcaptcha" value="" />
     <input type="hidden" name="format" value="<%= format%>" />
     <input type="submit"/>
</form>
</body>
</html>
