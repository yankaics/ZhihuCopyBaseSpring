

angular.module("zhihu")
    .controller("mainCtrl",function($scope,$http, $window){
        $scope.title="知乎";

        $scope.up=function(ans_id){
            $http({
                    method: 'get',
                    url: "/answer/up?ans_id="+ans_id,
                }
            ).success(function (data) {
                $window.location.href='/';
                console.log(data);
            }).error(function (data) {
                alert("error");
                alert(data);
                console.log(data);
            });
        };

        $scope.down=function(ans_id){
            $http({
                    method: 'get',
                    url: "/answer/down?ans_id="+ans_id,
                }
            ).success(function (data) {
                $window.location.href='/';
            }).error(function (data) {
                alert("error");
                alert(data);
                console.log(data);
            });
        };
    });