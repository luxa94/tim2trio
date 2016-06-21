/**
 * Created by Nina on 18-Jun-16.
 */
iceipiceApp.controller('guestSelectMenuItemController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService, ReservationService) {
    $scope.menuItems = [];
    $scope.menuItem = {};
    $scope.articleTypes = [];
    $scope.current.page = 3;


    $scope.selectedMenuItems = [];
    $scope.quantity = 0;
    $scope.orderItem = {};
    $scope.orderItems = [];



    console.log(ReservationService.asd.reservation.restaurantId);
    $http.get('/api/menuItems/allFromR/' + ReservationService.asd.reservation.restaurantId).success(function(data) {
        $scope.menuItems = data;
        // proveritiiii
        var orderList = ReservationService.asd.reservation.orders;
        if(orderList.length != 0) {
            for(var i = 0; i < orderList.length; i++) {
                for(var j = 0; j < $scope.menuItem.length; j++) {
                    if($scope.menuItem[j].id == order[i].menuItemId) {
                        $scope.selectedMenuItems.push($scope.menuItem[j]);
                    }
                }
            }
        }


    });

    $http.get('/api/articleTypes/all').success(function (data) {
        $scope.articleTypes = data;
    });
    
    $scope.goToAddNewReservation = function () {


        var items = [];
        for(var i = 0; i < $scope.selectedMenuItems.length; i++) {
            var item = {};
            item.amount = $scope.selectedMenuItems[i].amount;
            item.menuItemId = $scope.selectedMenuItems[i].id;
            items.push(item);
        }

        var reservation  = ReservationService.asd.reservation;
        reservation.orders = items;

        $state.transitionTo( "guest.addReservation");
    };

    $scope.goToInviteFriend = function (reservation) {
        var items = [];
        for(var i = 0; i < $scope.selectedMenuItems.length; i++) {
            var item = {};
            item.amount = $scope.selectedMenuItems[i].amount;
            item.menuItemId = $scope.selectedMenuItems[i].id;
            items.push(item);
        }

        var reservation  = ReservationService.asd.reservation;
        reservation.orders = items;

        $state.transitionTo( "guest.inviteFriend");
    };

    $scope.openDialogForMenuItemInfo= function(mi) {
        $scope.mi = mi;
        $scope.popup = new Foundation.Reveal($('#newShowMenuItem'));
        $scope.popup.open();
    };

    $scope.showMenuItem = function (mi) {
        $scope.openDialogForMenuItemInfo(mi);
       
    };

    $scope.cancel = function () {
        $scope.popup.close();
    };

    $scope.addMenuItem = function (mi,quantity) {

        $scope.selectedMenuItems.push(mi);
        mi.amount = quantity;
     //   $scope.orderItem.menuItem.name = mi.name;
        $scope.orderItems.push($scope.orderItem);
        $scope.popup.close();

    };

    $scope.refreshTable = function (mi) {

        var index =  $scope.selectedMenuItems.indexOf(mi);
        if(index != -1)
            $scope.selectedMenuItems.splice( index, 1 );

        $state.transitionTo( "guest.selectMenuItem");
    };


    $scope.createNewReservation = function () {

        var items = [];
        for(var i = 0; i < $scope.selectedMenuItems.length; i++) {
            var item = {};
            item.amount = $scope.selectedMenuItems[i].quantity;
            item.menuItemId = $scope.selectedMenuItems[i].id;
            items.push(item);
        }

        var reservation  = ReservationService.asd.reservation;
        reservation.orders = items;
        ReservationService.Create(reservation).then(function (data) {
            alert("Vaša rezervacija je uspešno dodata!");
        }, function(){
            alert("Vaša rezervacija nije uspešno dodata!");
        });
        $state.transitionTo( "guest.selectMenuItem");
    };

});