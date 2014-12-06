
$rest = "http://localhost:8081/backend/rest/";
function StatisticsController($scope, $http) {
	$scope.drawExpensesByCategory =        function(){ 
							$http.get($rest + "statistics/expensesByCategory")
							.success(function(response) {
								// Create the data table.
								var data = new google.visualization.DataTable();
								data.addColumn('string', 'Category');
								data.addColumn('number', 'Expenses');
								rows = []
								for(i=0;i<(Object.keys(response).length);i++){
									key = Object.keys(response)[i];
									value = response[Object.keys(response)[i]];
									rows.push([key, value]);
									data.addRow([key, value]);
								}
								// Set chart options
								var options = {'title':'Expenditures by category ',
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
}