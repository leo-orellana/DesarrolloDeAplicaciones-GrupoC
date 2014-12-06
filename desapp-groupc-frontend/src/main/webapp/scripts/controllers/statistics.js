

function StatisticsController($scope, $http) {
	$scope.drawExpensesByCategory =        function(){ 
							// Create the data table.
						     var data = new google.visualization.DataTable();
						    data.addColumn('string', 'Category');
						    data.addColumn('number', 'Expenses');
						    data.addRows([
						      ['Tvs', 3000],
						      ['Planchas', 1000],
						      ['Sueldos', 6000]
						    ]);
						
						    // Set chart options
						    var options = {'title':'Expenditures by category ',
						                   'width':400,
						                   'height':300};
						
						    // Instantiate and draw our chart, passing in some options.
						    var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
						    chart.draw(data, options);
	}
}