iceipiceApp.controller('sysmanagerAddRestaurantController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurant = {};

    $scope.current.page = 1;
    
    $scope.typeNames = [];
    
    $http.get('/api/restaurantTypes/all').success(function(data) {
        console.log("****data: " + data);
        $scope.typeNames = data;
    });
    
    $scope.cancel = function () {
        $scope.restaurant = {};
    };
    
    $scope.submit = function () {
        $http.post('/api/restaurant/create', $scope.restaurant).success(function (data) {
            console.log('Restoran uspešno dodat');
            alert('Restoran uspešno dodat');
            $scope.cancel();
        }).error(function () {
            alert('Greška pri dodavanju restorana');
        });
    };
});