iceipiceApp.controller('sysmanagerAddRestaurantManagerController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 2;

    $scope.restaurantManager = {};

    $scope.restaurants = [];

    $scope.cancel = function () {
        $scope.restaurantManager = {};
    };

    $http.get('/api/restaurants/all').success(function (data) {
        $scope.restaurants = data;
    });
    
    $scope.submit = function () {
        $http.post('api//restaurantManager/create', $scope.restaurantManager).success(function (data) {
            console.log('Uspešno dodat menadžer restorana');
            alert('Uspešno dodat menadžer restorana');
            $scope.cancel();
        }).error(function () {
            alert('Greška pri dodavanju menadžera restorana');
        });
    };
});