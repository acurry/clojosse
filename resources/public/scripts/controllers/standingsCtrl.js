'use strict';

angular.module('anglelaxApp')
    .controller('StandingsCtrl', function($scope, $http, $routeParams, Team, Standings) {
        $scope.teamStats = Team.query().then(function(teams) {
            return _.map(teams, function(team) {
                return _.extend(team, {
                    stats: Standings.getTeamStats(team.Team_ID)
                });
            });
        });

        $scope.predicate = "stats.percentage";
        $scope.reverse = true;
    });
