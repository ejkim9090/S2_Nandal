<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
    <script src="<%=request.getContextPath()%>/js/info_api.js"></script>
    <script src="<%=request.getContextPath()%>/js/info.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=22e814de5ddfa7ab67223da7c1c400b9&libraries=services,clusterer,drawing"></script>

    <script>
     $( function() {
       $("#datepicker").on("change",datepickerClick);
       $("#buy_btn").on("click",classBuyHandler);
     });
     function buyTimeClickHandler() {
    	 console.log("라벨 클릭됨");
   	    // 다른 prod_tab에서 selected 삭제
   	    $(".buy_time").not($(this)).css("background-color","rgba(105,108,255,.85)");
   	    // 클릭한 prod_tab에서 selected 추가
   	    $(this).css("background-color","rgba(105,108,255,1)");
     }
     function classBuyHandler() {
    	 var formQuery = $("#buy_form").serialize();
    	 console.log(formQuery);
     }
     function datepickerClick() {
    	 var selected = $(this).val();
         console.log("선택날짜 : " + selected);
         
         //선택된 날짜의 요일 구하기 일:0,월:1,화:2,수:3, ...
         var today_num = new Date(selected).getDay();
         console.log(today_num);
         var day = 0;
         switch(today_num) {
         case 0: day = 64; break; 
         case 1: day = 1; break; 
         case 2: day = 2; break; 
         case 3: day = 4; break; 
         case 4: day = 8; break; 
         case 5: day = 16; break; 
         case 6: day = 32; break; 
         }
         console.log(day);
         
         var $buyTime = $("#buy_time_wrap");
         console.log($buyTime);
         $.ajax({
     		url : "<%=request.getContextPath()%>/date.lo",
     		type : "post",
     		data:
     		 	{
	     			classCode : ${classVo.classCode},
	   	   		  	date : selected,
	   	   		  	day : day,
     			  }, 
     	 	dataType : "json",  
     		success: function(data){ 
     					if(data != null) {
     						let addHtml = "";
     						for(var i = 0; i < data.length; i++) {
     							addHtml += "<input type='radio' name='csCode' value='"+data[i].csCode+"' id='csCode"+data[i].csCode+"'>"+"<label class='buy_time' for='csCode"+data[i].csCode+"'><h4 class='time_text'>"+data[i].csStime+" - "+data[i].csFtime+"<p class='f_12'>최대수강인원 "+${classVo.classMax}+"명</p></h4><p class='time_check'>선택</p></label>";
     						}
     						$buyTime.html(addHtml);
     					} else {
     						$buyTime.html("<label class='buy_time'><h4 class='time_text'>해당 날짜에 일정이 없습니다.</h4></label>");
     					}
     			     	//일정에 시간대 선택 시
     			       $(".buy_time").on("click",buyTimeClickHandler);
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
	                    <form id="buy_form">
	                            <div id="datepicker"></div>
	                            <div id="buy_time_wrap">
	                            	<label class='buy_time'><h4 class='time_text'>날짜를 선택해주세요.</h4></label>
	                            </div>
	                            <div>
	                                <div class="option_label_wrap">
	                                    <label class="f_14_b">세부옵션</label>
	                                    <label class="f_14_b">인원</label>
	                                </div>
	                                <div class="option_select_wrap">
	                                    <select name="buyOption" class="c_line buy_option">
	                                        <option value="0">세부옵션 선택</option>
	                                        <c:if test="${empty coList}">
	                                        	<option value="99">옵션이 없습니다</option>
	                                        </c:if>
	                                        <c:if test="${not empty coList}">
					                        	<c:forEach items="${coList}" var="vo">
	                                       			 <option value="${vo.coCode}">${vo.coName} ${vo.coPrice}원</option>
					                        	</c:forEach>
					                        </c:if>
					                        </div>
					                        <div>
					                        <c:if test="${not empty cpIntroList}">
					                        	<c:forEach items="${cpIntroList}" var="vo">
					                            	<img class="intro_img" src=".${vo.cpRoute}" alt="소개 이미지">
					                        	</c:forEach>
					                        </c:if>
	                                    </select>
	                                    <select name="buyNum" class="c_line buy_num">
	                                        <option value="0">인원 선택</option>
	                                        <c:forEach var="num" begin="${classVo.classMin}" end="${classVo.classMax}" step="1">
	                                       		 <option value="${num}">${num}명</option>
	                                        </c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                            <button type="button" id="buy_btn">
	                                <span class="f_16_b">클래스 신청</span>
	                            </button>
		                </form>
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
                        <c:choose>
                        <c:when test="${empty reList}">
	                        <div class="review_total">
	                            <div class="review_score_wrap">
	                                <img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
	                                <p class="review_score f_16">0.0</p>
	                                <img class="review_arrow" src="<%=request.getContextPath()%>/images/review_arrow.webp">
	                                <div class="score_detail_wrap">
	                                    <div class="score_detail"><label class="f_16_b">친절도</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 0.0</p></div>
	                                    <div class="score_detail"><label class="f_16_b">시설</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 0.0</p></div>
	                                    <div class="score_detail"><label class="f_16_b">수업구성</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 0.0</p></div>
	                                    <div class="score_detail"><label class="f_16_b">난이도 안내</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png"> 0.0</p></div>
	                                </div>
	                            </div>
	                            <div class="review_total_rmd"><label class="f_12_b total_rmd">추천:</label><p class="f_12">혼자(0) 연인(0) 친구(0) 가족(0)</p></div>
	                        </div>
                        	<div class="review_wrap">
	                            <p class="f_16_b">리뷰 목록이 없습니다.</p>
	                        </div>
                        </c:when>
                        <c:otherwise>
	                        <div class="review_total">
	                            <div class="review_score_wrap">
	                                <img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
	                                <p class="review_score f_16">
	                                <!-- 리뷰 총점 평균 -->
	                                <c:set var="gradeNum" value="0"/>
	                                <c:set var="grade" value="0.0"/>
	                                <c:forEach items="${reList}" var="vo">
	                                	<c:set var="gradeNum" value="${gradeNum + 1}" />
	                                	<c:set var="grade" value="${grade + vo.reviewGrade}" />
	                                </c:forEach>
	                                <c:set var="grade" value="${grade div gradeNum}"/>
	                                <fmt:formatNumber var="grade" value="${grade}" pattern=".0"/>
	                                ${grade}(${gradeNum})
	                                </p>
	                                <img class="review_arrow" src="<%=request.getContextPath()%>/images/review_arrow.webp">
	                                <div class="score_detail_wrap">
	                                    <div class="score_detail">
	                                    	<label class="f_16_b">친절도</label>
	                                    	<p>&#9867;&#9867;&#9867;&#9867;&#9867;</p>
	                                    	<p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
	                                		<!-- 친절도 총점 평균 -->
	                                    	<c:set var="kindNum" value="0"/>
			                                <c:set var="kind" value="0.0"/>
			                                <c:forEach items="${reList}" var="vo">
			                                	<c:set var="kindNum" value="${kindNum + 1}" />
			                                	<c:set var="kind" value="${kind + vo.reviewKind}" />
			                                </c:forEach>
			                                <c:set var="kind" value="${kind div kindNum}"/>
			                                <fmt:formatNumber var="kind" value="${kind}" pattern=".0"/>
			                                ${kind}
	                                    	</p>
	                                    </div>
	                                    <div class="score_detail">
	                                    	<label class="f_16_b">시설</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p>
	                                    	<p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
	                                    	<!-- 시설 총점 평균 -->
	                                    	<c:set var="componentNum" value="0"/>
			                                <c:set var="component" value="0.0"/>
			                                <c:forEach items="${reList}" var="vo">
			                                	<c:set var="componentNum" value="${componentNum + 1}" />
			                                	<c:set var="component" value="${component + vo.reviewComponent}" />
			                                </c:forEach>
			                                <c:set var="component" value="${component div componentNum}"/>
			                                <fmt:formatNumber var="component" value="${component}" pattern=".0"/>
			                                ${component}
	                                    	</p>
	                                    </div>
	                                    <div class="score_detail">
	                                    	<label class="f_16_b">수업구성</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p>
	                                    	<p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
	                                    	<!-- 수업구성 총점 평균 -->
	                                    	<c:set var="facilityNum" value="0"/>
			                                <c:set var="facility" value="0.0"/>
			                                <c:forEach items="${reList}" var="vo">
			                                	<c:set var="facilityNum" value="${facilityNum + 1}" />
			                                	<c:set var="facility" value="${facility + vo.reviewFacility}" />
			                                </c:forEach>
			                                <c:set var="facility" value="${facility div facilityNum}"/>
			                                <fmt:formatNumber var="facility" value="${facility}" pattern=".0"/>
			                                ${facility}
			                                </p>
	                                    </div>
	                                    <div class="score_detail">
	                                    	<label class="f_16_b">난이도 안내</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p>
	                                    	<p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
	                                    	<!-- 난이도 총점 평균 -->
	                                    	<c:set var="levelNum" value="0"/>
			                                <c:set var="level" value="0.0"/>
			                                <c:forEach items="${reList}" var="vo">
			                                	<c:set var="levelNum" value="${levelNum + 1}" />
			                                	<c:set var="level" value="${level + vo.reviewLevel}" />
			                                </c:forEach>
			                                <c:set var="level" value="${level div levelNum}"/>
			                                <fmt:formatNumber var="level" value="${level}" pattern=".0"/>
			                                ${level}
	                                    	</p>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="review_total_rmd"><label class="f_12_b total_rmd">추천:</label>
	                            <p class="f_12">
	                            <c:set var="one" value="0"/>
	                            <c:set var="two" value="0"/>
	                            <c:set var="three" value="0"/>
	                            <c:set var="four" value="0"/>
	                            <c:forEach items="${reList}" var="vo">
	                            	<c:choose>
	                                	<c:when test="${vo.reviewGroup eq 1}">
	                                		<c:set var="one" value="${one + 1}"/>
	                                	</c:when>
	                                	<c:when test="${vo.reviewGroup eq 2}">
	                                		<c:set var="two" value="${two + 1}"/>
	                                	</c:when>
	                                	<c:when test="${vo.reviewGroup eq 3}">
	                                		<c:set var="three" value="${three + 1}"/>
	                                	</c:when>
	                                	<c:when test="${vo.reviewGroup eq 4}">
	                                		<c:set var="four" value="${four + 1}"/>
	                                	</c:when>
	                                </c:choose>
	                            </c:forEach>
	                            혼자(${one}) 연인(${three}) 친구(${two}) 가족(${four})
	                            </p></div>
	                        </div>
                        	<c:forEach items="${reList}" var="vo">
                        		<div class="review_wrap">
		                            <p class="f_16_b">${vo.memberName}</p>
		                            <div class="review_score_wrap">
		                                <img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">
		                                <p class="review_score f_16">${vo.reviewGrade}</p>
		                                <img class="review_arrow" src="<%=request.getContextPath()%>/images/review_arrow.webp">
		                                <c:if test="${vo.reviewGroup ne 0}">
			                                <c:choose>
			                                	<c:when test="${vo.reviewGroup eq 1}">
			                                		<c:set var="group" value="혼자"/>
			                                	</c:when>
			                                	<c:when test="${vo.reviewGroup eq 2}">
			                                		<c:set var="group" value="친구"/>
			                                	</c:when>
			                                	<c:when test="${vo.reviewGroup eq 3}">
			                                		<c:set var="group" value="연인"/>
			                                	</c:when>
			                                	<c:when test="${vo.reviewGroup eq 4}">
			                                		<c:set var="group" value="가족"/>
			                                	</c:when>
			                                </c:choose>
			                                	&nbsp;&nbsp;${group}
		                                </c:if>
		                                <div class="score_detail_wrap">
		                                    <div class="score_detail">
		                                    	<label class="f_16_b">친절도</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">${vo.reviewKind}</p>
		                                    </div>
		                                    <div class="score_detail">
		                                    	<label class="f_16_b">시설</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">${vo.reviewComponent}</p>
		                                    </div>
		                                    <div class="score_detail">
		                                    	<label class="f_16_b">수업구성</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">${vo.reviewFacility}</p>
		                                    </div>
		                                    <div class="score_detail">
		                                    	<label class="f_16_b">난이도 안내</label><p>&#9867;&#9867;&#9867;&#9867;&#9867;</p><p><img class="review_star"src="<%=request.getContextPath()%>/images/review_star_full.png">${vo.reviewLevel}</p>
		                                    </div>
		                                </div>
		                            </div>
		                            <p>${vo.reviewCont}</p>
		                            
		                            <c:if test="${not empty vo.rpRoute}">
		                            	<c:forEach items="${vo.rpRoute}" var="rpVo">
		                            		<img src=".${rpVo}" alt="리뷰이미지">
		                            	</c:forEach>
		                            </c:if>   
		                        </div>
                        	</c:forEach>
                        </c:otherwise>
                        </c:choose>
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