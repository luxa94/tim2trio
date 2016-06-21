iceipiceApp.controller('waiterBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page: 0
    };

    $scope.user = authorizationService.getUser();

    $scope.home = function() {
        $scope.current.page = 0;
        $state.transitionTo('waiter.home');
    };

    $scope.logout = function() {
        $state.transitionTo('logout');
    };

    $scope.shifts = function () {
        $state.transitionTo('waiter.shifts');
    };

    $scope.reservations = function () {
        $state.transitionTo('waiter.reservations');
    };

});