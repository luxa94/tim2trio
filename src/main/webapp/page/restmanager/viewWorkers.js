/**
 * Created by Sandra on 24.5.2016.
 */
iceipiceApp.controller('viewWorkersController', function ($scope, $http, $state, $stateParams, authorizationService,GuestService) {

    $scope.restaurant = {};

    $scope.current.page = 6;

    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
    });


});