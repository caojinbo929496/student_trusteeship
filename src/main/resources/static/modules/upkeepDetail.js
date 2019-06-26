scriptApp.controller("upkeepDetailReferCtl", function ($http, $scope) {

    $scope.upkeepDetail = {};
    $scope.initData = function () {
        var upkeepId = $("#upkeepId").val();
        $http.get("/upkeep/detail/" + upkeepId).success(function (result) {
            if (result.success) {
                $scope.upkeepDetail = result.data;
            }
        });
    };
    $scope.initData();


});