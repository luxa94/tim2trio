/**
 * Created by Sandra on 22.6.2016.
 */
iceipiceApp.controller('providerMyBidsController', function ($scope, $http, $state, $stateParams, authorizationService) {

    $scope.current.page = 3;
    $scope.user = authorizationService.getUser();
    $scope.bidStatus = ["u pripremi", "aktivna", "istekla","odbijena","odobrena"];
    $scope.currentStatus = $scope.bidStatus[0];
    $scope.bids = [];

    $http.get('api/bid/getBidsFromProvider/' + $scope.user.id).success(function (data) {
        $scope.bids = data;
        console.log("BIDS: " + JSON.stringify(data));
    })


    $scope.previewBid = function(b){
        $scope.selectedBid = b;
        console.log("\n\nsss: " + JSON.stringify(b));

        $http.get('api/bid/getBidItems/' + $scope.selectedBid.id).success(function (data) {
            console.log("STAVKE: " + JSON.stringify(data));
           // $scope.selectedList.items = data;
        })

    }
});