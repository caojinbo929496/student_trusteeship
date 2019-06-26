scriptApp.controller('driverLicenseCtrl', function ($scope) {

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
        elem: '#startDate', //指定元素
        format: 'yyyy-MM-dd',
        done: function (value, date) {
            if (value) {
                $scope.vo.startDate = new Date(value);
            }
            //将结束日的初始值设定为开始日
            end.config.min = doneTime(date);
            end.config.value = doneTime(date);
        }
    });
    var end = laydate.render({
        elem: '#endDate', //指定元素
        format: 'yyyy-MM-dd',
        done: function (value, date) {
            if (value) {
                $scope.vo.endDate = new Date(value);
            }
            //结束日选好后，重置开始日的最大日期
            start.config.max = doneTime(date);
        }
    });
    // 将时间戳转换为yyyy-mm-dd
    function formatDateTime(inputTime) {
        if (null === inputTime) {
            return "未上传";
        }
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }

    var table = layui.table;

    $scope.vo = {};

    table.render({
        elem: '#driverLicenseList'
        , url: '/vehicle/listDriverLicense'
        , method: "post"
        , cols: [[
            {
                field: '', title: '序号', align: 'center', width: 100, templet: function (d) {
                    return d.LAY_INDEX;
                }
            }
            , {field: 'owners', title: '所有人'}
            , {field: 'carNumber', title: '车牌号'}
            , {field: 'vehicleType', title: '品牌类型'}
            , {field: 'brandModelNumber', title: '车辆品牌'}
            , {field: 'engineNumber', title: '发动机号'}
            , {field: 'frameNumber', title: '车架号'}
            , {
                field: 'registrationTime', title: '注册日期', templet: function (d) {
                    return formatDateTime(d.registrationTime);
                }
            }
            , {
                field: 'validityDate', title: '年检有效期至', templet: function (d) {
                    return formatDateTime(d.validityDate);
                }
            }
        ]]
        , id: 'testReload'
        , page: true
        , even: true
    });


    var $ = layui.$, active = {
        reload: function () {
            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: $scope.vo
            });
        }
    };


    //点击查询
    $scope.clickButton = function () {
        var startDate = angular.element('#startDate').val();
        var endDate = angular.element('#endDate').val();
        if (startDate) {
            $scope.vo.startDate = new Date(startDate);
        }
        if (endDate) {
            $scope.vo.endDate = new Date(endDate);
        }

        active.reload.call(this);
    };
    //点击清空数据
    $scope.clearData = function () {
        angular.element("#startDate").val(null);
        angular.element("#endDate").val(null);
        $scope.vo = "";
        active.reload.call(this);
    }

});
