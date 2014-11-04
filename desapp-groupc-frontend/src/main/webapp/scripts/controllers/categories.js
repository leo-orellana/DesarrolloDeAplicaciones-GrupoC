
$rest = "http://localhost:8081/backend/rest/";

function CategoryControllerList($scope, $http) {
	$http.get($rest + "categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			}).error(function() {
				console.log("error");
			});
}

function CategoryControllerNew($scope, $http, $location, alert){
	$http.get($rest + "movementService/movements")
			.success(function(response) {
				$scope.movements = response;
			})
			.error(function() {
				console.log("error");
			});	
	$scope.title = 'New category';
	$scope.submit = function(form){
		$http.get($rest + "categoryService/save/"+$scope.nameCategory+"/"+$scope.idMovement)
			.success(function(response){
				alert("The category <" + response.name + "> was created successfully")
				.then(function(){
						$location.path('/categories');
					});
			})
			.error(function() {
				console.log("error");
		});
	}
}

function CategoryControllerEdit($scope, $http, $routeParams, $location, alert){
	$http.get($rest + "movementService/movements")
	.success(function(response) {
		$scope.movements = response;
	})
	.error(function() {
		console.log("error");
	});	
	$scope.title = 'Edit category';
	$http.get($rest + "categoryService/category/" + $routeParams.categoryId)
	.success(function(response) {
		$scope.category = response;
		$scope.nameCategory =  $scope.category.name;
		$scope.idMovement = $scope.category.movement.id;
	})
	.error(function() {
		console.log("error");
	});	
	
	
		
	$scope.submit = function(form){
		$http.get($rest + "categoryService/update/"+$routeParams.categoryId+'/'+$scope.nameCategory+"/"+$scope.idMovement)
			.success(function(response){
				alert("The category <" + response.name + "> was updated successfully")
					.then(function(){
						$location.path('/categories');
					});
			})
			.error(function() {
				console.log("error");
		});
	}		
}

function CategoryControllerDelete($scope, $http, $routeParams, $location, alert) {
			$http.get($rest + "categoryService/delete/"+$routeParams.categoryId)
			.success(function(response){
				alert("The category <" + response.name + "> was deleted successfully")
				.then(function(){
					$location.path('/categories');
				});
			}).error(function(){
				console.log("error");
			});
}
