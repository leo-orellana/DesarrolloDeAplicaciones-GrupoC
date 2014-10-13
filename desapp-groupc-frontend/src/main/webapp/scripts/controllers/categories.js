angular.module('app')
.controller('CategoryController', ['$scope', function($scope, $http, $log) {

	 $http.get("http://localhost:8081/backend/rest/categoryService/categories")
	 .success(function(data) {
	 $scope.users = data;
	 }).error(function() {
	 console.log("error");
	 });
	 
	 $scope.gridOptions = {
	 data : 'users'
	 };
	 }]);