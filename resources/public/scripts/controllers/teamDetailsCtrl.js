'use strict';

angular.module('anglelaxApp')
    .controller('TeamDetailsCtrl', function($scope, $http, $routeParams, Team, Player, Game, Location) {

        $scope.teamId = Number($routeParams.id);

        $scope.team = Team.get($scope.teamId);

        $scope.locations = Location.query();

        $scope.futureGames = Game.getAllFutureGamesByTeam($scope.teamId).then(function(games) {
            return _.map(games, function(game) {
                return _.extend(game, {
                    loc: Location.get(game.Location),
                    opponent: $scope.getOpponent(game)
                });
            });
        });

        $scope.getOpponent = function(game) {
            if (game.homeTeamId === $scope.teamId) {
                return Team.get(game.Away_team);
            } else {
                return Team.get(game.Home_team);
            }
        };

        $scope.pastGames = Game.getAllPastGamesByTeam($scope.teamId).then(function(data) {
            return _.map(data, function(game) {
                if (game.Home_score > game.Away_score) {
                    var _game = _.extend(game, {
                        result: (game.Home_team === $scope.teamId ? 'WIN' : 'LOSS'),
                        loc: Location.get(game.Location),
                        opponent: $scope.getOpponent(game)
                    });
                    return _game;
                } else {
                    var _game = _.extend(game, {
                        result: (game.Away_team === $scope.teamId ? 'WIN' : 'LOSS'),
                        loc: Location.get(game.Location),
                        opponent: $scope.getOpponent(game)
                    });
                    return _game;
                }
            });
        });

        Player.getPublicPlayersOnTeam($scope.teamId, function(players) {
            $scope.players = players;
        });

    });