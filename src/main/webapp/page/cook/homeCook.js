/**
 * Created by Nina on 21-Apr-16.
 */
iceipiceApp.controller('cookHomeController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.user = authorizationService.getUser();

    $scope.modifyUserInfo = function () {
        $http.post('/api/cook/update', $scope.user).success(function (data) {
            authorizationService.removeUser();
            authorizationService.setUser(data);

        }).error(function () {
            console.log("Modifikacija nije uspela. Proverite da li ste pravilno uneli sve parametre forme.");
        });
    };

    $scope.cancel = function () {

    };
});