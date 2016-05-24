iceipiceApp.controller('registerController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.user = {};

    $scope.toLogin = function () {
        $state.transitionTo('login');
    };

    $scope.register = function () {
        if ($scope.registerForm.$valid) {
            authorizationService.register($scope.user, function (data) {
                authorizationService.setUser(data);
                $state.transitionTo(data.type + ".home");
            }, function () {
                alert('Registracija nije uspela! Proverite da li ste ispravno uneli sve parametre forme.');
            });
        }
    };
});