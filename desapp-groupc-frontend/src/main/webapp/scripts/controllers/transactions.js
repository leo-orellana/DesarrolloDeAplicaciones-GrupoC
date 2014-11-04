
$rest = "http://localhost:8081/backend/rest/";


function TransactionControllerList($scope, $http) {
	$http.get($rest + "transactionService/transactions")
			.success(function(response) {
				$scope.transactions = response;
			}).error(function() {
				console.log("error");
			});
	$http.get($rest + "accountManagerService/accountManager")
	.success(function(response) {
		$scope.accountManager = response;
	}).error(function() {
		console.log("error");
	});
}

function TransactionControllerNew($scope, $http, $location, alert){
	$http.get($rest + "categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			})
			.error(function() {
				console.log("error");
			});	
	$http.get($rest + "categoryService/categories")
	.success(function(response) {
		$scope.categories = response;
	})
	.error(function() {
		console.log("error");
	});
	
	$scope.title = 'New transaction';
//	$scope.submit = function(form){
//		$http.get($rest + "categoryService/save/"+$scope.nameCategory+"/"+$scope.idMovement)
//			.success(function(response){
//				alert("The category <" + response.name + "> was created successfully")
//				.then(function(){
//						$location.path('/categories');
//					});
//			})
//			.error(function() {
//				console.log("error");
//		});
//	}
}