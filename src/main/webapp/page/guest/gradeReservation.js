/**
 * Created by Nina on 17-Jun-16.
 */

/**
 * Created by Nina on 21-Apr-16.
 */
iceipiceApp.controller('guestGradeReservationController', function ($scope, $http, $state, $stateParams, authorizationService, ReservationService) {
    $scope.grade = {}
    $scope.grade.meal_grade = 0;
    $scope.grade.waiter_grade = 0;
    $scope.grade.atmosphere_grade= 0;
    $scope.grade.meal_comment = null;
    $scope.grade.waiter_comment= null;
    $scope.grade.atmosphere_comment = null;

    $scope.rateFunction = function(rating) {
        console.log('Rating selected: ' + rating);
    };

    $scope.gradeReservation = function() {
        $scope.grade.userId = $scope.user.id;
        $scope.grade.reservationId = ReservationService.getUnderReview().id;
        console.log($scope.grade);
        ReservationService.CreateReview($scope.grade);
    };

    $scope.cancel = function () {
        $scope.popup.close();
    };

});