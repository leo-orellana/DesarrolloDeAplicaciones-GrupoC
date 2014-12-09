'use strict';

/**
 * @ngdoc overview
 * @name app
 * @description # app
 * 
 * Main module of the application.
 */
var app = angular.module(
		'app',
		[ 'ngCsv', 'ngAnimate', 'ngCookies', 'ngResource', 'ngRoute', 'ngSanitize',
				'ngTouch', 'ngGrid', 'ui.bootstrap', 'angucomplete-alt' ]).config(function($routeProvider) {
	$routeProvider
	
	/*** CATEGORIES ***/
	.when('/categories', {
		templateUrl : 'views/categories.html',
		controller : 'CategoryControllerList'
	})
	.when('/deleteCategory/:categoryId', {
		templateUrl : 'views/categories.html',
		controller : 'CategoryControllerDelete'
	})
	.when('/newCategory', {
		templateUrl : 'views/editCategory.html',
		controller : 'CategoryControllerNew'
	})
	.when('/editCategory/:categoryId', {
		templateUrl : 'views/editCategory.html',
		controller : 'CategoryControllerEdit'
	})
	
	/*** SUBCATEGORIES ***/
	.when('/subcategories', {
		templateUrl : 'views/subcategories.html',
		controller : 'SubcategoryControllerList'
	})
	.when('/newSubcategory', {
		templateUrl : 'views/editSubcategory.html',
		controller : 'SubcategoryControllerNew'
	})
	.when('/deleteSubcategory/:subcategoryId', {
		templateUrl : 'views/subcategories.html',
		controller : 'SubcategoryControllerDelete'
	})
	.when('/editSubcategory/:subcategoryId', {
		templateUrl : 'views/editSubcategory.html',
		controller : 'SubcategoryControllerEdit'
	})
	
	// TRANSACTIONS
	.when('/transactions', {
		templateUrl : 'views/transactions.html',
		controller : 'TransactionControllerList',
		resolve: {
			transactions: function(sifeagService) {
				return sifeagService.getTransactions();
			}
		}
	})
	.when('/newTransaction', {
		templateUrl : 'views/editTransaction.html',
		controller : 'TransactionControllerNew'
	})
	.when('/save/:date/:subcategoryId/:concept/:time/:accountId/:bankOperationId/:amount', {
		templateUrl : 'views/editTransaction.html',
		controller : 'TransactionControllerNew'
	})
	.when('/editTransaction/:transactionId',{
		templateUrl : 'views/editTransaction.html',
		controller : 'TransactionControllerEdit'
	})
	
	// ACCOUNTMANAGER
	.when('/consolidate', {
		templateUrl : 'views/transactions.html',
		controller : 'AccountManagerControllerConsolidate'
	})
	
	// RECEIPTS
	.when('/receipts', {
		templateUrl : 'views/receipts.html',
		controller : 'ReceiptControllerList',
		resolve: {
			receipts: function(sifeagService) {
				return sifeagService.getReceipts();
			}
		}
	})
	.when('/newReceipt', {
		templateUrl : 'views/editReceipt.html',
		controller : 'ReceiptControllerNew',
		resolve: {
			suppliers: function(sifeagService) {
				return sifeagService.getSuppliers();
			},
			categoryCompras: function(sifeagService) {
				return sifeagService.getCategoryCompras();
			}
		}
	})
	.when('/editReceipt/:receiptId', {
		templateUrl : 'views/editReceipt.html',
		controller : 'ReceiptControllerEdit',
		resolve: {
			suppliers: function(sifeagService) {
				return sifeagService.getSuppliers();
			},
			receipt: function (sifeagService, $route) {
				var id = $route.current.params.receiptId;
				return sifeagService.getReceipt(id);
			},
			typeReceipts: function (sifeagService) {
				return sifeagService.getTypeReceipts();
			}
		}
	})
	
	// statistics
	.when('/statistics', {
		templateUrl : 'views/statistics.html',
		controller : 'StatisticsController',
		resolve: {
			categoriesEgress: function(sifeagService) {
				return sifeagService.getCategoriesEgress();
			},
			categoriesIngress: function(sifeagService) {
				return sifeagService.getCategoriesIngress();
			}
		}
	})
	
	.otherwise({
		redirectTo : '/'
	});
});

app.factory(
		"alert",
		function( $window, $q ) {
		 
		// Define promise-based alert() method.
		function alert( message ) {
		 
		var defer = $q.defer();
		 
		$window.alert( message );
		 
		defer.resolve();
		 
		return( defer.promise );
		 
		}
		 
		return( alert );
		 
		}
		);

app.factory('sifeagService', ['$http', function($http) {
	var rest = "http://localhost:8081/backend/rest/";
	
	var result = {
		getReceipts: function() {
			var promise = $http({ method: 'GET', url: rest + 'receiptService/receipts' }).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		},
	
		getTransactions: function() {
			var promise = $http({ method: 'GET', url: rest + 'transactionService/transactions' }).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		},
		
		getCategoriesEgress: function() {
			var promise = $http({ method: 'GET', url: rest + 'categoryService/categoriesEgress' }).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		},
		
		getCategoriesIngress: function() {
			var promise = $http({ method: 'GET', url: rest + 'categoryService/categoriesIngress' }).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		},
		
		getSuppliers: function() {
			var promise = $http({ method: 'GET', url: rest + 'supplierService/suppliers' }).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		},
		
		getReceipt: function(id) {
			var promise = $http({ method: 'GET', url: rest + 'receiptService/receipt/' + id}).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		},
		getTypeReceipts: function() {
			var promise = $http({ method: 'GET', url: rest + 'typeReceiptService/receipts'}).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		},
		getCategoryCompras: function() {
			var promise = $http({ method: 'GET', url: rest + 'categoryService/filterByName/Compras'}).success(function(data, status, headers, config) {
				return data;
			});
			return promise;
		}
	}
	return result;
}]);

app.factory('focus', function($timeout) {
    return function(id) {
        // timeout makes sure that is invoked after any other event has been triggered.
        // e.g. click events that need to run before the focus or
        // inputs elements that are in a disabled state but are enabled when those events
        // are triggered.
        $timeout(function() {
          var element = document.getElementById(id);
          if(element)
            element.focus();
        });
      };
    });

app.directive('eventFocus', function(focus) {
    return function(scope, elem, attr) {
      elem.on(attr.eventFocus, function() {
        focus(attr.eventFocusId);
      });

      // Removes bound events in the element itself
      // when the scope is destroyed
      scope.$on('$destroy', function() {
        element.off(attr.eventFocus);
      });
    };
  });

app.directive('receiptEdit', function($timeout){
	return {
		restrict: 'A',
		link: function (scope, element, attr) {
			if (!scope.editMode){
				document.getElementById("datePicker").focus();
				
				$("html, body").scrollTop(0);
				
//				$( "#datePicker" ).focus(function() {
//					$("html, body").animate({ scrollTop: 0 }, "slow");
//				})
				
				$('form').on("keyup keypress", function(e) {
					  var code = e.keyCode || e.which; 
					  if (code  == 13) {               
					    e.preventDefault();
					    return false;
					  }
				});
				
				$( "#totalBill" ).blur(function() {
					if(scope.isTypeA){
						var total = parseFloat($("#totalBill").val());
					
						if($.isNumeric(total)){
							var iva = (total * 21) / 100;
							var taxed = total - iva;
							var untaxed = 0;
							
							$("#taxed").val(taxed);
							$("#iva").val(iva);
							$("#untaxed").val(untaxed);
						}
					}
				});
				
			}else{
				$timeout(function(){
					$('form').on("keyup keypress", function(e) {
						  var code = e.keyCode || e.which; 
						  if (code  == 13) {               
						    e.preventDefault();
						    return false;
						  }
					});
				}, 500);
			}
        }
	}
});

app.directive('transactionEdit', function($timeout){
	return {
		restrict: 'A',
		link: function (scope, element, attr) {
			if (!scope.editMode){
				document.getElementById("datePicker").focus();
//				$('form').on("keyup keypress", function(e) {
//					  var code = e.keyCode || e.which; 
//					  if (code  == 13) {               
//					    e.preventDefault();
//					    return false;
//					  }
//				});
			}else{
				document.getElementById("shift").focus();
			}
//			$('form').on("keyup keypress", function(e) {
//				  var code = e.keyCode || e.which; 
//				  if (code  == 13) {               
//				    e.preventDefault();
//				    return false;
//				  }
//			});
        }
	}
});

app.directive('supplierEdit', function($timeout){
	return {
		restrict: 'A',
		link: 	function (scope, element, attr) {
					$timeout(function(){
						$("#code").focus();
					
						$("#code").focus(function() {
							scope.codeInUse = false;
						});
					}, 400);
//					$('form').on("keyup keypress", function(e) {
//						  var code = e.keyCode || e.which; 
//						  if (code  == 13) {               
//						    e.preventDefault();
//						    return false;
//						  }
//					});
				}
        	}
});

app.factory('supplierService', function ($timeout, $q, $http) {
	var _checkCodeUnique = function (code) {
		var deferred = $q.defer();
		$timeout(function () {
			var unique;
			$http.get($rest + 'supplierService/checkCodeUnique/' + code)
			.success(function(response) {
				deferred.resolve(response);
			}).error(function() {
				console.log("error");
				deferred.reject("error");
			});
		}, 250);
		return deferred.promise;
	};
	
	var _getSuppliers = function () {
		var deferred = $q.defer();
		$timeout(function () {
			var unique;
			$http.get($rest + 'supplierService/suppliers')
			.success(function(response) {
				deferred.resolve(response);
			}).error(function() {
				deferred.reject("error");
			});
		}, 250);
		return deferred.promise;
	};
	
	return {
		checkCodeUnique:_checkCodeUnique,
		getSuppliers:_getSuppliers,
	}
});

app.factory('receiptService', function ($timeout, $q, $http) {
	var _checkNameUnique = function (code) {
		var deferred = $q.defer();
		$timeout(function () {
			var unique;
			$http.get($rest + 'supplierService/checkCodeUnique/' + code)
			.success(function(response) {
				deferred.resolve(response);
			}).error(function() {
				console.log("error");
				deferred.reject("error");
			});
		}, 250);
		return deferred.promise;
	};
	
	var _getReceipts = function () {
		var deferred = $q.defer();
		$timeout(function () {
			var unique;
			$http.get($rest + 'receiptService/receipts')
			.success(function(response) {
				deferred.resolve(response);
			}).error(function() {
				deferred.reject("error");
			});
		}, 250);
		return deferred.promise;
	};
	
	return {
		checkNameUnique:_checkNameUnique,
		getReceipts:_getReceipts,
	}
});

app.factory('categoryService', function ($timeout, $q, $http) {
	var _checkNameNotInUse = function (name) {
		var deferred = $q.defer();
		$timeout(function () {
			var unique;
			$http.get($rest + 'categoryService/checkNameNotInUse/' + name)
			.success(function(response) {
				deferred.resolve(response);
			}).error(function() {
				console.log("error");
				deferred.reject("error");
			});
		}, 250);
		return deferred.promise;
	};
	
	var _checkCategoryNotInUse = function (id) {
		var deferred = $q.defer();
		$timeout(function () {
			var unique;
			$http.get($rest + 'subcategoryService/checkCategoryNotInUse/' + id)
			.success(function(response) {
				deferred.resolve(response);
			}).error(function() {
				console.log("error");
				deferred.reject("error");
			});
		}, 250);
		return deferred.promise;
	};
	
	return {
		checkNameNotInUse:_checkNameNotInUse,
		checkCategoryNotInUse:_checkCategoryNotInUse,
	}
});

function MessageController($scope, $http, $modalInstance, $location, locationPath){
	
	$scope.message = "Exito!";
	
	$scope.close = function(){
		$modalInstance.close();
		$location.path(locationPath);
	}
}

function MessageControllerReceipt($scope, $http, $modalInstance, $location, locationPath){
	
	$scope.message = "Exito!";
	
	$scope.close = function(){
		$modalInstance.close();
	}
}

