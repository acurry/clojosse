angular.module('anglelaxApp')
    .factory('Player', function Player(UberResource, $http) {

        return _.extend(UberResource('players/:id'), {

            onTeam: function(team, onSuccessCallback, onErrorCallback) {
                return this.query({}, function(data) {
                    var result = [];
                    angular.forEach(data, function(player) {
                        if (player.Team === team) {
                            result.push(player);
                        }
                    });
                    onSuccessCallback && onSuccessCallback(result);
                }, function(response) {
                    onErrorCallback && onErrorCallback(response);
                });
            },

            getPublicPlayersOnTeam: function(team, onSuccessCallback) {
                return $http.get('players/onPublicTeam/' + team).then(function(response) {
                    onSuccessCallback && onSuccessCallback(response.data);
                });
            },

            getPlayerForTeam: function(id, onSuccessCallback, onErrorCallback) {
                return this.query({}, function(data) {
                    var result = {};
                    angular.forEach(data, function(player) {
                        if (player.Player_ID === id) {
                            result = player;
                        }
                    });

                    result.DOB = new Date(result.DOB + ' EST');

                    onSuccessCallback && onSuccessCallback(result);
                }, function(response) {
                    onErrorCallback && onErrorCallback(response);
                });
            },

            getPublicData: function(team, callback) {
                return $http.get('players/getPublicData').then(function(response) {
                    var result = [];
                    angular.forEach(response.data, function(player) {
                        if (player.Team === team) {
                            result.push(player);
                        }
                    });
                    callback && callback(result);
                });
            }

        });
    });