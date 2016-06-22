iceipiceApp.controller('waiterNewReservationController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 3;
    $scope.tables = [];
    $scope.selectedTables = [];
    $scope.user = authorizationService.getUser();
    $scope.menuItems = [];
    $scope.articleTypes = [];

    $scope.canvas = new fabric.CanvasEx('restaurant_canvas');
    var canvas = $scope.canvas;
    canvas.hoverCursor = 'pointer';
    canvas.backgroundColor = '#222';

    $scope.redrawTables = function () {
        canvas.clear();

        for (var i in $scope.tables) {
            var table = $scope.tables[i];
            var t = JSON.parse(table.fabricTable);
            var fabricTable;
            if (t.type == 'rect') {
                fabricTable = new fabric.Rect(t);
                canvas.add(fabricTable);
                table.fabricTable = fabricTable;
                fabricTable.selectable = false;
            } else if (t.type == 'circle') {
                fabricTable = new fabric.Circle(t);
                canvas.add(fabricTable);
                table.fabricTable = fabricTable;
                fabricTable.selectable = false;
            }
        }
        canvas.renderAll();
    };

    $http.get('/api//tables/waiter/' + $scope.user.id).success(function (data) {
        $scope.tables = data;
        $scope.redrawTables();
    });

    canvas.on('mouse:dblclick', function (options) {
        if (options.target != undefined) {
            options.target.opacity = 1.5 - options.target.opacity;
            canvas.renderAll();
            for (var i in $scope.tables) {
                var t = $scope.tables[i];
                if (t.fabricTable == options.target) {
                    $scope.table = t;
                    var index = $scope.selectedTables.indexOf(t.id);
                    if (index > -1) {
                        $scope.selectedTables.splice(index, 1);
                    } else {
                        $scope.selectedTables.push(t.id);
                    }
                    console.log($scope.selectedTables);
                    break;
                }
            }
        }
    });

    $http.get('/api/articleTypes/all').success(function (data) {
        $scope.articleTypes = data;
    });

    $http.get('/api/menuItems/allFromR/' + $scope.user.restaurant.id).success(function (data) {
        for (var i in data) {
            var d = data[i];
            d.count = 0;
        }
        $scope.menuItems = data;
        // debugger;
    });


    $scope.cancelReservation = function () {
        for (var i in $scope.menuItems) {
            var item = $scope.menuItems[i];
            item.count = 0;
        }
    }

});
