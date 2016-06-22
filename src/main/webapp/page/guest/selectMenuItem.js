/**
 * Created by Nina on 18-Jun-16.
 */
iceipiceApp.controller('guestSelectMenuItemController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService, ReservationService) {
    $scope.menuItems = [];
    $scope.menuItem = {};
    $scope.articleTypes = [];
    $scope.current.page = 3;

    $scope.tooLate = false;
    $scope.selectedMenuItems = [];
    $scope.quantity = 0;
    $scope.orderItem = {};
    $scope.orderItems = [];

    $scope.asd = ReservationService.asd;

    console.log(ReservationService.asd.reservation.restaurantId);
    $http.get('/api/menuItems/allFromR/' + ReservationService.asd.reservation.restaurantId).success(function(data) {
        $scope.menuItems = data;
        // proveritiiii
///////////////////////////////////////////////////////////////////////////////////////////////////////
        var today = new Date(Date.now() - (1000 /*sec*/ * 60 /*min*/ * 30 )).getTime();


            var calculatedDate = new Date(ReservationService.asd.reservation.date);
            //  console.log(calculatedDate);

            var todayDay = calculatedDate.getDay();
            console.log(todayDay);
            var todayMonth = calculatedDate.getMonth();
            console.log(todayMonth);
            var todayYear = calculatedDate.getFullYear();
            console.log(todayYear);
            var startHour = parseInt(ReservationService.asd.reservation.start_hour.substring(0,3));
            var startMinutes = parseInt(ReservationService.asd.reservation.start_hour.substring(3));

            var date = calculatedDate.getTime();
            console.log(date);


            ReservationService.asd.reservation.date = new Date(ReservationService.asd.reservation.date);
            ReservationService.asd.reservation.niceDate = ReservationService.asd.reservation.date.toISOString().substring(0, 10);
            //      calculatedDate = new Date(todayYear, todayMonth, todayDay, startHour, startMinutes).getTime();


            if( ReservationService.asd.reservation.date.getTime() <= today) {
                ReservationService.asd.reservation.tooLate = true;
            } else {
                ReservationService.asd.reservation.tooLate = false;
            }
                  console.log("IS IT TOO LATE?",    ReservationService.asd.reservation );
            $scope.tooLate = ReservationService.asd.reservation.tooLate;
            if(ReservationService.asd.reservation.tooLate) {

                return;
            }


//////////////////////////////////////////////////////////////////////////////////////////////









        var orderList = ReservationService.asd.reservation.orders;
        if(orderList.length != 0) {
            for(var i = 0; i < orderList.length; i++) {
                for(var j = 0; j < $scope.menuItems.length; j++) {
                    if($scope.menuItems[j].id == orderList[i].menuItemId) {
                        $scope.menuItems[j].amount = orderList[i].amount;
                        $scope.selectedMenuItems.push($scope.menuItems[j]);

                    }
                }
            }
        }




    });

    $http.get('/api/articleTypes/all').success(function (data) {
        $scope.articleTypes = data;
    });
    
    $scope.goToAddNewReservation = function (reservation) {

        var items = [];
        for(var i = 0; i < $scope.selectedMenuItems.length; i++) {
            var item = {};
            item.amount = $scope.selectedMenuItems[i].amount;
            item.menuItemId = $scope.selectedMenuItems[i].id;
            items.push(item);
        }
        //var reservation  = ReservationService.asd.reservation;
     //   reservation.restaurantId = $scope.asd.reservation.restaurantId;
      //  // vrati dobar id restorana
      //  console.log("vracam rez: " + reservation.restaurantId);

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
        $state.transitionTo( "guest.homePage");
    };

});