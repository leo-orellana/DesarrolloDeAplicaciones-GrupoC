
$rest = "http://localhost:8081/backend/rest/";

function CategoryControllerList($scope, $http, $modal, $route, categoryService) {
	$http.get($rest + "categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			}).error(function() {
				console.log("error");
			});
	
	
	$scope.categoryNotInUse = true;
	
	$scope.tryDelete = function(categoryId){
		$scope.idToDelete = categoryId;
		$scope.unique($scope.idToDelete);
	};
	
	$scope.unique = function(id){
		if (typeof(id) == 'undefined'){
			return;
		}else{
			categoryService.checkCategoryNotInUse(id)
			.then($scope.updateCategoryNotInUse)
			.then($scope.deleteCategory);
		}
	};
	
	$scope.updateCategoryNotInUse = function(result){
		$scope.categoryNotInUse = result === 'true';
	};
	
	$scope.deleteCategory = function(){
		if($scope.categoryNotInUse){
			$http.get($rest + "categoryService/delete/"+$scope.idToDelete)
			.success(function(data, status, headers, config){
				$scope.showMessage("The category <strong>" + data.name + "</strong> <br> was deleted successfully!!", '/categories');
			}).error(function(data, status, headers, config){
				console.log(error);
			});
		}else{
			$http.get($rest + "categoryService/category/"+$scope.idToDelete)
			.success(function(data, status, headers, config){
				$scope.showMessage("The category <strong>" + data.name + "</strong> <br> is already in use and and can not be removed", '/categories');
			}).error(function(data, status, headers, config){
				console.log(error);
			});
		}
	}
	
	$scope.showMessage = function (message, path) {
		var modalInstance = $modal.open({
		template: 
			'<div class="row message-container">' +
			'<div class="col-md-12">' +
			'<div id="messageSuccess" class="alert alert-success" ng-show="message != null">' + message +  '</div>' +
			'<div class="pull-right"><button class="btn btn-primary" ng-click=close()>Close</button></div>' +
			'</div>' +
			'</div>',
		backdrop: 'static',
		keyboard: false,
		size: 'sm',
		controller: 'MessageController',
		resolve: {
	        locationPath: function () {
	          return path;
	        }
	      }
		});
		
		modalInstance.result.then(function(){
			$route.reload();
		});
		modalInstance.dismiss(function(){
			$route.reload();
		});
	};
}

function CategoryControllerNew($scope, $http, $location, alert, $modal, categoryService){
	$http.get($rest + "movementService/movements")
			.success(function(response) {
				$scope.movements = response;
			})
			.error(function() {
				console.log("error");
			});	
	
	$scope.title = 'New category';
	$scope.submitted = false;
	$scope.nameNotInUse = true;
	
	$scope.submit = function(isValid){
		$scope.valid = isValid;
		$scope.submitted = true;
		$scope.unique($scope.nameCategory);
	};
	
	$scope.saveCategory = function(){
		if ($scope.valid){
			if ($scope.nameNotInUse){
				$http.get($rest + "categoryService/save/"+$scope.nameCategory+"/"+$scope.idMovement)
					.success(function(response){
						$scope.showMessage("The category <strong>" + response.name + "</strong> <br> was created successfully!!", '/categories');
					})
					.error(function() {
						console.log("error");
				});
			}
		}
		return;
	}
	
	$scope.unique = function(name){
		if (typeof(name) == 'undefined'){
			return;
		}else{
			categoryService.checkNameNotInUse(name)
			.then($scope.updateNameExists)
			.then($scope.saveCategory);
		}
	};
	
	$scope.updateNameExists = function(result){
		$scope.nameNotInUse = result === 'true';
	};
	
	$scope.showMessage = function (message, path) {
		var modalInstance = $modal.open({
		template: 
			'<div class="row message-container">' +
			'<div class="col-md-12">' +
			'<div id="messageSuccess" class="alert alert-success" ng-show="message != null">' + message +  '</div>' +
			'<div class="pull-right"><button class="btn btn-primary" ng-click=close()>Close</button></div>' +
			'</div>' +
			'</div>',
		backdrop: 'static',
		keyboard: false,
		size: 'sm',
		controller: 'MessageController',
		resolve: {
	        locationPath: function () {
	          return path;
	        }
	      }
		});
		
		modalInstance.result.then(function(){
			$location.path(path);
		});
		modalInstance.dismiss(function(){
			$location.path(path);
		});
	};
}

function CategoryControllerEdit($scope, $http, $routeParams, $location, alert, $modal){
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
				$scope.showMessage("The category <strong>" + response.name + "</strong> <br> was edited successfully!!", '/categories');
			})
			.error(function() {
				console.log("error");
		});
	}
	
	$scope.showMessage = function (message, path) {
		var modalInstance = $modal.open({
		template: 
			'<div class="row message-container">' +
			'<div class="col-md-12">' +
			'<div id="messageSuccess" class="alert alert-success" ng-show="message != null">' + message +  '</div>' +
			'<div class="pull-right"><button class="btn btn-primary" ng-click=close()>Close</button></div>' +
			'</div>' +
			'</div>',
		backdrop: 'static',
		keyboard: false,
		size: 'sm',
		controller: 'MessageController',
		resolve: {
	        locationPath: function () {
	          return path;
	        }
	      }
		});
		
		modalInstance.result.then(function(){
			$location.path(path);
		});
		modalInstance.dismiss(function(){
			$location.path(path);
		});
	};
}
