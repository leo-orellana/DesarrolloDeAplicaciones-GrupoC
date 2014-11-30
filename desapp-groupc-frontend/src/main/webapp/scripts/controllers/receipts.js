$rest = "http://localhost:8081/backend/rest/";


function ReceiptControllerList($scope, $http) {
	$http.get($rest + "receiptService/receipts")
			.success(function(response) {
				$scope.receipts = response;
			}).error(function() {
				console.log("error");
			});
}


