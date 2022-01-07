<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
	<link rel="stylesheet" href="./index.css" type="text/css" />
	<script src="script.js" defer></script>
</head>
<body>

<% String username = (String) session.getAttribute("username");
	if (username != null) {
		response.sendRedirect("index.jsp");
	}
%>

	<jsp:include page="./header.jsp" />
	<div class="modal-container modal-login">
		<section class="img-container">
			<img src="./images/leftCover.jpg" alt="coding-pic" class="left-cover" />
		</section>

		<form class="form-container" action="handleLogin.jsp" method="POST">
			<h2 class="mb-5">
				Sign In to <span class="title">Tony's Automotive</span>:
			</h2>

			<div class="mb-3">
				<label for="username" class="form-label">Username: </label> <input
					type="text" class="form-control" name="username" id="username"
					placeholder="Your Username" required />
			</div>
			<div class="mb-3">
				<label for="passWord" class="form-label">Password: </label> <input
					type="password" class="form-control" name="password" id="passWord"
					placeholder="Your Password" required/>
			</div>

			<button type="submit" class="btn btn-submit">Sign In</button>
		</form>
	</div>
</body>
</html>