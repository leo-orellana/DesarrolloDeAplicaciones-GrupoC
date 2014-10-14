function CategoryControllerList($scope, $http) {
	$http.get("http://localhost:8081/backend/rest/categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			}).error(function() {
				console.log("error");
			});
}

function CategoryControllerNew($scope, $http, $location, alert){
	$http.get("http://localhost:8081/backend/rest/movementService/movements")
			.success(function(response) {
				$scope.movements = response;
			})
			.error(function() {
				console.log("error");
			});	
	
	$scope.submit = function(form){
		$http.get("http://localhost:8081/backend/rest/categoryService/save/"+$scope.nameCategory+"/"+$scope.idMovement)
			.success(function(response){
				console.log("Exito!");
				alert("La categoría <" + response.name + "> se ha creado correctamente")
					.then(function(){
						$location.path('/categories');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
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
