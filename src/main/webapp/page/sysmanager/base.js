iceipiceApp.controller('sysmanagerBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = 0;

    $scope.home = function() {
        $scope.current = 0;
        $state.transitionTo('sysmanager.home');
    };

    $scope.addRestaurant = function() {
        $scope.current = 1;
        $state.transitionTo();
    };

    $scope.showRestaurants = function() {
        $scope.current = 2;
        $state.transitionTo();
    };

    $scope.addReservation = function() {
        $scope.current = 3;
        $state.transitionTo();
    };

});