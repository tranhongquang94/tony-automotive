<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of Cars - Admin page</title>
</head>
<body>
	<h1>Car Lists</h1>
	<table>
		<tr>
			<th>id</th>
			<th>Thumbnail</th>
			<th>Name</th>
			<th>Make</th>
			<th>Model</th>
			<th>Year</th>
			<th>Price</th>
			<th>Category</th>
			<th>Posted at</th>
			<th>Stock</th>
		</tr>
		<c:forEach items="${carList }" var="car">
			<tr>
				<td>${car.id }</td>
				<td><img src="${car.imageURL }"/></td>
				<td>${car.name }</td>
				<td>${car.make }</td>
				<td>${car.model }</td>
				<td>${car.year }</td>
				<td>${car.price }</td>
				<td>${car.categoryId }</td>
				<td>${car.created_at }</td>
				<td>${car.stock }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>