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
              //  if(data.length != $scope.numberOfActiveFR){
                    for(var i = 0; i  < data.length; i++) {
                        if(data[i].unread && data[i].fromUser.id == $scope.user.id) {
                            // someone accepted u as a friend life++ ?
                            alert(data[i].toUser.name + " je prihvatio Vas zahtev za prijateljstvo!");
                            // mark as read...!
                            $http.get('api/guest/markRequestAsRead/' + data[i].id);
                        } // markRequestAsRead
                        if(data[i].status) {
                            // already handled, skip it?
                            return;
                        }
                        //guest/handleFriendRequest/{myId}/{friendId}/{accepted}
                        // fromUser
                        // toUser
                        var accepted = confirm((data[i].fromUser.name) + " želi da bude vaš prijatelj. Prihvati?" );

                        if(accepted){
                            alert("Uspesno ste dodali prijatelja!")
                            $http.get('api/guest/handleFriendRequest/'+ data[i].toUser.id + '/'+ data[i].fromUser.id + '/true');
                        }
                        else{
                            alert("Odbili ste zahtev za prijateljstvo!");
                            $http.get('api/guest/handleFriendRequest/'+ data[i].toUser.id + '/'+ data[i].fromUser.id + '/false');
                        }
                    }


             //   }else{
               //    alert("Nemate novih obavestenja");
             //   }
            });
        }, 5000);
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