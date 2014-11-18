$rest = "http://localhost:8081/backend/rest/";

function AccountManagerControllerConsolidate($scope, $http, $location, alert) {
	$http.get($rest + "accountManagerService/consolidateAccount")
	.success(function(response){
		alert("Consolidation successful")
		.then(function(){
			$location.path('/transactions');
		});
	}).error(function(){
		console.log("error");
	});
}