
$rest = "http://localhost:8081/backend/rest/";


function TransactionControllerList($scope, $http, $modal, $route, transactions) {
	
	$scope.transactions = transactions.data;
	
	$scope.exportTransactions = function(){
			listDict = 			[{
				a: 'Date',
				b: 'Category',
				c: 'Subcategory',
				d: 'Concept',
				e: 'Was_consolidated',
				f: 'Time',
				g: 'Cash_account_amount',
				h: 'Checking_account_amount',
				i: 'Bank_account_amount',
				j: 'total_cash',
				k: 'total_checking',
				l: 'available_bank',
				m: 'accrued_bank',
				n: 'Should_be_consolidated'
			}];
			for (var i = 0; i < $scope.transactions.length; i++) {
			t = $scope.transactions[i];
			dict = 
				{
				a: t.date,
				b: t.subcategory.category.name,
				c: t.subcategory.name,
				d: t.concept,
				e: t.wasConsolidated,
				f: t.time,
				g: t.operationCashAccount.amount,
				h: t.operationCheckingAccount.amount,
				i: t.operationBankAccount.amount,
				j: t.amountOfCashAccount,
				k: t.amountOfCheckingAccount,
				l: t.amountAvailableBank,
				m: t.amountAccruedBank,
				n: t.shouldBeConsolidated
				};
			listDict.push(dict);
			}
			return listDict;
	}
	
	$scope.viewReceipt = function (receipt) {
		$scope.receipt = receipt;
		
        $modal.open({
            templateUrl: 'views/receipt.html',
            backdrop: true,
            scope: $scope,
            controller: 'ReceiptController'
        });
    }
	
	
	/*PAGINATION*/
    $scope.filteredTransactions = [];
    $scope.currentPage = 1;
    $scope.itemsPerPage = 4;
    $scope.changeItemsPerPage = function(value){
    	$scope.itemsPerPage = value;
    }
    
    $scope.totalItems = function(){
    	return $scope.transactions.length;
    }
    
    $scope.numPages = function () {
        return Math.ceil($scope.transactions.length / $scope.itemsPerPage);
    };
    
    $scope.$watch('currentPage + itemsPerPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.itemsPerPage);
        var end = begin + $scope.itemsPerPage; 
        $scope.filteredTransactions = $scope.transactions.slice(begin, end);
    });
}

function TransactionControllerNew($scope, $http, $location, alert, focus){
	
	$scope.title = 'New transaction';
	$scope.times = ["Morning", "Afternoon", "Night"];
	$scope.editMode = false;
	
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
  				$scope.focusOnSelectBankOperation();
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
	
      $scope.focusOnSelectBankOperation = function(){
			focus('inputBankOperation');
      }
    
	$scope.submit = function(form){
		var dateString = ($scope.date).getFullYear() + '-' + (($scope.date).getMonth() + 1) + '-' + ($scope.date).getDate();
		
		if (typeof($scope.idBankOperation) == 'undefined'){
			$scope.idBankOperation = 0;
		}
		
		$http.get(
				$rest + "transactionService/save" + "/" + dateString + "/"
						+ $scope.idSubcategory + "/" + $scope.concept + "/"
						+ $scope.time + "/"
						+ $scope.idAccount + "/" + $scope.idBankOperation + "/" + $scope.amount)
			.success(function(response){
				alert("The transaction <" + response.concept + "> was created successfully")
				.then(function(){
						$location.path('/transactions');
					});
			})
			.error(function() {
				console.log("error");
		});
	};
	
	/*** <DATEPICKER> ***/
	$scope.today = function() {
	    $scope.date = new Date();
	};
	
	$scope.today();

	$scope.clear = function () {
		$scope.date = null;
	};

	$scope.open = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	};

	$scope.dateOptions = {
			formatYear: 'yyyy',
			startingDay: 1	
	};

	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate', 'yyyy-MM-dd'];
	$scope.format = $scope.formats[0];
	/*** </DATEPICKER> ***/
}

function TransactionControllerEdit($scope, $http, $routeParams, $location, alert) {
	$scope.title = 'Edit transaction';
	$scope.times = ["Morning", "Afternoon", "Night"];
	$scope.editMode = true;
	
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
	
	$scope.setBankProperties = function(transaction) {
		if(transaction.operationCashAccount.amount > 0) {
			$http.get($rest + "accountService/filterByName/Cash")
			.success(function(response){
				$scope.idAccount = response[0].id;
				$scope.amount = transaction.operationCashAccount.amount;
			})
			.error(function() {
	  			console.log("error");
	  		});
		};
		if(transaction.operationCheckingAccount.amount > 0) {
			$http.get($rest + "accountService/filterByName/Checking")
			.success(function(response){
				$scope.idAccount = response[0].id;
				$scope.amount = transaction.operationCheckingAccount.amount;
			})
			.error(function() {
	  			console.log("error");
	  		});
		};
		if(transaction.operationBankAccount.amount > 0) {
			$http.get($rest + "accountService/filterByName/Bank")
			.success(function(response){
				$scope.idAccount = response[0].id;
				$scope.amount = transaction.operationBankAccount.amount;
			})
			.error(function() {
	  			console.log("error");
	  		});
		};
		
  		$http.get($rest + "bankOperationService/operations")
  		.success(function(response) {
  			if($scope.idAccount == 3){
  				$scope.showBankOperation = true; 				
  			}
  			else{
  				$scope.showBankOperation = false;
  			}
  			$scope.bankOperations = response;
  			$scope.idBankOperation = transaction.operationBankAccount.bankOperation.id;
  		})
  		.error(function() {
  			console.log("error");
  		});
	};
  		
	
	$http.get($rest + "transactionService/transaction/"+$routeParams.transactionId)
	.success(function(response) {
		$scope.date = response.date;
		$scope.idCategory = response.subcategory.category.id;
		$scope.changeSubcategory();
		$scope.idSubcategory = response.subcategory.id;
		$scope.concept = response.concept;
		$scope.time = response.time;
		$scope.setBankProperties(response);
	})
	.error(function() {
		console.log("error");
	});	
		
	$scope.submit = function(form){
		$http.get($rest + "transactionService/update/" + $routeParams.transactionId + '/' + $scope.concept + "/" + $scope.time)
			.success(function(response){
				alert("The transaction <" + response.concept + "> was updated successfully")
					.then(function(){
						$location.path('/transactions');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
}

function ReceiptController($scope) {
	var types = ["B", "C", "X"];
	
	$scope.isTypeA = function(){
		return !isInArray(types, $scope.receipt.typeReceipt.name);
	}
}

function isInArray(array, search)
{
	console.log(array.indexOf(search) >= 0);
    return array.indexOf(search) >= 0;
}
