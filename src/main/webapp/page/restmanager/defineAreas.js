/**
 * Created by Sandra on 17.4.2016.
 */
iceipiceApp.controller('restmanagerDefineAreasController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.current.page = 3;

    var canvas = new fabric.CanvasEx('restaurant_canvas');
    $scope.canvas = canvas;
    $scope.areas = [];
    $scope.tables = [];
    $scope.areaName = '';
    $scope.user = authorizationService.getUser();
    $scope.table = {};

    canvas.backgroundColor = '#222';
    canvas.renderAll();

    $http.get('/api/areas/forManager/' + $scope.user.id).success(function (data) {
        $scope.areas = data;
    });

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
            } else if (t.type == 'circle') {
                fabricTable = new fabric.Circle(t);
                canvas.add(fabricTable);
                table.fabricTable = fabricTable;
            }
        }
    };

    $scope.refreshTables = function () {
        $http.get('/api/tables/restaurantManager/' + $scope.user.id).success(function (data) {
            $scope.tables = data;
            $scope.redrawTables();
        });
    };

    $scope.openDialogForTable = function (t) {
        $scope.table = t;
        $scope.popup = new Foundation.Reveal($('#newTable'));
        $scope.popup.open();
    };

    $scope.refreshTables();

    $scope.addRoundTable = function () {
        $scope.openDialogForTable({});
        var circle = new fabric.Circle({
            radius: 25, fill: 'red', left: 100, top: 100
        });
        $scope.table.fabricTable = circle;
    };

    $scope.addRectTable = function () {
        $scope.openDialogForTable({});
        var rect = new fabric.Rect({
            left: 100, top: 100, fill: 'red', width: 50, height: 50
        });
        $scope.table.fabricTable = rect;
    };

    $scope.deleteTable = function () {
        // if (canvas.getActiveGroup()) {
        //     canvas.getActiveGroup().forEachObject(function (o) {
        //         canvas.remove(o)
        //     });
        //     canvas.discardActiveGroup().renderAll();
        // } else {
        //     canvas.remove(canvas.getActiveObject());
        // }
    };

    $scope.addTable = function () {
        $scope.tables.push($scope.table);
        $scope.canvas.add($scope.table.fabricTable);
        $scope.popup.close();
    };

    $scope.cancelTable = function () {
        $scope.popup.close();
    };

    $scope.addArea = function () {
        if ($scope.areaName != '') {
            var areaDTO = {
                name: $scope.areaName,
                managerId: $scope.user.id
            };
            $http.post('/api/areas/new', areaDTO).success(function (data) {
                $scope.areas.push(data);
            });
            $scope.areaName = '';
        }
    };

    $scope.saveTables = function () {
        for (var i in $scope.tables) {
            var t = $scope.tables[i];
            t.fabricTable.fill = 'white';
            t.fabricTable = JSON.stringify(t.fabricTable);
            if (t.area.id) {
                t.area = t.area.id;
            }
        }
        $http.post('/api/tables/update/all/' + $scope.user.id, $scope.tables).success(function (data) {
            $scope.tables = data;
            $scope.redrawTables();
        })
    };

    canvas.on('mouse:dblclick', function (options) {
        for (var i in $scope.tables) {
            var t = $scope.tables[i];
            if (t.fabricTable == options.target) {
                $scope.$apply(function () {
                    $scope.table = t;
                });
                // $scope.openDialogForTable(t);
                console.log($scope.table);
                break;
            }
        }
    });

    canvas.on('before:selection:cleared', function() {
        $scope.$apply(function () {
            $scope.table = {};
        });
    });

    $(document).on('closed.zf.reveal', function (e) {
        if (e.target.id === 'newTable') {
            $scope.table = {};
        }
    });

});

