<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Handle LogOut</title>
</head>
<body>
	<%
			session.setAttribute("username", null);
			session.setAttribute("password",null);
	%>
</body>
</html>