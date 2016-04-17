/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestHomeController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.user = {};

    $scope.modifyUserInfo = function () {
        $http.post('/api/businesses/insert', $scope.guest).success(function () {
            $scope.close();
        }).error(function () {
            $scope.close();
        });
    };
});