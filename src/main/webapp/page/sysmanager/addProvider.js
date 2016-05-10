iceipiceApp.controller('sysmanagerAddProviderController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 3;

    $scope.provider = {};

    $scope.cancel = function () {
        $scope.provider = {};
    };

    $scope.submit = function () {
        console.log('asdasdasd');
        $http.post('/api/provider/create', $scope.provider).success(function (data) {
            console.log('Uspesno dodat ponuđač.');
            $scope.cancel();
        }).error(function () {
            alert('Greška pri dodavanju ponuđača.');
        });
    };
});