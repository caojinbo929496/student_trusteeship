scriptApp.controller('violationCtrl', function ($scope, $http) {
    $scope.startType = angular.element('#type').val();
    $scope.startType1 = angular.element('#type1').val();
    $scope.startType2 = angular.element('#type2').val();
    $scope.commit = {};
    $scope.vm = {};
    var status = null;

    laydate.render({
        elem: '#startDate',
        format: 'yyyy-MM-dd',
        done: function (value) {
            if (value) {
                $scope.commit.startDateView = new Date(value).getTime();
            }
        }
    });

    laydate.render({
        elem: '#endDate',
        format: 'yyyy-MM-dd',
        done: function (value) {
            if (value) {
                $scope.commit.endDateView = new Date(value).getTime();
            }
        }
    });

    //配置分页基本参数
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20]
    };

    var getDataList = function () {
        if (status == null) {
            $scope.start();
        }
        $scope.commit.currentPage = ($scope.paginationConf.currentPage - 1) * $scope.paginationConf.itemsPerPage;
        $scope.commit.pageSize = $scope.paginationConf.itemsPerPage;
        $http.post("/violation/web/listViolation", $scope.commit).success(function (result) {
            if (result.success) {
                $scope.vm.violationList = result.data.data;
                $scope.paginationConf.totalItems = result.data.count;

            }
        });
    };

    $scope.searchClick = getDataList;
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', getDataList);

    //点击查询
    $scope.queryByCondition = function () {
        $scope.commit.startDateView = new Date(angular.element('#startDate').val()).getTime();
        $scope.commit.endDateView = new Date(angular.element('#endDate').val()).getTime() + 1 * 24 * 60 * 60 * 1000;
        if ("1" === angular.element('#status').val()) {
            $scope.commit.status = true;
            $scope.violationStatus = "1";
        }
        if ("0" === angular.element('#status').val()) {
            $scope.commit.status = false;
            $scope.violationStatus = "0";
        }
        getDataList();
    };


    $scope.start = function () {
        var nowdate = new Date();
        if ("0" === $scope.startType) {
        }
        if ("1" === $scope.startType) {
            $scope.commit.status = false;
            $scope.violationStatus = "0";
        }
        if ("2" === $scope.startType) {
            currentYear = nowdate.getFullYear();
            currentMonth = nowdate.getMonth();
            $scope.commit.startDateView = new Date(currentYear, currentMonth, 1).getTime();
            $scope.commit.endDateView = nowdate.getTime();
            $scope.showStart = formatDateTime($scope.commit.startDateView);
            $scope.showEnd = formatDateTime(nowdate.getTime());
        }
        if ("3" === $scope.startType) {
            $scope.commit.startDateView = new Date(nowdate - 30 * 24 * 3600 * 1000).getTime();
            $scope.commit.endDateView = nowdate.getTime();
            $scope.showStart = formatDateTime($scope.commit.startDateView);
            $scope.showEnd = formatDateTime(nowdate.getTime());
        }
        if ("4" === $scope.startType) {
            $scope.commit.startDateView = new Date(nowdate - 60 * 24 * 3600 * 1000).getTime();
            $scope.commit.endDateView = nowdate.getTime();
            $scope.showStart = formatDateTime($scope.commit.startDateView);
            $scope.showEnd = formatDateTime(nowdate.getTime());

        }
        if (offender = $scope.startType1) {
            $scope.commit.offender = offender;
            $scope.commit.status = false;

        }
        if (carNumber = $scope.startType2) {
            $scope.commit.carNumber = carNumber;
            $scope.commit.status = false;

        }
    };

    $scope.clearData = function () {
        //点击清空数据
        status = "jump";
        $scope.commit = {};
        $scope.violationStatus = "";
        angular.element("#startDate").val(null);
        angular.element("#endDate").val(null);
        getDataList();
    };

    // 将时间戳转换为yyyy-mm-dd
    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }

});
