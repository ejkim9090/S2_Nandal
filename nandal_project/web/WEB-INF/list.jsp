<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/reset.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/share.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/scrollup.css">
    
  
    <!-- TODO css 넣기 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/list.css">
    
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
    <!-- list에서는 상단 header 고정을 제거하기위해 제외 -->
    <%-- <script src="<%=request.getContextPath()%>/js/share.js"></script> --%>
    
    <!-- TODO js 넣기 -->
    <script src="<%=request.getContextPath()%>/js/list.js"></script>
    
    <title>메인페이지</title>
    <!-- 전체공통 -->
   
<script>
	$(function(){
		listStartSet();
		listDataAction();
		$("#btn_search").on("click",listDataAction);
		$("select[name=classLineUp]").on("change",listDataAction);
		$("select[name=reviewLineUp]").on("change",listDataAction);
	});
	
	function listStartSet() {
		var search = "${search}";
		var area = "${area}";
		var day1 = "${day1}";
		var day2 = "${day2}";
		var day3 = "${day3}";
		var level1 = "${level1}";
		var level2 = "${level2}";
		var level3 = "${level3}";
		var area = "${area}";
		var category = "${category}";
		var priceMin = "${priceMin}";
		var priceMax = "${priceMax}";
		console.log("search : " + search);
		console.log("area : " + area);
		console.log("category : " + category);
		console.log("priceMin : " + priceMin);
		console.log("priceMax : " + priceMax); 
		console.log("day1 : " + day1);
		console.log("day2 : " + day2);
		console.log("day3 : " + day3);
		console.log("level1 : " + level1);
		console.log("level2 : " + level2);
		console.log("level3 : " + level3); 
		
		
		//가져온 키워드 적용
		$("#keyword").val(search);
		//가져온 지역 선택값 적용
		var $areaOption = $("select[name=area] > option");
		for(var i = 0; i < $areaOption.length; i++) {
			if($areaOption.get(i).value == area) {
				$areaOption.get(i).setAttribute('selected',true);
			}
		}
		//가져온 카테고리 선택값 적용
		var $categoryOption = $("select[name=category] > option");
		for(var i = 0; i < $categoryOption.length; i++) {
			if($categoryOption.get(i).value == category) {
				$categoryOption.get(i).setAttribute('selected',true);
			}
		}
		//가져온 요일 적용
		var $checkboxDay = $("input[type=checkbox][name=day]");
		if(day1 != "") {
			for(var i = 0; i < $checkboxDay.length; i++) {
				if($checkboxDay.get(i).value == day1) {$checkboxDay.get(i).setAttribute('checked',true);}
			}
		}
		if(day2 != "") {
			for(var i = 0; i < $checkboxDay.length; i++) {
				if($checkboxDay.get(i).value == day2) {$checkboxDay.get(i).setAttribute('checked',true);}
			}
		}
		if(day3 != "") {
			for(var i = 0; i < $checkboxDay.length; i++) {
				if($checkboxDay.get(i).value == day3) {$checkboxDay.get(i).setAttribute('checked',true);}
			}
		}
		//가져온 난이도 적용
		var $checkboxLevel = $("input[type=checkbox][name=level]");
		if(level1 != "") {
			for(var i = 0; i < $checkboxLevel.length; i++) {
				if($checkboxLevel.get(i).value == level1) {$checkboxLevel.get(i).setAttribute('checked',true);}
			}
		}
		if(level2 != "") {
			for(var i = 0; i < $checkboxLevel.length; i++) {
				if($checkboxLevel.get(i).value == level2) {$checkboxLevel.get(i).setAttribute('checked',true);}
			}
		}
		if(level3 != "") {
			for(var i = 0; i < $checkboxLevel.length; i++) {
				if($checkboxLevel.get(i).value == level3) {$checkboxLevel.get(i).setAttribute('checked',true);}
			}
		}
		//가져온 금액 정보 적용
		if(priceMin != "") {
			$("input[type=text][name=priceMin]").val(priceMin);
		}
		if(priceMax != "") {
			$("input[type=text][name=priceMax]").val(priceMax);
		}
		
	}
	
	var pagenum = 1;
	function pageItemHandler(num) {
		pagenum = num;
		listDataAction();
	}
	function listDataAction() {
		$("#search_form").serialize();
		console.log($("#search_form").serialize());
		
		
		var lineUp1 = $("select[name=classLineUp]").val();
		var lineUp2 = $("select[name=reviewLineUp]").val();
		console.log("lineUp1 : "+lineUp1);
		console.log("lineUp2 : "+lineUp2);
		
		var searchData = $("#search_form").serialize()+ "&pagenum=" + pagenum + "&classLineUp=" + lineUp1 + "&reviewLineUp=" + lineUp2;
		$.ajax({
	   		url : "<%=request.getContextPath()%>/list.lo",
	   		type : "get",
	   		data: searchData,
	  	 	dataType : "json", 
	   		success: function(data){ 
	   					if(data == null) {
	   						alert("금액에는 정수만 입력 가능합니다.");
	   					} else {
		   					console.log(data);
		   					console.log(data.cnt);
		   					console.log(data.currentPage);
		   					console.log(data.endPage);
		   					console.log(data.pageCnt);
		   					console.log(data.startPage); 
		   					
		   					$("p#classCnt").text(data.cnt+"개의 클래스");
		   					//페이지 이동 nav 생성 코드
		   					var pagehtml = "";
		   					if(data.pageCnt != 1 && data.pageCnt != 0) {
		   						if(data.startPage != 1) {
		   							pagehtml += "<a class='page_item pre' onclick='pageItemHandler("+(data.startPage-1)+");'><img class='page_img' src='<%=request.getContextPath()%>/images/my_arrow_180.png' alt='이전'></a>";
		   						}
		   						for(var i = data.startPage;i <= data.endPage;i++) {
		   							if(i == data.currentPage) {
		   								pagehtml += "<a class='page_item now' onclick='pageItemHandler("+i+");'>"+i+"</a>";
		   							} else {
		   								pagehtml += "<a class='page_item' onclick='pageItemHandler("+i+");'>"+i+"</a>";
		   							}
		   						}
		   						if(data.endPage < data.pageCnt) {
		   							pagehtml += "<a class='page_item next' onclick='pageItemHandler("+(data.endPage+1)+");'><img class='page_img' src='<%=request.getContextPath()%>/images/my_arrow.png' alt='다음'></a>";
		   						}
		   					}
		   					$("div#pagination").html(pagehtml);
		   				
		   					var classhtml ="";
		   					if(data.classlist != undefined) {
		   						var divCnt = 0;
		   						for(var j = 0; j < data.classlist.length; j++) {
		   							if(divCnt%3 == 0) {
		   								classhtml += "<div class='list_class_wrap'>";
		   							}
		   							classhtml += "<a href='${pageContext.request.contextPath }/info/"+data.classlist[j].classCode+"' class='list_class'>"+
						   	                         "<div class='list_class_img_wrap'>"+
						  	                             "<img src='${pageContext.request.contextPath}"+data.classlist[j].classImg+"' alt='클래스 이미지'>"+
						  	                         "</div>"+
						  	                         "<div class='list_class_info_wrap'>"+
						  	                         	 "<div class='allavg'><img class='review_star' src='<%=request.getContextPath()%>/images/review_star_full.png'><span class='f_14_b'>"+data.classlist[j].allAvg+"("+data.classlist[j].allCnt+")</span></div>"+
						  	                             "<h3 class='f_16_b'>"+data.classlist[j].className+"</h3>"+
						  	                             "<span class='f_14_b f_purple'>"+data.classlist[j].classAddress+"</span>"+
						  	                             "<span class='f_14_b'>"+data.classlist[j].classPrice+"원</span>"+
						  	                         "</div>"+
						  	                     "</a>";
			  	                   	if(divCnt%3 == 2) {
		   								classhtml += "</div>";
		   							} else if(j == data.classlist.length-1) {
		   								classhtml += "</div>";
		   							}
			  	                  	divCnt++;
		   						}
		   					} else {
		   						classhtml += "<div class='list_class_not'><p>해당 조건의 클래스가 없습니다.</p></div>";
		   					}
		   					$("div#list_content").html(classhtml);
	   					
	   					}
	   				 },
	   		error : function(request, status, error){
	   					console.log(request);	
	   					console.log(status);	
	   					console.log(error);	
	   					alert("code:"+request.status+"\n"
	   							+"message"+request.responseText+"\n"
	   							+"error"+error);
	   				}
	   	}); 
	}
</script>
</head>
<body>
    <div>
    <%@include file="/WEB-INF/share/header.jsp" %>
        <div id="section">
        <!-- TODO 여기에 만든 공간 넣기 -->
           <div class="wrap_1050">
                <div class="list_top">
                    <p id="classCnt"></p>
                    <div>
                        <select name="classLineUp">
                            <option value="미선택">선택</option>
                            <option value="인기순">인기순</option>
                            <option value="높은평점순">높은 평점순</option>
                            <option value="낮은가격순">낮은 가격순</option>
                            <option value="높은가격순">높은 가격순</option>
                        </select>
                        <select name="reviewLineUp">
                            <option value="0">선택</option>
                            <option value="1">혼자</option>
                            <option value="2">친구</option>
                            <option value="3">연인</option>
                            <option value="4">가족</option>
                        </select>
                    </div>
                </div>
                <div id="list_content">
                </div>
            </div>
            <div class="wrap_1050">
                <div class="pagination" id="pagination">
                </div>
            </div>
           <div id="scrollup">
               <a href="#">
                   <span></span>
               </a>
           </div>
        </div>
    <%@include file="/WEB-INF/share/footer.jsp" %>
    </div>
</body>
</html>