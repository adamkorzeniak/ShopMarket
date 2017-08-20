(function() {
	'use strict';

	angular.module("myApp").config(function($routeProvider) {
		$routeProvider.when("/", {
			templateUrl : "static/html/main.html",
			controller : "HomeController",
			controllerAs : "vm",
			title : "Shop Market"
		}).when("/products", {
			templateUrl : "static/html/product.html",
			controller : "ProductController",
			controllerAs : "vm",
			title : "Shop Market - Products"
		}).when("/sellProduct", {
			templateUrl : "static/html/sellProduct.html",
			controller : "AddProductController",
			controllerAs : "vm",
			title : "Shop Market - Add Product"
		}).when("/cart", {
			templateUrl : "static/html/cart.html",
			controller : "CartController",
			controllerAs : "vm",
			title : "Shop Market - Cart"
		}).when("/login", {
			templateUrl : "static/html/login.html",
			controller : "LoginController",
			controllerAs : "vm",
			title : "Shop Market - Login"
		}).when("/register", {
			templateUrl : "static/html/register.html",
			controller : "RegisterController",
			controllerAs : "vm",
			title : "Shop Market - Register"
		})
	}).config(function($httpProvider) {

		  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

		});
})();