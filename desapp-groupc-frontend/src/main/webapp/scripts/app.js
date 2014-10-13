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
	.when('/categories', {
		templateUrl : 'views/categories.html',
		controller : 'CategoryControllerList'
	})
	.when('/editCategory/:categoryId', {
		templateUrl : 'views/editCategory.html'
		//controller : 'CategoryControllerEdit'
	})
	.when('/deleteCategory/:categoryId', {
		controller : 'DeleteCategoryController'
	})
	.otherwise({
		redirectTo : '/'
	});
});



//function CategoryController($scope, $http) {
//	$http.get("http://localhost:8081/backend/rest/categoryService/categories")
//			.success(function(response) {
//				$scope.categories = response;
//			}).error(function() {
//				console.log("error");
//			});
//}
