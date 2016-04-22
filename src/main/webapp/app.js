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
        .state('system_manager', {
            url: '/system_manager',
            abstract: true,
            templateUrl: 'page/sysmanager/base.html',
            controller: 'sysmanagerBaseController'
        })
        .state('system_manager.home',{
            url: '/home',
            templateUrl: 'page/sysmanager/home.html',
            controller: 'sysmanagerHomeController'
        })
        .state('system_manager.addRestaurant',{
            url: '/addRestaurant',
            templateUrl: 'page/sysmanager/addRestaurant.html',
            controller: 'sysmanagerAddRestaurantController'
        })
        .state('system_manager.addRestaurantManager',{
            url: '/addRestaurantManager',
            templateUrl: 'page/sysmanager/addRestaurantManager.html',
            controller: 'sysmanagerAddRestaurantManagerController'
        })
        .state('system_manager.addSystemManager',{
            url: '/addSystemManager',
            templateUrl: 'page/sysmanager/addSystemManager.html',
            controller: 'sysmanagerAddSystemManagerController'
        })
        .state('system_manager.addProvider', {
            url: '/addProvider',
            templateUrl: 'page/sysmanager/addProvider.html',
            controller: 'sysmanagerAddProviderController'
        })
        .state('restaurant_manager',{
            url: '/restaurant_manager',
            abstract:true,
            templateUrl: 'page/restmanager/base.html',
            controller: 'restmanagerBaseController'
        })
        .state('restaurant_manager.home', {
            url: '/home',
            templateUrl: 'page/restmanager/home.html',
            controller: 'restmanagerHomePageController'
        })
        .state('restaurant_manager.registerWorker', {
            url: '/registerWorker',
            templateUrl: 'page/restmanager/registerWorker.html',
            controller: 'restmanagerRegisterWorkerController'
        })
        .state('restaurant_manager.defineAreas', {
            url: '/defineAreas',
            controller: 'restmanagerDefineAreasController',
            templateUrl: 'page/restmanager/defineAreas.html'
        })
        .state('restaurant_manager.defineTimetable', {
            url: '/defineTimetable',
            controller: 'restmanagerDefineTimetableController',
            templateUrl: 'page/restmanager/defineTimetable.html'
        })
        .state('restaurant_manager.makeBid', {
            url: '/makeBid',
            controller: 'restmanagerMakeBidController',
            templateUrl: 'page/restmanager/makeBid.html'
        })
        .state('guest', {
            url: '/guest',
            abstract: true,
            // ovde promeniti kada napravimo celu formu :)
            templateUrl: 'page/guest/base.html',
            controller: 'guestHomeController'
        })
        .state('guest.home',{
            url: '/home',
            templateUrl: 'page/guest/homeGuest.html',
            controller: 'guestHomeController'
        })
        .state('guest.addFriend',{
            url: '/addFriend',
            templateUrl: 'page/guest/addFriend.html',
            controller: 'guestAddFriendController'
        })
        .state('guest.showRestaurants',{
            url: '/showRestaurants',
            templateUrl: 'page/guest/showRestaurants.html',
            controller: 'guestShowRestaurantsController'
        })

        .state('guest.addReservation',{
            url: '/addReservation',
            templateUrl: 'page/guest/addReservation.html',
            controller: 'guestAddReservationController'
        })

        .state('bartender', {
            url: '/bartender',
            abstract: true,
            templateUrl: 'page/bartender/base.html',
            controller: 'bartenderHomeController'
        })

        .state('bartender.home',{
            url: '/home',
            templateUrl: 'page/bartender/homeBartender.html',
            controller: 'bartenderHomeController'
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