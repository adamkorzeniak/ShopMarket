(function() {
'use strict';

angular.module('myApp').factory('searchService', searchService);

searchService.$inject = [ '$http' ];

function searchService($http) {
	
	var lastSearch = {};
	
	return {
		search: searchAll,
		getLastSearch: getLastSearch,
	};

	function searchAll(pattern) {
		var url = "api/product/search?q=" + pattern
		return $http.get(url).then(function(response) {
			lastSearch.products = response.data;
			lastSearch.query = pattern;
			return response.data;
		}, function(response) {
			lastSearch = {};
		});
	}
	
	function getLastSearch() {
		return lastSearch;
	}
}
})();