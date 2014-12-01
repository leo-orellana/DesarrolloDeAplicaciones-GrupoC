$rest = "http://localhost:8081/backend/rest/";


function ReceiptControllerList($scope, $http) {
	$http.get($rest + "receiptService/receipts")
			.success(function(response) {
				$scope.receipts = response;
			}).error(function() {
				console.log("error");
			});
}

function ReceiptControllerNew($scope, $http){
	$scope.title = 'New Receipt';
	$scope.isTypeA = function(){
		return false;
	}
	
	
	
	$scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  $scope.format = $scope.formats[0];
}

