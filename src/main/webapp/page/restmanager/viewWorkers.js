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

            for (i = 0; i < cooks.length; i++){
                cooks[i].cook = true;
                cooks[i].bartender = false;
                cooks[i].waiter = false;
            }
        })
        $http.get('/api/bartender/allFromR/' + $scope.restaurant.id).success(function(data) {
            console.log("SANKERI: " + JSON.stringify(data));
            bartenders = data;
            for (i = 0; i < cooks.length; i++){
                bartenders[i].cook = false;
                bartenders[i].bartender = true;
                bartenders[i].waiter = false;
            }
            $scope.workers = cooks.concat(bartenders).concat(waiters);
            console.log("RADNICI: " + JSON.stringify($scope.workers));
        })

    });

    $scope.reverse = function(){
        if($scope.orderList == "name"){
            $scope.orderList = "-name";
        } else {
            $scope.orderList = "name";
        }
    };


});