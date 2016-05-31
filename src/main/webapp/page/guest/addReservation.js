/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestAddReservationController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurants = [];
    $scope.current.page = 3;


    $http.get('/api/restaurants/all').success(function (data) {
        $scope.restaurants = data;
    });

    $scope.selectedRow = null;  // initialize our variable to null
    $scope.setClickedRow = function(index,restaurant){  //function that sets the value of selectedRow to current index
        $scope.selectedRow = index;

        $scope.selectedRestaurant = restaurant;
    }

    $scope.createNewReservation = function () {
        alert("Vaša rezervacija je uspešno dodata!");
    };

});