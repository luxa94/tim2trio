iceipiceApp.controller('restanagerRegisterWorkerController', function ($scope, $http, $state, $stateParams, authorizationService) {
  $scope.restaurant = {};

  $scope.current = 1;

  $scope.cancel = function () {
    $scope.restaurant = {};
  };

  $scope.submit = function () {

  };
});