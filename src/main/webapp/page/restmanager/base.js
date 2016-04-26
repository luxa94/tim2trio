/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerBaseController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page : 0
    };

    $scope.user = authorizationService.getUser();

    $scope.home = function() {
        $state.transitionTo('restaurant_manager.home');
    };

    $scope.registerWorker = function() {
        $state.transitionTo('restaurant_manager.registerWorker');
    };

    $scope.defineTimetable = function() {
        $state.transitionTo('restaurant_manager.defineTimetable');
    };

    $scope.defineAreas = function() {
        $state.transitionTo('restaurant_manager.defineAreas');
    };

    $scope.makeBid = function(){
        $state.transitionTo('restaurant_manager.makeBid');
    };

    $scope.viewRestaurant = function(){
        $state.transitionTo('restaurant_manager.viewRestaurant');
    };

    $scope.logout = function () {
        authorizationService.removeUser();
        $state.transitionTo('login');
    };

});