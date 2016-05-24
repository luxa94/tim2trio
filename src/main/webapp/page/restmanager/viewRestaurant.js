/**
 * Created by Sandra on 26.4.2016.
 */
iceipiceApp.controller('restManagerViewRestaurantController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurant = {};

    $scope.current.page = 5;
    
    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
    });

    $scope.cancel = function () {
        $scope.restaurant = {};
    };

    $scope.submit = function () {

    };
});