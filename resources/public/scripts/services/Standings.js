'use strict';

angular.module('anglelaxApp')
    .factory('Standings', function Player(Team, Game) {

        var Standings = {};

        Standings.getTeamStats = function(teamId) {

            return Game.getAllPastGamesByTeam(teamId).then(function(data) {
                var stats = {
                    wins: 0,
                    losses: 0,
                    percentage: 0.0
                };

                _.forEach(data, function(game) {
                    if (game.Home_team === teamId) {
                        if (game.Home_score > game.Away_score) {
                            stats.wins++;
                        } else {
                            stats.losses++;
                        }
                    } else {
                        if (game.Away_score > game.Home_score) {
                            stats.wins++;
                        } else {
                            stats.losses++;
                        }
                    }
                });

                stats.percentage = stats.wins / (stats.wins + stats.losses);

                if (stats.wins === 0 && stats.losses === 0) {
                    stats.percentage = 0;
                }

                return stats;
            });
        };

        return Standings;
    });