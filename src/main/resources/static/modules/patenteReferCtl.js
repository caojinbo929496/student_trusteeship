scriptApp.controller("patenteReferCtl", function ($http, $scope) {

    $scope.patenteList = [];
    $scope.patenteListTemp = [];
    $scope.patenteName = null;
    $scope.city = null;
    $scope.repository = null;
    $scope.pageObject = null;
    $scope.state = $("#state").val();


    /**
     * 作废功能
     * @param id
     */
    $scope.cancellation = function (id) {
        layer.confirm('确定作废?', {icon: 3, title: '提示'}, function (index) {
            $http.put("/discard/" + id).success(function (result) {
                if (result.success) {
                    location.replace(location.href);
                }
            });
        });
    };


    $scope.reset = function () {
        $scope.patenteName = "";
        $scope.city = "";
        $scope.repository = "";
        searchList();

    };

    //获取驾驶人列表
    $scope.showSelect = false;
    $scope.getPatenteName = function () {
        var patenteName = $scope.patenteName;
        if (patenteName === "") {
            patenteName = null;
        }
        $http.get("/likeName/" + patenteName).success(function (result) {
            if (result.success) {
                $scope.patenteNameList = result.data;
                if ($scope.patenteNameList.length) {
                    $scope.showSelect = true;
                }
            }
        });
    };
    //选中某个数据
    $scope.selectValue = function (value) {
        $scope.showSelect = false;
        $scope.patenteName = value;
    };
    $("#patenteName").blur(function () {
        $scope.showSelect = false;
    });

    $scope.showCitySelect = false;
    $scope.getCity = function () {
        var city = $scope.city;
        if (city === "") {
            city = null;
        }
        $http.get("/likeCity/" + city).success(function (result) {
            if (result.success) {
                $scope.cityList = result.data;
                if ($scope.cityList.length) {
                    $scope.showCitySelect = true;
                }
            }
        });
    };
    $scope.selectCityValue = function (value) {
        $scope.showCitySelect = false;
        $scope.city = value;
    };

    $("#city").blur(function () {
        $scope.showCitySelect = false;
    });


    $scope.showRepositorySelect = false;
    $scope.getRepository = function () {
        var repository = $scope.repository;
        if (repository === "") {
            repository = null;
        }
        $http.get("/likeRepository/" + repository).success(function (result) {
            if (result.success) {

                $scope.repositoryList = result.data;

                if ($scope.repositoryList.length) {
                    $scope.showRepositorySelect = true;
                }
            }
        });
    };
    $scope.selectRepositoryValue = function (value) {
        $scope.showRepositorySelect = false;
        $scope.repository = value;
    };
    $("#repository").blur(function () {
        $scope.showRepositorySelect = false;
    });


    /**
     * 查询功能
     */

    //配置分页基本参数
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20]
    };

    var searchList = function () {
        var name = $scope.patenteName;
        var city = $scope.city;
        var repository = $scope.repository;
        var state = $scope.state;
        if (null !== name && name.trim() === "") {
            name = null;
        }
        if (city !== null && city.trim() === "") {
            city = null;
        }
        if (repository !== null && repository.trim() === "") {
            repository = null;
        }
        if (state !== null && state.trim() === "") {
            state = 0;
        }

        $http.get("/pagePatent/" + $scope.paginationConf.currentPage + "/" + $scope.paginationConf.itemsPerPage + "/" + name + "/" + city + "/" + repository + "/" + state).success(function (result) {
            if (result.success) {
                $scope.patenteListTemp = result.data.listPatentes;
                $scope.paginationConf.totalItems = result.data.counts;
            }
        });

    };
    $scope.searchClick = searchList;
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', searchList);

});