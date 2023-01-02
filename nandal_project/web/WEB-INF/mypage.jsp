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
    
    <title>마이 페이지</title>
	<script>
	$(function(){
	    $(".my_nav").on("click",myNavHandler);
	    myNavSelectList();
	    $("form.model_form.a").submit(reviewWriteFormHandler);
	    $("form.model_form.c").submit(reviewWriteFormHandler);
	    $("input[type=file][multiple=multiple]").on("change",FileNumCheck);
	});
	function reviewWriteFormHandler() { // 리뷰 평점 필수 선택 확인
		var $kindCheck = $('.form_cont.a > div > input:radio[name=kind]').is(':checked');
		var $facilityCheck = $('.form_cont.a > div > input:radio[name=facility]').is(':checked');
		var $componentCheck = $('.form_cont.a > div > input:radio[name=component]').is(':checked');
		var $levelCheck = $('.form_cont.a > div > input:radio[name=level]').is(':checked');
		if($("textarea[name=reviewCont]").val().length < 10) {
			alert("최소 10자 이상 입력해주세요.");
			return false;
		} else if(!$kindCheck || !$facilityCheck || !$componentCheck || !$levelCheck) {
			alert("필수사항을 체크해주세요.");
			return false;
		}
	}
	function FileNumCheck(){ //리뷰 첨부파일 개수 제한
	    var maxFileCnt = 5;   // 첨부파일 최대 개수
	    var curFileCnt = $(this).get(0).files.length;  // 현재 선택된 첨부파일 개수
		console.log(curFileCnt);
	    // 첨부파일 개수 확인
	    if (curFileCnt > maxFileCnt) {
	        alert("사진은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
	        $(this).get(0).value = ""; //5개 초과시 선택된 파일 다 취소
	    } 
	}
	function reviewUpdateMadalShowHandler() {
	    $(".modal.c").show();
	    var $reviewCode = $(this).siblings("input[type=hidden]").val(); //수정 클릭한 목록의 리뷰코드
	    $("#review_code_update").val($reviewCode); //해당 몰당창에 input[type=hidden]에 해당 reviewCode로 수정
	    $(".form_cont.c > input[type=file][multiple=multiple]").get(0).value = ""; //다른 리뷰 수정 후 다른 리뷰 수정 클릭 시 파일선택이 남아있어서 제거
	    $.ajax({
      		url : "<%=request.getContextPath()%>/reviewUpdate.lo",
      		type : "post",
      		data: "reviewCode=" + $reviewCode,
     	 	dataType : "json", 
      		success: function(data){ 
      					if(data != null) {
      						$("div.form_cont.c > textarea").get(0).value = data.reviewCont;
      						$("div.form_cont.c > div > input[name=kind2]").get(data.reviewKind-1).checked = true;
      						$("div.form_cont.c > div > input[name=component2]").get(data.reviewComponent-1).checked = true;
      						$("div.form_cont.c > div > input[name=facility2]").get(data.reviewFacility-1).checked = true;
      						$("div.form_cont.c > div > input[name=level2]").get(data.reviewLevel-1).checked = true;
      						$("div.form_cont.c > div > input[name=group2]").get(data.reviewGroup).checked = true;
      						console.log(data.reviewCont);
      						console.log(data.reviewGrade);
      						console.log(data.reviewKind);
      						console.log(data.reviewComponent);
      						console.log(data.reviewFacility);
      						console.log(data.reviewLevel);
      						console.log(data.reviewGroup);
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
	function reviewDeleteHandler() {
	    var $reviewCode = $(this).siblings("input[type=hidden]").val();
	    console.log($reviewCode);
	    
	    $.ajax({
      		url : "<%=request.getContextPath()%>/reviewDelete.lo",
      		type : "post",
      		data: "reviewCode=" + $reviewCode,
      		success: function(data){ 
      					if(data == 1) {
							alert("해당 리뷰가 삭제되었습니다.")
						} else {
							alert("리뷰 삭제가 실패했습니다.")
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
     							let addPhoto = "";
     							if (data[i].reviewCheck == 0) {
       								addPhoto = "<button class='c_line c_color myList_btn model_a_show'>리뷰 등록</button>"+
                                      			"<button class='c_line c_color myList_btn cancle'>취소 신청</button>";
     							} else {
     								addPhoto = "<button class='c_line c_color myList_btn model_a_show' style='visibility: hidden;'>리뷰 등록</button>"+
                        							"<button class='c_line c_color myList_btn cancle'>취소 신청</button>";
     							}
     							
     							addHtml += "<div class='apply_list'>"+
					                            "<div class='myList_left'>"+
					                                "<a href='/nandal/info/"+data[i].classCode+"'><h3>"+data[i].className+"</h3></a>"+
					                                "<div><img class='my_img' src='<%=request.getContextPath()%>/images/calendar.png'><p class='f_16'>"+data[i].caDate+"</p><img class='my_img' src='<%=request.getContextPath()%>/images/clock.png'><p class='f_16'>"+data[i].csStime+"~"+data[i].csFtime+"</p><img class='my_img' src='<%=request.getContextPath()%>/images/option.png'><p class='f_16'>"
					                                +data[i].coName+"</p></div>"+
					                                "<div><p>"+data[i].caTotal+"명</p><p>"+((data[i].classPrice+data[i].coPrice)*data[i].caTotal)+"원</p></div>"+
					                            "</div>"+
					                            "<div class='myList_right'>"+
					                                "<p>"+data[i].caTime+"</p>"+
					                                "<div>"+
					                                    "<input type='hidden' name='caCode' value='"+data[i].caCode+"'>"+addPhoto+"</div></div></div>";
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
					                                "<a href='/nandal/info/"+data[i].classCode+"'><h3>"+data[i].className+"</h3></a>"+
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
					                            	"<a href='/nandal/info/"+data[i].classCode+"'><h3>"+data[i].className+"</h3></a>"+
					                                "<div><img class='my_img' src='<%=request.getContextPath()%>/images/review_star_full.png'><p class='f_16'>"+data[i].reviewGrade+"</p>"+
					                                "<p class='f_16'>"+reGroup+"</p></div>"+
					                                "<div class='reCont'>"+
					                                	"<div class='reCont_score'><label class='f_12_b'>친절도("+data[i].reviewKind+")</label>"+
					                                	"<label class='f_12_b'>시설("+data[i].reviewComponent+")</label>"+
					                                	"<label class='f_12_b'>수업구성("+data[i].reviewFacility+")</label>"+
					                                	"<label class='f_12_b'>난이도 안내("+data[i].reviewLevel+")</label></div>"+
					                                    "<p>"+data[i].reviewCont+"</p><div>"+addPhoto+"</div></div>"+
					                                "<div class='reCont_text'><p>더보기</p><img class='my_img reCont_arrow' src='<%=request.getContextPath()%>/images/review_arrow.png'></div>"+
					                            "</div>"+
					                            "<div class='myList_right'>"+
					                                "<p>"+data[i].reviewTime+"</p>"+
					                                "<div>"+
					                                	"<input type='hidden' name='reviewCode' value='"+data[i].reviewCode+"'>"+
					                                    "<button class='c_line c_color myList_btn model_c_show'>수정</button>"+
					                                    "<button class='c_line c_color myList_btn delete'>삭제</button>"+
					                                "</div>"+
					                            "</div>"+
					                        "</div>";
     						}
     						$listWrap.html(addHtml);
     					    $(".reCont_text").on("click",reviewContHandler);
     					    $(".model_c_show").on("click",reviewUpdateMadalShowHandler);
     					    $("button.delete").on("click",reviewDeleteHandler);
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
                        <div><img class="profile_img" src="<%=request.getContextPath()%>${loginSsInfo.memberImg}" alt="프로필 이미지"></div>
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
                        <input id="review_code" type="hidden" name="reviewCode" value="0">
                        <h3>리뷰 작성</h3>
                        <textarea name="reviewCont"></textarea>
                        <h3>사진 첨부</h3>
                        <input type="file" name="fileUpload" multiple="multiple" accept="image/*">
                        <p class="f_10">*사진은 최대 5개까지 등록가능합니다.</p>
                        <h3>평점 및 추천</h3>
                        <h4>친절도(필수)</h4>
                        <div>
                            <input type="radio" name="kind" id="kind1" value="1"><label class="f_14" for="kind1">불만족</label>
                            <input type="radio" name="kind" id="kind2" value="2"><label class="f_14" for="kind2">약간 불만족</label>
                            <input type="radio" name="kind" id="kind3" value="3"><label class="f_14" for="kind3">보통</label>
                            <input type="radio" name="kind" id="kind4" value="4"><label class="f_14" for="kind4">약간 만족</label>
                            <input type="radio" name="kind" id="kind5" value="5"><label class="f_14" for="kind5">만족</label>
                        </div>
                        <h4>시설(필수)</h4>
                        <div>
                            <input type="radio" name="facility" id="facility1" value="1"><label class="f_14" for="facility1">불만족</label>
                            <input type="radio" name="facility" id="facility2" value="2"><label class="f_14" for="facility2">약간 불만족</label>
                            <input type="radio" name="facility" id="facility3" value="3"><label class="f_14" for="facility3">보통</label>
                            <input type="radio" name="facility" id="facility4" value="4"><label class="f_14" for="facility4">약간 만족</label>
                            <input type="radio" name="facility" id="facility5" value="5"><label class="f_14" for="facility5">만족</label>
                        </div>
                        <h4>수업구성(필수)</h4>
                        <div>
                            <input type="radio" name="component" id="component1" value="1"><label class="f_14" for="component1">불만족</label>
                            <input type="radio" name="component" id="component2" value="2"><label class="f_14" for="component2">약간 불만족</label>
                            <input type="radio" name="component" id="component3" value="3"><label class="f_14" for="component3">보통</label>
                            <input type="radio" name="component" id="component4" value="4"><label class="f_14" for="component4">약간 만족</label>
                            <input type="radio" name="component" id="component5" value="5"><label class="f_14" for="component5">만족</label>
                        </div>
                        <h4>난이도 안내(필수)</h4>
                        <div>
                            <input type="radio" name="level" id="level1" value="1"><label class="f_14" for="level1">불만족</label>
                            <input type="radio" name="level" id="level2" value="2"><label class="f_14" for="level2">약간 불만족</label>
                            <input type="radio" name="level" id="level3" value="3"><label class="f_14" for="level3">보통</label>
                            <input type="radio" name="level" id="level4" value="4"><label class="f_14" for="level4">약간 만족</label>
                            <input type="radio" name="level" id="level5" value="5"><label class="f_14" for="level5">만족</label>
                        </div>
                        <h4>유형 추천(선택)</h4>
                        <div>
                            <input type="radio" name="group" value="0" style="display:none;" checked="checked">
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
            <form class="model_form b" action="${pageContext.request.contextPath}/memberUpdate.do" method="post" enctype="multipart/form-data">
                <div class="form_cont b">
                    <h2>회원 정보 수정</h2>
                    <h3>프로필 사진 변경</h3>
                    <input id="memberFile" type="file" name="fileUpload" accept="image/*">
                    <h3>아이디(이메일)</h3>
                    <input type="text" name="memberId" value="${loginSsInfo.memberId}" readonly>
                    <h3>비밀번호 확인</h3>
                    <input type="password" name="pwdCheck">
                    <p class="f_10" id="pwdCheck_text">*필수</p>
                    <h3>새 비밀번호</h3>
                    <input type="password" name="pwdChange">
                    <p class="f_10" id="pwdChange_text">*최소 8자이상, 영문,숫자,특수문자(_!@#$% 가능) 최소 1개 이상</p>
                    <h3>새 비밀번호 확인</h3>
                    <input type="password" name="pwdChangeCheck">
                    <p class="f_10" id="pwdChangeCheck_text"></p>
                    <h3>이름</h3>
                    <input type="text" name="memberName" value="${loginSsInfo.memberName}" readonly>
                    <h3>휴대폰 번호 변경</h3>
                    <input type="text" name="memberPhone">
                 	<p class="f_10" id="memberPhone_text"></p>
                </div>
                <div class="model_btn_wrap">
                    <button type="submit" class="c_line c_color model_btn">수정</button>
                    <button id="member_reset" type="button" class="c_line c_color model_btn madal_close">취소</button>
                </div>
            </form>
<script>
	//취소 클릭 시 모달창 내용 기존으로 되돌리기
	$("#member_reset").click(memberFromResetHandler);
	function memberFromResetHandler() {
		$("#memberFile").val("");
		$("input[type=password][name=pwdCheck]").val("");
		$("#pwdCheck_text").text("*필수");
		$("#pwdCheck_text").css("color","black");
		$("input[type=password][name=pwdChange]").val("");
		$("#pwdChange_text").text("*최소 8자이상, 영문,숫자,특수문자(_!@#$% 가능) 최소 1개 이상");
		$("#pwdChange_text").css("color","black");
		$("input[type=password][name=pwdChangeCheck]").val("");
		$("#pwdChangeCheck_text").text("");
		$("input[type=text][name=memberPhone]").val("");
		$("#memberPhone_text").text("");
	}
	//회원 정보 수정 submit 시 필수 체크
	$("form.model_form.b").submit(memberUpdateFromHandler);
	function memberUpdateFromHandler() { 
		//초기 비번 체크
		var $pwdCheck = $("input[type=password][name=pwdCheck]").val();
		var pwd = "${loginSsInfo.memberPwd}";
		if($pwdCheck != pwd) {
			alert("현재 비밀번호를 확인 해주세요.");
			return false;
		}
		
		//비밀번호 변경할 경우 체크
		var $pwdChange = $("input[type=password][name=pwdChange]").val();
		var $pwdChangeCheck = $("input[type=password][name=pwdChangeCheck]").val();
		var reg = /^(?=.*[a-z])(?=.*[0-9])(?=.*[_!@#$%])[A-Za-z0-9_!@#$%]{8,}$/;
		
		if($pwdChange != "" && $pwdChange != null) {
			if($pwdChange == pwd) {
				alert("새 비밀번호가 기존과 같습니다.");
				return false;
			} else if(!reg.test($pwdChange)) {
				alert("새 비밀번호가 조건에 맞지 않습니다.");
				return false;
			} else if($pwdChange != $pwdChangeCheck) {
				alert("새 비밀번호 확인이 새 비밀번호와 맞지 않습니다.");
				return false;
			}
		}
		
		//휴대폰 번호 변경할 경우 체크
		var $memberPhone = $("input[type=text][name=memberPhone]").val();
		
		var reg = /^[0][1][016789]-[0-9]{4}-[0-9]{4}$/;
		if($memberPhone != "" && $memberPhone != null) {
			if(!reg.test($memberPhone)) {
				alert("휴대폰 번호가 형식에 맞지 않습니다.");
				return false;
			} 
		}
		
	}
	//비밀번호 확인 문구안내
	$("input[type=password][name=pwdCheck]").on("propertychange change paste input",pwdCheckBluredHandler);
	function pwdCheckBluredHandler() {
		var $pwdCheck = $("input[type=password][name=pwdCheck]").val();
		var pwd = "${loginSsInfo.memberPwd}";
		
		if($pwdCheck == "" || $pwdCheck == null) {
			$("#pwdCheck_text").text("*필수");
			$("#pwdCheck_text").css("color","black");
		} else if($pwdCheck == pwd) {
			$("#pwdCheck_text").text("");
			$("#pwdCheck_text").css("color","black");
		} else {
			$("#pwdCheck_text").text("*현재 비밀번호를 입력 해주세요.");
			$("#pwdCheck_text").css("color","red");
		}
	}
	//변경할 비밀번호 문구안내
	$("input[type=password][name=pwdChange]").on("propertychange change paste input",pwdChangeBluredHandler);
	function pwdChangeBluredHandler() {
		var $pwdChange = $("input[type=password][name=pwdChange]").val();
		var pwd = "${loginSsInfo.memberPwd}";
		
		var reg = /^(?=.*[a-z])(?=.*[0-9])(?=.*[_!@#$%])[A-Za-z0-9_!@#$%]{8,}$/;
		if($pwdChange == pwd) {
			$("#pwdChange_text").text("*현재 비밀번호와 같습니다.");
			$("#pwdChange_text").css("color","red");
		} else if(reg.test($pwdChange) || $pwdChange == "") {
			$("#pwdChange_text").text("*최소 8자이상, 영문,숫자,특수문자(_!@#$% 가능) 최소 1개 이상");
			$("#pwdChange_text").css("color","black");
		} else {
			$("#pwdChange_text").text("*최소 8자이상 및 영문,숫자,특수문자(_!@#$% 가능)를 최소 1개씩 입력 해주세요.");
			$("#pwdChange_text").css("color","red");
		}
	} 
	//변경할 비밀번호 확인 문구안내
	$("input[type=password][name=pwdChangeCheck]").on("propertychange change paste input",pwdChangeCheckBluredHandler);
	function pwdChangeCheckBluredHandler() {
		var $pwdChangeCheck = $("input[type=password][name=pwdChangeCheck]").val();
		var $pwdChange = $("input[type=password][name=pwdChange]").val();
		if($pwdChangeCheck == $pwdChange) {
			$("#pwdChangeCheck_text").text("");
		} else {
			$("#pwdChangeCheck_text").text("*새 비밀번호와 동일하게 입력 해주세요.");
			$("#pwdChangeCheck_text").css("color","red");
		}
	}
	//휴대폰 번호 형식 문구안내
	$("input[type=text][name=memberPhone]").on("propertychange change paste input",memberPhoneCheckBluredHandler);
	function memberPhoneCheckBluredHandler() {
		var $memberPhone = $("input[type=text][name=memberPhone]").val();
		
		var reg = /^[0][1][016789]-[0-9]{4}-[0-9]{4}$/;
		if(reg.test($memberPhone) || $memberPhone == "") {
			$("#memberPhone_text").text("");
		} else {
			$("#memberPhone_text").text("*휴대폰 번호가 형식에 맞지 않습니다.");
			$("#memberPhone_text").css("color","red");
		}
	}
</script>
        </div>
    </div>
    <div class="modal c">
            <div class="modal_content c">
                <form class="model_form c" action="${pageContext.request.contextPath}/reviewUpdate.do" method="post" enctype="multipart/form-data">
                    <div class="form_cont c">
                        <h2>리뷰 수정</h2>
                        <input id="review_code_update" type="hidden" name="reviewCode2" value="0">
                        <h3>리뷰 작성</h3>
                        <textarea name="reviewCont2"></textarea>
                        <h3>사진 첨부</h3>
                        <input type="file" name="fileUpload2" multiple="multiple" accept="image/*">
                        <p class="f_10">*사진은 최대 5개까지 등록가능합니다.</p>
                        <h3>평점 및 추천</h3>
                        <h4>친절도</h4>
                        <div>
                            <input type="radio" name="kind2" id="kind21" value="1"><label class="f_14" for="kind21">불만족</label>
                            <input type="radio" name="kind2" id="kind22" value="2"><label class="f_14" for="kind22">약간 불만족</label>
                            <input type="radio" name="kind2" id="kind23" value="3"><label class="f_14" for="kind23">보통</label>
                            <input type="radio" name="kind2" id="kind24" value="4"><label class="f_14" for="kind24">약간 만족</label>
                            <input type="radio" name="kind2" id="kind25" value="5"><label class="f_14" for="kind25">만족</label>
                        </div>
                        <h4>시설</h4>
                        <div>
                            <input type="radio" name="facility2" id="facility21" value="1"><label class="f_14" for="facility21">불만족</label>
                            <input type="radio" name="facility2" id="facility22" value="2"><label class="f_14" for="facility22">약간 불만족</label>
                            <input type="radio" name="facility2" id="facility23" value="3"><label class="f_14" for="facility23">보통</label>
                            <input type="radio" name="facility2" id="facility24" value="4"><label class="f_14" for="facility24">약간 만족</label>
                            <input type="radio" name="facility2" id="facility25" value="5"><label class="f_14" for="facility25">만족</label>
                        </div>
                        <h4>수업구성</h4>
                        <div>
                            <input type="radio" name="component2" id="component21" value="1"><label class="f_14" for="component21">불만족</label>
                            <input type="radio" name="component2" id="component22" value="2"><label class="f_14" for="component22">약간 불만족</label>
                            <input type="radio" name="component2" id="component23" value="3"><label class="f_14" for="component23">보통</label>
                            <input type="radio" name="component2" id="component24" value="4"><label class="f_14" for="component24">약간 만족</label>
                            <input type="radio" name="component2" id="component25" value="5"><label class="f_14" for="component25">만족</label>
                        </div>
                        <h4>난이도 안내</h4>
                        <div>
                            <input type="radio" name="level2" id="level21" value="1"><label class="f_14" for="level21">불만족</label>
                            <input type="radio" name="level2" id="level22" value="2"><label class="f_14" for="level22">약간 불만족</label>
                            <input type="radio" name="level2" id="level23" value="3"><label class="f_14" for="level23">보통</label>
                            <input type="radio" name="level2" id="level24" value="4"><label class="f_14" for="level24">약간 만족</label>
                            <input type="radio" name="level2" id="level25" value="5"><label class="f_14" for="level25">만족</label>
                        </div>
                        <h4>유형 추천</h4>
                        <div>
                            <input type="radio" name="group2" value="0" style="display:none;" checked="checked">
                            <input type="radio" name="group2" id="group21" value="1"><label class="f_14" for="group21">혼자</label>
                            <input type="radio" name="group2" id="group22" value="2"><label class="f_14" for="group22">친구</label>
                            <input type="radio" name="group2" id="group23" value="3"><label class="f_14" for="group23">연인</label>
                            <input type="radio" name="group2" id="group24" value="4"><label class="f_14" for="group24">가족</label>
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