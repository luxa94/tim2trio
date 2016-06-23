/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestAddReservationController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService, ReservationService) {
    $scope.restaurants = [];
    $scope.current.page = 3;
    $scope.asd = ReservationService.asd;
    $scope.update = false;
    $scope.tables = [];
    $scope.selectedTables = [];

    $scope.canvas = new fabric.CanvasEx('restaurant_canvas');
    var canvas = $scope.canvas;
    canvas.hoverCursor = 'pointer';
    canvas.backgroundColor = '#222';
    canvas.renderAll();

    function isEmpty(obj) {
        for (var x in obj) {
            return false;
        }
        return true;
    }

    if (!isEmpty($scope.asd)) {
        if (typeof $scope.asd.reservation.date != "undefined" || $scope.asd.reservation.date == null) {
            $scope.update = true;
        }
    }

    console.log("UPDATEEEEEE" + $scope.update);

    if (typeof $scope.asd.reservation.guests == "undefined" || $scope.asd.reservation.guests == null) {
        $scope.asd.reservation.guests = [$scope.user];
    }

    if (typeof $scope.asd.reservation.orders == "undefined" || $scope.asd.reservation.orders == null) {
        $scope.asd.reservation.orders = [];
    }

    var pasedRestaurant = GuestService.getSelectedRestaurant();

    var previousRestaurant = GuestService.getSelectedRestaurant();

    $http.get('/api/restaurants/all').success(function (data) {
        $scope.restaurants = data;
        for (var i = 0; i < $scope.restaurants.length; i++) {
            if (typeof previousRestaurant === 'undefined' || previousRestaurant == null) {
                break;
            }
            if ($scope.restaurants[i].id === previousRestaurant.id) {
                $scope.selectedRow = i;
                $scope.selectedRestaurant = $scope.restaurants[i];
                GuestService.setSelectedRestaurant($scope.selectedRestaurant);
                return;
            }
        }
    });


    $scope.selectedRow = null;  // initialize our variable to null

    $scope.redrawTables = function () {
        canvas.clear();

        for (var i in $scope.tables) {
            var table = $scope.tables[i];
            var t = JSON.parse(table.fabricTable);
            var fabricTable;
            if (t.type == 'rect') {
                fabricTable = new fabric.Rect(t);
                canvas.add(fabricTable);
                table.fabricTable = fabricTable;
                fabricTable.opacity = 0.5;
                fabricTable.selectable = false;
            } else if (t.type == 'circle') {
                fabricTable = new fabric.Circle(t);
                fabricTable.opacity = 0.5;
                canvas.add(fabricTable);
                table.fabricTable = fabricTable;
                fabricTable.selectable = false;
            }
        }
        canvas.renderAll();
    };

    $scope.setClickedRow = function (index, restaurant) {  //function that sets the value of selectedRow to current index
        $scope.selectedRow = index;
        $scope.asd.reservation.restaurantId = restaurant.id;
        $scope.asd.reservation.restaurant = restaurant;
        $scope.selectedRestaurant = restaurant;

        $scope.selectedTables = [];

        $http.get('/api/tables/restaurant/' + restaurant.id).success(function (data) {
            $scope.tables = data;
            $scope.redrawTables();

        });
    };

    if (GuestService.getSelectedRestaurant()) {
        $http.get('/api/tables/restaurant/' + GuestService.getSelectedRestaurant().id).success(function (data) {
            $scope.tables = data;
            $scope.redrawTables();

        });
    }

    canvas.on('mouse:dblclick', function (options) {
        if (options.target != undefined) {
            options.target.opacity = 1.5 - options.target.opacity;
            canvas.renderAll();
            for (var i in $scope.tables) {
                var t = $scope.tables[i];
                if (t.fabricTable == options.target) {
                    $scope.table = t;
                    var index = $scope.selectedTables.indexOf(t.id);
                    if (index > -1) {
                        $scope.selectedTables.splice(index, 1);
                    } else {
                        $scope.selectedTables.push(t.id);
                    }
                    console.log($scope.selectedTables);
                    break;
                }
            }
        }
    });

    $scope.createNewReservation = function (reservation) {

        if ($scope.selectedTables.length == 0) {
            alert('Morate rezervisati bar jedan sto!');
            return;
        }

        if (reservation.start_hour == null) {
            alert("Morate uneti početno vreme rezervacije!");
            return;
        }

        if (reservation.end_hour == null) {
            alert("Morate uneti zavšno vreme rezervacije!");
            return;
        }

        if ($scope.asd.reservation.restaurantId == null && pasedRestaurant.id == null) {
            alert("Morate selektovati željeni restoran!");
            return;
        }

        if (reservation.date == null) {
            alert("Morate uneti datum rezervacije!");
            return;
        }

        reservation.tableIds = $scope.selectedTables;

        if (pasedRestaurant != null) {
            reservation.restaurantId = pasedRestaurant.id;
        }
        else {
            reservation.restaurantId = $scope.asd.reservation.restaurantId;
        }
        if (ReservationService.update == true) {
            ReservationService.Update(reservation).then(function (data) {
                alert("Vaša rezervacija je uspešno izmenjena!");

            }, function () {
                alert("Vaša rezervacija nije uspešno izmenjena!");
            });
            return;
        }
        else {
            ReservationService.Create(reservation).then(function (data) {
                alert("Vaša rezervacija je uspešno dodata!");
            }, function () {
                alert("Vaša rezervacija nije uspešno dodata!");
            });
        }
        $state.transitionTo("guest.home");
    };

    $scope.goToSelectMenuItem = function (reservation) {

        if ($scope.selectedTables.length == 0) {
            alert('Morate rezervisati bar jedan sto!');
            return;
        }

        if (reservation.start_hour == null) {
            alert("Morate uneti početno vreme rezervacije!");
            return;
        }
        if (reservation.end_hour == null) {
            alert("Morate uneti zavšno vreme rezervacije!");
            return;
        }
        if ($scope.asd.reservation.restaurantId == null && pasedRestaurant.id == null) {
            alert("Morate selektovati željeni restoran!");
            return;
        }
        if (reservation.date == null) {
            alert("Morate uneti datum rezervacije!");
            return;
        }

        $scope.asd.reservation.tableIds = $scope.selectedTables;

        if (pasedRestaurant != null) {
            reservation.restaurantId = pasedRestaurant.id;
        }
        else {
            reservation.restaurantId = $scope.asd.reservation.restaurantId;
            reservation.restaurant = $scope.restaurant;
            reservation.tableIds = $scope.selectedTables;
        }
        GuestService.setSelectedRestaurant($scope.asd.reservation.restaurant);
        $state.transitionTo("guest.selectMenuItem");
    };

    $scope.validateDate = function (reservation) {
        if (typeof reservation.date === 'undefined' || reservation.date === null) {
            return;
        }
        //     console.log(reservation.date);
        var today = new Date();
        console.log("todai date: NOWWWW" + today.toLocaleString());
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        today.setMilliseconds(0);
        console.log("todai date: at MIDNIGHT" + today.toLocaleString());
        var selectedDate = reservation.date;
        //    var selectedDate = new Date(reservation.date.getFullYear(), reservation.date.getMonth(), reservation.date.getDay() );
        selectedDate.setHours(0);
        selectedDate.setMinutes(0);
        selectedDate.setSeconds(0);
        selectedDate.setMilliseconds(0);
        console.log(selectedDate.getTime() - today.getTime());
        console.log("selected::" + selectedDate.getTime());
        console.log(today.getTime());
        console.log("is in past:" + (today.getTime() - selectedDate.getTime()) > 0);
        console.log("sel date:" + selectedDate.toISOString());
        console.log("todai date:" + today.toISOString());
        if ((today.getTime() - selectedDate.getTime()) > 0) {

            alert("Datum rezervacije ne sme biti u prošlosti!");
            reservation.date = null;
        }
    };

    $scope.validateTime = function (reservation) {
        var startHour;
        var endHour;


        if (reservation.start_hour == null) {
            return;
        }

        if (reservation.start_hour.length == 1 || reservation.start_hour.length == 2) {
            if (isNaN(reservation.start_hour)) {
                alert("Uneta vrednost za vreme mora biti broj!");
            }
            return;
        }

        if (reservation.start_hour.length == 3) {

            if (reservation.start_hour.charAt(2) != ":") {
                alert("Vreme se unosi u formatu hh:mm 24h.");
            }
            return;
        }

        if (reservation.start_hour.length == 4) {
            startHour = parseInt(reservation.start_hour.substring(3));
            if (isNaN(startHour)) {
                alert("Uneta vrednost za vreme mora biti broj!");
            }
            return;
        }

        if (reservation.start_hour.length == 5) {
            startHour = parseInt(reservation.start_hour.substring(4));
            if (isNaN(startHour)) {
                alert("Uneta vrednost za vreme mora biti broj!");
                return;
            }

        }


        var patt = new RegExp("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
        if (!patt.test(reservation.start_hour)) {
            alert("Vreme početka rezervacije mora da bude u formatu hh:mm 24h.");
            return;
        }

        ///////////////////////////
        if (reservation.end_hour == null) {
            return;
        }

        if (reservation.end_hour.length == 1 || reservation.end_hour.length == 2) {
            if (isNaN(reservation.end_hour)) {
                alert("Uneta vrednost za vreme mora biti broj!");
            }
            return;
        }

        if (reservation.end_hour.length == 3) {

            if (reservation.end_hour.charAt(2) != ":") {
                alert("Vreme se unosi u formatu hh:mm 24h.");
            }
            return;
        }

        if (reservation.end_hour.length == 4) {
            endHour = parseInt(reservation.end_hour.substring(3));
            if (isNaN(endHour)) {
                alert("Uneta vrednost za vreme mora biti broj!");
            }
            return;
        }

        if (reservation.end_hour.length == 5) {
            endHour = parseInt(reservation.end_hour.substring(4));
            if (isNaN(endHour)) {
                alert("Uneta vrednost za vreme mora biti broj!");
                return;
            }

        }


        var patt = new RegExp("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
        if (!patt.test(reservation.end_hour)) {
            alert("Vreme početka rezervacije mora da bude u formatu hh:mm 24h.");
            return;
        }


        if (reservation.start_hour != null && reservation.end_hour != null) {

            startHour = parseInt(reservation.start_hour.substring(0, 3));
            var startMinutes = parseInt(reservation.start_hour.substring(3));

            var endHour = parseInt(reservation.end_hour.substring(0, 3));
            var endMinutes = parseInt(reservation.end_hour.substring(3));


            if (startHour == endHour) {
                if (!(startMinutes < endMinutes)) {
                    alert("Vreme početka rezervacije mora biti veće od završetka!");
                }
            }

            if (startHour > endHour) {
                alert("Vreme početka rezervacije mora biti veće od završetka!");
            }
        }


    };
});