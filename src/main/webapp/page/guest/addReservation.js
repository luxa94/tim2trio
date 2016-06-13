/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestAddReservationController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService) {
    $scope.restaurants = [];
    $scope.current.page = 3;
    var previousRestaurant = GuestService.getSelectedRestaurant();

    $http.get('/api/restaurants/all').success(function (data) {
        $scope.restaurants = data;
        for(var i = 0; i < $scope.restaurants.length; i++){
            if($scope.restaurants[i].id === previousRestaurant.id) {
                $scope.selectedRow = i;
                $scope.selectedRestaurant = $scope.restaurants[i];
                return;
            }
        }
    });


    $scope.selectedRow = null ;  // initialize our variable to null

    $scope.setClickedRow = function(index,restaurant){  //function that sets the value of selectedRow to current index
        $scope.selectedRow = index;

        $scope.selectedRestaurant = restaurant;
    }

    $scope.createNewReservation = function () {
        alert("Vaša rezervacija je uspešno dodata!");
    };

    $scope.goToInviteFriend = function () {
        $state.transitionTo( "guest.inviteFriend");
    };

});