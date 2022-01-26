<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Details</title>
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
	<c:forEach items="${sessionScope.cart.cars}" var="car">
		<div class="card-container">
			<a href="CarsListServlet?carId=${car.id }">
				<div class="img-container">
					<img src="${car.imageURL }" alt="car-image">
				</div>
				<div class="description">
					<div class="text-container">
						<p class="product-name">${car.name }</p>
						<p class="product-price">$ ${car.price}</p>
					</div>
					<a href="CartServlet?action=remove&carId=${car.id }">Remove Item</a>
				</div>
			</a>
		</div>
	</c:forEach>
		<a href="CartServlet?action=checkout">Check out Cart</a>
	</main>
</body>
</html>