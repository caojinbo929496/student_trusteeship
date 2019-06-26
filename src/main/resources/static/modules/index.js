var layer = layui.layer;
var form = layui.form;
var laypage = layui.laypage;
var laydate = layui.laydate;
var $ = layui.jquery;
var element = layui.element;
var upload = layui.upload;
var scriptApp = angular.module('scriptApp', ['tm.pagination']);


/**金额校验*/
scriptApp.directive('priceFormat', function () {
    return {
        restrict: 'A',
        scope: {
            price: '='
        },
        link: function ($scope, element, attrs) {

            element.bind('keyup', function () {
                var regStrs = [
                    ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
                    ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
                    ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
                    ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
                ];
                for (var i = 0; i < regStrs.length; i++) {
                    var reg = new RegExp(regStrs[i][0]);
                    this.value = this.value.replace(reg, regStrs[i][1]);
                }
            });
            element.bind('blur', function () {
                var v = this.value;
                if (v === '') {
                    v = '0.00';
                } else if (v === '0') {
                    v = '0.00';
                } else if (v === '0.') {
                    v = '0.00';
                } else if (/^0+\d+\.?\d*.*$/.test(v)) {
                    v = v.replace(/^0+(\d+\.?\d*).*$/, '$1');
                    //v = inp.getRightPriceFormat(v).val;
                } else if (/^0\.\d$/.test(v)) {
                    v = v + '0';
                } else if (!/^\d+\.\d{2}$/.test(v)) {
                    if (/^\d+\.\d{2}.+/.test(v)) {
                        v = v.replace(/^(\d+\.\d{2}).*$/, '$1');
                    } else if (/^\d+$/.test(v)) {
                        v = v + '.00';
                    } else if (/^\d+\.$/.test(v)) {
                        v = v + '00';
                    } else if (/^\d+\.\d$/.test(v)) {
                        v = v + '0';
                    } else if (/^[^\d]+\d+\.?\d*$/.test(v)) {
                        v = v.replace(/^[^\d]+(\d+\.?\d*)$/, '$1');
                    } else if (/\d+/.test(v)) {
                        v = v.replace(/^[^\d]*(\d+\.?\d*).*$/, '$1');
                        //ty = false;
                    } else if (/^0+\d+\.?\d*$/.test(v)) {
                        v = v.replace(/^0+(\d+\.?\d*)$/, '$1');
                        //ty = false;
                    } else {
                        v = '0.00';
                    }
                }
                this.value = v;
            });
        }
    }
});

/**
 * 控制输入最大值
 */
scriptApp.directive('numberMax', function () {
    return {
        restrict: 'AE',
        require: 'ngModel',
        link: function (scope, element, attr, linkfn) {
            var numberMax = Number(attr.numberMax);
            scope.$watch(attr.ngModel, function (newValue, oldValue) {
                if (!newValue) {
                    return;
                }
                if (angular.isNumber(numberMax) && !isNaN(numberMax)) {
                    if (newValue > numberMax) {
                        var exp = attr.ngModel + '=' + oldValue;
                        element.val(oldValue);
                        scope.$eval(exp);
                    }
                }
            });
        }
    }
});
/**
 * 控制输入最小值
 */
scriptApp.directive('numberMin', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attr, linkfn) {
            var numberMin = Number(attr.numberMin);
            scope.$watch(attr.ngModel, function (newValue, oldValue) {
                if (!newValue) {
                    return;
                }
                if (angular.isNumber(numberMin) && !isNaN(numberMin)) {
                    if (oldValue == '-') {
                        var exp = attr.ngModel + '=' + undefined;
                        element.val(oldValue);
                        scope.$eval(exp);
                    } else if (newValue < numberMin) {
                        var exp = attr.ngModel + '=' + oldValue;
                        element.val(oldValue);
                        scope.$eval(exp);
                    }
                }
            });
        }
    }
});

$(document).ready(function () {
    $(window).keyup(function (e) {
        if (e.keyCode === 27) {//此处代表按的是键盘的Esc键
            layer.closeAll();
        }
    });
});


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

