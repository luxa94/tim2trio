/**
 * Created by Sandra on 21.6.2016.
 */
iceipiceApp.controller('providerNewBidController', function ($scope, $http, $state, $stateParams, ProviderService, authorizationService) {
    $scope.current.page = 2;
    $scope.bidStatus = ["u pripremi", "aktivna", "istekla","odbijena","odobrena"];
    $scope.status = ["aktivna lista", "zatvorena lista","istekla"];
    $scope.currentStatus = $scope.status[0];
    $scope.auctionsList = [];
    $scope.d = false;
    $scope.selectedList = ProviderService.getSelectedList();
    $scope.selectedListItem = {};
    $scope.bidItem = {};
    $scope.aiUnits = [];
    $scope.newUnit = {};

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


});
