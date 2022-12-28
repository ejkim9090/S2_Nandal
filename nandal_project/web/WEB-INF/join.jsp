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
                    <form id="join_form" action="<%=request.getContextPath()%>/join.do" method="post">
                        <div class="input_area">
                            <div><label>아이디(이메일)</label><input type="text" name="memberId" placeholder="아이디(이메일)를 입력해주세요"></div>
                            <div><label>비밀번호</label><input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요"></div>
                            <p id="memberPwd_text" class="f_12">*최소 8자이상, 영문,숫자,특수문자(_!@#$% 가능) 최소 1개 이상</p>
                            <div><label>비밀번호 확인</label><input type="password" name="memberPwdCheck" placeholder="비밀번호 확인을 입력해주세요"></div>
                            <p id="memberPwdCheck_text" class="f_12"></p>
                            <div><label>이름</label><input type="text" name="memberName" placeholder="이름을 입력해주세요"></div>
                            <div><label>휴대폰</label><input type="text" name="memberPhone" placeholder="000-0000-0000"></div>
                            <p id="memberPhone_text" class="f_12"></p>
                        </div>
<script>
	$("#join_form").submit(memberInsertFromHandler);
	function memberInsertFromHandler() { 
		
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