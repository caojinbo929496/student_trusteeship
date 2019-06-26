scriptApp.controller("upkeepReferCtl", function ($http, $scope) {

    $scope.upkeepList = [];
    $scope.upkeepListTemp = [];
    $scope.queryCondition = {};
    $scope.obj = null;
    $scope.cityList = [];
    $scope.repositoryList = [];
    $scope.patenteNameList = [];
    $scope.carNumberList = [];
    var start, end;

    //月份修正
    function doneTime(date) {
        return {
            year: date.year,
            month: date.month - 1,
            date: date.date
        }
    };
    var start = laydate.render({
        elem: '#upkeepDateStart', //指定元素
        done: function (value, date) {
            //将结束日的初始值设定为开始日
            end.config.min = doneTime(date);
            end.config.value = doneTime(date);
        }
    });
    var end = laydate.render({
        elem: '#upkeepDateEnd', //指定元素
        done: function (value, date) {
            //结束日选好后，重置开始日的最大日期
            start.config.max = doneTime(date);
        }
    });

    $scope.queryKeywords = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = $scope.paginationUpkeepConf.itemsPerPage;
        $scope.queryCondition.startDateView = Date.parse(new Date($("#upkeepDateStart").val()));
        $scope.queryCondition.endDateView = Date.parse(new Date($("#upkeepDateEnd").val())) + 1 * 24 * 60 * 60 * 1000;
        $http.put("/queryKeyWords", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.upkeepList = result.data.listUpkeeps;
                $scope.upkeepListTemp = result.data.listUpkeeps;
                $scope.paginationUpkeepConf.totalItems = result.data.counts;
            }
        });

    };


    $scope.showCitySelect = false;
    $scope.getCity = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = 50;
        $http.put("/queryKeyWords", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.cityList = [];
                angular.forEach(result.data.listUpkeeps, function (a) {
                    if ($.inArray(a.city, $scope.cityList) == -1) {
                        $scope.cityList.push(a.city);
                    }
                });
                if ($scope.cityList.length) {
                    $scope.showCitySelect = true;
                }
            }
        });
    };
    // 选中某个数据
    $scope.selectCityValue = function (value) {
        $scope.showCitySelect = false;
        $scope.queryCondition.cityName = value;
    };
    $("#cityName").blur(function () {
        $scope.showCitySelect = false;
        $scope.$apply();
    });

    $scope.showRepositorysSelect = false;
    $scope.getRepository = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = 50;
        $http.put("/queryKeyWords", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.repositoryList = [];
                angular.forEach(result.data.listUpkeeps, function (a) {
                    if ($.inArray(a.warehouse, $scope.repositoryList) == -1) {
                        $scope.repositoryList.push(a.warehouse);
                    }
                });
                if ($scope.repositoryList.length) {
                    $scope.showRepositorysSelect = true;
                }
            }
        });
    };
    $scope.selectRepositorysValue = function (value) {
        $scope.showRepositorysSelect = false;
        $scope.queryCondition.repository = value;
    };
    $("#repository").blur(function () {
        $scope.showRepositorysSelect = false;
        $scope.$apply();
    });

    $scope.showUpkeepPatenteSelect = false;
    $scope.getUpkeepPatente = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = 50;
        $http.put("/queryKeyWords", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.patenteNameList = [];
                angular.forEach(result.data.listUpkeeps, function (a) {
                    if ($.inArray(a.patenteName, $scope.patenteNameList) == -1) {
                        $scope.patenteNameList.push(a.patenteName);
                    }
                });
                if ($scope.patenteNameList.length) {
                    $scope.showUpkeepPatenteSelect = true;
                }
            }
        });
    };
    $scope.selectUpkeepPatenteValue = function (value) {
        $scope.showUpkeepPatenteSelect = false;
        $scope.queryCondition.patenteName = value;
    };
    $("#upkeepPatente").blur(function () {
        $scope.showUpkeepPatenteSelect = false;
        $scope.$apply();
    });

    $scope.showCarNumberSelects = false;
    $scope.getCarNumber = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = 50;
        $http.put("/queryKeyWords", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.carNumberList = [];
                angular.forEach(result.data.listUpkeeps, function (a) {
                    if ($.inArray(a.carNumber, $scope.carNumberList)) {
                        $scope.carNumberList.push(a.carNumber);
                    }
                });
                if ($scope.carNumberList.length) {
                    $scope.showCarNumberSelects = true;
                }
            }
        });
    };
    $scope.selectCarNumberValue = function (value) {
        $scope.showCarNumberSelects = false;
        $scope.queryCondition.carNumber = value;
    };
    $("#carNumber").blur(function () {
        $scope.showCarNumberSelects = false;
        $scope.$apply();
    });

    $scope.reset = function () {
        $scope.queryCondition = {};
        angular.element("#upkeepDateStart").val(null);
        angular.element("#upkeepDateEnd").val(null);
        searchUpkeepList();
    };


    $scope.paginationUpkeepConf = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20]
    };
    var searchUpkeepList = function () {
        $scope.queryCondition.currentPage = $scope.paginationUpkeepConf.currentPage;
        $scope.queryCondition.pageSize = $scope.paginationUpkeepConf.itemsPerPage;
        $http.put("/upkeepList", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.upkeepList = result.data.listUpkeeps;
                $scope.upkeepListTemp = result.data.listUpkeeps;
                $scope.paginationUpkeepConf.totalItems = result.data.counts;
            }
        });
    };
    $scope.searchUpkeepClick = searchUpkeepList;
    $scope.$watch('paginationUpkeepConf.currentPage + paginationUpkeepConf.itemsPerPage', searchUpkeepList);


});