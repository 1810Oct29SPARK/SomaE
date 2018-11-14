// API End Point List
// Cryptocurrency Aggregate Data
const cryptoEndPoint = "https://api.iextrading.com/1.0/stock/market/crypto";
// Sector Performance Data
const sectorEndPoint = "https://api.iextrading.com/1.0/stock/market/sector-performance";
// Gainers & Losers Data
const gainersEndPoint = "https://api.iextrading.com/1.0/stock/market/list/gainers";
const loserEndPoint = "https://api.iextrading.com/1.0/stock/market/list/losers";

// Fetch Options
const fetchOptionsDefault = {
	method: "GET",
	headers: {Accept: "application/json"}
};

// URL Constructors
const makeStockQuoteUrl = function(symbol) {
	return "https://api.iextrading.com/1.0/stock/" + symbol + "/quote";
}

const makeCompanyNewsUrl = function(symbol) {
	return "https://api.iextrading.com/1.0/stock/" + symbol + "/news";
}

// Generic Fetch Function
const fetchFromAPI = function(url, options, successFunc, failureFunc) {
	fetch(url, options)
	.then(function(response) {
		return response.json();
	})
	.then(successFunc)
	.catch(failureFunc);
}

// Logging Function (for test only)
const logStatus = function(msg) {
	console.log(msg);
}

// Fix a Number to (at least) Two Decimal Places
const toTwoDecimal = function(num) {
	num = parseFloat(num);
	return num.toFixed(2);
}

// Fix a Number to (at least) Two Decimal Places
const leastTwoDecimal = function(num) {
	num = parseFloat(num);
	return num.toFixed(Math.max(2, (num.toString().split('.')[1] || []).length));
}