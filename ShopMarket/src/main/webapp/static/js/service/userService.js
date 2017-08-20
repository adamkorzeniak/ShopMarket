(function() {
'use strict';

angular.module('myApp').factory('userService', userService);

userService.$inject = [ '$http' ];

function userService($http) {
	var emails = ["a@a", "c@c"];
	var usernames = ["a", "c"];
	return {
		isUsernameAvailable: isUsernameAvailable,
		isEmailAvailable: isEmailAvailable,
		register: register,
		login: login
	};
	
	function isUsernameAvailable(username) {
		for (var u of usernames) {
			if (u === username) {
				return false;
			}
		}
		return true;
	}
	
	function isEmailAvailable(email) {
		for (var e of emails) {
			if (e === email) {
				return false;
			}
		}
		return true;
	}
	
	function register(user) {
		var userDTO = new UserForm(user.username, user.email, user.password);
		return $http.post("api/register", userDTO).then(function(response) {
			return response.data;
		});
	}
	
	function login(user) {
		var userDTO = new UserForm(user.username, user.email, user.password);
		return $http.post("login", userDTO).then(function(response) {
			return response.data;
		});
	}
	
	function UserForm(username, email, password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
})();