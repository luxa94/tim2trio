/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerHomePageController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.user = authorizationService.getUser();
    $scope.current.page = 0;
    
    $scope.modifyUserInfo = function () {
        $http.post('/api/restaurantManager/update', $scope.user).success(function (data) {
            authorizationService.removeUser();
            authorizationService.setUser(data);

        }).error(function () {
            console.log("Modifikacija nije uspela. Proverite da li ste pravilno uneli sve parametre forme.");
        });
    };

    $scope.cancel = function () {
        $http.get('/api/restaurantManager/one/' + $scope.user.id).success(function (data) {
            $scope.user = data;
            authorizationService.setUser($scope.user);
        });
    };
});