/**
 * Created by Nina on 09-May-16.
 */
(function () {
    'use strict';

    angular
        .module('iceipiceApp')
        .factory('GuestService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};
        var selectedRestaurant = null;
        var invitedFriends = [];
        var menuItems = [];

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.AddFriend = AddFriend;
        service.GetAllFriends = GetAllFriends;
        service.RemoveFriend = RemoveFriend;
        service.InviteFriend = InviteFriend;
        // prosledjujem restoran
        service.setSelectedRestaurant =  setSelectedRestaurant;
        service.getSelectedRestaurant = getSelectedRestaurant;
        // prosledjujem listu pozvanih prijatelja
        service.setInvitedFriends = setInvitedFriends;
        service.getInvitedFriends = getInvitedFriends;
        // prosledjujem listu meni itema
        service.setMenuItems = setMenuItems;
        service.getMenuItems = getMenuItems;

        return service;

        function setMenuItems(menu_items) {
            menuItems = menu_items;
        }
        function getMenuItems() {
            return menuItems;
        }

        function setInvitedFriends(friends) {
            invitedFriends = friends;
        }
        function getInvitedFriends() {
            return invitedFriends;
        }

        function setSelectedRestaurant(restaurant) {
                selectedRestaurant = restaurant;
        }
        function getSelectedRestaurant() {
            return selectedRestaurant;
        }


        function GetAll() {
            return $http.get('/api/guest/all').then(handleSuccess, handleError('Error getting all guests'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get('/api/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            return $http.post('/api/users', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        function AddFriend(myId, friendId) {
            var addFriendDTO = {myId: myId, friendId: friendId};
            return $http.post('/api/guest/addFriend', addFriendDTO).then(handleSuccess, handleError('Error updating user'));
        }

        function GetAllFriends(id) {
            return $http.get('/api/guest/all/' +id).then(handleSuccess, handleError('Error updating user'));
        }

        function RemoveFriend(myId, friendId) {
            var addFriendDTO = {myId: myId, friendId: friendId};
            return $http.post('/api/guest/removeFriend', addFriendDTO).then(handleSuccess, handleError('Error updating user'));
        }

        function InviteFriend(id, friendEmail) {
            var addFriendDTO = {myId : id, email : friendEmail };
            return $http.post('/api/guest/inviteFriend', addFriendDTO).then(handleSuccess, handleError('Error inviting user'));
        }
        // private functions


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
