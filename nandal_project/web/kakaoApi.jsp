<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.min.css"> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.structure.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.structure.min.css"> --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.theme.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.theme.min.css"> --%>
<!--datepicker 테마 css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker_theme.css">
<!--  제이쿼리 js -->
 <script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
 <!-- 제이쿼리 ui js -->
 <script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
 <%-- <script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script> --%>
 
 <script>
 	$.datepicker.setDefaults({
	  	dateFormat: 'yy-mm-dd',
	  	prevText: '이전 달',
	  	nextText: '다음 달',
	 	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	  	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	  	dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	  	dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	  	dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	  	showMonthAfterYear: true,
	  	yearSuffix: '년'
	});
  $( function() {
	  $( "#datepicker" ).datepicker();
	  //datepicker만들며, 날짜 선택 시 해당 날짜값 가져오기
//    $( "#datepicker" ).datepicker({
//        onSelect: function(dateString) {
//            alert(dateString);
//        }
//    });
    
    $("#datepicker").on("change",function(){
        var selected = $(this).val();
        alert(selected);
    });
  } );
  </script>
</head>
<body>
<div id="map" style="width:500px;height:400px;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=22e814de5ddfa7ab67223da7c1c400b9"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
	<p>Date:</p> <div id="datepicker"></div>
</body>
</html>