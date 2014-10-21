function TransactionControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend/rest/transactionService/transactions")
			.success(function(response) {
				$scope.transactions = response;
			}).error(function() {
				console.log("error");
			});
}