/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestShowRestaurantsController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService) {
    $scope.restaurants = [];
    $scope.current.page = 2;

    $http.get('/api/restaurants/all').success(function (data) {
        $scope.restaurants = data;
      //  console.log($scope.restaurants);
    });

    // Reverse Order Button
    $scope.reverse = function(){
        if($scope.orderList == "name"){
            $scope.orderList = "-name";
        } else {
            $scope.orderList = "name";
        }
    };

    $scope.openDialogForRestaurantInfo= function(restaurant) {
        $scope.restaurant = restaurant;
        $scope.restaurant.address = restaurant.address;
        console.log(restaurant.address);
        $scope.popup = new Foundation.Reveal($('#newShowRestaurant'));
        $scope.popup.open();
    };

    $scope.showRestaurant = function (restaurant) {
        $scope.openDialogForRestaurantInfo(restaurant);
    };

    $scope.gotToReservation = function (restaurant) {
        
        GuestService.setSelectedRestaurant(restaurant);
        $state.transitionTo( "guest.addReservation");
    };

    $scope.cancel = function () {
        $scope.popup.close();
    };
});