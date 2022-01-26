<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<section class="category-list">
		<c:forEach items="${categories }" var="category">
			<a href="CarsListServlet?categoryId=${category.id }">${category.name}</a>
		</c:forEach>
	</section>