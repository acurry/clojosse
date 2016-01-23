'use strict';

angular.module('anglelaxApp')
    .controller('MyProfileEditCtrl', function($scope, $http, $location) {

        $scope.isAuthorized = true;

        $http.get('myInfo')
            .success(function(data) {
                $scope.coach = data;
            })
            .error(function(data, status) {
                if (status === 401) {
                    $scope.isAuthorized = false;
                }
            });

        $scope.saveMyProfile = function(e) {
            $http.post('myInfo', $scope.coach)
                .success(function(data) {
                    $location.path("/myProfile");
                })
                .error(function(data, status) {
                    $scope.coachForm.Email.$error.conflict = true
                });
        };

        $scope.cancel = function(e) {
            $location.path("/myProfile");
        };


    });