iceipiceApp.controller('sysmanagerBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = 0;

    $scope.home = function() {
        $scope.current = 0;
        $state.transitionTo('system_manager.home');
    };

    $scope.addRestaurant = function() {
        $scope.current = 1;
        $state.transitionTo('system_manager.addRestaurant');
    };

    $scope.addRestaurantManager = function() {
        $scope.current = 2;
        $state.transitionTo('system_manager.addRestaurantManager');
    };

    $scope.addSystemManager = function() {
        $scope.current = 3;
        $state.transitionTo('system_manager.addSystemManager');
    };

});