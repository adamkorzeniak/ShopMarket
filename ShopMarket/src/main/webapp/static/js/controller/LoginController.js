(function() {
'use strict';

angular.module("myApp").controller('LoginController', LoginController);

LoginController.$inject = ['$scope', '$location','authenticationService', 'userService'];

function LoginController($scope, $location, authenticationService, userService) {
	var vm = this;
	
	vm.rememberme = false;
	
	vm.login = login;
	vm.forgotPassword = forgotPassword;
	
	function login() {
			authenticationService.login(vm.user).then(function(data) {
				if (data) {
					$location.path("/");
				} else {
					alert("wrong password");
				}
			}, function(response) {
				alert("error");
			});
		}
	}
	
	function forgotPassword() {
		console.log("forgot");
	}
})();
