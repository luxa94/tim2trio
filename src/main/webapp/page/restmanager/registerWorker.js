iceipiceApp.controller('restmanagerRegisterWorkerController', function ($scope, $http, $state, $stateParams, authorizationService) {

  $scope.worker = {};

  $scope.current.page = 1;

  $scope.cancel = function () {
    $scope.restaurant = {};
  };

  $scope.submit = function () {

    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
      console.log("RESTORAN: " + JSON.stringify(data));
      $scope.restaurant = data;

    $scope.worker.restaurantId = $scope.restaurant.id;
  //kuvar
    $http.post('/api/cook/create', $scope.worker).success(function (data) {
      console.log('Uspešno dodat kuvar');
      alert('Uspešno dodat kuvar');
      $scope.cancel();
    }).error(function () {
      alert('Greška pri dodavanju kuvara');
    });
    });


  };
});