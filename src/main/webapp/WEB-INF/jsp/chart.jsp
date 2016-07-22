<!DOCTYPE HTML>
<html>

<head>
<script type="text/javascript">
window.onload = function () {

    var reISO = /^(\d{4})-(\d{2})-(\d{2})$/;

    JSON.dateParser = function (key, value) {
        if (typeof value === 'string') {
            var a = reISO.exec(value);
            if (a)
                return new Date(value);
        }
        return value;
    };

	var da = JSON.parse('${dataString}', JSON.dateParser);
	console.log(da);


	var chart = new CanvasJS.Chart("chartContainer",
	{
		title:{
			text: "VIX Candlestick Chart",
		},
		exportEnabled: true,
		axisY: {
			includeZero: false,
			prefix: "$",
		},
		axisX: {
			valueFormatString: "DD-MM",
		},
		data: [
		{
			type: "candlestick",
			dataPoints: da
		}
		]
	});
	chart.render();
}
</script>
<script type="text/javascript" src="js/canvasjs.min.js"></script>
</head>
<body>
<div id="chartContainer" style="width:100%;height:300px;"></div>
</body>
</html>

