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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mypage.css">
    
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
    <script src="<%=request.getContextPath()%>/js/share.js"></script>
    
    <!-- TODO js 넣기 -->
    <script src="<%=request.getContextPath()%>/js/mypage.js"></script>
    
    <title>메인페이지</title>
	<script>
	$(function(){
	    $(".my_nav").on("click",myNavHandler);
	    myNavSelectList();
	});
	function applyCancleHandler() {
	    var $caCode = $(this).siblings("input[type=hidden]").val();
	    console.log($caCode);
	    
	    $.ajax({
      		url : "<%=request.getContextPath()%>/applyCancel.lo",
      		type : "post",
      		data: "caCode=" + $caCode,
      		success: function(data){ 
      					if(data == 1) {
							alert("신청한 클래스가 취소되었습니다.")
						} else {
							alert("취소 신청이 실패했습니다.")
						}
					    myNavSelectList();
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
	function myNavHandler() {
	    $(".my_nav").not($(this)).removeClass("my_nav_select");
	    $(this).addClass("my_nav_select");
	    $("#nav_text").text($(this).text());
	    
	    myNavSelectList();
	}
	function myNavSelectList() {
		var $navId = $(".my_nav.my_nav_select").attr("id");
	    console.log($navId);
	    var $listWrap = $("#apply_list_wrap");
	    $.ajax({
      		url : "<%=request.getContextPath()%>/my.lo",
      		type : "post",
      		data: "navId="+$navId, 
     	 	dataType : "json", 
      		success: function(data){ 
      				 switch($navId) {
      				case "my_apply" : 
      					console.log("신청 내역");
      					if(data != null) {
     						let addHtml = "";
     						console.log(data);
     						console.log(data.length);
     						for(var i = 0; i < data.length; i++) {
     							addHtml += "<div class='apply_list'>"+
					                            "<div class='myList_left'>"+
					                                "<h3>"+data[i].className+"</h3>"+
					                                "<div><img class='my_img' src='<%=request.getContextPath()%>/images/calendar.png'><p class='f_16'>"+data[i].caDate+"</p><img class='my_img' src='<%=request.getContextPath()%>/images/clock.png'><p class='f_16'>"+data[i].csStime+"~"+data[i].csFtime+"</p><img class='my_img' src='<%=request.getContextPath()%>/images/option.png'><p class='f_16'>"
					                                +data[i].coName+"</p></div>"+
					                                "<div><p>"+data[i].caTotal+"명</p><p>"+((data[i].classPrice+data[i].coPrice)*data[i].caTotal)+"원</p></div>"+
					                            "</div>"+
					                            "<div class='myList_right'>"+
					                                "<p>"+data[i].caTime+"</p>"+
					                                "<div>"+
					                                    "<input type='hidden' name='caCode' value='"+data[i].caCode+"'>"+
					                                    "<button class='c_line c_color myList_btn model_a_show'>리뷰 등록</button>"+
					                                    "<button class='c_line c_color myList_btn cancle'>취소 신청</button>"+
					                                "</div></div></div>";
     						}
     						$listWrap.html(addHtml);
     					    $(".model_a_show").on("click",reviewWriteMadalShowHandler);
     					    $("button.cancle").on("click",applyCancleHandler);
     					} else {
     						$buyTime.html("<div class='apply_list'><h2>클래스 신청 내역이 없습니다.</h2></div>");
     					}
      					break;
      				case "my_cancel" : 
      					console.log("취소 내역");
      					if(data != null) {
     						let addHtml = "";
     						console.log(data);
     						console.log(data.length);
     						for(var i = 0; i < data.length; i++) {
     							addHtml += "<div class='apply_list'>"+
					                            "<div class='myList_left'>"+
					                                "<h3>"+data[i].className+"</h3>"+
					                                "<div><img class='my_img' src='<%=request.getContextPath()%>/images/calendar.png'><p class='f_16'>"+data[i].caDate+"</p><img class='my_img' src='<%=request.getContextPath()%>/images/clock.png'><p class='f_16'>"+data[i].csStime+"~"+data[i].csFtime+"</p><img class='my_img' src='<%=request.getContextPath()%>/images/option.png'><p class='f_16'>"
					                                +data[i].coName+"</p></div>"+
					                                "<div><p>"+data[i].caTotal+"명</p><p>"+((data[i].classPrice+data[i].coPrice)*data[i].caTotal)+"원</p></div>"+
					                            "</div>"+
					                            "<div class='myList_right'>"+
					                                "<p>"+data[i].caTime+"</p>"+
					                                "<div>"+
					                                "</div></div></div>";
     						}
     						$listWrap.html(addHtml);
     					} else {
     						$buyTime.html("<div class='apply_list'><h2>클래스 취소 내역이 없습니다.</h2></div>");
     					}
      					break;
      				case "my_review" : 
      					console.log("리뷰 관리");
      					if(data != null) {
     						let addHtml = "";
     						console.log(data);
     						console.log(data.length);
     						for(var i = 0; i < data.length; i++) {
     							switch(data[i].reviewGroup) {
                                case 0: var reGroup = ""; break;
                                case 1: var reGroup = "혼자"; break;
                                case 2: var reGroup = "친구"; break;
                                case 3: var reGroup = "연인"; break;
                                case 4: var reGroup = "가족"; break;
                                }
     							var rpRoute = data[i].rpRoute;
     							console.log(rpRoute);
     							
         						let addPhoto = "";
     							if (!(rpRoute === undefined)) {
     								for(var j = 0; j < rpRoute.length; j++) {
         								addPhoto += "<img class='reCont_img' src='<%=request.getContextPath()%>"+rpRoute[j]+"'>";
         							}
     							} 
     							addHtml += "<div class='apply_list'>"+
					                            "<div class='myList_left'>"+
					                                "<h3>"+data[i].className+"</h3>"+
					                                "<div><img class='my_img' src='<%=request.getContextPath()%>/images/review_star_full.png'><p class='f_16'>"+data[i].reviewGrade+"</p><p class='f_16'>"+
					                                reGroup
					                                +"</p></div>"+
					                                "<div class='reCont'>"+
					                                    "<p>"+data[i].reviewCont+"</p><div>"+addPhoto+"</div></div>"+
					                                "<div class='reCont_text'><p>더보기</p><img class='my_img reCont_arrow' src='<%=request.getContextPath()%>/images/review_arrow.png'></div>"+
					                            "</div>"+
					                            "<div class='myList_right'>"+
					                                "<p>"+data[i].reviewTime+"</p>"+
					                                "<div>"+
					                                    "<button class='c_line c_color myList_btn model_c_show'>수정</button>"+
					                                    "<button class='c_line c_color myList_btn'>삭제</button>"+
					                                "</div>"+
					                            "</div>"+
					                        "</div>";
     						}
     						$listWrap.html(addHtml);
     					    $(".reCont_text").on("click",reviewContHandler);
     					    $(".model_c_show").on("click",reviewUpdateMadalShowHandler);
     					} else {
     						$buyTime.html("<div class='apply_list'><h2>작성하신 리뷰 내역이 없습니다.</h2></div>");
     					}
      					break;
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
        	<div class="profile_wrap"> <!--상단 프로필-->
                <div class="wrap_1050">
                    <div><h2>프로필</h2></div>
                    <div class="profile_data">
                        <div><img class="profile_img" src="https://img-cf.kurly.com/cdn-cgi/image/width=400,format=auto/shop/data/goods/1654826608504l0.jpg" alt="프로필 이미지"></div>
                        <div class="profile_text">
                            <p class="f_20_b">${loginSsInfo.memberName}님</p>
                            <p class="f_20_b">이메일(ID) : ${loginSsInfo.memberId}</p>
                            <p class="f_20_b">전화번호 : ${loginSsInfo.memberPhone}</p>
                        </div>
                    </div>
                    <div><button class="f_14_b" id="btn_profile">회원정보 수정</button></div>
                </div>
            </div>
            <div class="wrap_1050 my_list"><!--신청/취소 내역, 리뷰 관리-->
                <div class="my_list_nav">
                    <h2>클래스</h2>
                    <div class="my_nav my_nav_select" id="my_apply"><p>신청 내역</p><img class="my_arrow" src="<%=request.getContextPath()%>/images/my_arrow.png" alt="화살표"></div>
                    <div class="my_nav" id="my_cancel"><p>취소 내역</p><img class="my_arrow" src="<%=request.getContextPath()%>/images/my_arrow.png" alt="화살표"></div>
                    <div class="my_nav" id="my_review"><p>리뷰 관리</p><img class="my_arrow" src="<%=request.getContextPath()%>/images/my_arrow.png" alt="화살표"></div>
                </div>
                <div class="list_data_nav">
                    <div class="nav_text_wrap">
                        <h3 id="nav_text">신청 내역</h3>
                    </div>
                    <div id="apply_list_wrap">
                    </div>
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
    <div class="modal a">
            <div class="modal_content a">
                <form class="model_form a" action="${pageContext.request.contextPath}/review.do" method="post" enctype="multipart/form-data">
                    <div class="form_cont a">
                        <h2>리뷰 등록</h2>
                        <h3>리뷰 작성</h3>
                        <textarea name="reviewCont"></textarea>
                        <h3>사진 첨부</h3>
                        <input type="file" name="fileUpload" multiple="multiple">
                        <p class="f_10">*사진은 최대 5개까지 등록가능합니다.</p>
                        <h3>평점 및 추천</h3>
                        <h4>친절도</h4>
                        <div>
                            <input type="radio" name="kind" id="kind1" value="1"><label class="f_14" for="kind1">불만족</label>
                            <input type="radio" name="kind" id="kind2" value="2"><label class="f_14" for="kind2">약간 불만족</label>
                            <input type="radio" name="kind" id="kind3" value="3"><label class="f_14" for="kind3">보통</label>
                            <input type="radio" name="kind" id="kind4" value="4"><label class="f_14" for="kind4">약간 만족</label>
                            <input type="radio" name="kind" id="kind5" value="5"><label class="f_14" for="kind5">만족</label>
                        </div>
                        <h4>시설</h4>
                        <div>
                            <input type="radio" name="facility" id="facility1" value="1"><label class="f_14" for="facility1">불만족</label>
                            <input type="radio" name="facility" id="facility2" value="2"><label class="f_14" for="facility2">약간 불만족</label>
                            <input type="radio" name="facility" id="facility3" value="3"><label class="f_14" for="facility3">보통</label>
                            <input type="radio" name="facility" id="facility4" value="4"><label class="f_14" for="facility4">약간 만족</label>
                            <input type="radio" name="facility" id="facility5" value="5"><label class="f_14" for="facility5">만족</label>
                        </div>
                        <h4>수업구성</h4>
                        <div>
                            <input type="radio" name="component" id="component1" value="1"><label class="f_14" for="component1">불만족</label>
                            <input type="radio" name="component" id="component2" value="2"><label class="f_14" for="component2">약간 불만족</label>
                            <input type="radio" name="component" id="component3" value="3"><label class="f_14" for="component3">보통</label>
                            <input type="radio" name="component" id="component4" value="4"><label class="f_14" for="component4">약간 만족</label>
                            <input type="radio" name="component" id="component5" value="5"><label class="f_14" for="component5">만족</label>
                        </div>
                        <h4>난이도 안내</h4>
                        <div>
                            <input type="radio" name="level" id="level1" value="1"><label class="f_14" for="level1">불만족</label>
                            <input type="radio" name="level" id="level2" value="2"><label class="f_14" for="level2">약간 불만족</label>
                            <input type="radio" name="level" id="level3" value="3"><label class="f_14" for="level3">보통</label>
                            <input type="radio" name="level" id="level4" value="4"><label class="f_14" for="level4">약간 만족</label>
                            <input type="radio" name="level" id="level5" value="5"><label class="f_14" for="level5">만족</label>
                        </div>
                        <h4>유형 추천</h4>
                        <div>
                            <input type="radio" name="group" id="group1" value="1"><label class="f_14" for="group1">혼자</label>
                            <input type="radio" name="group" id="group2" value="2"><label class="f_14" for="group2">친구</label>
                            <input type="radio" name="group" id="group3" value="3"><label class="f_14" for="group3">연인</label>
                            <input type="radio" name="group" id="group4" value="4"><label class="f_14" for="group4">가족</label>
                        </div>
                    </div>
                    <div class="model_btn_wrap">
                        <button type="submit" class="c_line c_color model_btn">등록</button>
                        <button type="button" class="c_line c_color model_btn madal_close">취소</button>
                    </div>
                </form>
            </div>
        </div>
    <div class="modal b">
        <div class="modal_content b">
            <form class="model_form b">
                <div class="form_cont b">
                    <h2>회원 정보 수정</h2>
                    <h3>프로필 사진 변경</h3>
                    <input type="file">
                    <h3>아이디(이메일)</h3>
                    <input type="text" name="memberId" value="${loginSsInfo.memberId}" readonly>
                    <h3>비밀번호 확인</h3>
                    <input type="text" name="pwdChek">
                    <p class="f_10">*필수</p>
                    <h3>비밀번호 변경</h3>
                    <input type="text" name="pwdChange">
                    <p class="f_10">*최소 8자이상 및 영문,숫자,특수문자(_!@#$% 가능) 최소 1개 이상 입력</p>
                    <h3>비밀번호 변경 확인</h3>
                    <input type="text" name="pwdChangeChek">
                    <p class="f_10"></p>
                    <h3>이름</h3>
                    <input type="text" name="memberName" value="${loginSsInfo.memberName}" readonly>
                    <h3>전화번호</h3>
                    <div>
                        <input type="text" name="memberPhone">
                        <button type="button" class="c_line c_color model_btn">중복확인</button>
                    </div>
                </div>
                <div class="model_btn_wrap">
                    <button type="submit" class="c_line c_color model_btn">등록</button>
                    <button type="button" class="c_line c_color model_btn madal_close">취소</button>
                </div>
            </form>
        </div>
    </div>
    <div class="modal c">
            <div class="modal_content c">
                <form class="model_form c">
                    <div class="form_cont c">
                        <h2>리뷰 수정</h2>
                        <h3>리뷰 작성</h3>
                        <textarea name="reviewCont"></textarea>
                        <h3>사진 첨부</h3>
                        <input type="file">
                        <p class="f_10">*사진은 최대 5개까지 등록가능합니다.</p>
                        <h3>평점 및 추천</h3>
                        <h4>친절도</h4>
                        <div>
                            <input type="radio" name="kind" id="kind1" value="1"><label class="f_14" for="kind1">불만족</label>
                            <input type="radio" name="kind" id="kind2" value="2"><label class="f_14" for="kind2">약간 불만족</label>
                            <input type="radio" name="kind" id="kind3" value="3"><label class="f_14" for="kind3">보통</label>
                            <input type="radio" name="kind" id="kind4" value="4"><label class="f_14" for="kind4">약간 만족</label>
                            <input type="radio" name="kind" id="kind5" value="5"><label class="f_14" for="kind5">만족</label>
                        </div>
                        <h4>시설</h4>
                        <div>
                            <input type="radio" name="facility" id="facility1" value="1"><label class="f_14" for="facility1">불만족</label>
                            <input type="radio" name="facility" id="facility2" value="2"><label class="f_14" for="facility2">약간 불만족</label>
                            <input type="radio" name="facility" id="facility3" value="3"><label class="f_14" for="facility3">보통</label>
                            <input type="radio" name="facility" id="facility4" value="4"><label class="f_14" for="facility4">약간 만족</label>
                            <input type="radio" name="facility" id="facility5" value="5"><label class="f_14" for="facility5">만족</label>
                        </div>
                        <h4>수업구성</h4>
                        <div>
                            <input type="radio" name="component" id="component1" value="1"><label class="f_14" for="component1">불만족</label>
                            <input type="radio" name="component" id="component2" value="2"><label class="f_14" for="component2">약간 불만족</label>
                            <input type="radio" name="component" id="component3" value="3"><label class="f_14" for="component3">보통</label>
                            <input type="radio" name="component" id="component4" value="4"><label class="f_14" for="component4">약간 만족</label>
                            <input type="radio" name="component" id="component5" value="5"><label class="f_14" for="component5">만족</label>
                        </div>
                        <h4>난이도 안내</h4>
                        <div>
                            <input type="radio" name="level" id="level1" value="1"><label class="f_14" for="level1">불만족</label>
                            <input type="radio" name="level" id="level2" value="2"><label class="f_14" for="level2">약간 불만족</label>
                            <input type="radio" name="level" id="level3" value="3"><label class="f_14" for="level3">보통</label>
                            <input type="radio" name="level" id="level4" value="4"><label class="f_14" for="level4">약간 만족</label>
                            <input type="radio" name="level" id="level5" value="5"><label class="f_14" for="level5">만족</label>
                        </div>
                        <h4>유형 추천</h4>
                        <div>
                            <input type="radio" name="group" id="group1" value="1"><label class="f_14" for="group1">혼자</label>
                            <input type="radio" name="group" id="group2" value="2"><label class="f_14" for="group2">친구</label>
                            <input type="radio" name="group" id="group3" value="3"><label class="f_14" for="group3">연인</label>
                            <input type="radio" name="group" id="group4" value="4"><label class="f_14" for="group4">가족</label>
                        </div>
                    </div>
                    <div class="model_btn_wrap">
                        <button type="submit" class="c_line c_color model_btn">수정</button>
                        <button type="button" class="c_line c_color model_btn madal_close">취소</button>
                    </div>
                </form>
            </div>
        </div>
</body>
</html>