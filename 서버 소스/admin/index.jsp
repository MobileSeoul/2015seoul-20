<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script>
$(function () {
	$.ajax({
		method: "POST",
		url : "/admin/admin.jsp",
		success: function(result) {
			alert(result);
			$("#ajaxTxt").html(result);
			init(JSON.parse(result));
		}
	});
});

function init(result) {
	$('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'World\'s largest cities per 2014'
        },
        subtitle: {
            text: 'Source: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '사용량 (회)'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '사용량 : <b>{point.y} 회</b>'
        },
        series: [{
            name: 'useRate',
            data: result,
            dataLabels: {
                enabled: true,
                rotation: 0,
                color: '#FFFFFF',
                align: 'right',
                format: '{point.y}', // one decimal
                y: 10, // 10 pixels down from the top
                x: -50,
                style: {
                    fontSize: '14px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        }]
    });
}
</script>
</head>
<body>
	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<div id="ajaxTxt"
		style="min-width: 310px; height: 400px; margin: 0 auto; background-color: #A5FFEA"></div>
</body>
</html>