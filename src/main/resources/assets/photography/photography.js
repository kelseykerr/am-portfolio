angular.module('portfolioApp.photography', ['ngRoute', 'akoenig.deckgrid', 'ngAnimate'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/photography', {
            templateUrl: 'photography/photography.html',
            controller: 'PhotographyCtrl',
            activeTab: 'photography'
        });
    }])

    .controller('PhotographyCtrl', ['$scope', '$route', 'photographyService',
        function($scope, $route, photographyService) {
        $scope.$route = $route;
        $scope.photos = [];
        photographyService.getPhotographs().then(function(result) {
            $scope.photos = result.data;
        })

    }])


    .factory('photographyService', ['$http', function ($http) {
        return {
            getPhotographs: function () {
                return $http({
                    method: "GET",
                    url: 'api/portfolio/photography',
                    isArray: true
                });
            }
        }
    }]);

