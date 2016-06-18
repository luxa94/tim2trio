/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestAddReservationController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService) {
    $scope.restaurants = [];
    $scope.current.page = 3;

    
    var previousRestaurant = GuestService.getSelectedRestaurant();

    $http.get('/api/restaurants/all').success(function (data) {
        $scope.restaurants = data;
        for(var i = 0; i < $scope.restaurants.length; i++){
            if($scope.restaurants[i].id === previousRestaurant.id) {
                $scope.selectedRow = i;
                $scope.selectedRestaurant = $scope.restaurants[i];
                return;
            }
        }
    });


    $scope.selectedRow = null ;  // initialize our variable to null

    $scope.setClickedRow = function(index,restaurant){  //function that sets the value of selectedRow to current index
        $scope.selectedRow = index;

        $scope.selectedRestaurant = restaurant;
    }

    $scope.createNewReservation = function (reservation) {

        if(reservation.start_hour == null){
            alert("Morate uneti početno vreme rezervacije!");
            return;
        }
        alert("Vaša rezervacija je uspešno dodata!");
    };

    $scope.goToSelectMenuItem = function (reservation) {
        if(reservation.start_hour == null){
            alert("Morate uneti početno vreme rezervacije!");
            return;
        }
        $state.transitionTo( "guest.selectMenuItem");
    };

    $scope.validateDate = function (reservation) {
        if(typeof reservation.date === 'undefined' || reservation.date === null) {
            return;
        }
        console.log(reservation.date);
        var today = new Date();
        today  = new Date(today.getFullYear(), today.getMonth(), today.getDay());
        var selectedDate = new Date(reservation.date.getFullYear(), reservation.date.getMonth(), reservation.date.getDay() );
        console.log(today.getTime() - selectedDate.getTime());
        console.log(selectedDate.getTime());
        console.log(today.getTime());
        if(selectedDate.getTime() < today.getTime()){
            alert("Datum rezervacije ne sme biti u prošlosti!");
            reservation.date = null;
        }
    };

        $scope.validateTime = function(reservation){
            var startHour;
            var endHour;


            if(reservation.start_hour == null ){
                return;
            }

            if(reservation.start_hour.length == 1 || reservation.start_hour.length == 2){
                if(isNaN(reservation.start_hour)){
                    alert("Uneta vrednost za vreme mora biti broj!");
                }
                return;
            }

            if(reservation.start_hour.length == 3){

                if(reservation.start_hour.charAt(2) != ":"){
                    alert("Vreme se unosi u formatu hh:mm 24h.");
                }
                return;
            }

            if(reservation.start_hour.length == 4){
                startHour = parseInt(reservation.start_hour.substring(3));
                if(isNaN(startHour)){
                    alert("Uneta vrednost za vreme mora biti broj!");
                }
                return;
            }

            if(reservation.start_hour.length == 5){
                startHour = parseInt(reservation.start_hour.substring(4));
                if(isNaN(startHour)){
                    alert("Uneta vrednost za vreme mora biti broj!");
                    return;
                }

            }


            var patt = new RegExp("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            if(!patt.test(reservation.start_hour)){
                alert("Vreme početka rezervacije mora da bude u formatu hh:mm 24h.");
                return;
            }

            ///////////////////////////
            if(reservation.end_hour == null ){
                return;
            }

            if(reservation.end_hour.length == 1 || reservation.end_hour .length == 2){
                if(isNaN(reservation.end_hour )){
                    alert("Uneta vrednost za vreme mora biti broj!");
                }
                return;
            }

            if(reservation.end_hour.length == 3){

                if(reservation.end_hour.charAt(2) != ":"){
                    alert("Vreme se unosi u formatu hh:mm 24h.");
                }
                return;
            }

            if(reservation.end_hour.length == 4){
                endHour = parseInt(reservation.end_hour.substring(3));
                if(isNaN(endHour)){
                    alert("Uneta vrednost za vreme mora biti broj!");
                }
                return;
            }

            if(reservation.end_hour.length == 5){
                endHour = parseInt(reservation.end_hour.substring(4));
                if(isNaN(endHour)){
                    alert("Uneta vrednost za vreme mora biti broj!");
                    return;
                }

            }


            var patt = new RegExp("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
            if(!patt.test(reservation.end_hour)){
                alert("Vreme početka rezervacije mora da bude u formatu hh:mm 24h.");
                return;
            }



            if(reservation.start_hour != null && reservation.end_hour != null) {

                startHour = parseInt(reservation.start_hour.substring(0, 3));
                var startMinutes = parseInt(reservation.start_hour.substring(3));

                var endHour = parseInt(reservation.end_hour.substring(0, 3));
                var endMinutes = parseInt(reservation.end_hour.substring(3));



                if(startHour == endHour){
                    if(!(startMinutes < endMinutes)){
                        alert("Vreme početka rezervacije mora biti veće od završetka!");
                    }
                }

                if(startHour > endHour){
                    alert("Vreme početka rezervacije mora biti veće od završetka!");
                }
            }






        };
});