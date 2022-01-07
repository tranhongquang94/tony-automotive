<%@page import="utils.BirthDateUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Data</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="index.css" />
</head>
<body>
	<div class="modal-container">
		<section class="img-container">
			<img src="leftCover.jpg" alt="coding-pic" class="left-cover" />
		</section>
		
		<section class="result-container">
			<h2> Your information: </h2>
			<div class="results">
			Your Full Name: ${param.fullName }.<br /> 
			Your  Username: ${param.username }. <br /> 
			Your Gender: ${param.gender }. <br />
			Your birthday: <%= BirthDateUtils.parseDate(request.getParameter("birthdate")) %><br />
			Your chosen courses:
			
			<% String[] courses = request.getParameterValues("courses");
			for (int i = 0; i< courses.length; i++) {
				out.print(" <span>" + courses[i]+ "</span>");
				if (i != courses.length - 1) {
					out.print(", ");
				}
			}
			%>
			
			</div>
		</section>
	</div>

</body>
</html>