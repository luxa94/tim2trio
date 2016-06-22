/**
 * Created by Sandra on 20.6.2016.
 */
iceipiceApp.controller('providerBaseController', function ($interval,$scope, $http, $state, $stateParams, authorizationService) {

    $scope.current = {
        page : 0
    };

    $scope.user = authorizationService.getUser();
    $scope.numberOfActiveBids = 0;
    $http.get('/api/bid/getActiveBidsFromProvider/' + $scope.user.id).success(function (data) {
        $scope.numberOfActiveBids = data.length;
    });





    $scope.home = function() {
        $state.transitionTo('provider.home');
    };

    $scope.previewLists = function() {
        $state.transitionTo('provider.previewLists');
    };

    $scope.newBid = function() {
        $state.transitionTo('provider.newBid');
    };

    $scope.myBids = function() {
        $state.transitionTo('provider.myBids');
    };

    $scope.logout = function () {
        authorizationService.removeUser();
        $state.transitionTo('login');
    };

    $scope.unitNotSet = function () {
        return isEmpty($scope.bidItem.unit);
    }



    //PINGOVANJE

    var startedInterval;
    $scope.startPing = function(num) {
        if (angular.isDefined(startedInterval)) {
            return;
        }

        startedInterval = $interval(function() {
            $http.get('/api/bid/getActiveBidsFromProvider/' + $scope.user.id).success(function (data) {
                console.log("aktivne ponude: "+JSON.stringify(data));
                if(data.length != $scope.numberOfActiveBids){
                    alert("Imate obavestenje vezano za ponudu!!!");
                }else{
                    //console.log("Nema obavestenja");
                }
            });
        }, 1000);
    };

    $scope.stopPing = function() {
        if (angular.isDefined(startedInterval)){
            $interval.cancel(startedInterval);
            startedInterval = undefined;
        }
    };

    $scope.$on('$destroy', function() {
        $scope.stopPing();
    });

    var num = Math.random();
    $scope.startPing(num);


});