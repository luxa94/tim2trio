/**
 * Created by Nina on 26-May-16.
 */
iceipiceApp.controller('guestModifyReservationController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.reservations = [];
    $scope.current.page = 4;



    $http.get('/api/reservations/all/' + $scope.user.id).success(function (data) {
        $scope.reservations = data;

    });



});