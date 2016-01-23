'use strict';

angular.module('anglelaxApp')
	.controller('MyProfileCtrl', function($scope, $http, $location) {

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

	});