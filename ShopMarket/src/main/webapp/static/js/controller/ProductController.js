(function() {
	'use strict';

	angular.module("myApp").controller('ProductController', ProductController);

	ProductController.$inject = [ '$scope', 'productService', 'searchService', 'cartService', 'authenticationService' ];

	function ProductController($scope, productService, searchService, cartService, authenticationService) {
		var vm = this;

		vm.products = null;
		vm.searchedProducts = null;
		vm.searchQuery = null;

		vm.getProducts = getProducts;
		vm.addToCart = addToCart;
		vm.clearSearch = clearSearch;

		init();

		function init() {
			authenticationService.authenticate().then(function(data) {
				if(data) {
					getProducts();
				}
			});
		}

		function getProducts() {
			productService.getAll().then(function(data) {
				vm.products = data;
			});
		}

		function addToCart(product) {
			if (cartService.contains(product)) {
				alert("product already in cart")
			} else {
				cartService.addToCart(product);
			}
		}
		
		function clearSearch() {
			vm.searchedProducts = null;
			vm.searchQuery = null;
		}
		
		$scope.$on('search', function(event, data) { 
			var lastSearch = searchService.getLastSearch();
			vm.searchedProducts = lastSearch.products;
			vm.searchQuery = lastSearch.query;
		});
		
	}
})();