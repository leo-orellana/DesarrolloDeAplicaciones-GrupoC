function SubcategoryControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend/rest/subcategoryService/subcategories")
			.success(function(response) {
				$scope.subcategories = response;
			}).error(function() {
				console.log("error");
			});
}

function SubcategoryControllerNew($scope, $http, $location, alert){
	$http.get("http://localhost:8081/backend/rest/categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			})
			.error(function() {
				console.log("error");
			});	
	$scope.title = 'New Sub - Category';
	$scope.submit = function(form){
		$http.get("http://localhost:8081/backend/rest/subcategoryService/save/"+$scope.nameSubcategory+"/"+$scope.idCategory)
			.success(function(response){
				alert("The Subcategory <" + response.name + "> was created successfully")
				.then(function(){
						$location.path('/subcategories');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
}

function SubcategoryControllerDelete($scope, $http, $routeParams, $location, alert) {
	$http.get("http://localhost:8081/backend/rest/subcategoryService/delete/"+$routeParams.subcategoryId)
	.success(function(response){
		alert("The sub category <" + response.name + "> was deleted successfully")
		.then(function(){
			$location.path('/subcategories');
		});
	}).error(function(){
		console.log("error");
	});
}

function SubcategoryControllerEdit($scope, $http, $routeParams, $location, alert){
	$http.get("http://localhost:8081/backend/rest/categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			})
			.error(function() {
				console.log("error");
			});	
	$http.get("http://localhost:8081/backend/rest/subcategoryService/subcategory/"+$routeParams.subcategoryId)
	.success(function(response) {
		$scope.idCategory = response.category.id;
		$scope.nameSubcategory = response.name;
	})
	.error(function() {
		console.log("error");
	});	
	$scope.title = 'Edit Sub - Category';
	$scope.submit = function(form){
		$http.get("http://localhost:8081/backend/rest/subcategoryService/update/"+$routeParams.subcategoryId+"/"+$scope.nameSubcategory+"/"+$scope.idCategory)
			.success(function(response){
				alert("The Subcategory <" + response.name + "> was updated successfully")
				.then(function(){
						$location.path('/subcategories');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
}
