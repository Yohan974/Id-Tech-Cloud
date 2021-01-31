<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Customer</title>
		<link rel='stylesheet' type='text/css' href='styles.css'/>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/8665f855bb.js" crossorigin="anonymous"></script>
	</head>
	<body>
		<header class='app-header d-flex justify-content-end'>
			<div class='p-2'>${connectedUser.firstname} ${connectedUser.lastname}</div>
			<div class='p-2'><a href="<%= request.getContextPath() %>/logout">Logout</a></div>
		</header>