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
<script>
	$(function(){
		listDataAction();
		$("#btn_search").on("click",listDataAction);
	});
	var pagenum = 1;
	function pageItemHandler(num) {
		pagenum = num;
		listDataAction();
	}
	function listDataAction() {
		$("#search_form").serialize();
		console.log($("#search_form").serialize());
		
		
		$.ajax({
	   		url : "<%=request.getContextPath()%>/list.lo",
	   		type : "get",
	   		data: $("#search_form").serialize()+ "&pagenum=" + pagenum,
	  	 	dataType : "json", 
	   		success: function(data){ 
	   					console.log(data);
	   					console.log(data.cnt);
	   					console.log(data.currentPage);
	   					console.log(data.endPage);
	   					console.log(data.pageCnt);
	   					console.log(data.startPage);
	   					
	   					//페이지 이동 nav 생성 코드
	   					var pagehtml = "";
	   					if(data.pageCnt != 1) {
	   						if(data.startPage != 1) {
	   							pagehtml += "<a class='page_item pre' onclick='pageItemHandler("+(data.startPage-1)+");'><img class='page_img' src='<%=request.getContextPath()%>/images/my_arrow_180.png' alt='이전'></a>";
	   						}
	   						for(var i = data.startPage;i <= data.endPage;i++) {
	   							if(i == data.currentPage) {
	   								pagehtml += "<a class='page_item now' onclick='pageItemHandler("+i+");'>"+i+"</a>";
	   							} else {
	   								pagehtml += "<a class='page_item' onclick='pageItemHandler("+i+");'>"+i+"</a>";
	   							}
	   						}
	   						if(data.endPage < data.pageCnt) {
	   							pagehtml += "<a class='page_item next' onclick='pageItemHandler("+(data.endPage+1)+");'><img class='page_img' src='<%=request.getContextPath()%>/images/my_arrow.png' alt='다음'></a>";
	   						}
	   					}
	   					$("div#pagination").html(pagehtml);
	   				
	   					console.log(data.classlist);
	   					console.log(data.classlist.length);
	   					var classhtml ="";
	   					if(data.classlist != null) {
	   						var divCnt = 0;
	   						for(var j = 0; j < data.classlist.length; j++) {
	   							if(divCnt%3 == 0) {
	   								classhtml += "<div class='list_class_wrap'>";
	   							}
	   							classhtml += "<a href='${pageContext.request.contextPath }/info/"+data.classlist[j].classCode+"' class='list_class'>"+
					   	                         "<div class='list_class_img_wrap'>"+
					  	                             "<img src='${pageContext.request.contextPath}"+data.classlist[j].classImg+"' alt='클래스 이미지'>"+
					  	                         "</div>"+
					  	                         "<div class='list_class_info_wrap'>"+
					  	                             "<h3 class='f_16_b'>"+data.classlist[j].className+"</h3>"+
					  	                             "<span class='f_14_b'>"+data.classlist[j].classAddress+"</span>"+
					  	                             "<span class='f_14_b'>"+data.classlist[j].classPrice+"원</span>"+
					  	                         "</div>"+
					  	                     "</a>";
		  	                   	if(divCnt%3 == 2) {
	   								classhtml += "</div>";
	   							} else if(j == data.classlist.length-1) {
	   								classhtml += "</div>";
	   							}
		  	                  	divCnt++;
	   						}
	   					} else {
	   						classhtml += "<div class='list_class'>해당 조건의 클래스가 없습니다.</div>";ㄴ
	   					}
	   					$("div#list_content").html(classhtml);
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
           <div class="wrap_1050">
                <div class="list_top">
                    <p>000개의 클래스</p>
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
                <div id="list_content">

               <%--  <c:choose>
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
               </c:choose>  --%>
                </div>
            </div>
            <div class="wrap_1050">
                <div class="pagination" id="pagination">
                <%-- <c:if test="${pageCnt ne 1}"> <!-- 총 페이지수가 1뿐이면 페이지목록 안만들기 -->
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
                </c:if> --%>
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