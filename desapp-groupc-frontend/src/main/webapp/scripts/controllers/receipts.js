$rest = "http://localhost:8081/backend/rest/";


function ReceiptControllerList($scope, $http) {
	$http.get($rest + "receiptService/receipts")
			.success(function(response) {
				$scope.receipts = response;
			}).error(function() {
				console.log("error");
			});
}

//function ViewReceiptController($scope, $modal, $log) {
//
//	  $scope.open = function () {
//
//	    var modalInstance = $modal.open({
//	      templateUrl: 'receipt.html',
//	      controller: 'ReceiptController',
//	    });
//	  };
//	}
//
//function ReceiptController ($scope, $modalInstance) {
//
//	  $scope.ok = function () {
//	    $modalInstance.close($scope.selected.item);
//	  };
//
//	  $scope.cancel = function () {
//	    $modalInstance.dismiss('cancel');
//	  };
//	}

