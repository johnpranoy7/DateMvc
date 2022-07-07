<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUD</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	integrity="undefined" crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<div class="text-center">
			<h2>Login</h2>
		</div>
		<form:form action="/processLogin" method="post" modelAttribute="user">
			<div class="form-group pt-2">
				<form:label path="username">UserName:</form:label>
				<form:input type="text" class="form-control" path="username"
					placeholder="username" />
			</div>
			<div class="form-group pt-2">
				<form:label path="email">Email:</form:label>
				<form:input type="text" class="form-control" path="email"
					placeholder="user@email.com" />
			</div>
			<div class="form-group pt-2">
				<form:label path="phoneNo">PhoneNo:</form:label>
				<form:input path="phoneNo" type="text" class="form-control"
					placeholder="Phone Number" />
			</div>
			<div class="form-group pt-2">
				<form:label path="createdOn">createdOn:</form:label>
				<form:input path="createdOn" type="date" class="form-control"
					placeholder="createdOn (Date)" />
			</div>
			<div class="form-group pt-2">
				<form:label path="updatedOn">createdOn:</form:label>
				<form:input path="updatedOn" type="date" class="form-control"
					placeholder="updatedOn (LocalDate)" />
			</div>

			<a><button class="btn btn-success mt-2" type="submit">Create</button></a>
		</form:form>
	</div>
</body>
</html>