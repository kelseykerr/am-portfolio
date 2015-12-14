angular.module('portfolioApp', [
    'ngRoute',
    'ngAnimate',
    'portfolioApp.photography',
    'portfolioApp.about',
    'portfolioApp.artwork',
    'akoenig.deckgrid'
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

