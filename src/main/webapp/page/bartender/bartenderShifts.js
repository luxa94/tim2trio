iceipiceApp.controller('bartenderShiftsController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 1;

    $scope.shiftsInCalendar = [];
    $scope.user = authorizationService.getUser();

    $http.get('/api/bartenderShifts/forOne/' + $scope.user.id).success(function (data) {
        for (var i in data) {
            var d = data[i];
            var start = new Date(d.shift.day);

            var shift = {
                title: d.shift.startHour + "-" + d.shift.endHour,
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