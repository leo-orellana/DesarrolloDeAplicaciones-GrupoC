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
		controller : 'ReceiptControllerNew'
	})
	.when('/editReceipt/:receiptId', {
		templateUrl : 'views/editReceipt.html',
		controller : 'ReceiptControllerNew'
	})
	
	// statistics
	.when('/statistics', {
		templateUrl : 'views/statistics.html',
		controller : 'StatisticsController'
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
				$timeout(function(){
					document.getElementById("ex2_value").tabIndex = "4";
				}, 500);
				document.getElementById("datePicker").focus();
				$( "#datePicker").focus(function() {
					document.getElementById("ex2_value").tabIndex = "4";
				});
				$( "#shift" ).focus(function() {
					$("html, body").animate({ scrollTop: $(document).height() }, "slow");
				});
				
				$( "#datePicker" ).focus(function() {
					$("html, body").animate({ scrollTop: 0 }, "slow");
				});
			}else{
				$timeout(function(){
					document.getElementById("ex2_value").tabIndex = "4";
					document.getElementById("ex2_value").focus();
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
			}else{
				document.getElementById("shift").focus();
			}
        }
	}
});

