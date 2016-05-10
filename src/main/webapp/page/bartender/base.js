/**
 * Created by Nina on 22-Apr-16.
 */
iceipiceApp.controller('bartenderBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page: 0
    };

    $scope.user = authorizationService.getUser();

    $scope.home = function() {
        $scope.current.page = 0;
        $state.transitionTo('bartender.home');
    };

    $scope.logout = function() {
        $state.transitionTo('logout');
    };

});
