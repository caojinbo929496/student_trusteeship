scriptApp.controller("maintainDetailReferCtl", function ($http, $scope) {

    $scope.maintain = {};
    $scope.initData = function () {
        var maintainId = $("#maintainId").val();
        $http.get("/maintain/detail/" + maintainId).success(function (result) {
            if (result.success) {
                $scope.maintain = result.data;
            }
        });
    };
    $scope.initData();


});