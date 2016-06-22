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
        console.log(reservationId);

        $http.post('/api/orderItems/newForReservation/' + reservationId, $scope.newOrder).success(function (data) {
            $http.get("/api/reservations/waiter/all/" + $scope.user.id).success(function (data) {
                $scope.reservations = data;
            });
        });
        
        $scope.newOrder = {};
    };

    setInterval(function () {
        $http.get("/api/reservations/waiter/all/" + $scope.user.id).success(function (data) {
            $scope.reservations = data;
        });
        console.log('update');
    }, 3000);

});