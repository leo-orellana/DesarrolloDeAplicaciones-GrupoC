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
				'ngTouch', 'ngGrid' ]).config(function($routeProvider) {
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
