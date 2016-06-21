/**
 * Created by Sandra on 21.6.2016.
 */
(function () {
    'use strict';

    angular
        .module('iceipiceApp')
        .factory('ProviderService', pService);

    pService.$inject = ['$http'];
    function pService($http) {
        var service = {};
        var selectedList = null;


        // prosledjujem restoran
        service.setSelectedList =  setSelectedList;
        service.getSelectedList = getSelectedList;
        return service;

        function setSelectedList(list) {
            selectedList = list;
        }
        function getSelectedList() {
            return selectedList;
        }

    }

})();