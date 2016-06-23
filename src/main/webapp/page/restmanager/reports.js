/**
 * Created by Sandra on 22.6.2016.
 */
iceipiceApp.controller('restManagerReportsController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 8;
    $scope.grades = [];
    $scope.monthLabels = ["Januar", "Februar","Mart","April","Maj","Jun","Jul","Avgust","Septembar","Oktobar","Novembar","Decembar"];

    $scope.reportKinds1 = ["nedeljni","mesečni"];
    $scope.reportKinds2 = ["restorana","jela","usluge","posećenosti"];
    $scope.kind1 = $scope.reportKinds1[0];
    $scope.kind2 = $scope.reportKinds2[0];
    $scope.years = [];
    $scope.year = 2016;
    $scope.chartSourceLabels = [];

    var myDate = new Date();
    var year = myDate.getFullYear();
    for(var i = year; i > 1950;){
        $scope.years.push(i);
        i = i-1;
    }

    $http.get('/api/grade/all').success(function (data) {
        console.log("SVE OCENE: " + JSON.stringify(data));
        $scope.grades = data;
    });
    
    $scope.select1 = function (kind) {
        if(kind == $scope.reportKinds1[1]){
            console.log("MESECNI");
            $scope.chartSourceLabels = $scope.monthLabels;
        }
        else if(kind == $scope.reportKinds1[0]){
            console.log("NEDELJNI");
            $scope.chartSourceLabels = [];
            var weeksNo = getISOWeeks($scope.year);
            var i;
            for(i=0; i<weeksNo; i++){
                $scope.chartSourceLabels.push(i);
            }
        }
    }
    var myChart;
    $scope.setData = function (kind) {
        $scope.select1($scope.kind1);
        var dataset = [];
        var weeksNo = getISOWeeks($scope.year);
        //console.log("weekNo = " + weeksNo);
        var i;
        if(kind == $scope.reportKinds2[0]){
            //ocena restorana
            if ($scope.kind1 == $scope.reportKinds1[0]){
                //nedeljno
                var dataset = [];
                for (i=0; i<weeksNo; i++){
                    dataset.push(getAverageGradeWeek($scope.year,i,"restaurant",$scope.grades));
                };
            }
            else if($scope.kind1 == $scope.reportKinds1[1]){
                //mesecno
                for (i=0; i<12; i++){
                    dataset.push(getAverageGradeMonth($scope.year,i,"restaurant",$scope.grades));
                };
            }
        }
        else if(kind == $scope.reportKinds2[1]){
            //ocena jela
            if ($scope.kind1 == $scope.reportKinds1[0]){
                //nedeljno
                for (i=0; i<weeksNo; i++){
                    dataset.push(getAverageGradeWeek($scope.year,i,"meal",$scope.grades));
                };
            }
            else if($scope.kind1 == $scope.reportKinds1[1]){
                //mesecno
                for (i=0; i<12; i++){
                    dataset.push(getAverageGradeMonth($scope.year,i,"meal",$scope.grades));
                };
            }
        }
        else if(kind == $scope.reportKinds2[2]){
            //ocena usluge
            if ($scope.kind1 == $scope.reportKinds1[0]){
                //nedeljno
                for (i=0; i<weeksNo; i++){
                    dataset.push(getAverageGradeWeek($scope.year,i,"waiter",$scope.grades));
                };
            }
            else if($scope.kind1 == $scope.reportKinds1[1]){
                //mesecno
                for (i=0; i<12; i++){
                    dataset.push(getAverageGradeMonth($scope.year,i,"waiter",$scope.grades));
                };
            }
        }
        else if(kind == $scope.reportKinds2[3]){
            //posecenost
        }

        console.log("DATASET = " + JSON.stringify(dataset));

        var ctx = document.getElementById("myChart");
        if($scope.kind1 == $scope.reportKinds1[1]){
            //za mesecni izvestaj: CHART
        }
        console.log("labels: " + JSON.stringify($scope.chartSourceLabels));
        myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: $scope.chartSourceLabels,
                datasets: [{
                    label: 'Prosecna ocena',
                    data: dataset,
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });


    }


    getNumberOfVisistsPerMonth = function (year,month,grades) {

    }

    getNumberOfVisistsPerWeek = function (year,month,grades) {

    }

    getAverageGradeMonth = function (year,month,kind2,grades) {
        var i;
        var d;
        var br = 0;
        var average = 0;
        for(i=0; i<grades.length; i++){
            d = new Date(grades[i].reservation.date);
            if(d.getFullYear()==year && d.getMonth()==month){
                br++;
                if (kind2=="restaurant"){
                    average = average + grades[i].atmosphere_grade;
                }
                else if (kind2=="meal"){
                    average = average + grades[i].meal_grade;
                }
                else if (kind2=="waiter"){
                    average = average + grades[i].waiter_grade;
                }
            }
        }
        if(average == 0)
            return 0;
        average = average/br;
        return average;
    }

    getAverageGradeWeek = function (year,week,kind2,grades) {
        var i;
        var d;
        var br = 0;
        var average = 0;
        for(i=0; i<grades.length; i++){
            d = new Date(grades[i].reservation.date);
            if(d.getFullYear()==year && getWeekNumber(d)[1]==week){
                br++;
                if (kind2=="restaurant"){
                    average = average + grades[i].atmosphere_grade;
                }
                else if (kind2=="meal"){
                    average = average + grades[i].meal_grade;
                }
                else if (kind2=="waiter"){
                    average = average + grades[i].waiter_grade;
                }
            }
        }
        if(average == 0)
            return 0;
        average = average/br;
        return average;
    }

    function getWeekNumber(d) {
        // Copy date so don't modify original
        d = new Date(+d);
        d.setHours(0,0,0);
        // Set to nearest Thursday: current date + 4 - current day number
        // Make Sunday's day number 7
        d.setDate(d.getDate() + 4 - (d.getDay()||7));
        // Get first day of year
        var yearStart = new Date(d.getFullYear(),0,1);
        // Calculate full weeks to nearest Thursday
        var weekNo = Math.ceil(( ( (d - yearStart) / 86400000) + 1)/7);
        // Return array of year and week number
        return [d.getFullYear(), weekNo];
    }


    //koliko nedelja ima godina y
    function getISOWeeks(y) {
        var d,
            isLeap;

        d = new Date(y, 0, 1);
        isLeap = new Date(y, 1, 29).getMonth() === 1;

        //check for a Jan 1 that's a Thursday or a leap year that has a
        //Wednesday jan 1. Otherwise it's 52
        return d.getDay() === 4 || isLeap && d.getDay() === 3 ? 53 : 52
    }









});