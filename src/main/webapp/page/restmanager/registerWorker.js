iceipiceApp.controller('restmanagerRegisterWorkerController', function ($scope, $http, $state, $stateParams, authorizationService) {

  $scope.restaurant = {};

  $scope.current.page = 1;

  $scope.cancel = function () {
    $scope.restaurant = {};
  };

  $scope.submit = function () {
    
    $http.post('/api/restaurant_manager/registerWorker').success(function (data) {
      console.log('Radnik uspešno dodat');
      alert('Radnik uspešno dodat');
      $scope.cancel();
    }).error(function () {
      alert('Greška pri dodavanju radnika');
    });
  };
});