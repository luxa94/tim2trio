iceipiceApp.controller('restmanagerRegisterWorkerController', function ($scope, $http, $state, $stateParams, authorizationService) {

  $scope.restaurant = {};

  $scope.current.page = 1;

  $scope.cancel = function () {
    $scope.restaurant = {};
  };

  $scope.submit = function () {
    var x = document.getElementById("workertype");
    if (x.selectedIndex == 0) {
      
    }else if (x.selectedIndex == 1){

    }else {

    }
  };
});