<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shop Market</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.9/angular.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.9/angular-route.js"
	type="text/javascript"></script>
<script src="static/js/app.js" type="text/javascript"></script>
<script src="static/js/config.js" type="text/javascript"></script>
<script src="static/js/service/cartService.js" type="text/javascript"></script>
<script src="static/js/service/productService.js" type="text/javascript"></script>
<script src="static/js/service/searchService.js" type="text/javascript"></script>
<script src="static/js/service/userService.js" type="text/javascript"></script>
<script src="static/js/service/authenticationService.js" type="text/javascript"></script>
<script src="static/js/controller/IndexController.js" type="text/javascript"></script>
<script src="static/js/controller/HomeController.js" type="text/javascript"></script>
<script src="static/js/controller/ProductController.js" type="text/javascript"></script>
<script src="static/js/controller/AddProductController.js" type="text/javascript"></script>
<script src="static/js/controller/CartController.js" type="text/javascript"></script>
<script src="static/js/controller/RegisterController.js" type="text/javascript"></script>
<script src="static/js/controller/LoginController.js" type="text/javascript"></script>
</head>
<body ng-controller="IndexController as index">
	<ul>
		<li><a href="#/">Home</a></li>
		<li><a href="#/products">Products</a></li>
		<li><a href="#/sellProduct">Sell Product</a></li>
		<li><a href="#/cart">Cart</a></li>
		<li ng-if="index.authenticated" class="float-right"><a ng-click="index.logout()" href="#/login">Logout</a></li>
		<li ng-if="!index.authenticated" class="float-right"><a href="#/register">Register</a></li>
		<li ng-if="!index.authenticated" class="float-right"><a href="#/login">Login</a></li>
		<li class="float-right"><input ng-model="index.pattern"
			type="search" />
		<button ng-click=index.search(index.pattern)>Search</button>
	</ul>
	<div class="content" ng-view></div>
</body>
</html>