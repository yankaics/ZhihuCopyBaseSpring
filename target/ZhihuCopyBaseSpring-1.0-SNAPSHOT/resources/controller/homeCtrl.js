/**
 * @ngdoc controller
 * @name home:homeCtrl
 *
 * @description
 *
 *
 * @requires $scope
 * */
angular.module('home')
    .controller('homeCtrl', function($scope,$route){
        $scope.$route = $route;
        $scope.$on("$routeChangeSuccess", function(event, current, previous){
            $scope.activeMenu = current.$$route.activeMenu;
        });
        // console.log($route.current.$$route.activeMenu);
        // $scope.activeMenu=$route.current.$$route.activeMenu;
});
