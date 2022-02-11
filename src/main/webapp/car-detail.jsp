<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${car.name }</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="./index.css" type="text/css" />
<script src="script.js" defer></script>
</head>
<body>
	<jsp:include page="./components/header.jsp" />
	<video autoplay muted loop>
		<source src="./video/video.mp4" type="video/mp4" />
	</video>
	<main>
	<h1>Car Detail</h1>
		<div class="car-container">
			<div class="single-car-img-container">
				<img src="${car.imageURL }" alt="${car.name }" />
			</div>
			<div class="single-car-text-container">
				<h3 class="single-car-title">${car.name }</h3>
				<p>${car.price }</p>
				<hr>
				<p>
					<span>Make: </span>${car.make }
				<p>
					<span>Model: </span>${car.model }</p>
				<p>
					<span>Year: </span>${car.year }</p>
				<c:if test="${sessionScope.email != null }">
					<a href="CartServlet?action=add&carId=${car.id }">Order now</a>
				</c:if>
			</div>
		</div>
	</main>
</body>
</html>