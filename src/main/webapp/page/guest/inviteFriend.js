/**
 * Created by Nina on 13-Jun-16.
 */
iceipiceApp.controller('guestInviteFriendController', function ($scope, $http, $state, $stateParams, authorizationService, GuestService, ReservationService) {
    $scope.friends = [];
    $scope.invitedFriends = [];
    $scope.current.page = 3;

    GuestService.GetAllFriends($scope.user.id).then(function(data) {
        var friendsList = [];
        for(var i = 0; i < data.length; i++) {
            if(data[i].friend) {
                data[i].isInvited = false;
                friendsList.push(data[i]);
            }
        }
        $scope.friends = friendsList;
    });


    $scope.selectedRow = null;  // initialize our variable to null
    $scope.setClickedRow = function(index,friend){  //function that sets the value of selectedRow to current index
        $scope.selectedRow = index;

        $scope.selectedRestaurant = friend;
        
    }

    $scope.createNewReservation = function () {
        var reservation  = ReservationService.asd.reservation;
        reservation.guests = $scope.invitedFriends;
        reservation.guests.push($scope.user);
        ReservationService.Create(reservation).then(function (data) {
            alert("Vaša rezervacija je uspešno dodata!");
        }, function(){
            alert("Vaša rezervacija nije uspešno dodata!");
        });

    };

  //  $scope.goToInviteFriend = function () {
 //       $state.transitionTo( "guest.inviteFriend");
 //   };

    $scope.invitedChanged = function(friend){
            // ubaciti / izbaciti iz liste
        if(!friend.isInvited) {
            for (var i = 0; i < $scope.invitedFriends.length; i++) {
                if ($scope.invitedFriends[i].id === friend.id) { //If it is checked
                    $scope.invitedFriends.splice(i, 1);
                    return;
                }
            }
        }
        $scope.invitedFriends.push(friend);
        }

    $scope.goToSelectMenuItem = function (reservation) {

        var reservation  = ReservationService.asd.reservation;
        reservation.guests = $scope.invitedFriends;
        reservation.guests.push($scope.user);
        
        $state.transitionTo( "guest.selectMenuItem");
    };



    

});