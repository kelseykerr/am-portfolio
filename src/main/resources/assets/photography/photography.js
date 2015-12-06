angular.module('portfolioApp.photography', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/photography', {
            templateUrl: 'photography/photography.html',
            controller: 'PhotographyCtrl',
            activeTab: 'photography'
        });
    }])

    .controller('PhotographyCtrl', ['$scope', '$route', function($scope, $route) {
        $scope.$route = $route;
    }]);
