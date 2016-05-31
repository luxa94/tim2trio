/**
 * Created by Sandra on 10.5.2016.
 */
iceipiceApp.controller('restManagerViewMenuController', function ($scope, $http, $state, $stateParams, authorizationService) {
    $scope.menuItems = [];
    $scope.current.page = 6;
    $scope.menuItem = {};
    $scope.articleTypes = [];
    
    console.log("$scope.user.restaurant.id = " + $scope.user.restaurant.id);
    $http.get('/api/menuItems/allFromR/' + $scope.user.restaurant.id).success(function(data) {
        console.log("$scope.user.restaurant.id = " + $scope.user.restaurant.id);
        $scope.menuItems = data;
    });

    $http.get('/api/articleTypes/all').success(function (data) {
        $scope.articleTypes = data;
    })
    
    $scope.openDialogForNewMenuItem = function (t) {

        $scope.popup = new Foundation.Reveal($('#newMenuItem'));
        $scope.popup.open();
    };

    $scope.addMenuItemDialog = function () {
        $scope.openDialogForNewMenuItem({});
        
    };

    $scope.addMenuItem = function () {
        $http.post('/api/menuItem/create', $scope.menuItem).success(function (data) {
            console.log("Uspesno dodata stavka menija\n" + JSON.stringify(data));
        }).error(function () {
            alert('Greška pri dodavanju stavke menija');
        });
    }
});