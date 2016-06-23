/**
 * Created by Sandra on 22.6.2016.
 */
iceipiceApp.controller('providerMyBidsController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current.page = 3;
    $scope.user = authorizationService.getUser();
    $scope.bidStatus = ["u pripremi", "aktivna", "istekla","odbijena","odobrena"];
    $scope.currentStatus = $scope.bidStatus[0];
    $scope.bids = [];
    $scope.selectedBid = {};
    $scope.selectedBidItems = [];
    $scope.selectedAuctionItems = [];
    $scope.selectedBidItem = {};

    $http.get('api/bid/getBidsFromProvider/' + $scope.user.id).success(function (data) {
        $scope.bids = data;
        console.log("BIDS: " + JSON.stringify(data));
    })


    $scope.previewBid = function(b){
        $scope.selectedBid = b;
        $scope.selectedBid.timestamp = new Date(b.timestamp);
        console.log("\nsss: " + JSON.stringify(b));
        $http.get('api/auction/allItemsFromAuction/' + $scope.selectedBid.auction.id).success(function (data) {
            console.log("STAVKE Auction: " + JSON.stringify(data));
            $scope.selectedAuctionItems = data;

            //...

            $http.get('api/bid/getBidItems/' + $scope.selectedBid.id).success(function (data) {
                console.log("STAVKE BID: " + JSON.stringify(data));
                $scope.selectedBidItems = data;
            })
        })
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
    
    $scope.details = function (id) {
        var i;
        for(i=0; i<$scope.selectedBidItems.length; i++){
            if($scope.selectedBidItems[i].auctionItem.id == id){
                $scope.selectedBidItem =  $scope.selectedBidItems[i];
            }
        }
        $scope.popup = new Foundation.Reveal($('#bidItemDetails'));
        $scope.popup.open();
    }
});