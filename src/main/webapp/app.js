'use-strict';

var iceipiceApp = angular.module('iceipiceApp', ['ui.router',  'angular-input-stars']);




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
        .state('restaurant_manager.viewRestaurant', {
            url: '/viewRestaurant',
            controller: 'restManagerViewRestaurantController',
            templateUrl: 'page/restmanager/viewRestaurant.html'
        })
        .state('restaurant_manager.viewMenu', {
            url: '/viewMenu',
            controller: 'restManagerViewMenuController',
            templateUrl: 'page/restmanager/viewMenu.html'
        })
        .state('restaurant_manager.viewWorkers', {
            url: '/viewWorkers',
            controller: 'restManagerViewWorkersController',
            templateUrl: 'page/restmanager/viewWorkers.html'
        })
        .state('restaurant_manager.reports', {
            url: '/reports',
            controller: 'restManagerReportsController',
            templateUrl: 'page/restmanager/reports.html'
        })
        .state('guest', {
            url: '/guest',
            abstract: true,
            // ovde promeniti kada napravimo celu formu :)
            templateUrl: 'page/guest/base.html',
            controller: 'guestBaseController'
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
        .state('guest.modifyReservation',{
            url: '/modifyReservation',
            templateUrl: 'page/guest/modifyReservation.html',
            controller: 'guestModifyReservationController'
        })
        .state('guest.inviteFriend',{
            url: '/inviteFriend',
            templateUrl: 'page/guest/inviteFriend.html',
            controller: 'guestInviteFriendController'
        })
        .state('guest.selectMenuItem',{
            url: '/selectMenuItem',
            templateUrl: 'page/guest/selectMenuItem.html',
            controller: 'guestSelectMenuItemController'
        })
        .state('guest.gradeReservation',{
            url: '/gradeReservation',
            templateUrl: 'page/guest/gradeReservation.html',
            controller: 'guestGradeReservationController'
        })
        .state('verify', {
            url: '/authenticate/{id:int}',
            templateUrl: 'page/authentication.html',
            controller: 'verificationController'
        })
        .state('bartender', {
            url: '/bartender',
            abstract: true,
            // ovde promeniti kada napravimo celu formu :)
            templateUrl: 'page/bartender/base.html',
            controller: 'bartenderBaseController'
        })
        .state('bartender.home',{
            url: '/home',
            templateUrl: 'page/bartender/homeBartender.html',
            controller: 'bartenderHomeController'
        })
        .state('bartender.shifts', {
            url: '/shifts',
            templateUrl: '/page/bartender/bartenderShifts.html',
            controller: 'bartenderShiftsController'
        })
        .state('bartender.orders', {
            url: '/orders',
            templateUrl: 'page/bartender/bartenderOrders.html',
            controller: 'bartenderOrdersController'
        })
        .state('cook', {
            url: '/cook',
            abstract: true,
            // ovde promeniti kada napravimo celu formu :)
            templateUrl: 'page/cook/base.html',
            controller: 'cookBaseController'
        })
        .state('cook.home',{
            url: '/home',
            templateUrl: 'page/cook/homeCook.html',
            controller: 'cookHomeController'
        })
        .state('cook.orders', {
            url: '/orders',
            templateUrl: 'page/cook/cookOrders.html',
            controller: 'cookOrdersController'
        })
        .state('cook.shifts', {
            url: '/shifts',
            templateUrl: 'page/cook/cookShifts.html',
            controller: 'cookShiftsController'
        })
        .state('waiter', {
            url: '/waiter',
            abstract: true,
            // ovde promeniti kada napravimo celu formu :)
            templateUrl: 'page/waiter/base.html',
            controller: 'waiterBaseController'
        })
        .state('waiter.home',{
            url: '/home',
            templateUrl: 'page/waiter/homeWaiter.html',
            controller: 'waiterHomeController'
        })
        .state('provider',{
            url: '/provider',
            abstract : true,
            templateUrl: 'page/provider/base.html',
            controller: 'providerBaseController'
        })
        .state('provider.home',{
            url: '/home',
            templateUrl: 'page/provider/home.html',
            controller: 'providerHomeController'
        })
        .state('provider.previewLists',{
            url: '/previewLists',
            templateUrl: 'page/provider/previewLists.html',
            controller: 'providerPreviewListsController'
        })
        .state('provider.newBid',{
            url: '/newBid',
            templateUrl: 'page/provider/NewBid.html',
            controller: 'providerNewBidController'
        })
        .state('provider.myBids', {
            url: '/myBids',
            templateUrl: 'page/provider/myBids.html',
            controller: 'providerMyBidsController'
        })
        .state('waiter.shifts', {
            url: '/shifts',
            templateUrl: '/page/waiter/waiterShifts.html',
            controller: 'waiterShiftsController'
        })
        .state('waiter.reservations', {
            url: '/reservations',
            templateUrl: '/page/waiter/waiterReservations.html',
            controller: 'waiterReservationsController'
        })
        .state('waiter.newReservation', {
            url: 'newReservation',
            templateUrl: '/page/waiter/newReservation.html',
            controller: 'waiterNewReservationController'
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

iceipiceApp.controller('index_controller', function ($scope, $log,authorizationService) {

    /* Check if the user is logged prior to use the next code */
    var user = authorizationService.getUser();
    if (isEmpty(user)) {
        $log.log("user not logged, redirecting to Login view");
        // Redirect to Login view
        console.log("nijeee ulogovan");
        $scope.$state.go("login");
    }
});
/*
function isEmpty(obj) {
    for (var x in obj) { return false; }
    return true;
}

    var publicRoutes = ['/login', '/register'];

    var sysMenRoutes = ['/system_manager'];

    var isPublicRoute = function (route) {
        for(var i in publicRoutes) {
            if (publicRoutes[i] === route) {
                return true;
            }
        }
        return false;
    };

    var isSysMenRoute = function (route) {
        for(var i in sysMenRoutes) {
            if (sysMenRoutes[i] === route) {
                return true;
            }
        }
        return false;
    };

    $rootScope.$on('$stateChangeStart', function (ev, to, toParams, from, fromParams) {
        if (isPublicRoute($location.path())) {
            return;
        }
        if (!isPublicRoute($location.path()) && !authorizationService.getUser()) {
            $location.path('/login');
        }
        if (!isSysMenRoute($location.path()) && !authorizationService.getUser().get) {
            $location.path('/login');
        }

    });

*/