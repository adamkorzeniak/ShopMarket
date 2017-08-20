(function() {
'use strict';

angular.module("myApp", [ "ngRoute" ]);

angular.module("myApp").run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function(_, current) {
        document.title = current.$$route.title;
    });
}]);

})();