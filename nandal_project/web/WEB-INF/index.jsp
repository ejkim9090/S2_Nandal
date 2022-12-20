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
    <script src="<%=request.getContextPath()%>/js/index_section.js"></script>
    <title>메인페이지</title>
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
                        <img class="main_img" src=" ">
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
			                   				<div class="list_class">
		                                        <div class="list_class_img_wrap">
		                                            <img src="${vo.classImg}" alt="클래스 이미지">
		                                        </div>
		                                        <div class="list_class_info_wrap">
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
		                                    </div>
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
                                    <div class="list_class">
                                        <div class="list_class_img_wrap">
                                            <img src="https://img-cf.kurly.com/cdn-cgi/image/width=400,format=auto/shop/data/goods/1654826608504l0.jpg" alt="클래스 이미지">
                                        </div>
                                        <div class="list_class_info_wrap">
                                            <h3 class="f_16_b">
                                                [워커힐] 전복 삼계탕
                                            </h3>
                                            <span class="f_14_b">
                                                34,000 원
                                            </span>
                                            <span class="f_14_b">
                                                34,000 원
                                            </span>
                                        </div>
                                    </div>
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
                                    <div class="list_class">
                                        <div class="list_class_img_wrap">
                                            <img src="https://img-cf.kurly.com/cdn-cgi/image/width=400,format=auto/shop/data/goods/1654826608504l0.jpg" alt="클래스 이미지">
                                        </div>
                                        <div class="list_review_text">
                                            <span class="f_12">
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in magna libero. Sed nec pharetra nunc. Curabitur in magna libero.Sed nec pharetra nunc.
                                            </span>
                                        </div>
                                    </div>
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
</body>
</html>