
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
	
	$http.get($rest + "accountService/accounts")
	.success(function(response) {
		$scope.accounts = response;
	})
	.error(function() {
		console.log("error");
	});
	
	$scope.changeSubcategory = function() {
		$http.get($rest + "subcategoryService/filterByCategory/" + $scope.idCategory)
		.success(function(response) {
			$scope.subcategories = response;
		})
		.error(function() {
			console.log("error");
		});
      };
    
      $scope.changeBankOperation = function() {
  		$http.get($rest + "bankOperationService/operations")
  		.success(function(response) {
  			if($scope.idAccount == 3){
  				$scope.showBankOperation = true; 				
  			}
  			else{
  				$scope.showBankOperation = false;
  			}
  			$scope.bankOperations = response;
  		})
  		.error(function() {
  			console.log("error");
  		});
        };
	
	$scope.title = 'New transaction';
	$scope.times = ["Morning", "Afternoon", "Night"] 
		
	$scope.submit = function(form){
		$http.get(
				$rest + "transactionService/save" + "/" + $scope.date + "/"
						+ $scope.idSubcategory + "/" + $scope.concept + "/"
						+ $scope.time + "/" + $scope.numOperation + "/"
						+ $scope.idAccount + "/" + $scope.idBankOperation + "/" + $scope.amount)
			.success(function(response){
				alert("The transaction was created successfully")
				.then(function(){
						$location.path('/transactions');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
}