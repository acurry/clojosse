'use strict';

angular.module('anglelaxApp')
    .factory('Location', function Location($http) {

        var json = $http.get('locations').then(function(response) {
            return response.data;
        });

        var Location = function(data) {
            if (data) angular.copy(data, this);
        };

        Location.query = function() {
            return json.then(function(data) {
                return data.map(function(location) {
                    return new Location(location);
                });
            });
        };

        Location.get = function(id) {
            return json.then(function(locations) {
                return _.find(locations, function(loc) {
                    if (loc.Location_ID === id) {
                        return loc;
                    }
                });
            });
        }

        return Location;

    });