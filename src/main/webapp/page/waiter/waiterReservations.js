iceipiceApp.controller('waiterReservationsController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 2;
    $scope.reservations = [];
    $scope.tables = [];
    $scope.user = authorizationService.getUser();
    $scope.menuItems = [];
    $scope.newOrder = {};

    $http.get("/api/reservations/waiter/all/" + $scope.user.id).success(function (data) {
        $scope.reservations = data;
    });

    $http.get('/api/menuItems/allFromR/' + $scope.user.restaurant.id).success(function (data) {
        $scope.menuItems = data;
    });

    $scope.addOrder = function (reservationId) {
        if ($scope.newOrder.amount && $scope.newOrder.menuItemId) {
            $http.post('/api/orderItems/newForReservation/' + reservationId, $scope.newOrder).success(function (data) {
                $http.get("/api/reservations/waiter/all/" + $scope.user.id).success(function (data) {
                    $scope.reservations = data;
                });
            });
            $scope.newOrder = {};
        }
    };

    $scope.serve = function (orderId) {
        $http.post('/api/orders/serve/' + orderId).success(function () {
            $http.get("/api/reservations/waiter/all/" + $scope.user.id).success(function (data) {
                $scope.reservations = data;
            });
        });
    };

    $scope.interval = setInterval(function () {
        $http.get("/api/reservations/waiter/all/" + $scope.user.id).success(function (data) {
            $scope.reservations = data;
        });
    }, 3000);
    
    $scope.closeReservation = function (reservationId) {
        $http.get('/api/reservations/close/' + reservationId).success(function(data) {
            alert('Konacna cena: ' + data);
            $http.get("/api/reservations/waiter/all/" + $scope.user.id).success(function (data) {
                $scope.reservations = data;
            });
        });
    };
    
    $scope.$on('$destroy', function() {
        clearInterval($scope.interval);
    });
    
});