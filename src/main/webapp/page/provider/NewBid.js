/**
 * Created by Sandra on 21.6.2016.
 */
iceipiceApp.controller('providerNewBidController', function ($scope, $http, $state, $stateParams, ProviderService, authorizationService) {
    $scope.current.page = 2;
    $scope.user = authorizationService.getUser();
    $scope.bidStatus = ["u pripremi", "aktivna", "istekla","odbijena","odobrena"];
    $scope.status = ["aktivna lista", "zatvorena lista","istekla"];
    $scope.currentStatus = $scope.status[0];
    $scope.auctionsList = [];
    $scope.d = false;
    $scope.selectedList = ProviderService.getSelectedList();
    $scope.selectedListItem = {};
    $scope.bidItem = {};
    $scope.bidItems = [];
    $scope.aiUnits = [];
    $scope.newUnit = {};
    $scope.bid = {};
    $scope.bidAlreadyExist = false;

    $http.get('/api/auctionItemUnit/all').success(function (data) {
        $scope.aiUnits = data;
    });

    $scope.addUnit = function () {
        if($scope.newUnit){
            $http.post('/api/auctionItemUnit/new',$scope.newUnit).success(function (data) {
                console.log("new unit: " + JSON.stringify(data));
                $scope.aiUnits.push(data);
                //console.log("sve merne jedinice: " + JSON.stringify($scope.aiUnits));
            });
        }
    }

    //proveri da li je vec postavio ponudu!
    if(!isEmpty($scope.selectedList)) {
        $http.get('/api/bid/getBidFromAuctionIdAndProviderId/' + $scope.selectedList.id + '/' + $scope.user.id).success(function (data) {
            console.log("NEMAM POJMA STA CE ISPISATI: " + JSON.stringify(data));
            if (!isEmpty(data)) {
                $scope.bidAlreadyExist = true;
                $scope.bidId = data.id;
                $scope.bid.status = data.status;
                $scope.bid.timestamp = new Date(data.timestamp);
                $scope.bid.price = data.price;
                $scope.bid.providerId = data.provider.id;
                $scope.bid.auctionId = data.auction.id;
                $scope.bid.currency = data.currency;

                $http.get('/api/bid/getBidItems/' + data.id).success(function (data) {
                    console.log("NEMAM POJMA2 : " + JSON.stringify(data));
                    $scope.bidItems = data;
                    var i;
                    for (i = 0; i < $scope.bidItems.length; i++) {
                        $scope.bidItems[i].auctionItemId = $scope.bidItems[i].auctionItem.id;
                        $scope.bidItems[i].auctionItemUnitId = $scope.bidItems[i].auctionItemUnit.id;
                    }

                    console.log("BID ITEMS : " + JSON.stringify($scope.bidItems));
                })
            }
        })


    }

    $scope.setNewBidItem = function (ind) {
        $scope.selectedListItem = $scope.selectedList.items[ind];
        $scope.bidItem = getBidItem();
        console.log("selectedListItem = " + JSON.stringify($scope.selectedListItem));

    }

    getBidItem = function () {
        var i;
        for(i=0; i<$scope.bidItems.length; i++){
            if($scope.bidItems[i].auctionItemId == $scope.selectedListItem.id){
                return $scope.bidItems[i];
            }
        }
        return {};
    }

    $scope.addBidItem = function () {
        
        if(isEmpty(getBidItem())){
            console.log("BidItem za dodavanje: " + JSON.stringify($scope.bidItem));
            $scope.bidItem.auctionItemId = $scope.selectedListItem.id;
            $scope.bidItems.push($scope.bidItem);
            $scope.bid.price = $scope.totalPrice();
        }
    }

    $scope.addNewAuction = function () {

        $scope.bid.status = $scope.bidStatus[0];
        $scope.bid.timestamp = new Date();
        $scope.bid.price = $scope.totalPrice();
        $scope.bid.providerId = $scope.user.id;
        $scope.bid.auctionId = $scope.selectedList.id;
        console.log("BID: " + JSON.stringify($scope.bid));
        if($scope.bidItems.length == 0){
            alert("Ne postoji ponuda ni za jednu stavku! Ponuda neće biti kreirana.");
        }
        else if(!$scope.bidAlreadyExist) {
            $http.post('/api/bid/new', $scope.bid).success(function (data) {
                console.log("**items: " + JSON.stringify(data));

                var i;
                var item;
                for (i = 0; i < $scope.bidItems.length; i++) {
                    console.log("stavka: " + JSON.stringify(data));
                    item = {
                        "name": $scope.bidItems[i].name,
                        "quantity": $scope.bidItems[i].quantity,
                        "price": $scope.bidItems[i].price,
                        "auctionItemId": $scope.bidItems[i].auctionItemId,
                        "auctionItemUnitId": $scope.bidItems[i].unit.id,
                        "bidId": data.id
                    }
                    console.log("stavka ponude: " + JSON.stringify(item));
                    $http.post('api/bidItem/new', item).success(function (data) {
                        console.log("nova stavka aukcija: " + JSON.stringify(data));
                    });
                }
            })
        } else{

            //treba da se update-uje ponuda, a ne da se kreira nova!
            $http.post('/api/bid/update/' + $scope.bidId, $scope.bid).success(function (data) {
                console.log("** 129 linija " + JSON.stringify(data));

                var i;
                var item;
                for (i = 0; i < $scope.bidItems.length; i++) {
                    console.log("stavka: " + JSON.stringify(data));
                    item = {
                        "name": $scope.bidItems[i].name,
                        "quantity": $scope.bidItems[i].quantity,
                        "price": $scope.bidItems[i].price,
                        "auctionItemId": $scope.bidItems[i].auctionItemId,
                        "auctionItemUnitId": $scope.bidItems[i].auctionItemUnitId,
                        "bidId": data.id
                    }
                    console.log("stavka ponude: " + JSON.stringify(item));
                    $http.post('api/bidItem/update/' + $scope.bidItems[i].id, item).success(function (data) {
                        console.log("azurirana stavka ponude: " + JSON.stringify(data));
                    });
                }
            })

        }
    }

    $scope.checkSelectedListItem = function () {

        return isEmpty($scope.selectedListItem);
    }

    $scope.checkSelectedList = function () {

        return isEmpty($scope.selectedList);
    }

    $scope.totalPrice = function () {
        var i;
        var t;
        t = 0;
        for(i=0; i<$scope.bidItems.length; i++){
            t = t + parseFloat($scope.bidItems[i].price);
        }
        return t;
    }

    $scope.activateBid = function () {
        console.log("BID: " + JSON.stringify($scope.bid));
        $http.post('api/bid/activateBid', $scope.bid.id).success(function (data) {
            console.log("DATA: " + JSON.stringify(data));
        })
    }

    function isEmpty(obj) {
        for (var x in obj) { return false; }
        return true;
    }

    $scope.unitNotSet = function () {
        return isEmpty($scope.bidItem.unit);
    }
    
});
