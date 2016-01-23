'use strict';

angular.module('anglelaxApp', ['ngResource', 'ui.bootstrap.datepicker', 'xeditable'])
    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            })
            .when('/locations', {
                templateUrl: 'views/locations.html',
                controller: 'LocationsCtrl'
            })
            .when('/standings', {
                templateUrl: 'views/standings.html',
                controller: 'StandingsCtrl'
            })
            .when('/teams/:id', {
                templateUrl: 'views/partials/teamDetails.html',
                controller: 'TeamDetailsCtrl'
            })
            .when('/myTeam/:teamId', {
                templateUrl: 'views/myTeam.html',
                controller: 'MyTeamCtrl'
            })
            .when('/myTeam/:teamId/edit/:id', {
                templateUrl: 'views/playerEdit.html',
                controller: 'PlayerEditCtrl'
            })
            .when('/myTeam/:teamId/add', {
                templateUrl: 'views/playerAdd.html',
                controller: 'PlayerAddCtrl'
            })
            .when('/myProfile', {
                templateUrl: 'views/myProfile.html',
                controller: 'MyProfileCtrl'
            })
            .when('/myProfile/edit/:id', {
                templateUrl: 'views/myProfileEdit.html',
                controller: 'MyProfileEditCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    })
    .run(function(editableOptions) {
        editableOptions.theme = "bs2";
    });