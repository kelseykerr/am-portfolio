angular.module('portfolioApp.artwork', ['ngRoute', 'akoenig.deckgrid', 'ngAnimate'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/artwork', {
            templateUrl: 'artwork/artwork.html',
            controller: 'ArtworkCtrl',
            activeTab: 'artwork'
        });
    }])

    .controller('ArtworkCtrl', ['$scope', '$route', 'artworkService',
        function($scope, $route, artworkService) {
            $scope.$route = $route;
            $scope.art = [];
            artworkService.getArtwork().then(function(result) {
                $scope.art = result.data;
            })

        }])


    .factory('artworkService', ['$http', function ($http) {
        return {
            getArtwork: function () {
                return $http({
                    method: "GET",
                    url: 'api/portfolio/artwork',
                    isArray: true
                });
            }
        }
    }]);
