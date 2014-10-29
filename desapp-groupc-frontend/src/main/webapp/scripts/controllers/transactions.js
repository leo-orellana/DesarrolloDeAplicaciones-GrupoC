function TransactionControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend/rest/transactionService/transactions")
			.success(function(response) {
				$scope.transactions = response;
			}).error(function() {
				console.log("error");
			});
	$http.get("http://localhost:8081/backend/rest/accountManagerService/accountManager")
	.success(function(response) {
		$scope.accountManager = response;
	}).error(function() {
		console.log("error");
	});
}