/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerMakeBidController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 4;
    $scope.status = ["aktivna lista","na čekanju", "zatvorena lista","istekla"];
    $scope.currentStatus;
    $scope.auction = {
        id : "UCITATI",
        from_date: new Date(),
        to_date: new Date(),
        status: "UCITATI"
    };
    $scope.listItems = [];
    $scope.newListItem = {};
    $scope.newType = {};
    $scope.newUnit = {};
    
    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
        $scope.auction.restaurantId = $scope.restaurant.id;
    });

    $scope.addNewListItem = function(){
        $scope.newListItem = {};
        $scope.popup = new Foundation.Reveal($('#newListItem'));
        $scope.popup.open();
    };

    $scope.cancel = function () {
        $scope.popup.close();
    };

    $scope.addListItem = function () {
        
    }
});