iceipiceApp.controller('loginController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.user = {};

    $scope.login = function() {
        if ($scope.loginForm.$valid) {
            authorizationService.signIn($scope.user, function (data) {
                authorizationService.setUser(data);
                $state.transitionTo(data.type + ".home");
            }, function () {
                alert('Log in failed');
            });
        }
    };

    $scope.toRegister = function() {
        $state.transitionTo('register');
    }
});
