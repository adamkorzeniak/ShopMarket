(function() {
'use strict';

angular.module('myApp').factory('cartService', cartService);

cartService.$inject = [ '$http' ];

function cartService($http) {
	var cart = [];
	var totalCost = 0;
	return {
		getAll: getAll,
		getTotalCost: getTotalCost,
		addToCart : addToCart,
		removeFromCart: removeFromCart,
		contains: contains
	};
	
	function getAll() {
		return cart;
	}
	
	function getTotalCost() {
		return totalCost
	}
	
	function contains(product) {
		return indexOf(product) > -1;
	}

	function addToCart(product) {
		cart.push(product);
		totalCost += product.price;
	}
	
	function removeFromCart(product) {
		var index = indexOf(product);
		
		if (index > -1) {
			totalCost -= product.price;
			cart.splice(index, 1);
		}
	}
	
	function indexOf(product) {
		for (var i = 0; i < cart.length; i++) {
			if (product.id === cart[i].id) {
				return i;
			}
		}
		return -1;
	}
}
})();