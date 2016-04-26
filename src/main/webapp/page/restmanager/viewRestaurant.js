/**
 * Created by Sandra on 26.4.2016.
 */
iceipiceApp.controller('restManagerViewRestaurantController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurant = {};

    $scope.current.page = 6;

    $scope.typeNames = [];

    $http.get('/api/restaurantTypes/all').success(function(data) {
        $scope.typeNames = data;
    });

    $scope.cancel = function () {
        $scope.restaurant = {};
    };

    $scope.submit = function () {

    };
});