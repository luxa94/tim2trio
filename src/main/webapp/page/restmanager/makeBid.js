/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerMakeBidController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 4;
    $scope.status = ["aktivna lista","na čekanju", "zatvorena lista","istekla", "u pripremi"];
    $scope.currentStatus = "u pripremi";
    $scope.auction = {
        id : undefined,
        from_date: new Date(),
        to_date: new Date(),
        status: $scope.status[4]
    };
    $scope.listItems = [];
    $scope.newAuctionItem = {};
    $scope.newType = {};
    $scope.newUnit = {};
    $scope.aiUnits = [];
    $scope.aiTypes = [];
    $scope.auctionsList = [];
    $scope.d = false;
    $scope.selectedList = {};
    
    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
        $scope.auction.restaurantId = $scope.restaurant.id;
    });

    $http.get('/api/auction/all').success(function(data) {
        $scope.auctionsList = data;
        var i;
        for(i=0; i<data.length; i++){
            $scope.auctionsList[i].from_date = new Date(data[i].from_date).toISOString().substring(0, 10);
            $scope.auctionsList[i].to_date = new Date(data[i].from_date).toISOString().substring(0, 10);
        }
    });

    $http.get('/api/auctionItemType/all').success(function (data) {
        $scope.aiTypes = data;
    })

    $http.get('/api/auctionItemUnit/all').success(function (data) {
        $scope.aiUnits = data;
    })

    $scope.addNewListItem = function(){
        $scope.newAuctionItem = {};
        $scope.newType = {};
        $scope.newUnit = {};
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

    $scope.addType = function () {
        if($scope.newType){
            $http.post('/api/auctionItemType/new',$scope.newType).success(function (data) {
                //console.log(JSON.stringify(data));
                //$scope.aiTypes.push(data);
                $scope.aiTypes.push(data);
                console.log("novi tip: " + JSON.stringify(data));
            })

        }
    }

    $scope.addUnit = function () {
        if($scope.newUnit){
            $http.post('/api/auctionItemUnit/new',$scope.newUnit).success(function (data) {
                console.log(JSON.stringify(data));
                //$scope.aiTypes.push(data);
                $scope.aiUnits.push(data);
                console.log("sve merne jedinice: " + JSON.stringify($scope.aiUnits));
            })

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
            $http.post('/api/auction/new', $scope.auction).success(function(data) {
                console.log("nova aukcija: " + JSON.stringify(data));
                var i;
                var item;
                for (i=0; i<$scope.listItems.length; i++){
                    //$scope.listItems[i].auctionId = data.id;
                    //console.log("stavka: " + JSON.stringify($scope.listItems[i]));
                    item = {
                        "name":$scope.listItems[i].name,
                        "quantity":$scope.listItems[i].quantity,
                        "auctionItemTypeId": $scope.listItems[i].type.id,
                        "auctionItemUnitId": $scope.listItems[i].unit.id,
                        "auctionId":data.id
                    }
                    console.log("stavka: " + JSON.stringify(item));
                    $http.post('api/auctionItem/new',item).success(function (data) {
                        console.log("nova stavka aukcija: " + JSON.stringify(data));
                    });
                }
                data.from_date = new Date(data.from_date).toISOString().substring(0, 10);
                data.to_date = new Date(data.from_date).toISOString().substring(0, 10);
                $scope.auctionsList.push(data);

                //postavi praznu novu aukciju
                $scope.auction = {
                    from_date: new Date(),
                    to_date: new Date(),
                    status: $scope.status[4]
                };
                $scope.listItems = [];
                $scope.newAuctionItem = {};
            });


        }
    }
    
    $scope.deleteItem = function(ind){
        console.log("OBRISI STAVKU SA INDEXOM: " + ind);
        $scope.listItems.splice(ind, 1);
    }

    $scope.previewList = function(list){
        $scope.selectedList = list;
        $http.get('api/auction/allItemsFromAuction/' + $scope.selectedList.id).success(function (data) {
            console.log("STAVKE: " + JSON.stringify(data));
            $scope.selectedList.items = data;
            $scope.popup = new Foundation.Reveal($('#previewOneList'));
            $scope.popup.open();
        })

    }

});