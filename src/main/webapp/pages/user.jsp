<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>User Detail</title>
</head>
<body>
	<h4>${msg}</h4>
	<h3 align="center">Add User</h3>
	<div align="center" style="vertical-align: middle">
		<form action="<%=request.getContextPath()%>/users" method="POST"
			enctype="multipart/form-data">
			<table class="table table-hover table-condensed" style="width: 60%">
				<tr>
					<td><b>First Name</b></td>
					<td><input type="text" name="firstName" required="required" /></td>
				</tr>
				<tr>
					<td><b>Last Name</b></td>
					<td><input type="text" name="lastName" required="required" /></td>
				</tr>
				<tr>
					<td><b>Address</b></td>
					<td><input type="text" name="address" required="required" /></td>
				</tr>
				<tr>
					<td><b>Age</b></td>
					<td><input type="text" name="age" required="required" /></td>
				</tr>
				<tr>
					<td><b>Gender</b></td>
					<td><input type="text" name="gender" required="required" /></td>
				</tr>
				<tr>
					<td><b>Department</b></td>
					<td><input type="text" name="department" required="required" /></td>
				</tr>
				<tr>
					<td><b>File</b></td>
					<td><input type="file" name="file" required="required" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit"
						class="btn btn-primary" /></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<br>

	<div align="center" style="vertical-align: middle">
		<h3 align="center">User Detail</h3>
		<table
			class="table table-hover table-bordered table-striped table-condensed"
			style="width: 90%">
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Address</th>
				<th>Age</th>
				<th>Gender</th>
				<th>Department</th>
			</tr>
			<c:forEach items="${allUsers}" var="user">
				<tr>
					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.lastName}" /></td>
					<td><c:out value="${user.address}" /></td>
					<td><c:out value="${user.age}" /></td>
					<td><c:out value="${user.gender}" /></td>
					<td><c:out value="${user.department}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>