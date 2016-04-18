iceipiceApp.controller('sysmanagerAddRestaurantController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurant = {};

    $scope.cancel = function () {
        $scope.restaurant = {};
    };
    
    $scope.submit = function () {
        
    };
});