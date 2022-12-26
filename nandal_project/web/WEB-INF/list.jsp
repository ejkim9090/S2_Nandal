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
</head>
<body>
    <div>
    <%@include file="/WEB-INF/share/header.jsp" %>
        <div id="section">
        <!-- TODO 여기에 만든 공간 넣기 -->
           <div class="wrap_1050">
                <div class="list_top">
                    <p>${cnt}개의 클래스</p>
                    <div>
                        <select>
                            <option value="">선택</option>
                            <option value="">인기순</option>
                            <option value="">높은 평점순</option>
                            <option value="">낮은 가격순</option>
                            <option value="">높은 가격순</option>
                        </select>
                        <select>
                            <option value="">선택</option>
                            <option value="">혼자</option>
                            <option value="">친구</option>
                            <option value="">연인</option>
                            <option value="">가족</option>
                        </select>
                    </div>
                </div>
                <div>
                <c:choose>
               	<c:when test="${empty classlist}">
					<div class="list_class">
                     지금 목록 정보가 없음
                    </div>
   				</c:when> 
   				<c:otherwise>
   					<c:set var="divCnt" value="0"/>
    				<c:forEach items="${classlist}" var="vo">
    				<c:if test="${divCnt mod 3 eq 0}">
    					<div class="list_class_wrap">
    				</c:if>
     				<a href="${pageContext.request.contextPath }/info/${vo.classCode}" class="list_class">
                         <div class="list_class_img_wrap">
                             <img src="${pageContext.request.contextPath}${vo.classImg}" alt="클래스 이미지">
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
                     </a>
                     <c:if test="${divCnt mod 3 eq 2}">
    					</div>
    				</c:if>
    				<c:set var="divCnt" value="${divCnt+1}"/>
    				</c:forEach>
   				</c:otherwise>
               </c:choose> 
                </div>
            </div>
            <div class="wrap_1050">
                <div class="pagination">
                <c:if test="${pageCnt ne 1}"> <!-- 총 페이지수가 1뿐이면 페이지목록 안만들기 -->
                	<c:if test="${startPage ne 1}">
						<a class="page_item pre" href="list?pagenum=${startPage-1 }&search=${searchword}"><img class="page_img" src="<%=request.getContextPath()%>/images/my_arrow_180.png" alt="이전"></a>
					</c:if>
					<c:forEach begin="${startPage }" end="${endPage }" var="p" step="1">
						<c:choose>
							<c:when test="${p eq currentPage}">
								<a class="page_item now" href="list?pagenum=${p }&search=${searchword}">${p }</a>
							</c:when>
							<c:otherwise>
								<a class="page_item" href="list?pagenum=${p }&search=${searchword}">${p }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${endPage < pageCnt}">
						<a class="page_item next" href="list?pagenum=${endPage+1 }&search=${searchword}"><img class="page_img" src="<%=request.getContextPath()%>/images/my_arrow.png" alt="다음"></a>
					</c:if>
                </c:if>
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