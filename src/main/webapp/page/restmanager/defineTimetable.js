/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerDefineTimetableController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 2;
    $scope.restaurant = {};
    $scope.areas = [];
    $scope.workers = [];
    $scope.shift = {};
    $scope.selectedWorker = {};
    var cooks = [];
    var bartenders = [];
    var waiters = [];


    $http.get('/api/areas/forManager/' + $scope.user.id).success(function (data) {
        $scope.areas = data;
    });

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

    $(document).ready(function() {

        $('#calendar').fullCalendar({
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            editable: true,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            drop: function(date) { // this function is called when something is dropped

                // retrieve the dropped element's stored Event Object
                var originalEventObject = $(this).data('eventObject');

                // we need to copy it, so that multiple events don't have a reference to the same object
                var copiedEventObject = $.extend({}, originalEventObject);

                // assign it the date that was reported
                copiedEventObject.start = date;

                // render the event on the calendar
                // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }

            }
        });
    });

    $scope.reverse = function(){
        if($scope.orderList == "name"){
            $scope.orderList = "-name";
        } else {
            $scope.orderList = "name";
        }
    };

    $scope.setShift = function(){
        
    }


    $scope.openDialogForNewShift = function (t) {

        $scope.popup = new Foundation.Reveal($('#newShift'));
        $scope.popup.open();
    };


    $scope.addShiftDialog = function (worker) {
        $scope.selectedWorker = worker;
        $scope.shift.workerId = $scope.selectedWorker.id;
        $scope.shift.workerType = $scope.selectedWorker.type;
        //console.log("SELEKTOVAN RADNIK: " + JSON.stringify($scope.selectedWorker));
        $scope.openDialogForNewShift({});
    };

    $scope.cancel = function () {
        $scope.popup.close();
    };

    $scope.addShift = function () {
        
    }

});