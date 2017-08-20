(function() {
	'use strict';

	angular.module('myApp').factory('productService', productService);

	productService.$inject = [ '$http' ];

	function productService($http) {
		return {
			getAll : getAll,
			addProduct: addProduct
		};

		function getAll() {
			return $http.get("api/product").then(function(response) {
				return response.data;
			});
		}
		
		function addProduct(product) {
			return $http.post("api/product", product);
		}
	}
})();