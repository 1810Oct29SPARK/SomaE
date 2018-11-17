// Asset chart static specifications
const $chartCanvas = $("#asset-chart");
const chartLabels = ["USD", "Cryptos", "Stocks"];
const chartColors = ["rgba(102, 81, 145, 0.7)", "rgba(249, 93, 106, 0.7)", "rgba(255, 166, 0, 0.7)"];
const chartBorderWidth = 1;
const chartType = "doughnut";
const chartOptions = {
	events: ["click", "mousemove"],
	animation: {
		animateRotate: true,
		animateScale: true
	}
};

// Asset chart data
var chartDataPoints = [10, 10, 10];
var chartDataSet = [
	{
		data: chartDataPoints,
		backgroundColor: chartColors,
		borderWidth: chartBorderWidth
	}
]
var chartAllData = {
	labels: chartLabels,
	datasets: chartDataSet
}
var chartAllInputs = {
	type: chartType,
	data: chartAllData,
	options: chartOptions
}

// Draw chart on canvas
const drawAssetChart = function() {
	new Chart($chartCanvas, chartAllInputs);
}

// Wait until the document is ready for JQuery
$(function() {
	//
});