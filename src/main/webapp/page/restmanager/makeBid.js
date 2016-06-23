/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerMakeBidController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 4;
    $scope.status = ["aktivna lista", "zatvorena lista","istekla", "u pripremi"];
    $scope.currentStatus = "u pripremi";
    $scope.auction = {
        from_date: new Date(),
        to_date: new Date(),
        status: $scope.status[3]
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
    $scope.bids = [];
    $scope.selectedBid = {};
    



    
    
    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
        $scope.auction.restaurantId = $scope.restaurant.id;
    });

    $http.get('/api/auction/all').success(function(data) {
        $scope.auctionsList = data;
        var i;
        var today = new Date();
        for(i=0; i<data.length; i++){
            if(data[i].to_date.valueOf() < today.valueOf()){
                $scope.auctionsList[i].status = $scope.status[2];
                $http.post('api/auction/expireAuction', data[i].id).success(function (data) {
                    $scope.selectedList = data;
                })
            }
            $scope.auctionsList[i].from_date = new Date(data[i].from_date).toISOString().substring(0, 10);
            $scope.auctionsList[i].to_date = new Date(data[i].to_date).toISOString().substring(0, 10);
        }
        //console.log("SVE AUKCIJE ZA ISCRTAVANJE: " + JSON.stringify($scope.auctionsList));
        getBids($scope.auctionsList);
        console.log("###BIDS "  + JSON.stringify($scope.bids));
    });

    getBids = function(auctionsList){
        console.log("****aukcije glupave "  + JSON.stringify(auctionsList));
        var i;
        for(i=0; i<auctionsList.length; i++){
            console.log("aaaa");
            //$scope.bids[i]  =[];
            console.log("data =  " + JSON.stringify($scope.bids));
            $http.get('api/bid/getBidsFromAuctionId/' + auctionsList[i].id).success(function (data) {

                $scope.bids.push(data);
               // console.log("data =  " + JSON.stringify($scope.bids));
                //     console.log("\nponude: " + JSON.stringify(data) + " na aukciju: " + auctionsList[i].id);
            })
        }
        console.log("****BIDS "  + JSON.stringify($scope.bids));
    }

    $http.get('/api/auctionItemType/all').success(function (data) {
        $scope.aiTypes = data;
    });

    $http.get('/api/auctionItemUnit/all').success(function (data) {
        $scope.aiUnits = data;
    });

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
            $scope.auction.status = "u pripremi";
            console.log("$scope.auction: " + JSON.stringify($scope.auction));

            $http.post('/api/auction/new', $scope.auction).success(function(data) {
                console.log("nova aukcija: " + JSON.stringify(data));
                var i;
                var item;
                for (i=0; i<$scope.listItems.length; i++){
                   // console.log("stavka: " + JSON.stringify(data));
                    item = {
                        "name":$scope.listItems[i].name,
                        "quantity":$scope.listItems[i].quantity,
                        "auctionItemTypeId": $scope.listItems[i].type.id,
                        "auctionItemUnitId": $scope.listItems[i].unit.id,
                        "auctionId":data.id
                    }
                    console.log("stavka: " + JSON.stringify(item));
                    $http.post('api/auctionItem/new',item).success(function (data) {
                        //console.log("nova stavka aukcija: " + JSON.stringify(data));
                    });
                }
                data.from_date = new Date(data.from_date).toISOString().substring(0, 10);
                data.to_date = new Date(data.to_date).toISOString().substring(0, 10);
                $scope.auctionsList.push(data);

                //postavi praznu novu aukciju
                $scope.auction = {
                    from_date: new Date(),
                    to_date: new Date(),
                    status: $scope.status[3]
                };
                //console.log("resetovana aukcija: " + JSON.stringify($scope.auction ));
                $scope.listItems = [];
                $scope.newAuctionItem = {};
            });


        }
    }
    
    $scope.deleteItem = function(ind){
        //console.log("OBRISI STAVKU SA INDEXOM: " + ind);
        $scope.listItems.splice(ind, 1);
    }

    $scope.previewList = function(list){
        $scope.selectedList = list;
        $http.get('api/auction/allItemsFromAuction/' + list.id).success(function (data) {
            //console.log("STAVKE: " + JSON.stringify(data));
            $scope.selectedList.items = data;
            $scope.popup = new Foundation.Reveal($('#previewOneList'));
            $scope.popup.open();
        })

    }

    $scope.activateList = function (list) {

        var today = new Date();
        var to_date = new Date(list.to_date);
        //console.log("datum glupavi: " + to_date);
        //console.log("lista: " + JSON.stringify(list));
        if(to_date.valueOf() <= today.valueOf()){
            alert("Lista namirnica je ISTEKLA! Neće biti aktivirana.")
            $http.post('api/auction/expireAuction', list.id).success(function (data) {
                data.from_date = new Date($scope.selectedList.from_date).toISOString().substring(0, 10);
                data.to_date = new Date($scope.selectedList.to_date).toISOString().substring(0, 10);
                $scope.selectedList = data;
                var index = $scope.auctionsList.indexOf(list);
                if (index != -1)
                    $scope.auctionsList.splice(index, 1);
                $scope.auctionsList.push(data);
            })
            
        }
        else {

            console.log("aktiviraj listu: " + JSON.stringify(list));
            $http.post('api/auction/activateAuction', list.id).success(function (data) {
                console.log("DATA: " + JSON.stringify(data));
                $scope.selectedList = data;

                data.from_date = new Date($scope.selectedList.from_date).toISOString().substring(0, 10);
                data.to_date = new Date($scope.selectedList.to_date).toISOString().substring(0, 10);

                var index = $scope.auctionsList.indexOf(list);
                if (index != -1)
                    $scope.auctionsList.splice(index, 1);
                $scope.auctionsList.push(data);
            })
        }
    }



    //sve potrebno za pregled ponude
    $scope.previewBid = function(b){
        $scope.selectedBid = b;
        $scope.selectedBid.timestamp = new Date(b.timestamp).toISOString().substring(0, 10);
        console.log("\nsss: " + JSON.stringify(b));
        $http.get('api/auction/allItemsFromAuction/' + $scope.selectedBid.auction.id).success(function (data) {
            console.log("STAVKE Auction: " + JSON.stringify(data));
            $scope.selectedAuctionItems = data;

            //...

            $http.get('api/bid/getBidItems/' + $scope.selectedBid.id).success(function (data) {
                console.log("STAVKE BID: " + JSON.stringify(data));
                $scope.selectedBidItems = data;
                $scope.popup = new Foundation.Reveal($('#bidForm'));
                $scope.popup.open();
            });
        });


    }

    $scope.getBidItemPrice = function (id) {
        var i;
        for(i=0; i<$scope.selectedBidItems.length; i++){
            if($scope.selectedBidItems[i].auctionItem.id == id){
                return $scope.selectedBidItems[i].price;
            }
        }
        return "";
    }

    $scope.getBidItemName = function (id) {
        var i;
        for(i=0; i<$scope.selectedBidItems.length; i++){
            if($scope.selectedBidItems[i].auctionItem.id == id){
                return $scope.selectedBidItems[i].name;
            }
        }
        return "";
    }

    $scope.getBidItemQ = function (id) {
        var i;
        for(i=0; i<$scope.selectedBidItems.length; i++){
            if($scope.selectedBidItems[i].auctionItem.id == id){
                return $scope.selectedBidItems[i].quantity;
            }
        }
        return "";
    }

    $scope.getBidItemUnit = function (id) {
        var i;
        for(i=0; i<$scope.selectedBidItems.length; i++){
            if($scope.selectedBidItems[i].auctionItem.id == id){
                return $scope.selectedBidItems[i].auctionItemUnit.name;
            }
        }
        return "";
    }



    //ZATVARANJE LICITACIJE
    $scope.closeAuction = function(list,bid){
        var i;
        var j;
        for(i=0; i<$scope.auctionsList.length; i++){
            if($scope.auctionsList[i].id == list.id){
                $http.post('api/auction/closeAuction',list.id).success(function (data) {
                    console.log("\n\nglupa lista aukcija: " + JSON.stringify($scope.auctionsList));
                    console.log("Nadam se da je zatvorena aukcija: " + JSON.stringify(data));
                    console.log("i = " + i);
                    console.log("bids glupi!?!?!?!?: " + JSON.stringify($scope.bids));

                })
            }
        }


        for(j=0; j<$scope.bids.length; j++){

            if($scope.bids[j].length>0){
                if($scope.bids[j][0].auction.id == list.id){
                    var k;
                    for (k=0; k<$scope.bids[j].length; k++){
                        console.log("j = " + j +" k = " + k);
                        if($scope.bids[j][k].id == bid.id){
                            $http.post('api/bid/acceptBid',bid.id).success(function (data) {
                                console.log("Nadam se da je prihvacena ponuda: " + JSON.stringify(data));
                            })
                        }
                        else{
                            $http.post('api/bid/rejectedBid',bid.id).success(function (data) {
                                console.log("Nadam se da je odbijena ponuda: " + JSON.stringify(data));
                            })
                        }
                    }
                }
            }
        }
    }

});