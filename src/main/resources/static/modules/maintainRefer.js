scriptApp.controller("maintainReferCtl", function ($http, $scope) {

    $scope.maintainList = [];
    $scope.maintainListTemp = [];
    $scope.queryCondition = {};
    $scope.obj = null;
    $scope.cityList = [];

    $scope.queryKeywords = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = $scope.paginationMaintainConf.itemsPerPage;
        $scope.queryCondition.startDateView = Date.parse(new Date($("#upkeepDateStart").val()));
        $scope.queryCondition.endDateView = Date.parse(new Date($("#upkeepDateEnd").val())) + 1 * 24 * 60 * 60 * 1000;
        $http.put("/maintainList", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.maintainList = result.data.maintainLists;
                $scope.maintainListTemp = result.data.maintainLists;
                $scope.paginationMaintainConf.totalItems = result.data.counts;
            }
        });
    };

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
    $scope.showCitySelect = false;
    $scope.getCity = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = 50;
        $http.put("/queryKeyWordsMaintain", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.cityList = [];
                angular.forEach(result.data.maintainLists, function (a) {
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
    $scope.reset = function () {
        $scope.queryCondition = {};

        angular.element("#upkeepDateStart").val(null);
        angular.element("#upkeepDateEnd").val(null);

        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = $scope.paginationMaintainConf.itemsPerPage;
        $http.put("/maintainList", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.maintainList = result.data.maintainLists;
                $scope.maintainListTemp = result.data.maintainLists;
                $scope.paginationMaintainConf.totalItems = result.data.counts;
            }
        });
    };

    $scope.showRepositorysSelect = false;
    $scope.getRepository = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = 50;
        $http.put("/queryKeyWordsMaintain", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.repositoryList = [];
                angular.forEach(result.data.maintainLists, function (a) {
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
        $http.put("/queryKeyWordsMaintain", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.patenteNameList = [];
                angular.forEach(result.data.maintainLists, function (a) {
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

    $scope.showCarNumberSelect = false;
    $scope.getCarNumber = function () {
        $scope.queryCondition.currentPage = 1;
        $scope.queryCondition.pageSize = 50;
        $http.put("/queryKeyWordsMaintain", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.carNumberList = [];
                angular.forEach(result.data.maintainLists, function (a) {
                    if ($.inArray(a.carNumber, $scope.carNumberList)) {
                        $scope.carNumberList.push(a.carNumber);
                    }
                });
                if ($scope.carNumberList.length) {
                    $scope.showCarNumberSelect = true;
                }
            }
        });
    };
    $scope.selectCarNumberValue = function (value) {
        $scope.showCarNumberSelect = false;
        $scope.queryCondition.carNumber = value;
    };
    $("#carNumber").blur(function () {
        $scope.showCarNumberSelect = false;
        $scope.$apply();
    });

    $scope.paginationMaintainConf = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20]
    };
    var searchMaintainList = function () {
        $scope.queryCondition.currentPage = $scope.paginationMaintainConf.currentPage;
        $scope.queryCondition.pageSize = $scope.paginationMaintainConf.itemsPerPage;
        $http.put("/maintainList", $scope.queryCondition).success(function (result) {
            if (result.success) {
                $scope.maintainList = result.data.maintainLists;
                $scope.maintainListTemp = result.data.maintainLists;
                $scope.paginationMaintainConf.totalItems = result.data.counts;
            }
        });
    };
    $scope.searchMaintainClick = searchMaintainList;
    $scope.$watch('paginationMaintainConf.currentPage + paginationMaintainConf.itemsPerPage', searchMaintainList);


});