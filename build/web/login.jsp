<%-- 
    Document   : login
    Created on : Jan 16, 2021, 4:22:48 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="DECOR/loginCSS.css">
		<title>Login Page</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<div class="login-page">
			<div class="form">
				<form class="login-form" action="MainController" method="POST">
					<input type="text" placeholder="username" name="txtUsername" value=""/>
					<input type="password" placeholder="password" name="txtPassword" value=""/>
					<button type="submit" value="Login" name="action">login</button>
				</form>
				<div>
					<p class="message"><a href="#">Login With Google</a></p>
				</div>
			</div>
		</div>
	</body>
</html>
