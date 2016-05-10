/**
 * Created by Nina on 17-Apr-16.
 */
iceipiceApp.controller('guestAddFriendController', function ($scope, $http, $state, $stateParams, authorizationService,GuestService) {
    var friendsList = []
        $scope.orderList = "name";
        $scope.query;
        $scope.friends =  [];

        setTimeout(function () {
            GuestService.GetAllFriends($scope.user.id).then(function(data) {
                var filteredFriends = [];
                for (var i = 0; i < data.length; i++){
                    if(data[i].id !== $scope.user.id){
                        filteredFriends.push(data[i]);
                    }
                }
                $scope.friends = filteredFriends;
            }, function(data){
            });
        }, 2000);




            // [
            
     //       {name: "Mark", gender: "male", number: "(565) 355-3748", image: "http://markmurray.co/images/profiler.jpg"},
    //        {name: "Glen", gender: "male", number: "(233) 245-3753"},

            
      //  ];
        var list = $scope.friends;
        var arrayLength = list.length;

        // Using $http to fetch JSON data
        function getImage($scope, $http, index){
            $http({
                method: 'GET',
                url: 'http://uifaces.com/api/v1/random'
            })
                .success(function(data, status, headers, config) {
                    // Set image key = random JSON image
                    $scope.friends[index].image = data.image_urls.normal;
                }, $scope).error(function(data, status, headers, config) { alert(status); });
        };

        // Get a new image for each friend in the array
        for(var i=0;i<arrayLength;i++){
            getImage($scope, $http, i);
        }

        // Reverse Order Button
        $scope.reverse = function(){
            if($scope.orderList == "name"){
                $scope.orderList = "-name";
            } else {
                $scope.orderList = "name";
            }
        };

        // Add New User Button
        $scope.addFriend = function(id) {
            GuestService.AddFriend($scope.user.id, id).then(function(data){
               // added the friend
                console.log("added a new friend! :) Friendship is magic <3");
                alert("Prijatelj dodat!");
                for(var i = 0; i< $scope.friends.length; i++) {
                    if(id == $scope.friends[i]){
                        $scope.friends[i].friend = true;

                    }
                }
                window.location.reload();

            }, function(data) {
                // failed to add the friend
            });
        };

        $scope.removeFriend =  function(id) {

            var choice = confirm("Da li ste sigurni da želite da uklonite prijatelja?");
            if(!choice) {
                return;
            }
            GuestService.RemoveFriend($scope.user.id, id).then(function(data){
                // added the friend
                console.log("added a new friend! :) Friendship is magic <3");
                alert("Prijatelj izbrisan!");
                for(var i = 0; i< $scope.friends.length; i++) {
                    if(id == $scope.friends[i]){
                        $scope.friends[i].friend = false;
                    }
                }
                window.location.reload();

                // failed to add the friend
            });
        };
    });




