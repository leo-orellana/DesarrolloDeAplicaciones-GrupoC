
$rest = "http://localhost:8081/backend/rest/";

function SubcategoryControllerList($scope, $http, subcategoryService, $location, $route, $modal) {
	$http.get($rest + "subcategoryService/subcategories")
			.success(function(response) {
				$scope.subcategories = response;
			}).error(function() {
				console.log("error");
			});
	
	$scope.subcategoryNotInUse = true;
	
	$scope.tryDelete = function(subcategoryId){
		$scope.idToDelete = subcategoryId;
		$scope.unique($scope.idToDelete);
	};
	
	$scope.unique = function(id){
		if (typeof(id) == 'undefined'){
			return;
		}else{
			subcategoryService.checkSubCategoryNotInUse(id)
			.then($scope.updateSubCategoryNotInUse)
			.then($scope.deleteSubCategory);
		}
	};
	
	$scope.updateSubCategoryNotInUse = function(result){
		$scope.subcategoryNotInUse = result === 'true';
	};
	
	$scope.deleteSubCategory = function(){
		if($scope.subcategoryNotInUse){
			$http.get($rest + "subcategoryService/delete/"+$scope.idToDelete)
			.success(function(data, status, headers, config){
				$scope.showMessage("The category <strong>" + data.name + "</strong> <br> was deleted successfully!!", '/subcategories');
			}).error(function(data, status, headers, config){
				console.log(error);
			});
		}else{
			$http.get($rest + "subcategoryService/subcategory/"+$scope.idToDelete)
			.success(function(data, status, headers, config){
				$scope.showMessage("The category <strong>" + data.name + "</strong> <br> is already in use and and can not be removed", '/subcategories');
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

function SubcategoryControllerNew($scope, $http, $location, alert, $route, $modal, subcategoryService){
	
	$http.get($rest + "movementService/movements")
	.success(function(response) {
		$scope.movements = response;
	})
	.error(function() {
		console.log("error");
	});
	
	$scope.changeCategories = function() {
		$http.get($rest + "categoryService/filterByMovement/" + $scope.idMovement)
		.success(function(response) {
			$scope.categories = response;
		})
		.error(function() {
			console.log("error");
		});
      };
	
    $scope.title = 'New Subcategory';
  	$scope.submitted = false;
  	$scope.nameNotInUse = true;
  	
  	$scope.submit = function(isValid){
  		$scope.valid = isValid;
  		$scope.submitted = true;
  		$scope.unique($scope.nameSubcategory);
  	};
  	
	$scope.saveSubCategory = function(){
		if ($scope.valid){
			if ($scope.nameNotInUse){
				$http.get($rest + "subcategoryService/save/"+$scope.nameSubcategory+"/"+$scope.idCategory)
				.success(function(response){
					$scope.showMessage("The SubCategory <strong>" + response.name + "</strong> <br> was created successfully!!", '/subcategories');
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
			subcategoryService.checkNameNotInUse(name)
			.then($scope.updateNameExists)
			.then($scope.saveSubCategory);
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

function SubcategoryControllerDelete($scope, $http, $routeParams, $location, alert) {
	$http.get($rest + "subcategoryService/delete/"+$routeParams.subcategoryId)
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
	$http.get($rest + "categoryService/categories")
			.success(function(response) {
				$scope.categories = response;
			})
			.error(function() {
				console.log("error");
			});	
	$http.get($rest + "subcategoryService/subcategory/"+$routeParams.subcategoryId)
	.success(function(response) {
		$scope.idCategory = response.category.id;
		$scope.nameSubcategory = response.name;
	})
	.error(function() {
		console.log("error");
	});	
	$scope.title = 'Edit Sub - Category';
	$scope.submit = function(form){
		$http.get($rest + "subcategoryService/update/"+$routeParams.subcategoryId+"/"+$scope.nameSubcategory+"/"+$scope.idCategory)
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
