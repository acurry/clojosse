'use strict';

angular.module('anglelaxApp')
    .factory('Game', function Game($http) {

        var json = $http.get('games').then(function(response) {
            return response.data;
        });

        var Game = function(data) {
            if (data) angular.copy(data, this);
        };

        Game.query = function() {
            return json.then(function(data) {
                return data.map(function(game) {
                    return new Game(game);
                });
            });
        };

        Game.getAllByTeam = function(teamId) {
            return json.then(function(data) {
                var results = [];
                angular.forEach(data, function(game) {
                    if (game.Home_team === teamId ||
                        game.Away_team === teamId) {
                        results.push(new Game(game));
                    }
                });
                return results;
            });
        };

        var getAllGamesByTime = function(func) {
            return json.then(function(data) {
                var results = [];
                var now = moment();

                angular.forEach(data, function(game) {
                    if (moment(game.DateTime)[func](now)) {
                        results.push(new Game(game));
                    }
                });

                return results;
            });
        };

        Game.getAllFutureGamesByTeam = function(teamId) {
            return Game.getAllFutureGames().then(function(data) {
                var g = _.filter(data, function(game) {
                    return game.Home_team === teamId || game.Away_team === teamId;
                });

                return g;
            });
        };

        Game.getAllPastGamesByTeam = function(teamId) {
            return Game.getAllPastGames().then(function(data) {
                return _.filter(data, function(game) {
                    return game.Home_team === teamId || game.Away_team === teamId;
                });
            });
        };

        Game.getAllPastGames = function() {
            return getAllGamesByTime('isBefore');
        };

        Game.getAllFutureGames = function() {
            return getAllGamesByTime('isAfter');
        };

        Game.getWinnerAndLoser = function(game) {
            if (game.Home_score > game.Away_score) {
                return {
                    "winner": game.Home_team,
                    "loser": game.Away_team
                };
            } else {
                return {
                    "winner": game.Home_team,
                    "loser": game.Away_team
                };
            }
        };

        return Game;
    });