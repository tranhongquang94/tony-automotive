<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login Page</title>
</head>
<body>
	<form class="form-container" action="../../LoginBOServlet?action=login"
		method="POST">
		<h2 class="mb-5">
			Sign In to <span class="title">Tony's Automotive</span>:
		</h2>

		<div class="mb-3">
			<label for="username" class="form-label">Username: </label> <input
				type="text" class="form-control" name="email" id="username"
				placeholder="Your Email" required />
		</div>
		<div class="mb-3">
			<label for="passWord" class="form-label">Password: </label> <input
				type="password" class="form-control" name="password" id="passWord"
				placeholder="Your Password" required />
		</div>

		<button type="submit" class="btn btn-submit">Sign In</button>
	</form>
</body>
</html>