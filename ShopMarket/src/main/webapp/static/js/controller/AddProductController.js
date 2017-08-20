(function() {
'use strict';

angular.module("myApp").controller('AddProductController', AddProductController);

AddProductController.$inject = ['$scope', 'authenticationService', 'productService', '$location'];

function AddProductController($scope, authenticationService, productService, $location) {
	var vm = this;
	
	vm.addProduct = addProduct;
	
	init();

	function init() {
		authenticationService.authenticate().then(function(data) {
			if(data) {
			};
		});
	}
	
	function addProduct(product) {
		
		productService.addProduct(product).then(function(data) {
			$location.path("products");
		});
	}
}
})();
