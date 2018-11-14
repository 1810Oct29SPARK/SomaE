// Formatting data cells for each crypto
const formatCrypto = function(data, symbol) {
	$(symbol + "p").html(leastTwoDecimal(data.latestPrice));
	$(symbol + "h").html(leastTwoDecimal(data.high));
	$(symbol + "l").html(leastTwoDecimal(data.low));
	if (parseFloat(data.change) > 0) {
		$(symbol + "cc").html("+" + leastTwoDecimal(parseFloat(data.change)));
		$(symbol + "cc").removeClass("down");
		$(symbol + "cc").addClass("up");
		$(symbol + "cp").html("+" + toTwoDecimal(parseFloat(data.changePercent) * 100) + "%");
		$(symbol + "cp").removeClass("down");
		$(symbol + "cp").addClass("up");
	}
	else if (parseFloat(data.change) < 0) {
		$(symbol + "cc").html(leastTwoDecimal(parseFloat(data.change)));
		$(symbol + "cc").removeClass("up");
		$(symbol + "cc").addClass("down");
		$(symbol + "cp").html(toTwoDecimal(parseFloat(data.changePercent) * 100) + "%");
		$(symbol + "cp").removeClass("up");
		$(symbol + "cp").addClass("down");
	}
	else {
		$(symbol + "cc").html(leastTwoDecimal(parseFloat(data.change)));
		$(symbol + "cc").removeClass("up");
		$(symbol + "cc").removeClass("down");
		$(symbol + "cp").html(toTwoDecimal(parseFloat(data.changePercent) * 100) + "%");
		$(symbol + "cp").removeClass("up");
		$(symbol + "cp").removeClass("down");
	}
}

// Update crypto prices in the table
const updateCryptoTable = function(data) {
	// BTC
	const btcSymbol = "#btc-";
	const btcData = data[0];
	formatCrypto(btcData, btcSymbol);

	// EOS
	const eosSymbol = "#eos-";
	const eosData = data[1];
	formatCrypto(eosData, eosSymbol);

	// ETH
	const ethSymbol = "#eth-";
	const ethData = data[2];
	formatCrypto(ethData, ethSymbol);

	// BNB
	const bnbSymbol = "#bnb-";
	const bnbData = data[3];
	formatCrypto(bnbData, bnbSymbol);

	// BCH
	const bchSymbol = "#bch-";
	const bchData = data[5];
	formatCrypto(bchData, bchSymbol);

	// XRP
	const xrpSymbol = "#xrp-";
	const xrpData = data[7];
	formatCrypto(xrpData, xrpSymbol);

	// LTC
	const ltcSymbol = "#ltc-";
	const ltcData = data[10];
	formatCrypto(ltcData, ltcSymbol);

	// ETC
	const etcSymbol = "#etc-";
	const etcData = data[11];
	formatCrypto(etcData, etcSymbol);

	// IOT
	const iotSymbol = "#iot-";
	const iotData = data[12];
	formatCrypto(iotData, iotSymbol);

	// QTUM
	const qtumSymbol = "#qtum-";
	const qtumData = data[17];
	formatCrypto(qtumData, qtumSymbol);
}

// Make an API call to retrieve crypto data
const getCryptoData = function() {
	fetchFromAPI(cryptoEndPoint, fetchOptionsDefault, updateCryptoTable, logStatus);
}

// Wait until the document is ready for JQuery
$(function() {
	//
});