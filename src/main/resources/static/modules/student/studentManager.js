var globalFileInput;

function forUploadCardButtonOnclickAjax() {
    console.log("获得的文件对象是:" + globalFileInput.files[0]);
    var virtualFormData = new FormData();
    virtualFormData.append("vehicleExcel", globalFileInput.files[0]);
    $.ajax({
        type: "POST",
        url: "/vehicle/uploadSeveralVehicleInformation",
        data: virtualFormData,
        contentType: false,
        processData: false,
        success: function (data) {
            if ("success" == data["result"]) {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "提示",
                    area: ["auto", 'auto'],
                    id: 'addSeveralPopPop3',
                    content: "导入成功",
                    btn: "确定",
                    yes: function () {
                        layer.closeAll();
                        location.href = "/vehicle/newVehicle";
                    },
                    cancel: function () {
                        layer.closeAll();
                        location.href = "/vehicle/newVehicle";
                    }
                });
            }
            if ("false" == data["result"]) {


                if (data["carPONotAddList"] == null) {
                    layer.open({
                        type: 1,
                        skin: 'layui-layer-molv',
                        title: "提示",
                        area: ["auto", 'auto'],
                        id: 'addSeveralPopPop',
                        content: "<span style='display:inline-block;margin:30px 40px;margin-top: 30px;color: red;'>上传的文件不是excel，或服务器内部错误，请稍后再试</span>",
                        btn: "确定",
                        yes: function () {
                            layer.closeAll();
                        },
                        cancel: function () {
                            layer.closeAll();
                        }
                    });
                    return;
                }
                var notAddList = "";
                for (var i = 0; i < data["carPONotAddList"].length; i++) {
                    notAddList += "<span style='display:inline-block;margin:0px 100px;margin-bottom: 20px;'>" + ("" + (i + 1) + ".") + "&nbsp;车辆uuid:" + data["carPONotAddList"][i].id + "&nbsp;&nbsp;&nbsp;&nbsp;车辆城市:" + data["carPONotAddList"][i].city + "&nbsp;&nbsp;&nbsp;&nbsp;车牌号:" + data["carPONotAddList"][i].carNumber + "</span><br/>";
                }
                $("#popNotAddDiv").html(notAddList);
                layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "以下车辆信息补全失败，请重新补全(原因可能是必填字段为空、数值为负、起止时间大小不合适或服务器内部错误)",
                    area: ["800px", '600px'],
                    id: 'addSeveralInsurancePop',
                    content: $("#insurance-add-several-car"),
                    btn: "确定",
                    yes: function () {
                        $("#popNotAddDiv").html("");
                        layer.closeAll();
                        location.href = "/vehicle/newVehicle";

                    },
                    cancel: function () {
                        $("#popNotAddDiv").html("");
                        layer.closeAll();
                        location.href = "/vehicle/newVehicle";
                    }
                })
            }

        }

    });
}

scriptApp.controller('studentManager', function ($scope, $http) {
    var upload = layui.upload;
    $scope.data = {};
    $scope.data.vehicleList = {};
    $scope.home = "/";
    var table = layui.table;
    var showImg = [];//存放图片预览时的file文件

    $scope.commit = {};
    table.render({
        elem: '#newVehicleList'
        , url: '/vehicle/listCar'
        , method: "post"
        , cols: [[
            {
                field: '', title: '序号', align: 'center', width: 100, templet: function (d) {
                    return d.LAY_INDEX;
                }
            }
            , {field: 'city', title: '学生姓名', align: 'center'}
            , {field: 'carNumber', title: '性别'}
            , {field: 'vehicleType', title: '家长姓名', align: 'center'}
            , {field: 'vehicleType', title: '家长联系方式', align: 'center'}
            , {field: 'warehouse', title: '学生联系方式', align: 'center'}
            , {field: 'brandModelNumber', title: '注册时间', align: 'center'}
            , {
                field: 'status', title: '意向', align: 'center', templet: function (d) {
                    if (d.status) {
                        return "启用";
                    } else {
                        return "停用";
                    }
                }
            }
            , {field: '', title: '操作', toolbar: '#barDemo', align: 'center'}
        ]]
        , id: 'testReload'
        , page: true
        , even: true

    });

    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            addVehicleInfo(data);
        }
    });


    var $ = layui.$, active = {
        reload: function () {
            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: $scope.commit
            });
        }
    };

    function reload() {
        table.reload('testReload', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: $scope.commit
        });
    }

    //点击查询
    $scope.clickButton = function () {
        // $scope.commit = {};
        reload();
    };


    //点击清空数据
    $scope.clearData = function () {
        angular.element('#startDate').val("");
        angular.element('#endDate').val("");
        $scope.commit = "";
        active.reload.call(this);
        $scope.commit = {};
    };

    var start = laydate.render({
        elem: '#registeredStartTime', //指定元素
        done: function (value, date) {
            //将结束日的初始值设定为开始日
            end.config.min = doneTime(date);
            end.config.value = doneTime(date);
        }
    });
    var end = laydate.render({
        elem: '#registeredEndTime', //指定元素
        done: function (value, date) {
            //结束日选好后，重置开始日的最大日期
            start.config.max = doneTime(date);
        }
    });

    //打开弹窗
    function addVehicleInfo(vehicle) {

        var imgList = new FormData();//图片上传到后台以表单的格式提交
        $scope.commit = {};
        $scope.commit.carNumber = vehicle.carNumber;

        //begin-控制开始时间小于结束时间
        //月份修正
        function doneTime(date) {
            return {
                year: date.year,
                month: date.month - 1,
                date: date.date + 1
            }
        }

        var start = laydate.render({
            elem: '#registrationTime', //指定元素
            done: function (value, date) {
                //将结束日的初始值设定为开始日
                end.config.min = doneTime(date);
                end.config.value = doneTime(date);
                $scope.commit.registrationTime = new Date(value).getTime();
            }
        });
        var end = laydate.render({
            elem: '#validityDate', //指定元素
            done: function (value, date) {
                //结束日选好后，重置开始日的最大日期
                start.config.max = doneTime(date);
                $scope.commit.validityDate = new Date(value).getTime();
            }
        });
        //end-控制开始时间小于结束时间


        layer.open({
            type: 1,
            area: ['900px', '700px'], //宽高
            skin: 'layui-layer-molv', //加上边框
            title: '编辑',
            content: $('#add-vehicle-info')
            , btn: ['确定', '取消']
            , success: function (layero, index) {
                layero.addClass('layui-form');//添加form标识
                layero.find('.layui-layer-btn0').attr('lay-filter', 'fromContent').attr('lay-submit', '');//将按钮弄成能提交的
                form.render();
            }
            , yes: function (index, layero) {
                form.on('submit(fromContent)', function (data) {
                    if ($scope.commit.registrationTime && $scope.commit.validityDate) {

                        if ($scope.commit.insuranceFee <= $scope.commit.cashBackAmount) {
                            layer.msg('返现金额必须小于保险费用！！！');
                            return;
                        }

                        $http.post('/vehicle/addExtraInfo', $scope.commit).success(function (result) {
                            if (result.success) {
                                //数据上传成功后执行上传图片操作-如果有图片，则上传图片
                                if (showImg.length) {
                                    fileUpload();
                                    $scope.commit = {};
                                    active.reload.call(this);
                                } else {
                                    layer.alert('操作成功', {icon: 6});
                                    $("#registrationTime").val("");
                                    $("#validityDate").val("");
                                    $scope.commit.insuranceFee = null;
                                    $scope.commit.cashBackAmount = null;
                                    $scope.commit.vehiclePrice = null;
                                    layer.close(index);
                                    $scope.commit = {};
                                    active.reload.call(this);
                                }
                            } else {
                                layer.alert('操作失败', {icon: 5});
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
                        url: '/vehicle/addInsuranceImage/' + vehicle.carNumber, 　　  //请求路径
                        headers: {'Content-Type': undefined},
                        data: imgList　　　　　　//通常在发送post请求时使用。
                    }).success(function (response) {
                        //返回上传成功图片的信息
                        if (response.success) {
                            layer.alert('操作成功', {icon: 6});
                            layer.close(index);
                        } else {
                            layer.alert('图片上传失败', {icon: 5});
                        }
                    });
                }
            }
        });

        $("#ImgPreview").empty();

    }


    //初始化上传图片的环境
    upload.render({
        elem: '#insurance'//“选择文件”按钮的ID
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

    $scope.exportTemplate = function () {
        location.href = "/vehicle/responseVehicleTemplate";
    };

    $scope.importInformation = function () {
        var fileInput = document.createElement('input');
        fileInput.setAttribute('type', 'file');
        fileInput.setAttribute("name", "vehicleExcel");
        $(fileInput).trigger("click");
        globalFileInput = fileInput;
        console.log(globalFileInput);
        $(fileInput).attr("onchange", "forUploadCardButtonOnclickAjax();");
    }
});
