(function() {
'use strict';

angular.module("myApp").controller('RegisterController', RegisterController);

RegisterController.$inject = ['$scope', '$rootScope', '$location', 'userService', 'authenticationService'];

function RegisterController($scope, $rootScope, $location, userService, authenticationService) {
	var vm = this;
	
	vm.user = {};
	vm.passwordMatch = true;
	vm.usernameTaken = false;
	vm.emailTaken = false;
	vm.registered = false;
	
	vm.register = register;
	vm.comparePasswords = comparePasswords;
	vm.confirm = confirm;
	
	function register() {
		if (isFormValid()) {
			userService.register(vm.user).then(function(data) {
				console.log("added");
				vm.registered = true;
			}, function(response) {
				alert("error");
			});
		}
	}
	
	function confirm() {
		authenticationService.confirmToken(vm.token).then(function(data) {
		})
	}
	
	function isFormValid() {
		vm.usernameTaken = false;
		vm.emailTaken = false;
		
		if (/*$scope.form.$invalid ||*/ !vm.passwordMatch) {
			return false;
		}
		
		if (!userService.isUsernameAvailable(vm.user.username)) {
			vm.usernameTaken = true;
		}
		if (!userService.isEmailAvailable(vm.user.email)) {
			vm.emailTaken = true;
		}
		return !vm.usernameTaken && !vm.emailTaken;
	}
	
	function comparePasswords() {
		if (!vm.user || vm.user.password === vm.user.passwordConfirm) {
			vm.passwordMatch = true;
		} else {
			vm.passwordMatch = false;
		}
	}
}
})();
