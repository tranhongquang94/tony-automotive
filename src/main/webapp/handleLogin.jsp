<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Handle Login Page</title>
</head>
<body>
	<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	if (username.equals("admin") && password.equals("admin")) {
		session.setAttribute("username", username);
		session.setAttribute("password",password);
		response.sendRedirect("index.jsp");
	} else {
		response.sendRedirect("login.jsp");
	}
	%>
</body>
</html>