/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = 0;

    $scope.user = authorizationService.getUser();

    $scope.home = function() {
        $scope.current = 0;
        $state.transitionTo('restaurantManager.home');
    };

    $scope.registerWorker = function() {
        $scope.current = 1;
        $state.transitionTo('restaurantManager.registerWorker');
    };

    $scope.defineTimetable = function() {
        $scope.current = 2;
        $state.transitionTo('restaurantManager.defineTimetable');
    };

    $scope.defineAreas = function() {
        $scope.current = 3;
        $state.transitionTo('restaurantManager.defineAreas');
    };

    $scope.makeBid = function(){
        $scope.current = 4;
        $state.transitionTo('restaurantManager.makeBid');
    }

});