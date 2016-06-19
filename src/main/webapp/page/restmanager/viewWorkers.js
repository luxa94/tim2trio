/**
 * Created by Sandra on 24.5.2016.
 */
iceipiceApp.controller('restManagerViewWorkersController', function ($scope, $http, $state, $stateParams, authorizationService,GuestService) {

    $scope.restaurant = {};
    $scope.workers = [];
    $scope.current.page = 7;
    var cooks = [];
    var bartenders = [];
    var waiters = [];

    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
        console.log("****Restaurant id = " + $scope.restaurant.id);
        $http.get('/api/cook/allFromR/' + $scope.restaurant.id).success(function(data) {
            console.log("KUVARI: " + JSON.stringify(data));
            cooks = data;

            for (i = 0; i < cooks.length; i++){
                cooks[i].cook = true;
                cooks[i].bartender = false;
                cooks[i].waiter = false;
            }

            $http.get('/api/bartender/allFromR/' + $scope.restaurant.id).success(function(data) {
                console.log("SANKERI: " + JSON.stringify(data));
                bartenders = data;
                for (i = 0; i < bartenders.length; i++){
                    bartenders[i].cook = false;
                    bartenders[i].bartender = true;
                    bartenders[i].waiter = false;
                }

                $http.get('/api/waiter/allFromR/' + $scope.restaurant.id).success(function(data) {
                    console.log("KONOBARI: " + JSON.stringify(data));
                    waiters = data;
                    for (i = 0; i < waiters.length; i++) {
                        waiters[i].cook = false;
                        waiters[i].bartender = false;
                        waiters[i].waiter = true;
                    }
                    $scope.workers = cooks.concat(bartenders).concat(waiters);
                    console.log("RADNICI: " + JSON.stringify($scope.workers));
                })
            })
        })
    });

    $scope.reverse = function(){
        if($scope.orderList == "name"){
            $scope.orderList = "-name";
        } else {
            $scope.orderList = "name";
        }
    };

    $scope.setShift = function(){
        //$scope.current.page = 2;
        $state.transitionTo( "restaurant_manager.defineTimetable");
        //$scope.popup = new Foundation.Reveal($('#addShift'));
        //$scope.popup.open();
    }

    $(document).ready(function() {

        $('#calendar').fullCalendar({
            defaultDate: '2016-05-12',
            editable: true,
            eventLimit: true, // allow "more" link when too many events
            events: [
                {
                    title: 'radnik1',
                    start: '2016-05-01'
                },
                {
                    title: 'radnik2',
                    start: '2016-05-07',
                    end: '2016-05-10'
                },
                {
                    id: 999,
                    title: 'Repeating Event',
                    start: '2016-05-09T16:00:00'
                }
            ]
        });

    });

});