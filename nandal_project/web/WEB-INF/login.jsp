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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
    <script src="<%=request.getContextPath()%>/js/share.js"></script>

    <title>로그인 - 컬리</title>
</head>
<body>
    <div>
    <%@include file="/WEB-INF/share/header.jsp" %>
        <div id="section">
            <div id="section_child_1">
                <div class="login_area f_20_b login_txt">로그인</div>
                <div class="login_area login_user_area">
                    <form action="<%=request.getContextPath()%>/login.do" method="post">
                        <div class="input_area">
                            <input type="text" id="login_Id" name="memberId" placeholder="아이디를 입력해주세요">
                            <input type="password" id="login_Pwd" name="memberPwd" placeholder="비밀번호를 입력해주세요">
                        </div>
                        <div class="submit_area">
                            <button type="submit" id="login_btn">
                                <span class="f_16_b">로그인</span>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    <%@include file="/WEB-INF/share/footer.jsp" %>
    </div>
</body>
</html>