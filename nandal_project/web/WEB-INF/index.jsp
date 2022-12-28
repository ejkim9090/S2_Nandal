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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
    <script src="<%=request.getContextPath()%>/js/share.js"></script>
    
    <title>메인 페이지</title>
    <!-- 전체공통 -->
</head>
<body>
    <div>
    <%@include file="/WEB-INF/share/header.jsp" %>
        <div id="section">
            <div class="main_section">
                <!-- 맨위 상단 섹션-->
                <div>
                    <div class="main_img_wrap">
                        <img class="main_img" src="<%=request.getContextPath()%>/images/index_top_img.jpg">
                    </div>
                </div>
                <!-- 첫번째 섹션 -->
                <div>               
                    <div class="wrap_1050 rmd">
                        <div class="rmd_title_wrap">
                            <div>
                                <span class="rmd_title_text">
                                    행복한 크리스마스
                                </span>
                            </div>
                        </div>
                        <div class="rmd_list">
                            <div class="rmd_list_swiper_wrap">
                                <div class="rmd_list_swiper">  
                                <c:choose>
                                	<c:when test="${empty classlist1}">
                   						<div class="list_class">
	                                        지금 목록 정보가 없음
	                                    </div>
	                   				</c:when> 
	                   				<c:otherwise>
		                   				<c:forEach items="${classlist1}" var="vo">
			                   				<a href="${pageContext.request.contextPath }/info/${vo.classCode}" class="list_class">
		                                        <div class="list_class_img_wrap">
		                                            <img src="${pageContext.request.contextPath}${vo.classImg}" alt="클래스 이미지">
		                                        </div>
		                                        <div class="list_class_info_wrap">
		                                        	<div class="allavg"><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"><span class="f_14_b">${vo.allAvg}(${vo.allCnt})</span></div>
		                                            <h3 class="f_16_b">
		                                                ${vo.className}
		                                            </h3>
		                                            <span class="f_14_b">
		                                                ${vo.classAddress}
		                                            </span>
		                                            <span class="f_14_b">
		                                                ${vo.classPrice}원
		                                            </span>
		                                        </div>
		                                    </a>
		                   				</c:forEach>
	                   				</c:otherwise>
                                </c:choose>  
                                </div>
                            </div>
                            <button type="button" class="rmd_left_btn"></button>
                            <button type="button" class="rmd_right_btn"></button>
                        </div>
                    </div>
                </div>
                <div>               
                    <div class="wrap_1050 rmd">
                        <div class="rmd_title_wrap">
                            <div>
                                <span class="rmd_title_text">
                                    연인과 데이트 추천
                                </span>
                            </div>
                        </div>
                        <div class="rmd_list">
                            <div class="rmd_list_swiper_wrap">
                                <div class="rmd_list_swiper">     
                                <c:choose>
                                	<c:when test="${empty classlist2}">
                   						<div class="list_class">
	                                        지금 목록 정보가 없음
	                                    </div>
	                   				</c:when> 
	                   				<c:otherwise>
		                   				<c:forEach items="${classlist2}" var="vo">
			                   				<a href="${pageContext.request.contextPath }/info/${vo.classCode}" class="list_class">
		                                        <div class="list_class_img_wrap">
		                                            <img src="${pageContext.request.contextPath}${vo.classImg}" alt="클래스 이미지">
		                                        </div>
		                                        <div class="list_class_info_wrap">
		                                        <div class="allavg"><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"><span class="f_14_b">${vo.allAvg}(${vo.allCnt})</span></div>
		                                            <h3 class="f_16_b">
		                                                ${vo.className}
		                                            </h3>
		                                            <span class="f_14_b">
		                                                ${vo.classAddress}
		                                            </span>
		                                            <span class="f_14_b">
		                                                ${vo.classPrice}원
		                                            </span>
		                                        </div>
		                                    </a>
		                   				</c:forEach>
	                   				</c:otherwise>
                                </c:choose>      
                                </div>
                            </div>
                            <button type="button" class="rmd_left_btn"></button>
                            <button type="button" class="rmd_right_btn"></button>
                        </div>
                    </div>
                </div>
                <div>               
                    <div class="wrap_1050 rmd">
                        <div class="rmd_title_wrap">
                            <div>
                                <span class="rmd_title_text">
                                    가족과 함께~!
                                </span>
                            </div>
                        </div>
                        <div class="rmd_list">
                            <div class="rmd_list_swiper_wrap">
                                <div class="rmd_list_swiper">     
                                <c:choose>
                                	<c:when test="${empty classlist3}">
                   						<div class="list_class">
	                                        지금 목록 정보가 없음
	                                    </div>
	                   				</c:when> 
	                   				<c:otherwise>
		                   				<c:forEach items="${classlist3}" var="vo">
			                   				<a href="${pageContext.request.contextPath }/info/${vo.classCode}" class="list_class">
		                                        <div class="list_class_img_wrap">
		                                            <img src="${pageContext.request.contextPath}${vo.classImg}" alt="클래스 이미지">
		                                        </div>
		                                        <div class="list_class_info_wrap">
		                                        <div class="allavg"><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"><span class="f_14_b">${vo.allAvg}(${vo.allCnt})</span></div>
		                                            <h3 class="f_16_b">
		                                                ${vo.className}
		                                            </h3>
		                                            <span class="f_14_b">
		                                                ${vo.classAddress}
		                                            </span>
		                                            <span class="f_14_b">
		                                                ${vo.classPrice}원
		                                            </span>
		                                        </div>
		                                    </a>
		                   				</c:forEach>
	                   				</c:otherwise>
                                </c:choose>      
                                </div>
                            </div>
                            <button type="button" class="rmd_left_btn"></button>
                            <button type="button" class="rmd_right_btn"></button>
                        </div>
                    </div>
                </div>
                <div>               
                    <div class="wrap_1050 rmd">
                        <div class="rmd_title_wrap">
                            <div>
                                <span class="rmd_title_text">
                                    REVIEW
                                </span>
                            </div>
                        </div>
                        <div class="rmd_list">
                            <div class="rmd_list_swiper_wrap">
                                <div class="rmd_list_swiper">  
                                <c:choose>
	                                <c:when test="${empty reviewlist}">
	                                	<div class="list_class">
	                                        지금 목록 정보가 없음
	                                    </div>
	                                </c:when>
	                                <c:otherwise>
		                                <c:forEach items="${reviewlist}" var="vo">
		                                	<a href="${pageContext.request.contextPath }/info/${vo.classCode}" class="list_class relist">
		                                        <div class="list_class_img_wrap relist">
		                                            <img src="${pageContext.request.contextPath}${vo.rpRoute}" alt="리뷰 이미지">
		                                        </div>
		                                        <div class="list_review_text">
		                                            <span class="f_16_b f_k_hiMelody">
		                                                ${vo.reviewCont}
		                                            </span>
		                                        </div>
		                                    </a>
		                                </c:forEach>
	                                </c:otherwise>
                                </c:choose>   
                                </div>
                            </div>
                            <button type="button" class="rmd_left_btn"></button>
                            <button type="button" class="rmd_right_btn"></button>
                        </div>
                    </div>
                </div>
            <div id="scrollup">
                <a href="#">
                    <span></span>
                </a>
            </div>
        </div>
        </div>
    <%@include file="/WEB-INF/share/footer.jsp" %>
    </div>
<script>
        //각 목록 좌우 넘기기
        //크리스마스 추천 목록
        var $acnt = ${classlist1Cnt};
        var acnt = 0;
        if($acnt != 0 && $acnt%3 == 0) {acnt = Math.floor($acnt/3) - 1;}
        else {acnt = Math.floor($acnt/3);}
        var a = 0;
        document.getElementsByClassName("rmd_right_btn")[0].onclick = function () {
            a += -1137;
            var t_value = "translate3d(" + a +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[0].style.transform = t_value;

            document.getElementsByClassName("rmd_left_btn")[0].style.display = "inline-block";
            if(a == -1137*acnt) {
                document.getElementsByClassName("rmd_right_btn")[0].style.display = "none";
            }
        }
        document.getElementsByClassName("rmd_left_btn")[0].onclick = function () {
            a += +1137;
            var t_value = "translate3d(" + a +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[0].style.transform = t_value;

            document.getElementsByClassName("rmd_right_btn")[0].style.display = "inline-block";
            if(a == 0) {
                document.getElementsByClassName("rmd_left_btn")[0].style.display = "none";
            }
        }
        //연인 추천 유형
        var $bcnt = ${classlist2Cnt};
        var bcnt = 0;
        if($bcnt != 0 && $bcnt%3 == 0) {bcnt = Math.floor($bcnt/3) - 1;}
        else {bcnt = Math.floor($bcnt/3);}
        var b = 0;
        document.getElementsByClassName("rmd_right_btn")[1].onclick = function () {
            b += -1137;
            var t_value = "translate3d(" + b +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[1].style.transform = t_value;

            document.getElementsByClassName("rmd_left_btn")[1].style.display = "inline-block";
            if(b == -1137*bcnt) {
                document.getElementsByClassName("rmd_right_btn")[1].style.display = "none";
            }
        }
        document.getElementsByClassName("rmd_left_btn")[1].onclick = function () {
            b += +1137;
            var t_value = "translate3d(" + b +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[1].style.transform = t_value;

            document.getElementsByClassName("rmd_right_btn")[1].style.display = "inline-block";
            if(b == 0) {
                document.getElementsByClassName("rmd_left_btn")[1].style.display = "none";
            }
        }
        //가족 추천
        var $ccnt = ${classlist3Cnt};
        var ccnt = 0;
        if($ccnt != 0 && $ccnt%3 == 0) {ccnt = Math.floor($ccnt/3) - 1;}
        else {ccnt = Math.floor($ccnt/3);}
        var c = 0;
        document.getElementsByClassName("rmd_right_btn")[2].onclick = function () {
            c += -1137;
            var t_value = "translate3d(" + c +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[2].style.transform = t_value;

            document.getElementsByClassName("rmd_left_btn")[2].style.display = "inline-block";
            if(c == -1137*ccnt) {
                document.getElementsByClassName("rmd_right_btn")[2].style.display = "none";
            }
        }
        document.getElementsByClassName("rmd_left_btn")[2].onclick = function () {
            c += +1137;
            var t_value = "translate3d(" + c +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[2].style.transform = t_value;

            document.getElementsByClassName("rmd_right_btn")[2].style.display = "inline-block";
            if(c == 0) {
                document.getElementsByClassName("rmd_left_btn")[2].style.display = "none";
            }
        }
        //리뷰 총점 4점이상 사진있는 리뷰
        var $dcnt = ${reviewlistCnt};
        var dcnt = 0;
        if($dcnt != 0 && $dcnt%3 == 0) {dcnt = Math.floor($dcnt/3) - 1;}
        else {dcnt = Math.floor($dcnt/3);}
        var d = 0;
        document.getElementsByClassName("rmd_right_btn")[3].onclick = function () {
            d += -1128;
            var t_value = "translate3d(" + d +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[3].style.transform = t_value;

            document.getElementsByClassName("rmd_left_btn")[3].style.display = "inline-block";
            if(d == -1128*dcnt) {
                document.getElementsByClassName("rmd_right_btn")[3].style.display = "none";
            }
        }
        document.getElementsByClassName("rmd_left_btn")[3].onclick = function () {
            d += +1128;
            var t_value = "translate3d(" + d +"px, 0px, 0px)";
            document.getElementsByClassName("rmd_list_swiper")[3].style.transform = t_value;

            document.getElementsByClassName("rmd_right_btn")[3].style.display = "inline-block";
            if(d == 0) {
                document.getElementsByClassName("rmd_left_btn")[3].style.display = "none";
            }
        }
    </script>
</body>
</html>