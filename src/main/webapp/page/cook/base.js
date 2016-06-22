/**
 * Created by Nina on 26-Apr-16.
 */
/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('cookBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page: 0
    };

    $scope.user = authorizationService.getUser();

    $scope.home = function() {
        $scope.current.page = 0;
        $state.transitionTo('cook.home');
    };

    $scope.logout = function() {
        $state.transitionTo('logout');
    };

    $scope.shifts = function () {
        $state.transitionTo('cook.shifts');
    };

    $scope.orders = function () {
        $state.transitionTo('cook.orders');
    };

});