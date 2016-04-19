/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = 0;

    $scope.user = authorizationService.getUser();
    
    $scope.home = function() {
        $scope.current = 0;
        $state.transitionTo('guest.home');
    };

    $scope.addFriend = function() {
        $scope.current = 1;
        $state.transitionTo('guest.addFriend');
    };

    $scope.showRestaurants = function() {
        $scope.current = 2;
        $state.transitionTo('guest.showRestaurants');
    };

    $scope.addReservation = function() {
        $scope.current = 3;
        $state.transitionTo('guest.addReservation');
    };

    $scope.logOut = function() {
        $scope.current = 4;
        $state.transitionTo('logout');
    };

});