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
        service.asd = { reservation : {} };


        return service;
        
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

        function Create(user) {
            return $http.post('/api/users', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
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