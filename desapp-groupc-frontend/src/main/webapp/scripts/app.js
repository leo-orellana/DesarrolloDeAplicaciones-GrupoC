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
	.when('/deleteTransaction/:transactionId',{
		templateUrl : 'views/transactions.html',
		controller : 'TransactionControllerDelete'
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
