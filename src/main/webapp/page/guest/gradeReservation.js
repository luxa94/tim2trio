/**
 * Created by Nina on 17-Jun-16.
 */

/**
 * Created by Nina on 21-Apr-16.
 */
iceipiceApp.controller('guestGradeReservationController', function ($scope, $http, $state, $stateParams, authorizationService, ReservationService) {
    $scope.rating1 = 5;
    $scope.rating2 = 2;

    $scope.rateFunction = function(rating) {
        console.log('Rating selected: ' + rating);
    };

    alert("res get"+ ReservationService.getUnderReview());

});