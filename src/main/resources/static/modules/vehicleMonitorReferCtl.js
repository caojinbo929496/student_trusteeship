scriptApp.controller("vehicleMonitorCtl", function ($http, $scope) {

    $scope.standard = 0.00;
    $scope.oilWearList = [];
    $scope.oilWearListTemp = [];

//------------------------------------------------------------------------------------------------------------
    var startTime = new Date(new Date().getTime() - 604800000);
    var endTime = new Date();
    //页面默认显示的时间
    $scope.startTime = formatDateTime(startTime);
    $scope.endTime = formatDateTime(endTime);

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

    //月份修正
    function doneTime(date) {
        return {
            year: date.year,
            month: date.month - 1,
            date: date.date
        }
    }

    var start, end;
    var start = laydate.render({
        elem: '#trafficDateStart', //指定元素
        format: 'yyyy-MM-dd',
        done: function (value, date) {
            $scope.startTime = value;
            $scope.initData();
            //将结束日的初始值设定为开始日
            end.config.min = doneTime(date);
            end.config.value = doneTime(date);
        }
    });
    var end = laydate.render({
        elem: '#trafficDateEnd', //指定元素
        format: 'yyyy-MM-dd',
        done: function (value, date) {
            $scope.endTime = value;
            $scope.initData();
            //结束日选好后，重置开始日的最大日期
            start.config.max = doneTime(date);
        }
    });
    //开始时间
    //开始时间可选最小值的时间戳
    var minStart = new Date(new Date($scope.endTime).getTime() - 60 * 24 * 60 * 60 * 1000);
    //开始时间可选最小值
    start.config.min = {
        year: minStart.getFullYear(),
        month: minStart.getMonth(),
        date: minStart.getDate()
    };
    //开始时间可选最大值
    start.config.max = {
        year: endTime.getFullYear(),
        month: endTime.getMonth(),
        date: endTime.getDate()
    };
    start.config.value = $scope.startTime;

    //结束时间
    //结束时间可选最大值的时间戳
    var maxEnd = new Date(new Date($scope.startTime).getTime() + 60 * 24 * 60 * 60 * 1000);
    //结束时间可选最小值
    end.config.min = {
        year: startTime.getFullYear(),
        month: startTime.getMonth(),
        date: startTime.getDate()
    };
    end.config.max = {
        year: maxEnd.getFullYear(),
        month: maxEnd.getMonth(),
        date: maxEnd.getDate()
    };
    end.config.value = $scope.endTime;

    //结束时间可选最大值
//------------------------------------------------------------------------------------------------------------
    $scope.city = "";
    $scope.vehicleMonitors = [];
    $scope.driverMonitors = [];
    $scope.queryByCity = function () {
        $http.get("/vel/" + $scope.city).success(function (result) {
            if (result.success) {
                $scope.vehicleMonitors = result.data.vehicleMonitors;
                $scope.driverMonitors = result.data.driverMonitors;
                $scope.initData();
            }
        });
    };
    $scope.initData = function () {
        var city = "";
        city = $scope.city;
        if ($scope.city === null || $scope.city === "") {
            city = null;
        }
        var smallTime = new Date($scope.startTime);
        var bigTime = new Date($scope.endTime);
        $http.get("/oilWearList/" + smallTime + "/" + bigTime + "/" + city).success(function (result) {
            if (result.success) {
                $scope.oilWearList = result.data;
                $scope.oilWearListTemp = result.data;
                avgOil($scope.oilWearList)
            }
        });
    };
    $scope.initData();

    function avgOil(obj) {
        var total = 0;
        for (var index in obj) {
            var free = obj[index].totalFree;
            var mileage = obj[index].totalMileage;
            if (mileage === 0) {
                return;
            }
            total = total + free / mileage;
        }
        $scope.standard = parseFloat((total / obj.length).toFixed(2));
        if (isNaN($scope.standard)) {
            $scope.standard = 0.00;
        }
    }


});