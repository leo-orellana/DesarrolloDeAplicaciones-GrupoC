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
		[ 'ngAnimate', 'ngCookies', 'ngResource', 'ngRoute', 'ngSanitize',
				'ngTouch', 'ngGrid', 'ui.bootstrap' ]).config(function($routeProvider) {
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
		controller : 'TransactionControllerList'
	})
	.when('/newTransaction', {
		templateUrl : 'views/editTransaction.html',
		controller : 'TransactionControllerNew'
	})
	.when('/save/:date/:subcategoryId/:concept/:time/:numOperation/:accountId/:bankOperationId/:amount', {
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
		controller : 'ReceiptControllerList'
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
