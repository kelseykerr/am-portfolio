angular.module('portfolioApp', [
    'ngRoute',
    'portfolioApp.photography',
    'portfolioApp.about',
    'ngAnimate'
])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: 'home.html',
            controller: 'mainCtrl',
            activeTab: 'home'
        })
    }])

    .controller('mainCtrl', ['$scope', '$route', function ($scope, $route) {
        $scope.route = $route;
    }]);

