/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerHomePageController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.user = authorizationService.getUser();

    $scope.modifyUserInfo = function () {
        $http.post('/api/restaurant_manager/update', $scope.user).success(function (data) {
            authorizationService.removeUser();
            authorizationService.setUser(data);

        }).error(function () {
            console.log("Modifikacija nije uspela. Proverite da li ste pravilno uneli sve parametre forme.");
        });
    };

    $scope.cancel = function () {

    };
});