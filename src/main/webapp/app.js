'use-strict';

var iceipiceApp = angular.module('iceipiceApp', ['ui.router']);

iceipiceApp.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider
        .state('login', {
            url: '/login',
            controller: 'loginController',
            templateUrl: 'page/login.html',
            data: {
                pageTitle: 'Prijava'
            }
        })
        .state('register', {
            url: '/register',
            controller: 'registerController',
            templateUrl: 'page/register.html',
            data: {
                pageTitle: 'Registracija'
            }
        })
        .state('restaurant_manager.home', {

        })
        .state('restaurant_manager.registerWorker', {
            url: '/registerWorker',
            controller: 'registerWorkerController',
            templateUrl: '',
            data: {
                pageTitle: 'Registracija radnika'
            }

        })
        .state('logout', {
            url: '/logout',
            controller: function ($location, authorizationService) {
                authorizationService.removeUser();
                $location.path('/login');
            }
        });


    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }
    $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
    $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
}).run(function ($rootScope, $location, authorizationService) {
    // var publicRoutes = ['/signIn', '/register'];
    //
    // var isPublicRoute = function (route) {
    //     for(var i in publicRoutes) {
    //         if (publicRoutes[i] === route) {
    //             return true;
    //         }
    //     }
    //     return false;
    // };
    //
    // $rootScope.$on('$stateChangeStart', function (ev, to, toParams, from, fromParams) {
    //     if (isPublicRoute($location.path())) {
    //         return;
    //     }
    //     if (!isPublicRoute($location.path()) && !authorizationService.getUser()) {
    //         $location.path('/signIn');
    //     }
    // });
});