/**
 * Created by Sandra on 22.6.2016.
 */
iceipiceApp.controller('providerMyBidsController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current.page = 3;
    $scope.user = authorizationService.getUser();
});