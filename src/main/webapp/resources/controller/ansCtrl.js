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
    .controller('ansCtrl', function($scope,$http){
        $scope.answers={};
        $http({
                method: 'GET',
                url: "/answers",
            }
        ).success(function (data) {
            $scope.answers=data;
            console.log(data);
        }).error(function (data) {
            alert("error");
            alert(data);
            console.log(data);
        });
});
