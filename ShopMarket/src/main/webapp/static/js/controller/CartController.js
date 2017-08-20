(function() {
'use strict';

angular.module("myApp").controller('CartController', CartController);

CartController.$inject = ['$scope', 'cartService'];

function CartController($scope, cartService) {
	var vm = this;
	
	vm.remove = remove;;
	
	function remove(product) {
		if (cartService.contains(product)) {
			cartService.removeFromCart(product);
			vm.totalCost = cartService.getTotalCost();
		} else {
			alert("error");
		}
	}
	
	init();
	
	function init() {
		vm.productsInCart = cartService.getAll();
		vm.totalCost = cartService.getTotalCost();
	}
}
})();
