'use strict';

angular.module('anglelaxApp')
    .controller('LocationsCtrl', function($scope, Location, Team) {

        $scope.locations = Location.query();

    });