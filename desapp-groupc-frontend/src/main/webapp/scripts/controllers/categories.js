function CategoryControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend/rest/categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			}).error(function() {
				console.log("error");
			});
}

function CategoryControllerNew($scope, $http){
	$scope.movs="1";
	
	$http.get("http://localhost:8081/backend/rest/movementService/movements")
			.success(function(response) {
				$scope.movements = response;
			}).error(function() {
				console.log("error");
			});		
}

function CategoryControllerEdit($scope, $http, $routeParams){
	$scope.movs="1";
	
	$http.get("http://localhost:8081/backend/rest/categoryService/category/" + $routeParams.categoryId)
		.success(function(response) {
			$scope.category = response;
			$http.get("http://localhost:8081/backend/rest/movementService/movements")
				.success(function(response){
					$scope.movements = response;
				}).error(function(){
					console.log("error");
				});
		}).error(function() {
			console.log("error");
		});			
}

function CategoryControllerDelete($scope, $http, $routeParams) {
			$http.get("http://localhost:8081/backend/rest/categoryService/delete/"+$routeParams.categoryId)
			.success(function(response){
				$scope.message = "The category <" + response.name + "> has been deleted";
			}).error(function(){
				console.log("error");
				$scope.message = "Error!";
			});
}
