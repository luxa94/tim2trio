/**
 * Created by Nina on 22-Apr-16.
 */
iceipiceApp.controller('bartenderBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page: 0
    };

    $scope.home = function() {
        $state.transitionTo('bartender.home');
    };

    $scope.logout = function () {
        authorizationService.removeUser();
        $state.transitionTo('login');
    };

});
