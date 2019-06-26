var globalFileInput;

function forUploadCardButtonOnclickAjax() {
    console.log("获得的文件对象是:" + globalFileInput.files[0]);
    var virtualFormData = new FormData();
    virtualFormData.append("cardExcel", globalFileInput.files[0]);
    $.ajax({
        type: "POST",
        url: "/fuelCardManager/uploadServeralCards",
        data: virtualFormData,
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data["result"]);
            if (data["result"] == 1) {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "提示",
                    area: ["auto", 'auto'],
                    id: 'fuelCardManagerPop',
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

            if (data["fuelCardPONotAddList"].length == 0) {
                layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "提示",
                    area: ["auto", 'auto'],
                    id: 'fuelCardManagerPop',
                    content: "<span style='display:inline-block;margin:30px 40px;margin-top: 30px;'>导入成功</span>",
                    btn: "确定",
                    yes: function () {
                        layer.closeAll();
                        location.href = "/fuelCardManager/fuelCardManager";
                    },
                    cancel: function () {
                        layer.closeAll();
                        location.href = "/fuelCardManager/fuelCardManager";
                    }
                });

                return;
            }
            if (data["fuelCardPONotAddList"].length > 0) {
                var notAddList = "";

                for (var i = 0; i < data["fuelCardPONotAddList"].length; i++) {
                    notAddList += "<span style='display:inline-block;margin:0px 100px;margin-bottom: 20px;'>" + ("" + (i + 1) + ".") + "&nbsp;油卡商家:" + (data["fuelCardPONotAddList"][i].cardMerchant == -1 ? "" : data["fuelCardPONotAddList"][i].cardMerchant) + "&nbsp;&nbsp;&nbsp;&nbsp;主卡号:" + data["fuelCardPONotAddList"][i].masterCardNumber + "&nbsp;&nbsp;&nbsp;&nbsp;副卡号:" + data["fuelCardPONotAddList"][i].viceCardNumber + "</span><br/>";
                }
                $("#popNotAddDiv").html(notAddList);
                layer.open({
                    type: 1,
                    skin: 'layui-layer-molv',
                    title: "以下加油卡信息导入失败，请重新导入(原因可能是必填字段为空、字段格式错误、车牌号不存在、加油卡被添加过或重复添加)",
                    area: ["auto", 'auto'],
                    id: 'fuelCardManagerPop',
                    content: $("#card-add-several-fuelCard"),
                    btn: "确定",
                    yes: function () {
                        $("#popNotAddDiv").html("");
                        layer.closeAll();
                        location.href = "/fuelCardManager/fuelCardManager";

                    },
                    cancel: function () {
                        $("#popNotAddDiv").html("");
                        layer.closeAll();
                        location.href = "/fuelCardManager/fuelCardManager";
                    }
                })
            }
        }

    });
}


scriptApp.controller('fuelCardManagerCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.vo = {};


    $scope.bindCardNum = function (temp) {
        var bindCarNumberTitle;
        layer.open({
            type: 1,
            title: '绑定车牌号(' + (temp.cardMerchant == 0 ? '中石油' : '中石化') + '),',
            skin: 'layui-layer-molv',
            offset: 'auto',
            id: 'fuelCardManagerPop',
            area: ["614px", '600px'],
            content: $("#card-bind-carNumber"),
            btn: ['绑定', '取消'],
            btnAlign: 'c',
            shade: 0,
            success: function () {

                $("#pop-masterCardNumber").text(temp.masterCardNumber);
                $("#pop-viceCardNumber").text(temp.viceCardNumber);
            },
            yes: function () {
                var carNumber = $scope.bundleCarNumber;

                if (null == carNumber || carNumber.length < 1 || "" == carNumber) {
                    var bindCardNum_2_index = layer.open({
                        type: 0,
                        id: 'bindCardNum-2',
                        tipsMore: true,
                        content: "您未输入车牌号",
                        yes: function () {
                            layer.close(bindCardNum_2_index);
                        }
                    });
                    return;
                }
                if (carNumber.length > 8) {
                    var bindCardNum_1_index = layer.open({
                        type: 0,
                        id: 'bindCardNum-1',
                        tipsMore: true,
                        content: "您输入的车牌号太长，无法绑定",
                        yes: function () {
                            layer.close(bindCardNum_1_index);
                        }
                    });
                    return;
                }

                var bindDta = {
                    masterCardNumber: temp.masterCardNumber,
                    viceCardNumber: temp.viceCardNumber,
                    cardMerchant: temp.cardMerchant,
                    carNumber: carNumber
                };


                var fuelCardManagerPopToPopIndex = layer.open({
                    type: 1,
                    title: '添加油卡',
                    skin: 'layui-layer-molv',
                    offset: 'auto',
                    id: 'fuelCardManagerPopToPop',
                    area: ["auto", 'auto'],
                    content: "确定绑定车辆" + bindDta.carNumber + "?",
                    btn: ['添加', '取消'],
                    btnAlign: 'c',
                    shade: 0,
                    cancel: function () {
                        layer.close(fuelCardManagerPopToPopIndex);
                    },
                    btn2: function () {
                        layer.close(fuelCardManagerPopToPopIndex);
                    },
                    yes: function () {
                        layer.close(fuelCardManagerPopToPopIndex);
                        $http.post('/fuelCardManager/bindCarNumber', bindDta).success(function (response) {
                            console.log(response);
                            if ("success" == response.ifUpdateSuccess) {
                                var bind_success_index = layer.open({
                                    type: 0,
                                    id: 'bindCardNum-2',
                                    tipsMore: true,
                                    content: "绑定成功",
                                    yes: function () {
                                        layer.close(bind_success_index);
                                        location.href = "/fuelCardManager/fuelCardManager";
                                    }
                                });
                                $("#card-bind-carNumber").css("display", "none");
                                $scope.ifShowCarNumberList = false;
                                $scope.bundleCarNumber = null;
                                $scope.carNumberList = null;
                            } else {
                                var bind_fail_index = layer.open({
                                    type: 0,
                                    id: 'bindCardNum-2',
                                    tipsMore: true,
                                    content: "数据库中无此车牌号车牌号，无法绑定",
                                    yes: function () {
                                        layer.close(bind_fail_index);
                                    }
                                });

                            }

                        })
                    }
                })


            },
            btn2: function () {

                layer.closeAll();
                $scope.bundleCarNumber = null;
                $("#card-bind-carNumber").css("display", "none");
                $scope.ifShowCarNumberList = false;
                $scope.carNumberList = null;
            },
            cancel: function () {
                layer.closeAll();
                $("#card-bind-carNumber").css("display", "none");
                $scope.bundleCarNumber = null;
                $scope.ifShowCarNumberList = false;
                $scope.carNumberList = null;
            }
        });
    };
    $scope.clearClick = function () {
        $scope.vo.masterCardNumber = undefined;
        $scope.vo.viceCardNumber = undefined;
        $scope.vo.city = undefined;
        $scope.vo.bundlePlateNumber = undefined;
        $scope.vo.cardMerchant = 'all';
        $scope.vo.ifBundle = 'all';
        $http.post('../fuelCardManager/getFuelCardPOWithMultipleParams', $scope.vo).success(function (response) {
            $scope.fuelCardList = response.fuelCardList;
            console.log($scope.fuelCardList);
            $scope.pageOperation.totalItems = response.fuelCardNumber;
        });
    }
    $scope.exportTemplateClick = function () {

        location.href = "/fuelCardManager/getCardExcelTemplate";
    }


    $scope.importFuelCardExcel = function () {
        var fileInput = document.createElement('input');
        fileInput.setAttribute('type', 'file');
        fileInput.setAttribute("name", "cardExcel");
        $(fileInput).trigger("click");
        globalFileInput = fileInput;
        console.log(globalFileInput);
        $(fileInput).attr("onchange", "forUploadCardButtonOnclickAjax();");
    }


    $scope.pageOperation = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20]
    };

    var searchList = function () {
        $scope.vo.start = ($scope.pageOperation.currentPage - 1) * $scope.pageOperation.itemsPerPage;
        $scope.vo.length = $scope.pageOperation.itemsPerPage;
        $http.post('../fuelCardManager/getFuelCardPOWithMultipleParams', $scope.vo).success(function (response) {

            $scope.fuelCardList = response.fuelCardList;

            $scope.pageOperation.totalItems = response.fuelCardNumber;
        });
    };
    $scope.searchClick = function () {
        searchList();
    };
    $scope.$watch('pageOperation.currentPage + pageOperation.itemsPerPage', searchList);
    $scope.vo.cardMerchant = "all";
    $scope.vo.ifBundle = "all";

    $scope.bundleCarNumber = null;
    $scope.ifShowCarNumberList = false;
    $scope.carNumberList = null;
    $scope.getCarNumberNotBundled = function () {
        console.log("执行了getCarNumberNotBundled（）函数");
        var functionBundleCarNumber = $scope.bundleCarNumber;
        console.log("functionBundleCarNumber变量的值为:" + functionBundleCarNumber);
        console.log(functionBundleCarNumber == null || "" == functionBundleCarNumber);
        if (functionBundleCarNumber == null || "" == functionBundleCarNumber) {
            console.log("getCarNumberNotBundled（）函数结束了");
            $scope.carNumberList = null;
            $scope.ifShowCarNumberList = false;
            return;
        }
        $http.post('/fuelCardManager/listCarNumberNotBundled', functionBundleCarNumber).success(function (response) {
            console.log(" $http.post('fuelCardManager/listCarNumberNotBundled')这个函数执行了吗？");
            $scope.carNumberList = response;
            console.log(" $scope.carNumberList变量的值为:" + $scope.carNumberList);
            if ($scope.carNumberList.length > 0) {
                $scope.ifShowCarNumberList = true;
            }
            console.log($scope.carNumberList);
        }).error(function () {
            console.log(" $http.post('fuelCardManager/listCarNumberNotBundled')这个函数执行失败了");
        });
    }
    $scope.selectCarNumber = function (item) {
        $scope.bundleCarNumber = item;
        $scope.ifShowCarNumberList = false;
    }

    $scope.ifShowCarNumberListFalse = function () {
        if ($scope.ifIncarNumberList == false) {
            $scope.ifShowCarNumberList = false;
        }
    }

    $scope.ifShowCarNumberListTrue = function () {

        $scope.ifShowCarNumberList = true;
    }
    $scope.ifIncarNumberList = false;
    $scope.carNumberListFalse = function () {
        $scope.ifIncarNumberList = false;
    }
    $scope.carNumberListTrue = function () {
        $scope.ifIncarNumberList = true;
    }

    $scope.addFuelCard = function () {
        layer.open({
            type: 1,
            title: '添加油卡',
            skin: 'layui-layer-molv',
            offset: 'auto',
            id: 'fuelCardManagerPop',
            area: ["600px", '600px'],
            content: $("#card-add-fuelCard"),
            btn: ['确定', '取消'],
            btnAlign: 'c',
            shade: 0,
            success: function () {
                $("#popAddErrorDiv").html("");
                $("#pop-add-viceCardNumber").attr("disabled", null);
                $("#pop-add-masterCardNumber").val("");
                $("#pop-add-viceCardNumber").val("");
                $("#pop-add-cardMerchant").val("all");
                $("#pop-add-password").val("");
                $("#pop-add-cardType").val("all");
                $("#pop-add-masterCardNumber").attr("placeholder", null);
                $("#pop-add-viceCardNumber").attr("placeholder", null);
                $scope.bundleCarNumber = null;
                $scope.carNumberList = null;
                $("#viceCard").html("");
            },
            yes: function () {
                $("#pop-add-viceCardNumber").attr("disabled", null);
                var popAddFuelCardData = {
                    masterCardNumber: $("#pop-add-masterCardNumber").val(),
                    viceCardNumber: $("#pop-add-viceCardNumber").val(),
                    cardMerchant: $("#pop-add-cardMerchant").val(),
                    bundlePlateNumber: $scope.bundleCarNumber,
                    password: $("#pop-add-password").val(),
                    cardType: $("#pop-add-cardType").val()
                };

                $("#company").html("");
                $("#carKind").html("");
                $("#masterCar").html("");
                $("#viceCard").html("");
                $("#password").html("");


                var errorMessage = "";
                if (null == popAddFuelCardData.cardMerchant || "all" == popAddFuelCardData.cardMerchant) {
                    $("#company").children().remove();
                    $("#company").html("<span style='padding-bottom: 4px;display: inline-block;'>请选择油卡商家</span><br/><br/>");
                    /*errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>请选择油卡商家</span><br/><br/>";*/

                }

                if (null == popAddFuelCardData.cardType || "all" == popAddFuelCardData.cardType) {
                    $("#carKind").children().remove();
                    $("#carKind").html("<span style='padding-bottom: 4px;display: inline-block;'>您未选择油卡类型</span><br/><br/>");
                    /*errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>您未选择油卡类型</span><br/><br/>";*/
                }
                if (null == popAddFuelCardData.masterCardNumber || "" == popAddFuelCardData.masterCardNumber) {

                    $("#masterCar").children().remove();
                    $("#masterCar").html("<span style='display: inline-block;'>您未填写主卡号</span><br/><br/>");

                    /*errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>您未填写主卡号</span><br/><br/>";*/
                } else if (!(/^\d+\d+\d$/.test(popAddFuelCardData.masterCardNumber))) {
                    $("#masterCar").children().remove();
                    $("#masterCar").html("<span style='display: inline-block;'>主卡号只能为纯数字</span><br/><br/>");

                    /*errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'> 主卡号只能为纯数字</span><br/><br/>";*/
                }

                if (popAddFuelCardData.cardType == 1 && (null == popAddFuelCardData.viceCardNumber || "" == popAddFuelCardData.viceCardNumber)) {
                    $("#viceCard").children().remove();
                    $("#viceCard").html("<span style='display: inline-block;'>您未填写副卡号</span>");
                } else if (popAddFuelCardData.cardType == 1 && !(/^\d+\d+\d$/.test(popAddFuelCardData.viceCardNumber))) {
                    $("#viceCard").children().remove();
                    $("#viceCard").html("< span>style = 'display: inline-block;' > 副卡号只能为纯数字 < /span>  ");
                }
                if (null == popAddFuelCardData.password || "" == popAddFuelCardData.password) {
                    $("#password").children().remove();
                    $("#password").html("<span style='padding-bottom: 4px;display: inline-block;'>您未填写查询密码</span><br/><br/>");

                    /* errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>您未填写查询密码</span><br/><br/>";*/
                }
                if (null == popAddFuelCardData.bundlePlateNumber || "" == popAddFuelCardData.bundlePlateNumber) {

                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>您未填写车牌号</span><br/>";
                }

                if (errorMessage.length > 0) {
                    $("#popAddErrorDiv").html(errorMessage);
                    return;
                }

                var fuelCardManagerPopToPopIndex = layer.open({
                    type: 1,
                    title: '添加油卡',
                    skin: 'layui-layer-molv',
                    offset: 'auto',
                    id: 'fuelCardManagerPopToPop',
                    area: ["auto", 'auto'],
                    content: "确定添加油卡吗?",
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    shade: 0,
                    cancel: function () {
                        layer.close(fuelCardManagerPopToPopIndex);
                    },
                    btn2: function () {
                        layer.close(fuelCardManagerPopToPopIndex);
                    },
                    yes: function () {
                        layer.close(fuelCardManagerPopToPopIndex);
                        $http.post("/fuelCardManager/addAFuelCard", popAddFuelCardData).success(function (response) {
                            if ("添加成功" == response.result) {
                                var popAddSuccessIndex = layer.open({
                                    type: 0,
                                    id: 'popAddFuelCard-1',
                                    tipsMore: true,
                                    content: response.result,
                                    yes: function () {
                                        location.href = "/fuelCardManager/fuelCardManager";
                                    },
                                    cancel: function () {
                                        location.href = "/fuelCardManager/fuelCardManager";
                                    }
                                });
                            } else {
                                var popAddFailIndex = layer.open({
                                    type: 0,
                                    id: 'popAddFuelCard-2',
                                    tipsMore: true,
                                    content: response.result,
                                    yes: function () {
                                        layer.close(popAddFailIndex);
                                    }
                                });
                            }
                        });
                    }
                })

            },
            btn2: function () {
                $("#pop-add-viceCardNumber").attr("disabled", null);
                $("#pop-add-masterCardNumber").val("");
                $("#pop-add-viceCardNumber").val("");
                $("#pop-add-cardMerchant").val("all");
                $("#pop-add-password").val("");
                $("#pop-add-cardType").val("all");
                $("#pop-add-masterCardNumber").attr("placeholder", null);
                $("#pop-add-viceCardNumber").attr("placeholder", null);
                $scope.bundleCarNumber = null;
                $scope.carNumberList = null;
                $("#card-add-fuelCard").css("display", "none");

                $("#company").children().remove();
                $("#carKind").children().remove();
                $("#masterCar").children().remove();
                $("#password").children().remove();


                layer.closeAll();
            }
            ,
            cancel: function () {
                $("#pop-add-viceCardNumber").attr("disabled", null);
                $("#pop-add-masterCardNumber").val("");
                $("#pop-add-viceCardNumber").val("");
                $("#pop-add-cardMerchant").val("all");
                $("#pop-add-password").val("");
                $("#pop-add-cardType").val("all");
                $("#pop-add-masterCardNumber").attr("placeholder", null);
                $("#pop-add-viceCardNumber").attr("placeholder", null);
                $scope.bundleCarNumber = null;
                $scope.carNumberList = null;

                $("#company").children().remove();
                $("#carKind").children().remove();
                $("#masterCar").children().remove();
                $("#password").children().remove();


                $("#card-add-fuelCard").css("display", "none");
                layer.closeAll();
            }
        });
    }
    $scope.fuelCardDetail = function (tag) {
        location.href = "/fuelCardManager/fuelCardDetail/" + tag.masterCardNumber + "/" + ((tag.viceCardNumber == 0 || tag.viceCardNumber == "") ? 0 : tag.viceCardNumber) + "/" + tag.cardMerchant + "/" + tag.bundlePlateNumber;
    }
    $scope.modifyCard = function (tag) {
        layer.open({
            type: 1,
            title: '修改油卡',
            skin: 'layui-layer-molv',
            offset: 'auto',
            id: 'fuelCardManagerModifyPop',
            area: ["600px", '600px'],
            content: $("#card-modify-fuelCard"),
            btn: ['确定', '取消'],
            btnAlign: 'c',
            shade: 0,
            cancel: function () {
                $("#card-modify-fuelCard").css("display", "none");
                layer.closeAll();
            },
            success: function () {
                $("#pop-modify-viceCardNumber").val("");
                if (tag.cardType == 0) {
                    $("#pop-modify-viceCardNumber_parent").css("visibility", "hidden");
                }
                if (tag.cardType == 1) {
                    $("#pop-modify-viceCardNumber_parent").css("visibility", "visible");
                }
                $("#pop-modify-cardMerchant").val(tag.cardMerchant);
                $("#pop-modify-cardType").val(tag.cardType);
                $("#pop-modify-masterCardNumber").val(tag.masterCardNumber);
                $("#pop-modify-viceCardNumber").val(tag.viceCardNumber);
                $("#pop-modify-password").val(tag.password);

            },
            yes: function () {
                var perModifyPassword = $("#pop-modify-password").val();
                var perModifyMasterCardNumber = $("#pop-modify-masterCardNumber").val();
                var perModifyViceCardNumber = $("#pop-modify-viceCardNumber").val();
                var perModifyCardMerchant = $("#pop-modify-cardMerchant").val();
                var perModifyCardType = $("#pop-modify-cardType").val();
                var errorMessage = "";
                if ("all" == perModifyCardMerchant) {
                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>请选择油卡商家</span><br/><br/>";
                }

                if ("all" == perModifyCardType) {
                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>请选择油卡类型</span><br/><br/>";
                }
                if (null == perModifyMasterCardNumber || "" == perModifyMasterCardNumber) {
                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>请填写主卡号</span><br/><br/>";
                } else if (!(/^\d+\d+\d$/.test(perModifyMasterCardNumber))) {
                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'> 主卡号只能为纯数字</span><br/><br/>";
                }
                if ((null == perModifyViceCardNumber || "" == perModifyViceCardNumber) && perModifyCardType == '1') {
                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>请填写副卡号</span><br/><br/>";
                } else if (perModifyCardType == '1' && !(/^\d+\d+\d$/.test(perModifyViceCardNumber))) {
                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'> 副卡号只能为纯数字</span><br/><br/>";
                }
                if ("" == perModifyPassword || null == perModifyPassword) {
                    errorMessage += "<span style='padding-bottom: 4px;display: inline-block;'>请填写查询密码</span><br/><br/>";
                }
                $("#popModifyErrorDiv").html(errorMessage);
                console.log("错误信息是:" + errorMessage + "," + perModifyPassword);
                if (errorMessage != "") {
                    console.log("有字段没有填写");
                    return;
                }
                console.log("获取到的卡类型:" + (perModifyCardType == '0'));
                if (perModifyCardType == '0') {
                    perModifyViceCardNumber = "";
                }
                var popModifyFuelCardData = {
                    masterCardNumber: perModifyMasterCardNumber,
                    viceCardNumber: perModifyViceCardNumber,
                    cardMerchant: perModifyCardMerchant,
                    uuid: tag.uuid,
                    password: perModifyPassword,
                    cardType: perModifyCardType
                };


                var modifyCheckPopIndex = layer.open({
                    type: 1,
                    title: '修改油卡',
                    skin: 'layui-layer-molv',
                    offset: 'auto',
                    id: 'fuelCardManagerPopToPopModify',
                    area: ["auto", 'auto'],
                    content: "确定修改吗?",
                    btn: ['确定', '取消'],
                    btnAlign: 'c',
                    shade: 0,
                    cancel: function () {
                        layer.close(modifyCheckPopIndex);
                    },
                    btn2: function () {
                        layer.close(modifyCheckPopIndex);
                    },
                    yes: function () {
                        $http.post("/fuelCardManager/modifyAFuelCard", popModifyFuelCardData).success(function (response) {
                            layer.close(modifyCheckPopIndex);
                            var modifyCheckPopPopIndex = layer.open({
                                    type: 1,
                                    title: '修改油卡',
                                    skin: 'layui-layer-molv',
                                    offset: 'auto',
                                    id: 'fuelCardManagerPopToPopModify2',
                                    area: ["auto", 'auto'],
                                    content:
                                        '修改成功',
                                    btn:
                                        ['确定'],
                                    btnAlign:
                                        'c',
                                    shade:
                                        0,
                                    cancel:

                                        function () {
                                            layer.closeAll();
                                            location.href = "/fuelCardManager/fuelCardManager";
                                        }

                                    ,
                                    yes: function () {
                                        layer.closeAll();
                                        location.href = "/fuelCardManager/fuelCardManager";
                                    }
                                })
                            ;
                        })
                        ;
                    }
                })

            },
            btn2: function () {
                $("#card-modify-fuelCard").css("display", "none");
                layer.closeAll();
            }
        });

    }
}]);

function selectCardType(tag) {
    $("#pop-add-viceCardNumber").val("");
    $("#pop-add-masterCardNumber").val("");

    var cardTypeOption = $(tag).val();
    console.log("select值为:" + cardTypeOption);
    if (cardTypeOption == '0') {
        $("#pop-add-masterCardNumber").attr("placeholder", null);
        $("#pop-add-viceCardNumber").attr("disabled", "disabled");
        $("#pop-add-viceCardNumber").css("visibility", "hidden");
        $("#pop-add-viceCardNumber_parent").css("visibility", "hidden");
    } else if (cardTypeOption == '1') {
        $("#pop-add-viceCardNumber").val("");
        $("#pop-add-viceCardNumber").attr("disabled", null);
        $("#pop-add-viceCardNumber_parent").css("visibility", "visible");
        $("#pop-add-viceCardNumber").css("visibility", "visible");
    } else {
        $("#pop-add-masterCardNumber").attr("placeholder", null);
        $("#pop-add-viceCardNumber").attr("placeholder", null);

        $("#pop-add-viceCardNumber").attr("disabled", null);
        $("#pop-add-viceCardNumber_parent").css("visibility", "visible");
        $("#pop-add-viceCardNumber").css("visibility", "visible");
    }
}

function selectCardTypeModify(tag) {
    var cardTypeOption = $(tag).val();
    console.log("select值为:" + cardTypeOption);
    if (cardTypeOption == '0') {
        $("#pop-modify-viceCardNumber").css("visibility", "hidden");
        $("#pop-modify-viceCardNumber_parent").css("visibility", "hidden");
    } else if (cardTypeOption == '1') {

        $("#pop-modify-viceCardNumber_parent").css("visibility", "visible");
        $("#pop-modify-viceCardNumber").css("visibility", "visible");
    } else {

        $("#pop-modify-viceCardNumber_parent").css("visibility", "visible");
        $("#pop-modify-viceCardNumber").css("visibility", "visible");
    }
}
