iceipiceApp.controller('bartenderOrdersController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 2;

    $scope.user = authorizationService.getUser();
    $scope.orders = [];

    $http.get('/api/orders/bartender/' + $scope.user.id).success(function (data) {
        $scope.orders = data;
    });

    $scope.make = function(orderId) {
        $http.post('/api/orders/bartender/make/' +  $scope.user.id +'/order/' + orderId).success(function () {
            $http.get('/api/orders/bartender/' + $scope.user.id).success(function (data) {
                $scope.orders = data;
            });
        }).error(function () {
            alert('Stavka je preuzeta od strane drugog kuvara.')
        });
    };

    $scope.finish = function (orderId) {
        $http.post('/api/orders/finish/' + orderId).success(function () {
            $http.get('/api/orders/bartender/' + $scope.user.id).success(function (data) {
                $scope.orders = data;
            });
        });
    };

    $scope.interval = setInterval(function () {
        $http.get('/api/orders/bartender/' + $scope.user.id).success(function (data) {
            $scope.orders = data;
        });
    }, 2000);

    $scope.$on('$destroy', function() {
        clearInterval($scope.interval);
    });
});