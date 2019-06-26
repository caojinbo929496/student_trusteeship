scriptApp.controller('vehicleQueryCtrl', function ($scope, $http) {
    $scope.startType = angular.element('#type').val();
    $scope.vm = {};
    $scope.home = "/";
    $scope.vehicleStatus = "";
    var status = null;
    $scope.violationStatus = "";

    $scope.queryByCondition = function (tag) {

        // 保险开始时间
        $scope.commit.insuranceStartDate = null;
        $scope.commit.insuranceStartDateView = null;
        // 保险截止时间
        $scope.commit.insuranceStopDate = null;
        $scope.commit.insuranceStopDateView = null;
        // 年检状态
        $scope.commit.carCheckStartDate = null;
        $scope.commit.carCheckStartDateView = null;
        // 年检截止时间
        $scope.commit.carCheckStopDate = null;
        $scope.commit.insuranceStopDateView = null;

        if ($("#city").val() == "" && $("#warehouse").val() == "" && $("#carNumber").val() == "" && $("#vehicleStatus").val() == "" && $("#insuranceStatus").val() == "" && $("#carCheckStstus").val() == "" && $("#violationStatus").val() == "") {
            location.href = "/vehicle/vehicleQuery/0";
            return;
        }
        if ("0" === $('#vehicleStatus').val()) {
            $scope.commit.vehicleStatus = false;
            $scope.vehicleStatus = "0";
        }
        if ("1" === $('#vehicleStatus').val()) {
            $scope.commit.vehicleStatus = true;
            $scope.vehicleStatus = "1";
        }

        var nowdate = new Date();
        currentYear = nowdate.getFullYear();
        currentMonth = nowdate.getMonth();
        currentDay = nowdate.getDate();
        if ("0" === $('#insuranceStatus').val()) {
            //$scope.commit.insuranceStartDate = new Date(currentYear, currentMonth + 3, currentDay);
            $scope.commit.insuranceStartDateView = new Date(currentYear, currentMonth + 2, currentDay).getTime();

            $scope.commit.insuranceIsNull = 1;
        }
        if ("1" === $('#insuranceStatus').val()) {
            $scope.commit.insuranceStopDateView = new Date(currentYear, currentMonth, currentDay).getTime();

            $scope.commit.insuranceIsNull = 1;
        }
        if ("3" === $('#insuranceStatus').val()) {
            $scope.commit.insuranceStartDateView = nowdate.getTime();
            $scope.commit.insuranceStopDateView = new Date(currentYear, currentMonth + 2, currentDay).getTime();

            $scope.commit.insuranceIsNull = 1;
        }
        if ("4" === $('#insuranceStatus').val()) {
            $scope.commit.insuranceIsNull = 2;
        }

        if ("0" === $('#violationStatus').val()) {
            $scope.commit.violationStatus = false;
            $scope.violationStatus = "0";
        }
        if ("1" === $('#violationStatus').val()) {
            $scope.commit.violationStatus = true;
            $scope.violationStatus = "1";
        }

        if ("0" === $('#carCheckStstus').val()) {
            // $scope.commit.carCheckStartDate = new Date(currentYear, currentMonth + 2, currentDay);
            $scope.commit.carCheckStartDateView = new Date(currentYear, currentMonth + 2, currentDay).getTime();

            $scope.carCheckStstus = "0";
            $scope.commit.carCheckIsNull = "1";
        }
        if ("1" === $('#carCheckStstus').val()) {
            $scope.commit.carCheckStartDateView = new Date(currentYear, currentMonth, 1).getTime();
            $scope.commit.carCheckStopDateView = new Date(currentYear, currentMonth + 2, currentDay).getTime();
            $scope.carCheckStstus = "1";
            $scope.commit.carCheckIsNull = "1";

        }
        if ("2" === $('#carCheckStstus').val()) {
            $scope.commit.carCheckStopDateView = new Date(currentYear, currentMonth, currentDay).getTime();
            $scope.carCheckStstus = "2";
            $scope.commit.carCheckIsNull = "1";
        }
        if ("3" === $('#carCheckStstus').val()) {
            $scope.carCheckStstus = "3";
            $scope.commit.carCheckIsNull = "2";
        }


        getDataList();
    };

    $scope.clearData = function () {
        document.getElementById('vehicleStatus').value = "";
        $scope.commit = {};
        getDataList();
    };

    $scope.commit = {};

    //配置分页基本参数
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20],
        onChange: function () {//改变值
            getDataList();
        }
    };

    var getDataList = function () {
        if (status == null) {
            $scope.test();
        }

        $scope.commit.currentPage = $scope.paginationConf.currentPage;
        $scope.commit.pageSize = $scope.paginationConf.itemsPerPage;
        $http.post("/vehicle/web/listCarAllInfo", $scope.commit).success(function (result) {
            $scope.vm.carAllInfos = result.data.carAllInfos;
            for (i = 0; i < $scope.vm.carAllInfos.length; i++) {

                var oldDate = formatDateTime($scope.vm.carAllInfos[i].validityDate);
                if (null != oldDate) {
                    var oldYear = oldDate.substring(0, oldDate.indexOf("-"));
                    var oldMonth = oldDate.substring(oldDate.indexOf("-") + 1, oldDate.lastIndexOf("-"));
                    var oldDay = 0;

                    if (((oldYear % 4 == 0 && oldYear % 100 != 0) || oldYear % 400 == 0) && oldMonth == 2) {
                        oldDay = 29;
                    }
                    if (oldMonth == 2 && !((oldYear % 4 == 0 && oldYear % 100 != 0) || oldYear % 400 == 0)) {
                        oldDay = 28;
                    }
                    if ((oldMonth % 2 == 1 && oldMonth < 8) || (oldMonth % 2 == 0 && oldMonth > 7)) {
                        oldDay = 31;
                    } else if (oldMonth != 2) {
                        oldDay = 30;
                    }
                    $scope.vm.carAllInfos[i].validityDate = oldYear + "-" + oldMonth + "-" + oldDay;
                }
            }
            $scope.paginationConf.totalItems = result.data.count;
        });
    };

    $scope.searchClick = getDataList;

    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }

    $scope.test = function () {
        var nowdate = new Date();
        currentYear = nowdate.getFullYear();
        currentMonth = nowdate.getMonth();
        currentDay = nowdate.getDate();
        if ("0" === $scope.startType) {
        }

        if ("1" === $scope.startType) {
            $scope.commit.insuranceStopDateView = new Date(nowdate).getTime();
            $scope.insuranceStatus = "1";
            $scope.commit.insuranceIsNull = 1;

        }
        if ("2" === $scope.startType) {
            $scope.commit.insuranceStartDateView = new Date(nowdate).getTime();
            $scope.commit.insuranceStopDateView = new Date(currentYear, currentMonth, currentDay + 30).getTime();
            $scope.insuranceStatus = "3";
            $scope.commit.insuranceIsNull = 1;
        }
        if ("3" === $scope.startType) {

            $scope.commit.insuranceStartDateView = new Date(nowdate).getTime();
            $scope.commit.insuranceStopDateView = new Date(currentYear, currentMonth, currentDay + 60).getTime();
            $scope.insuranceStatus = "3";
            $scope.commit.insuranceIsNull = 1;
        }

        if ("4" === $scope.startType) {
            $scope.commit.carCheckStopDateView = new Date(currentYear, currentMonth, currentDay).getTime();
            $scope.carCheckStstus = "2";
            $scope.commit.carCheckIsNull = "1";

        }
        if ("5" === $scope.startType) {
            $scope.commit.carCheckStartDateView = new Date(currentYear, currentMonth, 1).getTime();
            $scope.commit.carCheckStopDateView = new Date(currentYear, currentMonth + 1, currentDay).getTime();
            $scope.carCheckStstus = "1";
            $scope.commit.carCheckIsNull = "1";
        }
        if ("6" === $scope.startType) {
            $scope.commit.carCheckStartDateView = new Date(currentYear, currentMonth, 1).getTime();
            $scope.commit.carCheckStopDateView = new Date(currentYear, currentMonth + 2, currentDay).getTime();
            $scope.carCheckStstus = "1";
            $scope.commit.carCheckIsNull = "1";
        }

    };

    $scope.clearData = function () {
        status = "jump";
        document.getElementById('violationStatus').value = "";
        document.getElementById('insuranceStatus').value = "";
        document.getElementById('carCheckStstus').value = "";
        document.getElementById('vehicleStatus').value = "";
        $scope.commit = {};
        getDataList();
    };

});
