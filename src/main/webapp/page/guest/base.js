/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = 0;

    $scope.home = function() {
        $scope.current = 0;
        $state.transitionTo('guest.home');
    };

    $scope.addFriend = function() {
        $scope.current = 1;
        $state.transitionTo();
    };

    $scope.addRestaurantManager = function() {
        $scope.current = 2;
        $state.transitionTo();
    };

    $scope.addSystemManager = function() {
        $scope.current = 3;
        $state.transitionTo();
    };

});