iceipiceApp.controller('sysmanagerAddSystemManagerController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 4;

    $scope.sysManager = {};

    $scope.cancel = function () {
        $scope.sysManager = {};
    };

    $scope.submit = function () {
        console.log('asdasdasd');
        $http.post('/api/sysmanager/create', $scope.sysManager).success(function (data) {
            console.log('Uspesno dodat menadzer sistema.');
            $scope.cancel();
        }).error(function () {
            alert('Greška pri dodavanju menadžera sistema.');
        });
    };
});