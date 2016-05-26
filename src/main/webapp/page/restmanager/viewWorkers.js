/**
 * Created by Sandra on 24.5.2016.
 */
iceipiceApp.controller('restManagerViewWorkersController', function ($scope, $http, $state, $stateParams, authorizationService,GuestService) {

    $scope.restaurant = {};
    $scope.workers = [];
    $scope.current.page = 7;
    var cooks = [];
    var bartenders = [];
    var waiters = [];

    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
        console.log("****Restaurant id = " + $scope.restaurant.id);
        $http.get('/api/cook/allFromR/' + $scope.restaurant.id).success(function(data) {
            console.log("KUVARI: " + JSON.stringify(data));
            cooks = data;
        });
        $http.get('/api/bartender/allFromR/' + $scope.restaurant.id).success(function(data) {
            console.log("SANKERI: " + JSON.stringify(data));
            bartenders = data;
            $scope.workers = cooks.concat(bartenders).concat(waiters);
            console.log("RADNICI: " + JSON.stringify($scope.workers));
        });

    });


});