/**
 * Created by Sandra on 20.6.2016.
 */
iceipiceApp.controller('providerBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page : 0
    };

    $scope.user = authorizationService.getUser();

    $scope.home = function() {
        $state.transitionTo('provider.home');
    };

    $scope.previewLists = function() {
        $state.transitionTo('provider.previewLists');
    };

    $scope.newBid = function() {
        $state.transitionTo('provider.newBid');
    };



    $scope.logout = function () {
        authorizationService.removeUser();
        $state.transitionTo('login');
    };


});