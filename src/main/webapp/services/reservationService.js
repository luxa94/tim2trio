/**
 * Created by Nina on 27-May-16.
 */
(function () {
    'use strict';

    angular
        .module('iceipiceApp')
        .factory('ReservationService', ReservationService);

    ReservationService.$inject = ['$http'];
    function ReservationService($http) {
        var service = {};
        var reservationUnderReview = null;

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.getUnderReview = getUnderReview;
        service.setUnderReview = setUnderReview;
        service.CreateReview = CreateReview;
        service.update = false;

        service.asd = { reservation : {} };
        service.order = {};


        return service;

        function CreateReview(review) {
            return $http.post('/api/grade/create', review).then(handleSuccess, handleError('Error creating grade'));
        }

        function getUnderReview() {
            return reservationUnderReview;
        }

        function setUnderReview(reservation) {
            reservationUnderReview = reservation;
        }

        function GetAll(id) {
            return $http.get('/api/reservation/all/' + id).then(handleSuccess, handleError('Error getting all reservations'));
        }

        function GetById(id) {
            return $http.get('/api/reservation/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function Create(reservation) {
            reservation.restaurant = null;
            console.log("Raz" + JSON.stringify(reservation));
            return $http.post('/api/reservation/create', reservation).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(reservation) {
            reservation.restaurant = null;

            return $http.post('/api/reservation/update', reservation).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/reservation/delete/' + id).then(handleSuccess, handleError('Error deleting reservation'));
        }

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }
})();