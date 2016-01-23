'use strict';

angular.module('anglelaxApp')
    .factory('Team', function Team($http) {
        
        var json = $http.get('teams').then(function(response) {
            return response.data;
        });

        var Team = function(data) {
            if (data) angular.copy(data, this);
        };

        Team.query = function() {
            return json.then(function(data) {
                return data.map(function(team) {
                    return new Team(team);
                });
            })
        };

        Team.get = function(id) {
            return json.then(function(teams) {
                return _.find(teams, function(team) {
                    if (team.Team_ID === id) {
                        return team;
                    }
                });
            })
        };

        return Team;
    });