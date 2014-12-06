
$rest = "http://localhost:8081/backend/rest/";
function StatisticsController($scope, $http, categories) {
	
	$scope.categories = categories.data;
	
	$scope.drawExpensesByCategory =        function(){ 
							$http.get($rest + "statistics/expensesByCategory")
							.success(function(response) {
								$scope.showSelectCategories = false;
								// Create the data table.
								var data = new google.visualization.DataTable();
								data.addColumn('string', 'Category');
								data.addColumn('number', 'Amount');
								rows = []
								for(i=0;i<(Object.keys(response).length);i++){
									key = Object.keys(response)[i];
									value = response[Object.keys(response)[i]];
									rows.push([key, value]);
									data.addRow([key, value]);
								}
								// Set chart options
								var options = {'title':'Egress by categories ',
										'width':800,
										'height':800};
								
								// Instantiate and draw our chart, passing in some options.
								var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
								chart.draw(data, options);
							})
							.error(function() {
								console.log("error");
							});
						
	}
	
	$scope.drawIngressByShift =        function(){ 
		$http.get($rest + "statistics/ingressByShift")
		.success(function(response) {
			$scope.showSelectCategories = false;
			// Create the data table.
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Shift');
			data.addColumn('number', 'Amount');
			rows = []
			for(i=0;i<(Object.keys(response).length);i++){
				key = Object.keys(response)[i];
				value = response[Object.keys(response)[i]];
				rows.push([key, value]);
				data.addRow([key, value]);
			}
			// Set chart options
			var options = {'title':'Ingress by shifts ',
					'width':800,
					'height':800};
			
			// Instantiate and draw our chart, passing in some options.
			var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		})
		.error(function() {
			console.log("error");
		});
	
}
	
	$scope.drawEgressBySubcategoriesInCategoryOnChange=function(){ 
		$http.get($rest + "statistics/egressBySubcategoriesInCategory/" + $scope.idCategory)
		.success(function(response) {
			// Create the data table.
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Subcategory');
			data.addColumn('number', 'Amount');
			rows = []
			for(i=0;i<(Object.keys(response).length);i++){
				key = Object.keys(response)[i];
				value = response[Object.keys(response)[i]];
				rows.push([key, value]);
				data.addRow([key, value]);
			}
			// Set chart options
			var options = {'title':'Ingress by shifts ',
					'width':800,
					'height':800};
			
			// Instantiate and draw our chart, passing in some options.
			var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		})
		.error(function() {
			console.log("error");
		});
	}
	
	$scope.drawEgressBySubcategoriesInCategory =        function(){ 
		$scope.idCategory = 1;
		$scope.drawEgressBySubcategoriesInCategoryOnChange()
		$scope.showSelectCategories = true;
	}
}