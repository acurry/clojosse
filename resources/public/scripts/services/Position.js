'use strict';

angular.module('anglelaxApp')
    .factory('Position', function Position($http) {

        var json = $http.get('positions').then(function(response) {
            return response.data;
        });

        var Position = function(data) {
            if (data) angular.copy(data, this);
        };

        Position.query = function() {
            return json.then(function(data) {
                return data.map(function(position) {
                    return new Position(position);
                });
            });
        };

        Position.get = function(id){
            return json.then(function(data) {
                return data.map(function(position) {
                    if (position.Position_ID === id) {
                        return new Position(position);    
                    }
                });
            });
        };

        return Position;
    });