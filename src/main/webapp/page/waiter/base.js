/**
 * Created by Nina on 10-May-16.
 */
/**
 * Created by Nina on 26-Apr-16.
 */
/**
 * Created by Nina on 17-Apr-16.
 */
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

});