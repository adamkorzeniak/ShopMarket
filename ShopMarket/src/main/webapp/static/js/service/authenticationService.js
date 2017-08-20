(function() {
'use strict';

angular.module('myApp').factory('authenticationService', authenticationService);

authenticationService.$inject = [ '$http', '$rootScope', '$location'];

function authenticationService($http, $rootScope, $location) {

	return {
		getLoggedUser: getLoggedUser,
		authenticate: authenticate,
		login: login,
		logout: logout,
		confirmToken: confirmToken
	};
	
	function authenticate() {
		return getLoggedUser().then(function(response) {
			if (response.name == null) { 
				$rootScope.$broadcast('auth', response.data);
				$location.path("login");
				return false;
			};
			return true;
	});
	}
	
	function logout() {
		return $http.post('logout', {}).then(function(response) {
			$rootScope.$broadcast('auth', response.data);
		});
	}
	
	function login(user) {
		return $http.get('api/user', {
		      headers : { authorization : "Basic "
		        + btoa(user.username
		        + ":"  + user.password)
		      }}).then(function(response) {
		    	  $rootScope.$broadcast('auth', response.data);
		    	  return response.data;
		  })
	}
	
	function confirmToken(token) {
		return $http.get("api/confirm?t=" + token).then(function(response) {
			$location.path("/login");
		});
	}
	
	function getLoggedUser() {
		return $http.get("api/user").then(function(response) {
			return response.data;
		});
	}
}
})();