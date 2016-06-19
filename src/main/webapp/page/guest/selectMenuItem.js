/**
 * Created by Nina on 18-Jun-16.
 */
iceipiceApp.controller('guestSelectMenuItemController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService, ReservationService) {
    $scope.menuItems = [];
    $scope.menuItem = {};
    $scope.articleTypes = [];
    $scope.current.page = 3;

    console.log(ReservationService.asd.reservation.restaurantId);
    $http.get('/api/menuItems/allFromR/' + ReservationService.asd.reservation.restaurantId).success(function(data) {
       // console.log("$scope.user.restaurant.id = " + $scope.user.restaurant.id);
        $scope.menuItems = data;
    });

    $http.get('/api/articleTypes/all').success(function (data) {
        $scope.articleTypes = data;
    });
    
    $scope.goToAddNewReservation = function () {

        $state.transitionTo( "guest.addReservation");
    };

    $scope.goToInviteFriend = function (reservation) {

        $state.transitionTo( "guest.inviteFriend");
    };


});