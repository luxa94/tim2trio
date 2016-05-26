/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page: 0
    };

    $scope.user = authorizationService.getUser();
    
    $scope.home = function() {
        $scope.current.page = 0;
        $state.transitionTo('guest.home');
    };

    $scope.addFriend = function() {
        $scope.current.page = 1;
        $state.transitionTo('guest.addFriend');
    };

    $scope.showRestaurants = function() {
        $scope.current.page = 2;
        $state.transitionTo('guest.showRestaurants');
    };

    $scope.addReservation = function() {
        $scope.current.page = 3;
        $state.transitionTo('guest.addReservation');
    };

    $scope.modifyReservation = function() {
        $scope.current.page = 4;
        $state.transitionTo('guest.modifyReservation');
    };

    $scope.logout = function() {
        $state.transitionTo('logout');
    };

});