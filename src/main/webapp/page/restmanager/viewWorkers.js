/**
 * Created by Sandra on 24.5.2016.
 */
iceipiceApp.controller('viewWorkersController', function ($scope, $http, $state, $stateParams, authorizationService,GuestService) {

    $scope.restaurant = {};
    $scope.workers = [];
    $scope.current.page = 6;
    var cooks = [];
    var bartenders = [];
    var waiters = [];

    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
    });


    $http.get('/api/cook/allFromR/' + $scope.restaurant.id).success(function(data) {
        console.log("KUVARI: " + JSON.stringify(data));
        cooks = data;
    })

    $http.get('/api/bartender/allFromR/' + $scope.restaurant.id).success(function(data) {
        console.log("SANKER: " + JSON.stringify(data));
        bartenders = data;
    })

    $http.get('/api/waiter/allFromR/' + $scope.restaurant.id).success(function(data) {
        console.log("KONOBAR: " + JSON.stringify(data));
        waiters = data;
    })

    $scope.workers = cooks.concat(bartenders).concat(waiters);

});