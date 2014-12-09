$rest = "http://localhost:8081/backend/rest/";


function ReceiptControllerList($scope, $http, $timeout, $modal, receipts, alert, $location, $route, receiptService) {

	$scope.receipts = receipts.data;

	/*PAGINATION*/
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
    
    $scope.deleteReceipt = function(receiptId) {
    	$http.get($rest + "receiptService/delete/" + receiptId)
    	.success(function(response){
	    	$scope.showMessage("The receipt <strong>" + response.concept + "</strong> <br> was deleted successfully!!", '/receipts');
    	}).error(function(){
    		console.log("error");
    	});
    }
    
    $scope.newReceipt = function(){
    	$location.path('/newReceipt');
    }
	
	$scope.showMessage = function (message, path) {
		var modalInstance = $modal.open({
		template: 
			'<div class="row message-container">' +
			'<div class="col-md-12">' +
			'<div id="messageSuccess" class="alert alert-success" ng-show="message != null">' + message +  '</div>' +
			'<div class="pull-right"><button class="btn btn-primary" ng-click=close()>Close</button></div>' +
			'</div>' +
			'</div>',
		backdrop: 'static',
		keyboard: false,
		size: 'sm',
		controller: 'MessageController',
		resolve: {
	        locationPath: function () {
	          return path;
	        }
	      }
		});
		
		modalInstance.result.then(function(){
			$route.reload();
		});
		modalInstance.dismiss(function(){
			$route.reload();
		});
	};
}

function ReceiptControllerNew($scope, $http, $modal, alert, $location, focus, $route, $timeout, suppliers, supplierService, $location, categoryCompras){
	$scope.title = 'New Receipt';
	$scope.editMode = false;
	$scope.submitted = false;
	
	$scope.suppliers = suppliers.data;
	
	$scope.selectedSupplier = undefined;
	
	$http.get($rest + "typeReceiptService/receipts")
	.success(function(response) {
		$scope.typeReceipts = response;
	}).error(function() {
		console.log("error");
	});
	
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
	
	var types = ["B", "C", "X"];
		  
	$scope.changeTypeReceipt = function(){
		$scope.isTypeA = !isInArray(types, $scope.typeReceipt.name);
	}
	  
	$scope.addNewSupplier = function () {
		var modalInstance = $modal.open({
		templateUrl: 'views/newSupplier.html',
		backdrop: false,
		size: 'sm',
		controller: 'SupplierControllerNew'
		});
		
		modalInstance.result.then(function(message){
			if (message != "error"){
				$scope.message = message;
				$scope.updateSuppliers();
			}else{
				$scope.messageError = message;
			}
		});
	};
	
	$scope.updateSuppliers = function(){
		supplierService.getSuppliers()
		.then($scope.refreshSuppliersList);
	}
	
	$scope.refreshSuppliersList = function(result){
		$scope.suppliers = result;
	};
	
	$scope.times = ["Morning", "Afternoon", "Night"];
	
	$http.get($rest + "categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			})
			.error(function() {
				console.log("error");
			});
	
	$scope.idCategory = categoryCompras.data[0].id;
	console.log("IDCAT" + categoryCompras.data[0].id);

	$scope.changeSubcategory = function() {
		$http.get($rest + "subcategoryService/filterByCategory/" + $scope.idCategory)
		.success(function(response) {
			$scope.subcategories = response;
		})
		.error(function() {
			console.log("error");
		});
      };
	
	
	$scope.changeSubcategory();
	
	$http.get($rest + "accountService/accounts")
	.success(function(response) {
		$scope.accounts = response;
	})
	.error(function() {
		console.log("error");
	});
    
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
    
    $scope.taxed = 0;
    $scope.untaxed = 0;
    $scope.iva = 0;
    
    $scope.submit = function(isValid){
    	$scope.submitted = true;
    	
    	if (isValid){
	    	var dateString = ($scope.date).getFullYear() + '-' + (($scope.date).getMonth() + 1) + '-' + ($scope.date).getDate();
	    	
			if (typeof($scope.idBankOperation) == 'undefined'){
				$scope.idBankOperation = 0;
			}
			$http.get(
					$rest + "receiptService/save/" + dateString + "/"
							+ $scope.typeReceipt.id + "/" + $scope.selectedSupplier.id + "/"
							+ $scope.concept + "/" + $scope.totalBill + "/"
							+ $scope.taxed + "/" + $scope.untaxed + "/" + $scope.iva + "/"
							+ $scope.idSubcategory + "/" + $scope.time  + "/" + $scope.idAccount + "/"
							+ $scope.idBankOperation)
				.success(function(response){
//					alert("The receipt <" + response.concept + "> was created successfully")
//					.then(function(){
//							$location.path('/receipts');
//						});
					$scope.showMessage("The receipt <strong>" + response.concept + "</strong> <br> was created successfully!!", '/receipts');
				})
				.error(function() {
					console.log("error");
			});
    	}else{
    		return;
    	}
	}
    
    $scope.showMessage = function (message, path) {
		var modalInstance = $modal.open({
		template: 
			'<div class="row message-container">' +
			'<div class="col-md-12">' +
			'<div id="messageSuccess" class="alert alert-success" ng-show="message != null">' + message +  '</div>' +
			'<div class="pull-right"><button class="btn btn-primary" ng-click=close()>Close</button></div>' +
			'</div>' +
			'</div>',
		backdrop: 'static',
		keyboard: false,
		size: 'sm',
		controller: 'MessageController',
		resolve: {
	        locationPath: function () {
	          return path;
	        }
	      }
		});
		
		modalInstance.result.then(function(){
			$location.path(path);
		});
		modalInstance.dismiss(function(){
			$location.path(path);
		});
	};
}

function SupplierControllerNew($scope, $http, alert, $modalInstance, $location, $route, $timeout, $q, supplierService, $controller){
	$scope.submitted = false;
	$scope.codeInUse = false;
	$scope.submit = function(isValid){
		$scope.valid = isValid;
		$scope.submitted = true;
		$scope.unique($scope.supplierCode);
	};
	
	$scope.saveSupplier = function(){
		if ($scope.valid){
			if (!$scope.codeInUse){
				$http.get($rest + 'supplierService/save/' + $scope.supplierCode + '/' + $scope.supplierCompanyName + '/' + $scope.supplierCuit)
				.success(function(data, status, headers, config) {
					$modalInstance.close(data.code);
				}).error(function() {
					console.log("error");
				});
			}
		}
		return;
	}
	
	$scope.closemodal = function(){
		$modalInstance.close();
	}
	
	$scope.unique = function(code){
		if (typeof(code) == 'undefined'){
			return;
		}else{
			supplierService.checkCodeUnique(code)
			.then($scope.updateCodeExists)
			.then($scope.saveSupplier);
		}
	};
	
	$scope.updateCodeExists = function(result){
		$scope.codeInUse = result === 'true';
	};
}

function ReceiptControllerEdit($scope, $http, $modal, alert, $routeParams, $location, focus, $route, $timeout, suppliers, supplierService, $location, receipt, typeReceipts){
	$scope.title = 'Edit Receipt';
	$scope.editMode = true;
	$scope.submitted = false;
	$scope.times = ["Morning", "Afternoon", "Night"];
	
	/*** <DATEPICKER> ***/
	$scope.today = function() {
	    $scope.date = new Date();
	};
	
	//$scope.today();

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
	
	var types = ["B", "C", "X"];
	
	$scope.suppliers = suppliers.data;
	
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
	
	$scope.receipt = receipt.data;

	
	$http.get($rest + "supplierService/supplier/" + $scope.receipt.supplier.id)
	.success(function(response) {
		$scope.selectedSupplier = response;
	}).error(function() {
		console.log("error");
	});
	
	$scope.typeReceipts = typeReceipts.data;
	
	$scope.typeReceipt = $scope.typeReceipts[receipt.data.typeReceipt.id - 1];
	
	$scope.changeTypeReceipt = function(){
		if (typeof($scope.typeReceipt) != 'undefined'){
			$scope.isTypeA = !isInArray(types, $scope.typeReceipt.name);
		}
	}
	
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

    $http.get($rest + "transactionService/getByReceiptId/"+$routeParams.receiptId)
	.success(function(response) {
		$scope.transaction = response;
		$scope.idCategory = response.subcategory.category.id;
		$scope.changeSubcategory();
		$scope.idSubcategory = response.subcategory.id;
		$scope.time = response.time;
		$scope.setBankProperties(response);
		$scope.changeBankOperation();
	})
	.error(function() {
		console.log("error");
	});	
    
	$scope.date = new Date( $scope.receipt.date.replace( /(\d{4})-(\d{2})-(\d{2})/, "$1/$2/$3"));
    $scope.taxed = $scope.receipt.taxed;
    $scope.untaxed = $scope.receipt.untaxed;
    $scope.iva = $scope.receipt.iva;
    $scope.totalBill = $scope.receipt.totalBill;
	$scope.concept = $scope.receipt.concept;
	$scope.changeTypeReceipt();
	
	$scope.addNewSupplier = function () {
		var modalInstance = $modal.open({
		templateUrl: 'views/newSupplier.html',
		backdrop: false,
		size: 'sm',
		controller: 'SupplierControllerNew'
		});
		
		modalInstance.result.then(function(message){
			if (message != "error"){
				$scope.message = message;
				$scope.updateSuppliers();
			}else{
				$scope.messageError = message;
			}
		});
	};
	
	$scope.updateSuppliers = function(){
		supplierService.getSuppliers()
		.then($scope.refreshSuppliersList);
	}
	
	$scope.refreshSuppliersList = function(result){
		$scope.suppliers = result;
	};
    
    $scope.focusOnSelectBankOperation = function(){
		focus('inputBankOperation');
    }
    
    $scope.submit = function(isValid){
    	$scope.submitted = true;
    	if (isValid){
			$http.get(
					$rest + "receiptService/update/" + $scope.receipt.id + "/"
							+ $scope.selectedSupplier.id + "/"
							+ $scope.concept + "/" + $scope.time)
				.success(function(response){
					$scope.showMessage("The receipt <strong>" + response.concept + "</strong> <br> was edited successfully!!", '/receipts');
				})
				.error(function() {
					console.log("error");
			});
    	}else{
    		return;
    	}
	}
    
    $scope.showMessage = function (message, path) {
		var modalInstance = $modal.open({
		template: 
			'<div class="row message-container">' +
			'<div class="col-md-12">' +
			'<div id="messageSuccess" class="alert alert-success" ng-show="message != null">' + message +  '</div>' +
			'<div class="pull-right"><button class="btn btn-primary" ng-click=close()>Close</button></div>' +
			'</div>' +
			'</div>',
		backdrop: 'static',
		keyboard: false,
		size: 'sm',
		controller: 'MessageController',
		resolve: {
	        locationPath: function () {
	          return path;
	        }
	      }
		});
		
		modalInstance.result.then(function(){
			$location.path(path);
		});
		modalInstance.dismiss(function(){
			$location.path(path);
		});
	};
}


function isInArray(array, search) {
	return array.indexOf(search) >= 0;
}