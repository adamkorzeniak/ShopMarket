(function() {
'use strict';

angular.module("myApp").controller('IndexController', IndexController);

IndexController.$inject = ['$scope', '$rootScope',  '$location', 'searchService', 'authenticationService'];

function IndexController($scope, $rootScope,  $location, searchService, authenticationService) {
	var vm = this;
	
	vm.authenticated = false;
	vm.username = null;
	
	vm.logout = logout;
	vm.search = search;
	
	init();
	
	function init() {
		authenticate();
	}
	
	function authenticate() {
		authenticationService.getLoggedUser().then(function(data) {
		});
	}
	
	function logout() {
		authenticationService.logout().finally(function() {
		    $location.path("/login");
		  });
	}
	
	$scope.$on('auth', function(event, data) {
			vm.authenticated = data ? data.authenticated : false;
		    vm.username = data? data.name: null;
	});
	
	function search(pattern) {
		$location.path("/products");
		searchService.search(pattern).then(function(data) {
			$rootScope.$broadcast('search', data);
		});
	}
	
}
})();