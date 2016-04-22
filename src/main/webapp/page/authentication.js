iceipiceApp.controller('verificationController', function ($state, $stateParams, $http, authorizationService) {
    $http.get('/api/verify/' + $stateParams.id).success(function (data) {
        authorizationService.setUser(data);
        $state.transitionTo('guest.home');
    }).error(function () {
        alert('Greška pri verifikaciji');
    });
});