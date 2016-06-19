/**
 * Created by Nina on 26-May-16.
 */
iceipiceApp.controller('guestModifyReservationController', function ($scope, $http, $state, $stateParams, authorizationService,ReservationService) {
    $scope.reservations = [];
    $scope.current.page = 4;



    $http.get('/api/reservations/all/' + $scope.user.id).success(function (data) {

        var today = new Date(Date.now() - (1000 /*sec*/ * 60 /*min*/ * 30 )).getTime();



        for(var i = 0; i < data.length; i++){


            var calculatedDate = new Date( data[i].date);
            var todayDay = calculatedDate.getDay();
            var todayMonth = calculatedDate.getMonth();
            var todayYear = calculatedDate.getYear();
            var startHour = parseInt(data[i].start_hour.substring(0,3));
            var startMinutes = parseInt(data[i].start_hour.substring(3));


            data[i].niceDate = new Date(todayYear, todayMonth, todayDay, startHour, startMinutes).toString();

            calculatedDate = new Date(todayYear, todayMonth, todayDay, startHour, startMinutes).getTime();


            if( data[i].date <= today) {
                data[i].tooLate = true;
            } else {
                data[i].tooLate = false;
            }
            console.log("IS IT TOO LATE?",    data[i] );
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

    $scope.gradeReservation = function() {

        if( $scope.selectedRow == -1 || $scope.selectedRow == null){
            alert("Morate odabrati jednu rezervaciju.");
            return;
        }
        ReservationService.setUnderReview($scope.selectedReservation );

        $state.transitionTo( "guest.gradeReservation");

    }

    
});