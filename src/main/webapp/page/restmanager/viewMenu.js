/**
 * Created by Sandra on 10.5.2016.
 */
iceipiceApp.controller('restManagerViewMenuController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurant = {};

    $scope.current.page = 6;

    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
    });
});