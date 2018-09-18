<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>Swing Analyzer - ${symbol}</title>
    <jsp:include page="../fragments/head-elements.jsp"/>
    <script>
        window.onload = function () {

            var dayChart = new CanvasJS.Chart("chartNextDayContainer", {
                animationEnabled: true,
                theme: "light2", // "light1", "light2", "dark1", "dark2"
                title:{
                    text: "Historical Next Day Changes"
                },
                axisY: {
                    title: "%"
                },
                data: [{
                    type: "column",
                    showInLegend: false,
                    legendMarkerColor: "grey",
                    dataPoints: ${nextDayStatistics}
                }]
            });
            dayChart.render();

            var monthChart = new CanvasJS.Chart("chartNextMonthContainer", {
                animationEnabled: true,
                theme: "light2", // "light1", "light2", "dark1", "dark2"
                title:{
                    text: "Historical Next Month Changes"
                },
                axisY: {
                    title: "%"
                },
                data: [{
                    type: "column",
                    showInLegend: false,
                    legendMarkerColor: "grey",
                    dataPoints: ${nextMonthStatistics}
                }]
            });
            monthChart.render();

            var monthPlusOneChart = new CanvasJS.Chart("chartMonthPlusOneContainer", {
                animationEnabled: true,
                theme: "light2", // "light1", "light2", "dark1", "dark2"
                title:{
                    text: "Historical Next Day Changes"
                },
                axisY: {
                    title: "%"
                },
                data: [{
                    type: "column",
                    showInLegend: false,
                    legendMarkerColor: "grey",
                    dataPoints: ${monthPlusOneStatistics}
                }]
            });
            monthPlusOneChart.render();


            var monthToMonthStatistics = new CanvasJS.Chart("chartMonthToMonthContainer", {
                animationEnabled: true,
                theme: "light2", // "light1", "light2", "dark1", "dark2"
                title:{
                    text: "Historical Next Month Changes"
                },
                axisY: {
                    title: "%"
                },
                data: [{
                    type: "column",
                    showInLegend: false,
                    legendMarkerColor: "grey",
                    dataPoints: ${monthToMonthStatistics}
                }]
            });
            monthToMonthStatistics.render();
        }
    </script>

</head>
<body>
<div class="container">
    <div class="title"> <h1 class="page-header">Swing Analyzer</h1></div>
    <div class="row">
        <h4>${symbol}</h4>
    </div>
    <div class="row">
        <div class="panel">
            <div class="panel-title">
                <h4 class="heading ks-panel-header">Daily Change: ${lastChange}% </h4>
            </div>
            <div class="panel-body">
                <div class="col-lg-5">
                    <div id="chartNextDayContainer" style="height: 370px; width: 100%;"></div>
                </div>
                <div class="col-lg-5">
                    <div id="chartNextMonthContainer" style="height: 370px; width: 100%;"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="panel">
            <div class="panel-title">
                <h4 class="heading ks-panel-header">Monthly Change: ${lastMonthChange}% </h4>
            </div>
            <div class="panel-body">
                <div class="col-lg-5">
                    <div id="chartMonthPlusOneContainer" style="height: 370px; width: 100%;"></div>
                </div>
                <div class="col-lg-5">
                    <div id="chartMonthToMonthContainer" style="height: 370px; width: 100%;"></div>
                </div>
            </div>
        </div>
    </div>


    <div class="footer"><h6 class="text-muted text-center"> © Kartik Shah</h6></div>
</div>
</body>
</html>

