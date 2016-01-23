'use strict';

angular.module('anglelaxApp')
    .filter('prettyDateTime', function() {
        return function(isoDate) {
            return moment(isoDate).format('MMM Do YYYY, h:mm a');
        };
    })
    .filter('prettyDate', function() {
        return function(isoDate) {
            return moment(isoDate).format('MMM Do YYYY');
        }
    })
    .filter('standardDate', function() {
        return function(isoDate) {
            return moment(isoDate).format('YYYY-MM-DD');
        }
    });