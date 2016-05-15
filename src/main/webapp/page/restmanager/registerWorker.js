iceipiceApp.controller('restmanagerRegisterWorkerController', function ($scope, $http, $state, $stateParams, authorizationService) {

  $scope.worker = {};

  $scope.current.page = 1;

  $scope.cancel = function () {
    $scope.restaurant = {};
  };

  $scope.submit = function () {

  //kuvar
    $http.post('/api/cook/create', $scope.worker).success(function (data) {
      console.log('Uspešno dodat kuvar');
      alert('Uspešno dodat kuvar');
      $scope.cancel();
    }).error(function () {
      alert('Greška pri dodavanju kuvara');
    });


    var x = document.getElementById("workertype");
    console.log("X = " +JSON.stringify(x));
    if (x.selectedIndex == 0) {

    }else if (x.selectedIndex == 1){

    }else if (x.selectedIndex==2){

    }
  };
});