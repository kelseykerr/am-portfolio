angular.module('portfolioApp.about', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/about', {
            templateUrl: 'about/about.html',
            controller: 'AboutCtrl',
            activeTab: 'about'
        });
    }])

    .controller('AboutCtrl', ['$scope', '$route', function($scope, $route) {
        $scope.$route = $route;
    }]);
