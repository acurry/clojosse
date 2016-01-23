'use strict';

angular.module('anglelaxApp')
    .controller('PlayerAddCtrl', function($scope, $location, $routeParams, $timeout, $filter, Player, Position) {

        $scope.teamId = Number($routeParams.teamId);

        $scope.isAuthorized = true;

        $scope.player = new Player({Team: $scope.teamId});

        $scope.player.Position = 1;

        $scope.showDatepicker = function() {
            $timeout(function() {
                $scope.opened = true;
            });
        };

        $scope.addPlayer = function(e) {
            $scope.player.DOB = $filter('date')($scope.player.DOB, 'yyyy-MM-dd');

            $scope.player.$create({}, function(player, headers) {
                $location.path("/myTeam/" + $scope.teamId);
            }, function(response) {
                $scope.playerForm.Email.$error.conflict = true;
            });
        };

        $scope.cancel = function(e) {
            $location.path("/myTeam/" + $scope.teamId);
        };

        $scope.dateOptions = {
            'year-format': "'yyyy'",
            'month-format': "'MM'",
            'day-format': "'dd'"
        };

        $scope.positions = Position.query().then(function(positions) {
            return _.map(positions, function(position) {
                return _.extend(position, {
                    value: position.Position_ID,
                    label: position.Type
                });
            });
        });

        $scope.format = 'yyyy-MM-dd';

    });