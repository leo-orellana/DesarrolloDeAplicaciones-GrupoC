$rest = "http://localhost:8081/backend/rest/";


function ReceiptControllerList($scope, $http, $timeout) {
	$http.get($rest + "receiptService/receipts")
			.success(function(response) {
				$scope.receipts = response;
			}).error(function() {
				console.log("error");
			});
	
	/*PAGINATION*/
	$timeout(function () {
	    $scope.filteredReceipts = [];
	    $scope.currentPage = 1;
	    $scope.itemsPerPage = 4;
	    $scope.changeItemsPerPage = function(value){
	    	$scope.itemsPerPage = value;
	    }
	    
	    $scope.totalItems = function(){
	    	return $scope.receipts.length;
	    }
	    
	    $scope.numPages = function () {
	        return Math.ceil($scope.receipts.length / $scope.itemsPerPage);
	    };
	    
	    $scope.$watch('currentPage + itemsPerPage', function() {
	        var begin = (($scope.currentPage - 1) * $scope.itemsPerPage);
	        var end = begin + $scope.itemsPerPage; 
	        $scope.filteredReceipts = $scope.receipts.slice(begin, end);
	    });
	}, 1000);
}

function ReceiptControllerNew($scope, $http, $modal, alert, $location){
	$scope.title = 'New Receipt';
	$scope.editMode = false;
	
	$http.get($rest + "supplierService/suppliers")
	.success(function(response) {
		$scope.suppliers = response;
	}).error(function() {
		console.log("error");
	});
	
	$http.get($rest + "typeReceiptService/receipts")
	.success(function(response) {
		$scope.typeReceipts = response;
	}).error(function() {
		console.log("error");
	});
	
	$scope.supplierSelectedCode = function(selected) {
		$scope.supplier = selected.originalObject;
	};
	
	$scope.supplierSelectedName = function(selected) {
		$scope.supplier = selected.originalObject;
	};
	
	$scope.today = function() {
	    $scope.date = new Date();
	};
	
	$scope.today();

	$scope.clear = function () {
		$scope.date = null;
	};
	
	// Disable weekend selection
	$scope.disabled = function(date, mode) {
		return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	};

	$scope.toggleMin = function() {
		$scope.minDate = $scope.minDate ? null : new Date();
	};
	
	$scope.toggleMin();

	$scope.open = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	};

	$scope.dateOptions = {
			formatYear: 'yy',
			startingDay: 1	
	};

	$scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate', 'yyyy-MM-dd'];
	$scope.format = $scope.formats[0];
	  
	var types = ["B", "C", "X"];
		  
	$scope.changeTypeReceipt = function(){
		$scope.isTypeA = !isInArray(types, $scope.typeReceipt.name);
	}
	  
	$scope.addNewSupplier = function () {
		var modalInstance = $modal.open({
		templateUrl: 'views/newSupplier.html',
		backdrop: true,
		scope: $scope,
		controller: 'NewSupplierController'
		});
	};
	
	$scope.times = ["Morning", "Afternoon", "Night"];
	
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
    
    $scope.taxed = 0;
    $scope.untaxed = 0;
    $scope.iva = 0;
    
    $scope.submit = function(form){
    	console.log(($scope.date).getFullYear() + '-' + (($scope.date).getMonth() + 1) + '-' + ($scope.date).getDate());
    	var dateString = ($scope.date).getFullYear() + '-' + (($scope.date).getMonth() + 1) + '-' + ($scope.date).getDate();
    	
		if (typeof($scope.idBankOperation) == 'undefined'){
			$scope.idBankOperation = 0;
		}
		console.log($rest + "receiptService/save/" + dateString + "/"
				+ $scope.typeReceipt.id + "/" + $scope.supplier.id + "/"
				+ $scope.concept + "/" + $scope.totalBill + "/"
				+ $scope.taxed + "/" + $scope.untaxed + "/" + $scope.iva + "/"
				+ $scope.idSubcategory + "/" + $scope.time + "/"
				+ $scope.numberOperation + "/" + $scope.idAccount + "/"
				+ $scope.idBankOperation);
		$http.get(
				$rest + "receiptService/save/" + dateString + "/"
						+ $scope.typeReceipt.id + "/" + $scope.supplier.id + "/"
						+ $scope.concept + "/" + $scope.totalBill + "/"
						+ $scope.taxed + "/" + $scope.untaxed + "/" + $scope.iva + "/"
						+ $scope.idSubcategory + "/" + $scope.time + "/"
						+ $scope.numberOperation + "/" + $scope.idAccount + "/"
						+ $scope.idBankOperation)
			.success(function(response){
				alert("The receipt <" + response.concept + "> was created successfully")
				.then(function(){
						$location.path('/receipts');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
}

function NewSupplierController($scope, $http, alert, $modalInstance, $location, $route){
	$scope.submit = function(form){
		$http.get($rest + 'supplierService/save/' + $scope.supplierCode + '/' + $scope.supplierCompanyName + '/' + $scope.supplierCuit)
		.success(function(response) {
			alert("The supplier <" + response.companyName + "> was created")
			.then(function(){
				$modalInstance.close();
				$location.path('/newReceipt')
				$route.reload();
			});
			
		}).error(function() {
			console.log("error");
		});
	};
}

function isInArray(array, search) {
	return array.indexOf(search) >= 0;
}