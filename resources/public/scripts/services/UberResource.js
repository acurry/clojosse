angular.module('anglelaxApp')
    .factory('UberResource', function($resource) {
        return function(url, params, methods) {
            var defaults = {
                update: {
                    url: 'players/:id',
                    method: 'put',
                    isArray: false
                },
                create: {
                    method: 'post'
                }
            };

            methods = angular.extend(defaults, methods);

            var resource = $resource(url, params, methods);

            resource.prototype.$save = function() {
                if (!this.id) {
                    return this.$create();
                } else {
                    return this.$update({
                        id: this.id
                    });
                }
            };

            return resource;
        };
    });