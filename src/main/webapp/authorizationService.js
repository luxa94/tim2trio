iceipiceApp.service('authorizationService', function ($http, $window) {
    var LOCAL_STORAGE_KEY = 'oroUser';
    var LOCAL_STORAGE_INSTANCE = $window.localStorage;

    return {
        signIn: function (user, successCallback, errorCallback) {
            $http.post("/api/login", user).success(successCallback).error(errorCallback);
        },
        signOut: function (successCallback, errorCallback) {
            var loggedInUser = this.getUser();
            if (loggedInUser) {
                $http.post("/api/logout", loggedInUser.id).success(successCallback).error(errorCallback);
            }
        },
        getUser: function () {
            if (LOCAL_STORAGE_INSTANCE) {
                var loggedInUser = LOCAL_STORAGE_INSTANCE.getItem(LOCAL_STORAGE_KEY);
                if (loggedInUser) {
                    return JSON.parse(loggedInUser);
                }
            }
        },
        setUser: function (user) {
            if (LOCAL_STORAGE_INSTANCE && user) {
                LOCAL_STORAGE_INSTANCE.setItem(LOCAL_STORAGE_KEY, JSON.stringify(user));
            }
        },
        removeUser: function () {
            LOCAL_STORAGE_INSTANCE && LOCAL_STORAGE_INSTANCE.removeItem(LOCAL_STORAGE_KEY);
        },
        register: function (user, successCallback, errorCallback) {

            $http({
                method: 'POST',
                url: "/api/guest/register",
                data: user,
                headers: {
                    'Content-Type': 'application/json'
                }}).success(successCallback).error(errorCallback);
        }
    }
});