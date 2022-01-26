<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="most-ordered-cars site-block">
			<h1 style="margin-bottom: 2rem;">Our Best Sellers</h1>
			
			<section class="cars-list">
				<c:forEach items="${mostOrderedCars }" var="car">
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
								<button class="order-btn">Order Now</button>
							</div>
						</a>
					</div>
				</c:forEach>
			</section>
		</div>