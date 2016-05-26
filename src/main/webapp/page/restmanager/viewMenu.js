/**
 * Created by Sandra on 10.5.2016.
 */
iceipiceApp.controller('restManagerViewMenuController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.menuItems = [];
    $scope.current.page = 6;
    console.log("$scope.user.restaurant.id = " + $scope.user.restaurant.id);
    $http.get('/api/menuItems/allFromR/' + $scope.user.restaurant.id).success(function(data) {
        console.log("$scope.user.restaurant.id = " + $scope.user.restaurant.id);
        $scope.menuItems = data;
    });
});