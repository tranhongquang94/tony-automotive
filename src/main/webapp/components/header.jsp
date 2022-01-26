<%@page import="java.util.ArrayList"%>
<%@page import="model.Navmenu"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
List<Navmenu> navmenu = new ArrayList<>();
navmenu.add(new Navmenu("Home", "CarsListServlet"));
navmenu.add(new Navmenu("About Us", "#"));
navmenu.add(new Navmenu("Contact", "#"));

pageContext.setAttribute("navmenu", navmenu);
%>
<!DOCTYPE html>
<html lang="en">
<header>

	<div class="toolbar">
		<div class="button-container">
			<c:if test="${sessionScope.email != null}">
				<span class="welcome-mes">Welcome back,
					${sessionScope.email }</span>
				<span class="cart-mes">Cart(${sessionScope.cart.cars.size()})</span>
				<button class="logout-btn btn">Sign Out</button>
			</c:if>
			<c:if test="${sessionScope.email == null}">
				<button class="login-btn btn">Sign In</button>
				<button class="register-btn btn">Register</button>
			</c:if>
		</div>
	</div>

	<div class="bottom-toolbar">
		<div class="row-container">
			<div class="logo-container">
				<a href="CarsListServlet"> <img alt="logo" src="./logo/logo.png">
				</a>
			</div>
			<nav class="menu-container">
				<c:forEach items="${navmenu }" var="item">
					<a href="${item.url }">${item.name }</a>
				</c:forEach>
			</nav>
		</div>

	</div>
</header>
</html>