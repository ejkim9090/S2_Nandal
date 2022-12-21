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

	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.structure.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery-ui.theme.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker_theme.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/info.css">
    
    <script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
    <script src="<%=request.getContextPath()%>/js/info.js"></script>
    <script src="<%=request.getContextPath()%>/js/info_api.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=22e814de5ddfa7ab67223da7c1c400b9&libraries=services,clusterer,drawing"></script>

    

    <script>
     $( function() {
       $("#datepicker").on("change",function(){
           var selected = $(this).val();
           console.log("선택날짜 : " + selected);
           
           //선택된 날짜의 요일 구하기 일:0,월:1,화:2,수:3, ...
           var today_num = new Date(selected).getDay();
           console.log(today_num);
       });
     });
     </script>
    <title>상세페이지</title>
</head>
<body>
    <div>
    <%@include file="/WEB-INF/share/header.jsp" %>
        <div id="section">
            <div class="wrap_1050 infoAll_wrap">
                <div> <!--상단-->
                    <div class="wrap_1050">
                        <div class="info_top_first">         
                            <div class="top_class_img"> <!--상단 이미지 -->
                                <div class="top_img_wrap">
                                    <img class="top_img" src=".${classVo.classImg}">
                                </div>
                                <div class="sub_list">
                                    <div class="sub_list_swiper_wrap">
                                        <div class="sub_list_swiper">     
                                            <div class="sub_img_list">
                                                <img class="sub_img" src=".${classVo.classImg}" alt="대표 이미지">
                                            </div>
                                            <c:if test="${not empty cpSubList}">
                                            	<c:forEach items="${cpSubList}" var="vo">
                                            		<div class="sub_img_list">
		                                                <img class="sub_img" src=".${vo.cpRoute}" alt="서브 이미지">
		                                            </div>
                                            	</c:forEach>
                                            </c:if>
                                        </div>
                                    </div>
                                </div> 
                            </div>
                        </div>
                        <div class="top_info_wrap"> <!-- 상단 클래스 요약 정보 -->
                            <div class="f_20_b">${classVo.className}</div>
                            <div>
                                <div class="top_info_text"><img src="">${classVo.classAlltime}</div>
                                <div class="top_info_text"><img src="">${classVo.classLevel}</div>
                                <div class="top_info_text"><img src="">${classVo.classMin}~${classVo.classMax}</div>
                                <div class="top_info_text"><img src="">${sumAddress}</div>
                            </div>
                        </div>
                        <div id="intro"></div>
                    </div>
                </div>
                <div class="prod_nav wrap_1050">
                    <nav class="wrap_1050">
                        <div class="prod_tab selected">
                            <a href="#intro" class="active"><span class="f_16_b">클래스 소개</span></a>
                        </div>
                        <div class="prod_tab">
                            <a href="#cur"><span class="f_16_b">커리큘럼</span></a>
                        </div>
                        <div class="prod_tab">
                            <a href="#host"><span class="f_16_b">호스트 소개</span></a>
                        </div>
                        <div class="prod_tab">
                            <a href="#prd"><span class="f_16_b">제공 및 유의사항</span></a>
                        </div>
                        <div class="prod_tab">
                            <a href="#address" class="active"><span class="f_16_b">위치</span></a>
                        </div>
                        <div class="prod_tab">
                            <a href="#review"><span class="f_16_b">후기</span></a>
                        </div>
                        <div id="nav_buy">
                            <span class="f_16_b">클래스 신청</span>
                        </div>
                    </nav>
                </div>
                <div class="buy_pick_wrap" > <!--일정 선택-->
                    <div class="buy_pick">
                            <div id="datepicker"></div>
                            <div class="buy_time_wrap">
                                <input type="radio" name="csCode" value="1" id="csCode1">
                                <label class="buy_time" for="csCode1"><h4 class="time_text">15:30 - 17:30<p class="f_12">최대수강인원 4명</p></h4><p class="time_check">선택</p></label>
                                <input type="radio" name="csCode" value="2" id="csCode2">
                                <label class="buy_time" for="csCode2"><h4 class="time_text">15:30 - 17:30<p class="f_12">최대수강인원 4명</p></h4><p class="time_check">선택</p></label>
                                <input type="radio" name="csCode" value="3" id="csCode3">
                                <label class="buy_time" for="csCode3"><h4 class="time_text">15:30 - 17:30<p class="f_12">최대수강인원 4명</p></h4><p class="time_check">선택</p></label>
                            </div>
                            <div>
                                <div class="option_label_wrap">
                                    <label class="f_14_b">세부옵션</label>
                                    <label class="f_14_b">인원</label>
                                </div>
                                <div class="option_select_wrap">
                                    <select name="buyOption" class="c_line buy_option">
                                        <option value="N">세부옵션 선택</option>
                                        <option value="A">옵션A 3000원</option>
                                        <option value="B">옵션B 4000원</option>
                                        <option value="C">옵션C 5000원</option>
                                    </select>
                                    <select name="buyNum" class="c_line buy_num">
                                        <option value="0">인원 선택</option>
                                        <option value="1">1명</option>
                                        <option value="2">2명</option>
                                        <option value="3">3명</option>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" id="buy_btn">
                                <span class="f_16_b">클래스 신청</span>
                            </button>
                    </div>
                </div>
                <div class="wrap_1050 info_wrap">
                    <div> <!-- 클래스 소개-->
                        <h2 class="info_h2">클래스 소개</h2>
                        <div>
                        <c:if test="${not empty introList}">
                        	<c:forEach items="${introList}" var="intro">
                            	<p>${intro}</p>
                            	<br>
                        	</c:forEach>
                        </c:if>
                        </div>
                        <div>
                        <c:if test="${not empty cpIntroList}">
                        	<c:forEach items="${cpIntroList}" var="vo">
                            	<img class="intro_img" src=".${vo.cpRoute}" alt="소개 이미지">
                        	</c:forEach>
                        </c:if>
                        </div>
                        <div id="cur"></div>
                    </div>
                    <div> <!-- 커리큘럼-->
                        <h2 class="info_h2">커리큘럼</h2>
                        <div>
                        <c:if test="${not empty curList}">
                        	<c:forEach items="${curList}" var="cur">
                            	<p>${cur}</p>
                            	<br>
                        	</c:forEach>
                        </c:if>
                        </div>
                        <div id="host"></div>
                    </div>
                    <div> <!-- 호스트 소개-->
                        <h2 class="info_h2">호스트 소개</h2>
                        <div>
	                        <c:if test="${not empty hostList}">
	                        	<c:forEach items="${hostList}" var="host">
	                            	<p>${host}</p>
	                            	<br>
	                        	</c:forEach>
	                        </c:if>
                        </div>
                        <div id="prd"></div>
                    </div>
                    <div> <!-- 제공 및 유의사항-->
                        <h2 class="info_h2">기타 제공사항</h2>
                        <div>
                        <c:if test="${not empty prdList}">
                        	<c:forEach items="${prdList}" var="prd">
                            	<p>${prd}</p>
                            	<br>
                        	</c:forEach>
                        </c:if>
                        </div>
                        <div id="address"></div>
                    </div>
                    <div> <!-- 위치-->
                        <h2 class="info_h2">위치</h2>
                            <p id = "address_text">${classVo.classAddress}</p>
                        <div id="map"></div>
                        <div id="review"></div>
                    </div>
                    <div> <!-- 후기-->
                        <h2 class="info_h2">후기</h2>
                        <div class="review_total">
                            <div class="review_score_wrap">
                                <img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
                                <p class="review_score f_16">4.7(2)</p>
                                <img class="review_arrow" src="<%=request.getContextPath()%>/images/review_arrow.webp">
                                <div class="score_detail_wrap">
                                    <div class="score_detail"><label class="f_16_b">친절도</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                    <div class="score_detail"><label class="f_16_b">시설</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                    <div class="score_detail"><label class="f_16_b">수업구성</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                    <div class="score_detail"><label class="f_16_b">난이도 안내</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                </div>
                            </div>
                            <div class="review_total_rmd"><label class="f_12_b total_rmd">추천:</label><p class="f_12">혼자(0) 연인(0) 친구(0) 가족(0)</p></div>
                        </div>
                        <div class="review_wrap">
                            <p class="f_16_b">김*수</p>
                            <div class="review_score_wrap">
                                <img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
                                <p class="review_score f_16">4.7(2)</p>
                                <img class="review_arrow" src="<%=request.getContextPath()%>/images/review_arrow.webp">
                                <div class="score_detail_wrap">
                                    <div class="score_detail"><label class="f_16_b">친절도</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                    <div class="score_detail"><label class="f_16_b">시설</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                    <div class="score_detail"><label class="f_16_b">수업구성</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                    <div class="score_detail"><label class="f_16_b">난이도 안내</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 4.8</p></div>
                                </div>
                            </div>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in magna libero. Sed nec pharetra nunc. Curabitur in magna libero.Sed nec pharetra nunc.
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur in magna libero. Sed nec pharetra nunc. Curabitur in magna libero.Sed nec pharetra nunc.    
                            </p>
                            <img src="https://img-cf.kurly.com/cdn-cgi/image/width=400,format=auto/shop/data/goods/1654826608504l0.jpg" alt="리뷰이미지">          
                        </div>
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
</body>
</html>