window.onload = function () {

    console.log("获取到的参数是:" + document.getElementById("masterCardNumber").value + "," + document.getElementById("viceCardNumber").value + "," + document.getElementById("cardMerchant").value + "," + document.getElementById("carNumber").value);
}
scriptApp.controller('fuelCardDetailCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.vo = {};
    $scope.pageOperation = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [5, 10, 20]
    };

    var searchList = function () {
        $scope.vo.startIndex = ($scope.pageOperation.currentPage - 1) * $scope.pageOperation.itemsPerPage;
        $scope.vo.recordsNumber = $scope.pageOperation.itemsPerPage;
        $scope.vo.masterCardNumber = document.getElementById("masterCardNumber").value;
        $scope.vo.viceCardNumber = document.getElementById("viceCardNumber").value;
        var searchListCardMerchant = document.getElementById("cardMerchant").value;
        $scope.vo.cardMerchant = ((searchListCardMerchant == '中石油') ? 0 : 1);
        $scope.vo.carNumber = document.getElementById("carNumber").value;

        $http.post('/fuelCardManager/fuelCardDetailRecord', $scope.vo).success(function (response) {

            $scope.refuelingList = response.refuelingList;

            $scope.pageOperation.totalItems = response.refuelingNumber;
        });
    };
    $scope.searchClick = function () {
        searchList();
    };
    $scope.$watch('pageOperation.currentPage + pageOperation.itemsPerPage', searchList);
    $("#masterCardNumber").attr("disabled", "disabled");
    $("#viceCardNumber").attr("disabled", "disabled");
    $("#cardMerchant").attr("disabled", "disabled");
    $("#carNumber").attr("disabled", "disabled");

}]);

