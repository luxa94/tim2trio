iceipiceApp.controller('sysmanagerAddRestaurantController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurant = {};

    $scope.current.page = 1;

    $scope.cancel = function () {
        $scope.restaurant = {};
    };
    
    $scope.submit = function () {
        
    };
});