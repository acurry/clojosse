'use strict';

angular.module('anglelaxApp')
    .controller('MainCtrl', function($scope, $http, $routeParams, $location, Team, Game, Location) {

        $scope.futureGames = Game.getAllFutureGames().then(function(games) {
            return _.map(games, function(game) {
                return _.extend(game, {
                    loc: Location.get(game.Location),
                    homeTeam: Team.get(game.Home_team),
                    awayTeam: Team.get(game.Away_team)
                });
            });
        });

        $scope.pastGames = Game.getAllPastGames().then(function(games) {
            return _.map(games, function(game) {
                return _.extend(game, {
                    loc: Location.get(game.Location),
                    homeTeam: Team.get(game.Home_team),
                    awayTeam: Team.get(game.Away_team)
                });
            });
        });

    });
