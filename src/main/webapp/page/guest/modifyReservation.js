/**
 * Created by Nina on 26-May-16.
 */
iceipiceApp.controller('guestModifyReservationController', function ($scope, $http, $state, $stateParams, authorizationService,ReservationService, GuestService) {
    $scope.reservations = [];
    $scope.current.page = 4;

    $scope.reservation = {
        niceDate: new Date()
    };

    $scope.grade = {};
    $scope.grade.meal_grade = 0;
    $scope.grade.waiter_grade = 0;
    $scope.grade.atmosphere_grade= 0;
    $scope.grade.meal_comment = null;
    $scope.grade.waiter_comment= null;
    $scope.grade.atmosphere_comment = null;

 //   $scope.asd = ReservationService.asd;

    $scope.rateFunction = function(rating) {
        console.log('Rating selected: ' + rating);
    };

    $scope.gradeRes = function() {
        $scope.grade.userId = $scope.user.id;
        $scope.grade.reservationId = ReservationService.getUnderReview().id;
        ReservationService.CreateReview($scope.grade);
        $scope.popup.close();

    };

    $http.get('/api/reservations/all/' + $scope.user.id).success(function (data) {
        
        var today = new Date(Date.now() - (1000 /*sec*/ * 60 /*min*/ * 30 )).getTime();

        for(var i = 0; i < data.length; i++){

            var calculatedDate = new Date(data[i].date);
          //  console.log(calculatedDate);

            var todayDay = calculatedDate.getDay();
            console.log(todayDay);
            var todayMonth = calculatedDate.getMonth();
            console.log(todayMonth);
            var todayYear = calculatedDate.getFullYear();
            console.log(todayYear);
            var startHour = parseInt(data[i].start_hour.substring(0,3));
            var startMinutes = parseInt(data[i].start_hour.substring(3));

            var date = calculatedDate.getTime();
            console.log(date);


              data[i].date = new Date(data[i].date);
            data[i].niceDate = data[i].date.toISOString().substring(0, 10);
      //      calculatedDate = new Date(todayYear, todayMonth, todayDay, startHour, startMinutes).getTime();


            if( data[i].date <= today) {
                data[i].tooLate = true;
            } else {
                data[i].tooLate = false;
            }
     //       console.log("IS IT TOO LATE?",    data[i] );
        }
        $scope.reservations = data;
    });


    $scope.selectedRow = null;  // initialize our variable to null
    $scope.setClickedRow = function(index,reservation){  //function that sets the value of selectedRow to current index
        $scope.selectedRow = index;

        $scope.selectedReservation = reservation;

    }

    $scope.mouseOverDelete = function() {
        if($scope.selectedReservation.tooLate){
            alert("Niste u mogućnosti da otkažete rezervaciju u poslednjih 30 min pre njenog početka.");
        }
    }

    $scope.removeReservation = function(){

        if($scope.selectedReservation == null){
            alert("Morate selektovati odgovarajuću rezervaciju.");
            return;

        }

        ReservationService.Delete($scope.selectedReservation.id).then(function(){
            for(var i = 0; $scope.reservations.length; i++){
                if($scope.reservations[i].id === $scope.selectedReservation.id){
                    $scope.reservations.splice(i,1);
                    $scope.selectedReservation = null;
                    return;
                }
            }
        }, function (){
            alert("Unable to delete reservation.");
        });
    }

    $scope.gradeReservation = function(reservation) {
        if( $scope.selectedRow == -1 || $scope.selectedRow == null){
            alert("Morate odabrati jednu rezervaciju.");
            return;
        }
        $scope.openDialogForGradeRestaurant(reservation);
        ReservationService.setUnderReview($scope.selectedReservation );
     //   $state.transitionTo( "guest.gradeReservation");


    }

    $scope.openDialogForGradeRestaurant = function(reservation) {
        $scope.reservation = reservation;
        $scope.popup = new Foundation.Reveal($('#newGradeRestaurant'));
        $scope.popup.open();
    };

    $scope.cancel = function () {
        $scope.popup.close();
    };


    $scope.modifyReservation = function(reservation) {
        
        if($scope.selectedReservation == null){
            alert("Morate selektovati odgovarajuću rezervaciju.");
            return;
        }
        ReservationService.update = true;

        ReservationService.setUnderReview(reservation);
        ReservationService.asd.reservation = reservation;
     //   GuestService.setSelectedRestaurant(reservation.restaurantId);
        $http.get('/api/restaurant/one/'+ reservation.restaurantId).success(function (data) {
            GuestService.setSelectedRestaurant(data);
            reservation.date = new Date(reservation.date);
            $state.transitionTo( "guest.addReservation");
        });
    };

    $scope.openDialogForReservationInfo = function(reservation) {
        $scope.reservation = reservation;
        $scope.popup = new Foundation.Reveal($('#newShowReservationInfo'));
        $scope.popup.open();
    };

    $scope.showReservation = function (reservation) {
        if($scope.selectedReservation == null){
            alert("Morate selektovati jednu rezervaciju!");
            return;
        }
   //     $scope.selectedReservation = reservation;
        $scope.openDialogForReservationInfo(reservation);
    };



});