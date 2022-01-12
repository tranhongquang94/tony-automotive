<%@page import="model.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	List<Car> cars = new ArrayList<>();
	cars.add(new Car("Nissan", "./images/products/nissan.jpg", 5000));
	cars.add(new Car("Honda", "./images/products/honda.jpg", 7000));
	cars.add(new Car("Toyota", "./images/products/toyota.jpg", 10000));
	cars.add(new Car("Mazda", "./images/products/mazda.jpg", 8000));
	cars.add(new Car("BMW", "./images/products/bmw.jpg", 12000));
	cars.add(new Car("Ford", "./images/products/ford.jpg", 12000));
	cars.add(new Car("Mitsubishi", "./images/products/mitsubishi.jpg", 12000));
	
	pageContext.setAttribute("cars", cars);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tony's Automotive and Motors</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous" />
<link rel="stylesheet" href="./index.css" type="text/css" />
</head>
<body>

	<jsp:include page="./header.jsp" />

	<main class="homepage-content">
		<h1>Product List</h1>
		<section class="cars-list">
		<c:forEach items="${cars }" var="car">
			<div class="card-container">
				<div class="img-container">
					<img src="${car.imageURL }" alt="car-image">
				</div>
				<div class="description">
					<div class="text-container">
						<p class="product-name">${car.name }</p>
						<p class="product-price">$ ${car.price}</p>
					</div>
					<button class="order-btn">Order Now</button>
				</div>
			</div>
		</c:forEach>
			
		</section>
	</main>
</body>
</html>