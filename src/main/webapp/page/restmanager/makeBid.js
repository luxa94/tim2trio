/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerMakeBidController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 4;
    $scope.status = ["aktivna lista","na čekanju", "zatvorena lista","istekla", "u pripremi"];
    $scope.currentStatus;
    $scope.auction = {
        id : undefined,
        from_date: new Date(),
        to_date: new Date(),
        status: $scope.status[4]
    };
    $scope.listItems = [{"name":"asd","quantity":1,"type":{"name":"a","$$hashKey":"object:16"},"unit":{"name":"d","$$hashKey":"object:17"}}];
    $scope.newAuctionItem = {};
    $scope.newType = {};
    $scope.newUnit = {};
    $scope.aiUnits = [];
    $scope.aiTypes = [];
    $scope.auctionsList = [];
    
    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
        $scope.auction.restaurantId = $scope.restaurant.id;
    });

    $http.get('/api/auction/all').success(function(data) {
        $scope.auctionsList = data;
    });

    $scope.addNewListItem = function(){
        $scope.newListItem = {};
        $scope.popup = new Foundation.Reveal($('#newListItem'));
        $scope.popup.open();
    };

    $scope.cancel = function () {
        //odustani od nove aukcije
        $scope.auction = {
            id : undefined,
            from_date: new Date(),
            to_date: new Date(),
            status: $scope.status[4]
        };
        $scope.listItems = [];
        $scope.newAuctionItem = {};
        $scope.popup.close();
    };

    $scope.addListItem = function () {
        $scope.listItems.push($scope.newAuctionItem);
        console.log(JSON.stringify($scope.listItems));
        $scope.popup.close();
    }

    $scope.addUnit = function () {
        if($scope.newUnit){
            //http post...!!!!!
            $scope.aiUnits.push($scope.newUnit);
            console.log(JSON.stringify($scope.aiUnits));
        }
    }

    $scope.addType = function () {
        if($scope.newType){
            //http post...!!!!!
            $scope.aiTypes.push($scope.newType);
            console.log(JSON.stringify($scope.aiTypes));
        }
    }

    $scope.cancelNewAuction = function(){
        $scope.auction = {
            from_date: new Date(),
            to_date: new Date(),
            status: $scope.status[4]
        };
        $scope.listItems = [];
        $scope.newAuctionItem = {};

    }

    $scope.addAuction = function () {
        if($scope.listItems.length == 0){
            alert("Vaša lista za nabavku ne sadrži ni jednu stavku!");
        }
        else{
            $http.post('/api/auction/newAuction', $scope.auction).success(function(data) {
                //treba dodati u bazu sve stavke liste!!!
            });
        }
    }

});