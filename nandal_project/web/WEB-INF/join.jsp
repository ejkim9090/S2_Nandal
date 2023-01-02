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
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
    <script src="<%=request.getContextPath()%>/js/share.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/join.css">
    
    <title>회원가입 페이지</title>
</head>
<body>
    <div>
    <%@include file="/WEB-INF/share/header.jsp" %>
        <div id="section">
            <div id="section_child_1">
                <div class="login_area f_20_b login_txt">회원가입</div>
                <div class="border_div"><p class="f_12">*전체입력 필수</p></div>
                <div class="login_area login_user_area">
                    <form id="join_form" action="<%=request.getContextPath()%>/join.do" method="post"  accept-charset="utf-8">
                        <div class="input_area">
                            <div><label>아이디(이메일)</label><input type="text" name="memberId" placeholder="아이디(이메일)를 입력해주세요" required><button id="IdCheck" class="IdCheck_btn" type="button">인증번호 받기</button></div>
                            <p id="memberId_text" class="f_12"></p>
                            <div id="memberIdCheck_div"><label></label><input type="text" name="memberIdCheck" placeholder="인증번호 입력"><div id="CheckTime"></div><input type="hidden" name="memberIdCheckCode"><button id="IdCheckCode" class="IdCheck_btn" type="button">인증번호 확인</button></div>
                            <div><label>비밀번호</label><input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요" required></div>
                            <p id="memberPwd_text" class="f_12">*최소 8자이상, 영문,숫자,특수문자(_!@#$% 가능) 최소 1개 이상</p>
                            <div><label>비밀번호 확인</label><input type="password" name="memberPwdCheck" placeholder="비밀번호 확인을 입력해주세요" required></div>
                            <p id="memberPwdCheck_text" class="f_12"></p>
                            <div><label>이름</label><input type="text" name="memberName" placeholder="이름을 입력해주세요" required></div>
                            <div><label>휴대폰</label><input type="text" name="memberPhone" placeholder="000-0000-0000" required></div>
                            <p id="memberPhone_text" class="f_12"></p>
                        </div>
<script>
	$("button#IdCheck").click(IdCheckAction);
	function IdCheckAction() {
		console.log("인증메일 보내기 함수 진입");
		var $memberId = $("input[type=text][name=memberId]").val();
		
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/;
		if(!regExp.test($memberId)) {
			$("#memberId_text").text("*이메일 형식에 맞지 않습니다.");
			$("#memberId_text").css("color","red");
		} else if($("#IdCheck").css("cursor") != "default") {
			$("#memberId_text").text("");
			$("#memberId_text").css("color","black");
			$.ajax({
	      		url : "<%=request.getContextPath()%>/joinIdCheck.lo",
	      		type : "post",
	      		data: "memberId=" + $memberId,
	      		success: function(data){ 
	      					if(data == 1) {
	      						$("#memberId_text").text("*이미 가입된 아이디(이메일)입니다.");
	      						$("#memberId_text").css("color","red");
	      						return;
	      					} else {
	      						$.ajax({
	      				      		url : "<%=request.getContextPath()%>/join.lo",
	      				      		type : "post",
	      				      		data: "memberId=" + $memberId,
	      				      		success: function(data){ 
	      				      					$("input[type=hidden][name=memberIdCheckCode]").val(data.replace("\n",""));
	      				      					$("input[type=text][name=memberId]").attr("readonly", "true");
	      				      					$("button#IdCheck").css("background-color","rgba(0,0,0,0.2)");
	      				      					$("button#IdCheck").css("cursor","default");
	      				      					$("#memberIdCheck_div").css("display", "flex");
	      				      					CheckTimer();
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
	}
	function CheckTimer() {
		var time = 180;
		var min = "";
		var sec = "";
		
		var x = setInterval(function(){
				min = parseInt(time/60);
				if(time%60 < 10) {
					sec = "0" + time%60;
				} else {
					sec = time%60;
				}
				
				$("#CheckTime").text(min+":"+sec);
				time--;
				
				if(time < 0) {
					clearInterval(x);
					alert("인증시간이 초과하였습니다.");
					location.reload();
				} else if($("button#IdCheckCode").css("cursor") == "default") {
					$("#CheckTime").text("");
					clearInterval(x);
				}
		}, 1000);
	}
	$("button#IdCheckCode").click(IdCheckCodeAction);
	function IdCheckCodeAction() {
		console.log("인증 함수 진입");
		var $memberIdCheck = $("input[type=text][name=memberIdCheck]").val();
		var $memberIdCheckCode = $("input[type=hidden][name=memberIdCheckCode]").val();
		console.log($memberIdCheck);
		console.log($memberIdCheckCode);
		if($memberIdCheck == $memberIdCheckCode) {
			console.log("인증 성공");
			alert("인증에 성공하셨습니다.");
			$("button#IdCheckCode").css("background-color","rgba(0,0,0,0.2)");
			$("button#IdCheckCode").css("cursor","default");
			$("button#IdCheckCode").text("인증 완료");
		} else {
			console.log("인증 실패");
			alert("인증에 실패하셨습니다. 인증번호를 확인바랍니다.");
		}
	}
	
	$("#join_form").submit(memberInsertFromHandler);
	function memberInsertFromHandler() { 
		//아이디 인증 체크
		if($("button#IdCheckCode").css("cursor") != "default") {
			alert("아이디(이메일)를 인증해주세요.");
			return false;
		}
		
		//비밀번호 체크
		var $memberPwd = $("input[type=password][name=memberPwd]").val();
		var $memberPwdCheck = $("input[type=password][name=memberPwdCheck]").val();
		var reg = /^(?=.*[a-z])(?=.*[0-9])(?=.*[_!@#$%])[A-Za-z0-9_!@#$%]{8,}$/;
		
		if($memberPwd != "" && $memberPwd != null) {
			if(!reg.test($memberPwd)) {
				alert("비밀번호가 조건에 맞지 않습니다.");
				return false;
			} else if($memberPwd != $memberPwdCheck) {
				alert("비밀번호 확인이 비밀번호와 맞지 않습니다.");
				return false;
			}
		}
		
		//휴대폰 번호 체크
		var $memberPhone = $("input[type=text][name=memberPhone]").val();
		
		var reg = /^[0][1][016789]-[0-9]{4}-[0-9]{4}$/;
		if($memberPhone != "" && $memberPhone != null) {
			if(!reg.test($memberPhone)) {
				alert("휴대폰 번호가 형식에 맞지 않습니다.");
				return false;
			} 
		}
	}
	//변경할 비밀번호 문구안내
	$("input[type=password][name=memberPwd]").on("propertychange change paste input",memberPwdChangeHandler);
	function memberPwdChangeHandler() {
		var $memberPwd = $("input[type=password][name=memberPwd]").val();
		
		var reg = /^(?=.*[a-z])(?=.*[0-9])(?=.*[_!@#$%])[A-Za-z0-9_!@#$%]{8,}$/;
		if(reg.test($memberPwd) || $memberPwd == "") {
			$("#memberPwd_text").text("*최소 8자이상, 영문,숫자,특수문자(_!@#$% 가능) 최소 1개 이상");
			$("#memberPwd_text").css("color","black");
		} else {
			$("#memberPwd_text").text("*최소 8자이상 및 영문,숫자,특수문자(_!@#$% 가능)를 최소 1개씩 입력 해주세요.");
			$("#memberPwd_text").css("color","red");
		}
	}
	//변경할 비밀번호 확인 문구안내
	$("input[type=password][name=memberPwdCheck]").on("propertychange change paste input",memberPwdCheckBluredHandler);
	function memberPwdCheckBluredHandler() {
		var $memberPwdCheck = $("input[type=password][name=memberPwdCheck]").val();
		var $memberPwd = $("input[type=password][name=memberPwd]").val();
		if($memberPwdCheck == $memberPwd || $memberPwdCheck == "") {
			$("#memberPwdCheck_text").text("");
		} else {
			$("#memberPwdCheck_text").text("*비밀번호와 동일하게 입력 해주세요.");
			$("#memberPwdCheck_text").css("color","red");
		}
	}
	//휴대폰 형식 문구안내
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
                		<div class="border_div"></div>
                        <div class="submit_area">
                            <button type="submit" id="login_btn">
                                <span class="f_16_b">회원가입</span>
                            </button>
                        </div>
                    </form>
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