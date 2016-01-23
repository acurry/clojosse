'use strict';

angular.module('anglelaxApp')
    .controller('MyTeamCtrl', function($scope, $location, $routeParams, $http, Team, Player) {

        $scope.teamId = Number($routeParams.teamId);

        $scope.team = Team.get($scope.teamId);

        $scope.isAuthorized = true;

        $scope.players = Player.query({}, function(response) {
            $scope.players = response;
        }, function(response) {
            if (response.status === 401) {
                $scope.isAuthorized = false;
            }
        });

        $scope.updateTeamName = function(teamName) {
            if (teamName === '') {
                return 'Team name cannot be empty';
            } else {
                $http.put('myTeam/' + $scope.teamId, {
                    Team_ID: $scope.teamId,
                    Team_name: teamName
                })
                    .success(function(data) {
                        $('.team-name-edit h2').html(teamName);
                        return true;
                    })
                    .error(function(data, status) {
                        console.log(data);
                        return false;
                    });
            }
        };

    });