/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestBaseController', function ($scope, $interval, $http, $state, $stateParams, authorizationService, ReservationService) {

    $scope.current = {
        page: 0
    };

    $scope.user = authorizationService.getUser();

    $scope.numberOfActiveFR = 0;
    $http.get('/api/quest/getActiveFriendshipRequests/' + $scope.user.id).success(function (data) {
        $scope.numberOfActiveFR = data.length;
    });


    
    $scope.home = function() {
        $scope.current.page = 0;
        $state.transitionTo('guest.home');
    };

    $scope.addFriend = function() {
        $scope.current.page = 1;
        $state.transitionTo('guest.addFriend');
    };

    $scope.showRestaurants = function() {
        $scope.current.page = 2;
        $state.transitionTo('guest.showRestaurants');
    };

    $scope.addReservation = function() {
        $scope.current.page = 3;
        ReservationService.asd.reservation.date = null;
        ReservationService.update = false;
        $state.transitionTo('guest.addReservation');
    };

    $scope.modifyReservation = function() {
        $scope.current.page = 4;
        $state.transitionTo('guest.modifyReservation');
    };

    $scope.logout = function() {
        $state.transitionTo('logout');
    };

    var startedInterval;
    $scope.startPing = function(num) {
        if (angular.isDefined(startedInterval)) {
            return;
        }

        startedInterval = $interval(function(retVal) {
            $http.get('api/guest/getActiveFriendshipRequests/' + $scope.user.id).success(function (data) {
           //     console.log("frrrrrrrr: "+JSON.stringify(data));
           //     console.log(data.length);
          //      console.log($scope.numberOfActiveFR);
                if(data.length != $scope.numberOfActiveFR){
                    if(confirm("Prijatelj" + " želi da bude vaš prijatelj. Prihvati?" ) == true){
                        alert("Uspesno ste dodali prijatelja!")
                        data.length = data.length - 1;
                    }
                    else{
                        alert("Odbili ste zahtev za prijateljstvo!");
                    }


                }else{
               //    alert("Nemate novih obavestenja");
                }
            });
        }, 1000);
    };

    $scope.stopPing = function() {
        if (angular.isDefined(startedInterval)){
            $interval.cancel(startedInterval);
            startedInterval = undefined;
        }
    };

    $scope.$on('$destroy', function() {
        $scope.stopPing();
    });

    var num = Math.random();
    $scope.startPing(num);



});