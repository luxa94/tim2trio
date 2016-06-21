/**
 * Created by Sandra on 20.6.2016.
 */
iceipiceApp.controller('providerHomeController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current.page = 0;
    $scope.user = authorizationService.getUser();

    $scope.modifyUserInfo = function(){
        //console.log("user: " + JSON.stringify($scope.user));
        $http.post('/api/provider/update', $scope.user).success(function (data) {
            authorizationService.removeUser();
            authorizationService.setUser(data);

        }).error(function () {
            alert("Modifikacija nije uspela. Proverite da li ste pravilno uneli sve parametre forme.");
        });
    }

    $scope.cancel = function () {
        $http.get('/api/provider/one/' + $scope.user.id).success(function (data) {
            $scope.user = data;
            authorizationService.setUser(data);
        })
    };
});