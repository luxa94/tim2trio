/**
 * Created by Nina on 18-Jun-16.
 */
iceipiceApp.controller('guestSelectMenuItemController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService) {
    $scope.friends = [];
    $scope.invitedFriends = [];
    $scope.current.page = 3;


    $scope.goToAddNewReservation = function () {

        $state.transitionTo( "guest.addReservation");
    };

    $scope.goToInviteFriend = function (reservation) {

        $state.transitionTo( "guest.inviteFriend");
    };


});