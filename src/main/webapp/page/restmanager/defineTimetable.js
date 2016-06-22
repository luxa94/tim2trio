/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerDefineTimetableController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 2;
    $scope.restaurant = {};
    $scope.areas = [];
    $scope.workers = [];
    $scope.shift = {};
    $scope.shiftsInCalendar = [];
    //$scope.shift.areaId = -1;
    $scope.selectedWorker = {};
    $scope.selectedAreas =  [];
    var cooks = [];
    var bartenders = [];
    var waiters = [];


    $http.get('/api/areas/forManager/' + $scope.user.id).success(function (data) {
        //console.log("areas: " + JSON.stringify(data));
        $scope.areas = data;
    });

    $http.get('/api/restaurant/oneM/' + $scope.user.id).success(function(data) {
        console.log("RESTORAN: " + JSON.stringify(data));
        $scope.restaurant = data;
        $scope.shift.restaurantId = $scope.restaurant.id;
        $http.get('/api/cook/allFromR/' + $scope.restaurant.id).success(function(data) {
            //console.log("KUVARI: " + JSON.stringify(data));
            cooks = data;

            for (i = 0; i < cooks.length; i++){
                cooks[i].cook = true;
                cooks[i].bartender = false;
                cooks[i].waiter = false;
            }

            $http.get('/api/bartender/allFromR/' + $scope.restaurant.id).success(function(data) {
                //console.log("SANKERI: " + JSON.stringify(data));
                bartenders = data;
                for (i = 0; i < bartenders.length; i++){
                    bartenders[i].cook = false;
                    bartenders[i].bartender = true;
                    bartenders[i].waiter = false;
                }

                $http.get('/api/waiter/allFromR/' + $scope.restaurant.id).success(function(data) {
                    //console.log("KONOBARI: " + JSON.stringify(data));
                    waiters = data;
                    for (i = 0; i < waiters.length; i++) {
                        waiters[i].cook = false;
                        waiters[i].bartender = false;
                        waiters[i].waiter = true;
                    }
                    $scope.workers = cooks.concat(bartenders).concat(waiters);
                    //console.log("RADNICI: " + JSON.stringify($scope.workers));
                })
            })
        })



        //UCITAVANJE SMENA ZA ISPIS U KALENDARU
        $http.get('/api/bartenderShift/getAllShiftsFromRestaurant/' + $scope.restaurant.id).success(function(data) {
            var index;
            var oneShiftInCalendar;
            console.log("SVE SMENE sankera" + JSON.stringify(data));
            for (index = 0; index < data.length; ++index) {
                oneShiftInCalendar = {
                    //id: data[index].id,
                    eventColor: "yellow",
                    title: data[index].bartender.name + " " + data[index].bartender.surname,
                    start: new Date(data[index].shift.day).toISOString().substring(0, 10) +  "T" + data[index].shift.startHour,
                    end: new Date(data[index].shift.day).toISOString().substring(0, 10) +  "T" + data[index].shift.endHour,
                    //allday: true
                };
                $scope.shiftsInCalendar.push(oneShiftInCalendar);
            }
            //console.log("SMENE ZA KALENDAR" + JSON.stringify($scope.shiftsInCalendar));


            $http.get('/api/waiterShift/getAllShiftsFromRestaurant/' + $scope.restaurant.id).success(function(data) {
                var index;
                var oneShiftInCalendar;
                console.log("**SVE SMENE konobara" + JSON.stringify(data));
                for (index = 0; index < data.length; ++index) {
                    oneShiftInCalendar = {
                        //id: data[index].id,
                        eventColor: "red",
                        title: data[index].waiter.name + " " + data[index].waiter.surname,
                        start: new Date(data[index].shift.day).toISOString().substring(0, 10) +  "T" + data[index].shift.startHour,
                        end: new Date(data[index].shift.day).toISOString().substring(0, 10) +  "T" + data[index].shift.endHour,
                        //allday: true
                    };
                    $scope.shiftsInCalendar.push(oneShiftInCalendar);
                }

                $http.get('/api/cookShift/getAllShiftsFromRestaurant/' + $scope.restaurant.id).success(function(data) {
                    var index;
                    var oneShiftInCalendar;

                    for (index = 0; index < data.length; ++index) {
                        oneShiftInCalendar = {
                            //id: data[index].id,
                            eventColor: "blue",
                            title: data[index].cook.name + " " + data[index].cook.surname,
                            start: new Date(data[index].shift.day).toISOString().substring(0, 10) +  "T" + data[index].shift.startHour,
                            end: new Date(data[index].shift.day).toISOString().substring(0, 10) +  "T" + data[index].shift.endHour,
                            //allday: true
                        };
                        $scope.shiftsInCalendar.push(oneShiftInCalendar)
                    }


                    console.log("%%%%SVE SMENE za kalendar" + JSON.stringify($scope.shiftsInCalendar));

                    $('#calendar').fullCalendar({
                        header: {
                            left: 'prev,next today',
                            center: 'title',
                            right: 'month,agendaWeek,agendaDay'
                        },
                        editable: false,
                        events: $scope.shiftsInCalendar

                    });



                });

            });


        });



    });




    $scope.reverse = function(){
        if($scope.orderList == "name"){
            $scope.orderList = "-name";
        } else {
            $scope.orderList = "name";
        }
    };

    $scope.openDialogForNewShift = function () {
        var i;
        $scope.selectedAreas = [];
        for(i=0; i< $scope.areas.length; i++){
            $scope.selectedAreas.push(false);
        }
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
        var oneShiftInCalendar = {};
        console.log("NOVA SMENA: " + JSON.stringify($scope.shift));
        if ($scope.selectedWorker.waiter) {
            console.log("Dodaj smenu konobaru");
            console.log("reoni: " + JSON.stringify($scope.areas));
            var i;
            $scope.shift.areas = [];
            for(i=0; i<$scope.areas.length; i++){
                if($scope.selectedAreas[i] == true){
                    $scope.shift.areas.push($scope.areas[i].id);
                }
            }
            console.log("Dodati reoni: " + JSON.stringify( $scope.shift.areas));
            $http.post('/api/waiterShift/newShift',$scope.shift).success(function(data) { });
        }
        else if ($scope.selectedWorker.bartender)
        {
            $http.post('/api/bartenderShift/newShift',$scope.shift).success(function(data) { });

        }
        else if ($scope.selectedWorker.cook)
        {
            console.log("Dodaj smenu kuvaru");
            $http.post('/api/cookShift/newShift',$scope.shift).success(function(data) { });

        }
        else{
            console.log("NEKI CETVRTI TIP RADNIKA!!!");
            return;
        }

        oneShiftInCalendar = {
            title: $scope.selectedWorker.name + " " + $scope.selectedWorker.surname,
            start: $scope.shift.startDate,
            end: $scope.shift.endDate,
            allday: true
        };


        $scope.shiftsInCalendar.push(oneShiftInCalendar);
        $scope.popup.close();
    }

});