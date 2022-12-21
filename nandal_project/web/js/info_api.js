// 네비 누를 시 색 변경
$(function() {
    //kakao map 설정
    var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(33.450701, 126.570667),
		level: 3
	};
	
	var map = new kakao.maps.Map(container, options);
	
	//지도 확대 축소 객체 추가
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	//지도 및 스카이뷰 컨트롤 객체 추가
	var mapTypeControl = new kakao.maps.MapTypeControl();
	//TOPRIGHT는 상단 우측에 위치하게
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	
	var geocoder = new kakao.maps.services.Geocoder();
	//주소로 좌표 검색
	
	//클래스 주소 값 ${classVo.classAddress}로 바로 안들어가서 변수에 담아서 넣기
	var address = $("#address_text").text();
	geocoder.addressSearch(address, function(result, status) {

	// 정상적으로 검색이 완료됐으면 
    if (status === kakao.maps.services.Status.OK) {
	       var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
	       // 결과값으로 받은 위치를 마커로 표시합니다
	       var marker = new kakao.maps.Marker({
	           map: map,
	           position: coords
	       });

	       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	       map.setCenter(coords);
	    } 
	});
	
	//datepicker 한글 커스텀
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
   //datepicker 화면 표시
   $( "#datepicker" ).datepicker();
});