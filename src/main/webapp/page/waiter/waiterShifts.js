iceipiceApp.controller('waiterShiftsController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 1;

    $scope.shiftsInCalendar = [];
    $scope.user = authorizationService.getUser();

    $http.get('/api/waiterShifts/forOne/' + $scope.user.id).success(function (data) {
        for (var i in data) {
            var d = data[i];
            var start = new Date(d.shift.day);
            var areas = "";
            
            for (var j in d.areas) {
                var a = d.areas[j];
                areas += a.name + ', '
            }

            var shift = {
                title: d.shift.startHour + "-" + d.shift.endHour + " " + areas,
                start: start,
                end: start
            };

            $scope.shiftsInCalendar.push(shift);
        }

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