laydate.render({
    elem: '#reufelingTime1',//指定元素
    type: 'date'
});
laydate.render({
    elem: '#reufelingTime2',//指定元素
    type: 'date'
});

function forDownRefuelingRecordButtonClicked() {
    var city = $("#second-first-left-city select:nth-child(2)").val();
    var driverName = $("#second-first-left-driver input:nth-child(2)").val();
    var wareHouse = $("#second-first-left-storehouse select:nth-child(2)").val();
    var carNumber = $("#second-first-right-plate input:nth-child(2)").val();
    var startTime = $("#second-first-right-fuelingTime input:nth-child(2)").val();
    var endTime = $("#second-first-right-fuelingTime input:nth-child(3)").val();

    //js动态创建form 提交表单
    var hiddenForm = document.createElement("form");
    //一定要加入到body中！！
    document.body.appendChild(hiddenForm);
    hiddenForm.method = 'post';
    hiddenForm.action = '/fuelCardManager/downRecordForRefuelingRecordPageExcel';

    //创建隐藏表单
    var newElement1 = document.createElement("input");
    newElement1.setAttribute("name", " city");
    newElement1.setAttribute("type", "text");
    newElement1.setAttribute("value", city);
    hiddenForm.appendChild(newElement1);
    //创建隐藏表单
    var newElement2 = document.createElement("input");
    newElement2.setAttribute("name", " driverName");
    newElement2.setAttribute("type", "text");
    newElement2.setAttribute("value", driverName);
    hiddenForm.appendChild(newElement2);
    //创建隐藏表单
    var newElement3 = document.createElement("input");
    newElement3.setAttribute("name", " wareHouse");
    newElement3.setAttribute("type", "text");
    newElement3.setAttribute("value", wareHouse);
    hiddenForm.appendChild(newElement3);
    //创建隐藏表单
    var newElement4 = document.createElement("input");
    newElement4.setAttribute("name", " carNumber");
    newElement4.setAttribute("type", "text");
    newElement4.setAttribute("value", carNumber);
    hiddenForm.appendChild(newElement4);
    //创建隐藏表单
    var newElement5 = document.createElement("input");
    newElement5.setAttribute("name", " startTime");
    newElement5.setAttribute("type", "text");
    newElement5.setAttribute("value", startTime);
    hiddenForm.appendChild(newElement5);
    //创建隐藏表单
    var newElement6 = document.createElement("input");
    newElement6.setAttribute("name", " endTime");
    newElement6.setAttribute("type", "text");
    newElement6.setAttribute("value", endTime);
    hiddenForm.appendChild(newElement6);
    hiddenForm.style.display = 'none';
    hiddenForm.submit();

}

scriptApp.controller('refuelRecordCtr', ['$scope', '$http', function ($scope, $http) {


    $scope.vo = {};
    $scope.vm = {};


    $scope.pageOperation = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20]
    };
    var searchList = function () {
        console.log("這個函數執行了");
        $scope.vo.startIndex = ($scope.pageOperation.currentPage - 1) * $scope.pageOperation.itemsPerPage;
        $scope.vo.recordsNumber = $scope.pageOperation.itemsPerPage;
        $scope.vo.startTimeMilis = new Date($('#startTime').val()).getTime();
        $scope.vo.endTimeMilis = new Date($('#endTime').val()).getTime();
        console.log("参数是:" + $scope.vo.city + "," + $scope.vo.driverName + "," + $scope.vo.wareHouse + "," + $scope.vo.carNumber + "," + $scope.vo.startTime + "," + $scope.vo.endTime);
        $http.post('../fuelCardManager/getRefuelingRecordForRefuelingRecordPage', $scope.vo).success(function (response) {
            $scope.refuelingList = response.refuelingList;

            $scope.pageOperation.totalItems = response.refuelingNumber;
        })
    };
    $scope.searchClick = function () {
        searchList();
    };
    $scope.$watch('pageOperation.currentPage + pageOperation.itemsPerPage', searchList);


    // 置空|
    $scope.resetClick = function () {
        console.log("进入了清空函数");
        $scope.vo.city = '';
        $scope.vo.driverName = '';
        $scope.vo.wareHouse = '';
        $scope.vo.carNumber = '';
        $('#startTime').val('');
        $('#endTime').val('');
        $scope.searchClick();
    };

    // 导出
    $scope.exportClick = function () {
        forDownRefuelingRecordButtonClicked();
    }

    $scope.showImg = function (tag) {
        var img = "<img src='" + tag.$$watchers[0].last + "' />";
        layer.open({
            type: 0,
            title: '拖动或右下角拉伸',
            offset: 'auto',
            shade: false,
            id: "showImg",
            area: ["400px", "400px"],
            btn: "确定",
            resize: true,
            content: img, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

        });
    }
    var wcxCarNumber = $("#wcxCarNumber").val();
    console.log("wcxCarNumber:" + (wcxCarNumber == ""));
    if (wcxCarNumber != "") {
        $scope.vo.carNumber = wcxCarNumber;
    }
}]);