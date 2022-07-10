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
<script>
var userDateFormat = "dd-MM-yyyy";
const formElem = document.querySelector('form');
const ddMMyyyy_Reg = /(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[13-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})/gi;
const yyyyMMdd_Reg = /^\d{4}\-\d{1,2}\-\d{1,2}$/;
formElem.addEventListener('formdata', (e) => {
e.preventDefault();
console.log("listnerr:");
console.log(e);
const formData = e.formData;
console.log(e.formData);
for(const pair of formData.entries()) {
	var newdate = "";
	if(ddMMyyyy_Reg.test(pair[1]))
	  {	
		var delim = pair[1].includes("/") ? "/" : "-";
		var datearray = date.split(delim);
		//Converting ddMMyyyy to MMddyyyy
	  	newdate = datearray[1] + "/" + datearray[0] + "/" + datearray[2];
		console.log(pair[0] + "," + pair[1]);
		formData.set(pair[0] , newdate);
	  }
	else if(yyyyMMdd_Reg.test(pair[1]))
	  {	
		var delim = pair[1].includes("/") ? "/" : "-";
		var datearray = pair[1].split(delim);
		//Converting yyyyMMdd to MMddyyyy
	  	newdate = datearray[1] + "/" + datearray[2] + "/" + datearray[0];
		console.log(pair[0] + "," + pair[1]);
		formData.set(pair[0] , newdate);
	  }
}

formData.set('username', formData.get('username').toUpperCase());
formData.set('email', formData.get('email').toUpperCase());
console.log(form.querySelector('input[name="username"]')); // FOO
console.log(form.querySelector('input[name="email"]')); // BAR


console.log("end");

/* const formData = new FormData(formElem);
console.log(formData.get('username')); // foo
console.log(formData.get('email')); // bar
});

formElem.addEventListener('formdata', (e) => {
console.log('formdata fired');

const formData = e.formData;
formData.set('username', formData.get('username').toUpperCase());
formData.set('email', formData.get('email').toUpperCase());
}); */
});

</script>
</html>