<!DOCTYPE html>
<html lang="en">

<nav>
	<div class="logo-container">
		<img alt="logo" src="./logo/logo.png">
	</div>

	<ul class="menu-container">
		<li><a href="index.jsp">Home</a></li>
		<li><a href="#">About Us</a></li>
		<li><a href="#">Contact</a></li>
	</ul>

	<div class="button-container">
	
	<% String username = (String) session.getAttribute("username");
	if (username != null) { %>
		<span class="welcome-mes">Welcome back, <%= username %></span>
		<button class="logout-btn btn">Sign Out</button>
	<%} else { %>
		<button class="login-btn btn">Sign In</button>
		<button class="register-btn btn">Register</button>
	 <% } %>
		
	</div>
	
</nav>
</html>