/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestShowRestaurantsController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurants = [];
    $scope.current.page = 2;

    $http.get('/api/restaurants/all').success(function (data) {
        $scope.restaurants = data;
    });

    // Reverse Order Button
    $scope.reverse = function(){
        if($scope.orderList == "name"){
            $scope.orderList = "-name";
        } else {
            $scope.orderList = "name";
        }
    };
});