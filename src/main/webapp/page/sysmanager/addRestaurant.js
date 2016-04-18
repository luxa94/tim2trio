iceipiceApp.controller('sysmanagerAddRestaurantController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.restaurant = {};

    $scope.current.page = 1;
    
    $scope.typeNames = [];
    
    $http.get('/api/restaurantTypes/all').success(function(data) {
        $scope.typeNames = data;
        console.log($scope.typeNames);
        
    });
    
    $scope.cancel = function () {
        $scope.restaurant = {};
    };
    
    $scope.submit = function () {
        $http.post('/api/restaurant/create', $scope.restaurant).success(function (data) {
            console.log('posted restaurant');
            alert('Restoran uspesno dodat');
            $scope.cancel();
        }).error(function () {
            console.log('error');
        });
    };

    // $(function () {
    //     $('select').multipleSelect();
    // });

    console.log('done');
});