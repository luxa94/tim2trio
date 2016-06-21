/**
 * Created by Sandra on 20.6.2016.
 */
iceipiceApp.controller('providerPreviewListsController', function ($scope, $http, $state, $stateParams, ProviderService, authorizationService) {

    $scope.current.page = 1;
    $scope.status = ["aktivna lista", "zatvorena lista","istekla"];
    $scope.currentStatus = $scope.status[0];
    $scope.auctionsList = [];
    $scope.d = false;
    $scope.selectedList = {};


    $http.get('/api/auction/all').success(function(data) {
        $scope.auctionsList = data;
        //console.log("sve aukcije: " + JSON.stringify(data));
        var i;
        var today = new Date();
        for(i=0; i<data.length; i++){
            if(data[i].to_date.valueOf() < today.valueOf()){
                data[i].status = "istekla";
            }
            $scope.auctionsList[i].from_date = new Date(data[i].from_date).toISOString().substring(0, 10);
            $scope.auctionsList[i].to_date = new Date(data[i].to_date).toISOString().substring(0, 10);
        }
        console.log("sve aukcije: " + JSON.stringify($scope.auctionsList));
    });

    $scope.previewList = function(list){
        $scope.selectedList = list;
        $http.get('api/auction/allItemsFromAuction/' + $scope.selectedList.id).success(function (data) {
            console.log("STAVKE: " + JSON.stringify(data));
            $scope.selectedList.items = data;
        })
    }
    
    $scope.newBid = function (list) {
        //prosledi listu kontroleru newBidController
        ProviderService.setSelectedList(list);
        $state.transitionTo( "provider.newBid");
    }
});