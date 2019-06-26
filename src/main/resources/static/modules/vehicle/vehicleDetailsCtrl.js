var result_handle_global = function (result, func) {
    if (result.success === true) {
        layer.alert('操作成功', {icon: 6});
    } else {
        layer.msg("添加失败，原因：" + result.message)
    }
    if (func) {
        func();
    }
};


scriptApp.controller('vehicleDetailsCtrl', function ($scope, $http) {
    $scope.data = {};
    $scope.data.vehicleList = {};
    $scope.home = "/";
    $scope.carNumber = $("#carNumber").val();
    var start = {};
    var modifyStartDate = {};

    var imgList = new FormData();//图片上传到后台以表单的格式提交
    var showImg = [];//存放图片预览时的file文件

    var result_handle = function (result) {
        result_handle_global(result, $scope.DnsList);
    };

    $scope.startDate = function () {
        if ($scope.data.freashInsurance === null) {
            $scope.data.freashInsurance = {};
        }
        start = laydate.render({
            elem: '#registrationTime' //指定元素
            , min: formatDateTime($scope.data.freashInsurance.stopDate)
            , done: function (value, date) {
                end.config.min = doneTime(date);
                end.config.value = doneTime(date);
            }
        });

        modifyStartDate = laydate.render({
            elem: '#modifyRegistrationTime' //指定元素
            , done: function (value, date) {
                modifyStopDate.config.min = doneTime(date);
                modifyStopDate.config.value = doneTime(date);
            }
        });
    };

    $scope.addInsurance = function () {
        $scope.commit = {};
        $("#registrationTime").val(null);
        $("#validityDate").val(null);
        $scope.commit.operatorId = $("#userId").val();
        $scope.commit.carNumber = $("#carNumber").val();
        layer.open({
            type: 1,
            area: ['800px', '600px'], //宽高
            skin: 'layui-layer-molv', //加上边框
            title: $scope.carNumber + "投保",
            content: $('#insurance-add')
            , btn: ['添加', '取消']
            , success: function (layero, index) {
                layero.addClass('layui-form');//添加form标识
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');//将按钮弄成能提交的
                form.render();
            }
            , yes: function (index) {

                $scope.commit.startDate = new Date($("#registrationTime").val()).getTime();
                $scope.commit.stopDate = new Date($("#validityDate").val()).getTime();
                form.on('submit(fromContent)', function (data) {
                    if ($scope.commit.startDate && $scope.commit.stopDate) {

                        if ($scope.commit.insuranceFee <= $scope.commit.cashBackAmount) {
                            layer.msg('返现金额必须小于保险费用！！！');
                            return;
                        }
                        $http.post('/vehicle/insurance', $scope.commit).success(function (result) {
                            if (result.success) {
                                if (showImg.length) {
                                    fileUpload();
                                } else {
                                    layer.alert('操作成功', {icon: 6});
                                    $("#registrationTime").val("");
                                    $("#validityDate").val("");
                                    $scope.commit = {};
                                    layer.close(index);
                                    $scope.carDetailsList();
                                }
                                imgList = new FormData();
                                showImg = [];
                            } else {
                                layer.alert('操作失败', {icon: 5});
                                return false;
                            }

                        });
                    } else {
                        layer.msg('时间必填！！！');
                        return false;
                    }
                });

                // 上传图片的处理
                function fileUpload() {
                    showImg.forEach(function (item) {
                        imgList.append('files', item);
                    });
                    $http({
                        method: "post",
                        url: '/vehicle/addInsuranceImage/' + $scope.commit.carNumber, 　　  //请求路径
                        headers: {'Content-Type': undefined},
                        data: imgList　　　　　　//通常在发送post请求时使用。
                    }).success(function (response) {
                        //返回上传成功图片的信息
                        if (response.success) {
                            layer.alert('操作成功', {icon: 6});
                            layer.close(index);
                            $scope.carDetailsList();
                        } else {
                            layer.alert('图片上传失败', {icon: 5});
                            layer.close(index);
                        }
                        imgList = new FormData();
                        showImg = [];
                    });
                }


            }
            , btn2: function (index) {

                layer.close(index);
            }
        });
    };


    $scope.modifyInsurance = function () {
        if ("undefined" === $scope.data.freashInsurance || $scope.data.freashInsurance === null) {
            layer.msg("当前还没投保");
            return;
        }
        $scope.vo = {} = $scope.data.freashInsurance;
        $scope.vo.operatorId = $("#userId").val();
        $scope.vo.carNumber = $("#carNumber").val();
        layer.open({
            type: 1,
            area: ['800px', '600px'], //宽高
            skin: 'layui-layer-molv', //加上边框
            title: $scope.carNumber + "修改当前保险",
            content: $('#insurance-modify')
            , btn: ['添加', '取消']
            , success: function (layero, index) {
                layero.addClass('layui-form');//添加form标识
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');//将按钮弄成能提交的
                form.render();
            }
            , yes: function (index) {
                $scope.vo.startDate = new Date($("#modifyRegistrationTime").val()).getTime();
                $scope.vo.stopDate = new Date($("#modifyValidityDate").val()).getTime();
                form.on('submit(fromContent)', function (data) {
                    if ($scope.vo.startDate && $scope.vo.stopDate) {

                        if ($scope.vo.insuranceFee <= $scope.vo.cashBackAmount) {
                            layer.msg('返现金额必须小于保险费用！！！');
                            return;
                        }
                        $http.post('/vehicle/modifyInsurance/' + $scope.data.freashInsurance.id, $scope.vo).success(function (result) {
                            if (result.success) {
                                if (showImg.length) {
                                    fileUpload();
                                } else {
                                    layer.alert('操作成功', {icon: 6});
                                    $("#modifyRegistrationTime").val("");
                                    $("#modifyValidityDate").val("");
                                    $scope.vo = {};
                                    layer.close(index);
                                    $scope.carDetailsList();
                                }
                                imgList = new FormData();
                                showImg = [];
                            } else {
                                layer.alert('操作失败', {icon: 5});
                            }

                        });
                    } else {
                        layer.msg('时间必填！！！');
                        return false;
                    }
                });
                layer.close(index);

                // 上传图片的处理
                function fileUpload() {
                    showImg.forEach(function (item) {
                        imgList.append('files', item);
                    });
                    $http({
                        method: "post",
                        url: '/vehicle/addInsuranceImage/' + $scope.commit.carNumber, 　　  //请求路径
                        headers: {'Content-Type': undefined},
                        data: imgList　　　　　　//通常在发送post请求时使用。
                    }).success(function (response) {
                        //返回上传成功图片的信息
                        if (response.success) {
                            layer.alert('操作成功', {icon: 6});
                            layer.close(index);
                            $scope.carDetailsList();
                        } else {
                            layer.alert('图片上传失败', {icon: 5});
                            layer.close(index);
                        }
                        imgList = new FormData();
                        showImg = [];
                    });
                }


            }
            , btn2: function (index) {
                layer.close(index);
            }
        });
    };


    $("#ImgPreview").empty();


    $scope.data.refuelingRecordForForms = [];
    $scope.data.refuelingRecordForFormsTemp = [];
    $scope.data.upkeeps = [];
    $scope.data.upkeepsTemp = [];
    $scope.data.maintains = [];
    $scope.data.maintainsTemp = [];
    $scope.data.violations = [];
    $scope.data.violationsTemp = [];
    $scope.data.insuranceAndImagesPOS = [];
    $scope.data.insuranceAndImagesPOSTemp = [];
    $scope.data.vehicleYearChecks = [];
    $scope.data.vehicleYearChecksTemp = [];

    $scope.carDetailsList = function () {
        $http.get("/vehicle/carAllInfo/" + $scope.carNumber).success(function (result) {
            $scope.data.car = result.data.car;
            $scope.data.refuelingRecordCount = result.data.refuelingRecordCount;
            $scope.data.refuelingRecordForForms = result.data.refuelingRecordForForms;
            $scope.data.refuelingRecordForFormsTemp = $scope.data.refuelingRecordForForms;
            $scope.data.upkeeps = result.data.upkeeps;
            $scope.data.upkeepsTemp = result.data.upkeeps;
            $scope.data.maintains = result.data.maintains;
            $scope.data.maintainsTemp = result.data.maintainsTemp;
            $scope.data.violationCountInfo = result.data.violationCountInfo;
            $scope.data.violations = result.data.violations;
            $scope.data.violationsTemp = result.data.violations;
            $scope.data.freashInsurance = result.data.freashInsurance;
            $scope.data.insuranceAndImagesPOS = result.data.insuranceAndImagesPOS;
            $scope.data.insuranceAndImagesPOSTemp = result.data.insuranceAndImagesPOS;
            $scope.data.vehicleYearChecks = result.data.vehicleYearChecks;
            $scope.data.vehicleYearChecksTemp = result.data.vehicleYearChecks;
            oprationData($scope.data);
            $scope.startDate();
        })
    };
    $scope.carDetailsList();


    $scope.clearData = function () {
        document.getElementById('carNumber').value = null;
        document.getElementById('startDate').value = null;
        document.getElementById('endDate').value = null;
        $scope.commit = {};
        $scope.query();
    };


    //初始化上传图片的环境
    upload.render({
        elem: '#insurances'//“选择文件”按钮的ID
        , auto: false//不自动上传设置
        , accept: 'images'//允许上传的文件类型
        , exts: 'jpg|png|gif|bmp|jpeg'//设置智能上传图片格式文件
        , size: 2 * 1024 //最大允许上传的文件大小
        , multiple: true//设置是否多个文件上传
        , choose: function (obj) {//选中图片后
            //将每次选择的文件追加到文件队列
            var files = obj.pushFile();
            //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
            obj.preview(function (index, file, result) {
                showImg.push(file);
                if (file.size > 0 && $('#ImgPreview').find('img').length === 0) {
                    $('#ImgPreview').empty();
                }
                // 添加图片
                $('#ImgPreview').append('<div class="image-container" id="container' + index + '"><div class="delete-css"><button id="upload_img_' + index + '" class="layui-btn layui-btn-danger layui-btn-xs">删除</button></div>' +
                    '<img id="showImg' + index + '" style="width: 150px; margin:10px;cursor:pointer;"src="' + result + '" alt="' + file.name + '"></div>');
                //删除某一张图片
                $("#upload_img_" + index).bind('click', function () {
                    delete files[index];
                    $("#container" + index).remove();
                    showImg.splice(index, 1);//删除数组中的图片
                });

                //某图片放大预览
                $("#showImg" + index).bind('click', function () {
                    var width = $("#showImg" + index).width();
                    var height = $("#showImg" + index).height();
                    var scaleWH = width / height;
                    var bigH = 600;
                    var bigW = scaleWH * bigH;
                    if (bigW > 900) {
                        bigW = 900;
                        bigH = bigW / scaleWH;
                    }

                    // 放大预览图片
                    layer.open({
                        type: 1,
                        title: false,
                        closeBtn: 1,
                        shadeClose: true,
                        area: [bigW + 'px', bigH + 'px'], //宽高
                        content: "<img width='" + bigW + "' height='" + bigH + "' src=" + result + " />"
                    });
                });

            });
        }
        , done: function (res) {
            if (res) {
                layer.close(index);
            }
        }
    });

    // 初始化上传图片的环境 修改操作
    upload.render({
        elem: '#modifyInsurance'//“选择文件”按钮的ID
        , auto: false//不自动上传设置
        , accept: 'images'//允许上传的文件类型
        , exts: 'jpg|png|gif|bmp|jpeg'//设置智能上传图片格式文件
        , size: 2 * 1024 //最大允许上传的文件大小
        , multiple: true//设置是否多个文件上传
        , choose: function (obj) {//选中图片后
            //将每次选择的文件追加到文件队列
            var files = obj.pushFile();
            //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
            obj.preview(function (index, file, result) {
                showImg.push(file);
                if (file.size > 0 && $('#modifyImgPreview').find('img').length === 0) {
                    $('#modifyImgPreview').empty();
                }
                // 添加图片
                $('#modifyImgPreview').append('<div class="image-container" id="container' + index + '"><div class="delete-css"><button id="upload_img_' + index + '" class="layui-btn layui-btn-danger layui-btn-xs">删除</button></div>' +
                    '<img id="showImg' + index + '" style="width: 150px; margin:10px;cursor:pointer;"src="' + result + '" alt="' + file.name + '"></div>');
                //删除某一张图片
                $("#upload_img_" + index).bind('click', function () {
                    delete files[index];
                    $("#container" + index).remove();
                    showImg.splice(index, 1);//删除数组中的图片
                });

                //某图片放大预览
                $("#modifyImgPreview" + index).bind('click', function () {
                    var width = $("#modifyImgPreview" + index).width();
                    var height = $("#modifyImgPreview" + index).height();
                    var scaleWH = width / height;
                    var bigH = 600;
                    var bigW = scaleWH * bigH;
                    if (bigW > 900) {
                        bigW = 900;
                        bigH = bigW / scaleWH;
                    }

                    // 放大预览图片
                    layer.open({
                        type: 1,
                        title: false,
                        closeBtn: 1,
                        shadeClose: true,
                        area: [bigW + 'px', bigH + 'px'], //宽高
                        content: "<img width='" + bigW + "' height='" + bigH + "' src=" + result + " />"
                    });
                });

            });
        }
        , done: function (res) {
            if (res) {
                layer.close(index);
            }
        }
    });


//执行一个laydate实例

    var end = laydate.render({
        elem: '#validityDate' //指定元素
        , done: function (value, date, endDate) {
            //结束日选好后，重置开始日的最大日期
            start.config.max = doneTimeMin(date);
        }
    });
    //执行一个laydate实例


    var modifyStopDate = laydate.render({
        elem: '#modifyValidityDate' //指定元素
        , done: function (value, date) {
            //结束日选好后，重置开始日的最大日期
            modifyStartDate.config.max = doneTimeMin(date);
        }
    });

    //月份修正
    function doneTime(date) {
        return {
            year: date.year,
            month: date.month - 1,
            date: date.date + 1
        }
    }

    function doneTimeMin(date) {
        return {
            year: date.year,
            month: date.month - 1,
            date: date.date - 1
        }
    }

    // 将时间戳转换为yyyy-mm-dd
    function formatDateTime(inputTime) {
        if (undefined === inputTime) {
            return '1900-10-10';
        }
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate() + 1;
        d = d < 10 ? ('0' + d) : d;
        return y + '-' + m + '-' + d;
    }

    function oprationData(tempData) {

        page1(tempData.refuelingRecordForForms);
        page2(tempData.maintains);
        page3(tempData.insuranceAndImagesPOS);
        page4(tempData.vehicleYearChecks);
        page5(tempData.upkeeps);

    }

    $scope.flag1 = false;
    $scope.flag2 = false;
    $scope.flag3 = false;
    $scope.flag4 = false;
    $scope.flag5 = false;

    function page1(list) {
        laypage.render({
            elem: 'demo20'
            , count: list.length
            , limit: 10
            , jump: function (obj, first) {
                if (!first) {
                    $scope.flag1 = true;

                    //模拟渲染
                    function myPage(list) {
                        var arr = [], thisData = list.concat().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                        layui.each(thisData, function (index, item) {
                            arr.push(item);
                        });
                        return arr;
                    };
                    var temps = myPage(list);
                    $scope.data.refuelingRecordForFormsTemp = temps;
                    $scope.$apply();
                }
            }
        });
    }

    function page2(list) {
        laypage.render({
            elem: 'demo22'
            , count: list.length
            , limit: 10
            , jump: function (obj, first) {
                if (!first) {
                    $scope.flag4 = true;

                    //模拟渲染
                    function myPage(list) {
                        var arr = [], thisData = list.concat().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                        layui.each(thisData, function (index, item) {
                            arr.push(item);
                        });
                        return arr;
                    };
                    var temps = myPage(list);
                    $scope.data.maintainsTemp = temps;
                    $scope.$apply();
                }
            }
        });
    }

    function page3(list) {
        laypage.render({
            elem: 'demo21'
            , count: list.length
            , limit: 10
            , jump: function (obj, first) {
                if (!first) {
                    $scope.flag2 = true;

                    //模拟渲染
                    function myPage(list) {
                        var arr = [], thisData = list.concat().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                        layui.each(thisData, function (index, item) {
                            arr.push(item);
                        });
                        return arr;
                    };
                    var temps = myPage(list);
                    $scope.data.insuranceAndImagesPOSTemp = temps;
                    $scope.$apply();
                }
            }
        });
    }

    function page4(list) {
        laypage.render({
            elem: 'demo23'
            , count: list.length
            , limit: 10
            , jump: function (obj, first) {
                if (!first) {
                    $scope.flag5 = true;

                    //模拟渲染
                    function myPage(list) {
                        var arr = [], thisData = list.concat().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                        layui.each(thisData, function (index, item) {
                            arr.push(item);
                        });
                        return arr;
                    };
                    var temps = myPage(list);
                    $scope.data.vehicleYearChecksTemp = temps;
                    $scope.$apply();
                }
            }
        });
    }

    function page5(list) {
        laypage.render({
            elem: 'demo24'
            , count: list.length
            , limit: 10
            , jump: function (obj, first) {
                if (!first) {
                    $scope.flag3 = true;

                    //模拟渲染
                    function myPage(list) {
                        var arr = [], thisData = list.concat().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                        layui.each(thisData, function (index, item) {
                            arr.push(item);
                        });
                        return arr;
                    };
                    var temps = myPage(list);
                    $scope.data.upkeepsTemp = temps;
                    $scope.$apply();
                }
            }
        });
    }

});
