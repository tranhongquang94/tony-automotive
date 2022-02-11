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
<script src="https://unpkg.com/micromodal/dist/micromodal.min.js" defer></script>
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
			<a href="CarsListServlet?carId=${car.key.id }">
				<div class="img-container">
					<img src="${car.key.imageURL }" alt="car-image">
				</div>
				<div class="description">
					<div class="text-container">
						<p class="product-name">${ car.key.name }</p>
						<p class="product-price">$ ${ car.key.price }</p>
					</div>
					<a href="CartServlet?action=remove&carId=${ car.key.id }">Remove Item</a>
				</div>
			</a>
			
			<!-- Number of this cars in cart -->
			<div class="itemNumInCart-container">
				<a href="CartServlet?action=reduceNum&carId=${car.key.id }" class="reduceBtn" data-id="${car.key.id }">-</a>
				<span class="carNumInCart" id="${car.key.id }">${car.value }</span>
				<a href="CartServlet?action=addNum&carId=${car.key.id }">+</a>
			</div>
		</div>
	</c:forEach>
	
	<c:if test="${sessionScope.cart.getCars().size() != 0}">
		<a href="CartServlet?action=checkout">Check out Cart</a>
	</c:if>
	
	<div class="modal-container"></div>
	</main>
</body>
</html>