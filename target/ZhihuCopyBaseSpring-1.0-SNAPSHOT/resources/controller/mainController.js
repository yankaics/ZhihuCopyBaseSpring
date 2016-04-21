

angular.module("zhihu")
    .controller("mainCtrl",function($scope,$http){
        $scope.title="知乎";
        $scope.answers={};
        $http({
                method: 'GET',
                url: "/latestAnswers",
            }
        ).success(function (data) {
            $scope.answers=data;
            console.log(data);
        }).error(function (data) {
            alert("error");
            alert(data);
            console.log(data);
        });


        $scope.up=function(ans){
            var upvote = ans.upvote;
            $http({
                    method: 'POST',
                    url: "/question/up",
                    data: upvote
                }
            ).success(function (data) {
                upvote.up=!upvote.up;
                if(upvote.up)
                    ans.upvoteNumber++;
                else
                    ans.upvoteNumber--;

                if(upvote.up && upvote.down){
                    upvote.down = !upvote.down;
                }
                console.log(data);
            }).error(function (data) {
                alert("error");
                alert(data);
                console.log(data);
            });
        };

        $scope.down=function(ans){
            var upvote = ans.upvote;
            $http({
                    method: 'POST',
                    url: "/question/down",
                    data: upvote
                }
            ).success(function (data) {
                upvote.down = !upvote.down;

                if(upvote.up && upvote.down){
                    ans.upvoteNumber--;
                    upvote.up = !upvote.up;
                }
                console.log(data);
            }).error(function (data) {
                alert("error");
                alert(data);
                console.log(data);
            });
        };
    });