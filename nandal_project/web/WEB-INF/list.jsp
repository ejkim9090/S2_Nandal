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
                <div>
                    <div class="list_class_wrap">
                        <a class="list_class">
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
                        </a>
                        <a class="list_class">
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
                        </a>
                        <a class="list_class">
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
                        </a>
                    </div>
                    <div class="list_class_wrap">
                        <a class="list_class">
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
                        </a>
                        <a class="list_class">
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
                        </a>
                        <a class="list_class">
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
                        </a>
                    </div>
                    <div class="list_class_wrap">
                        <a class="list_class">
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
                        </a>
                        <a class="list_class">
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
                        </a>
                        <a class="list_class">
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
                        </a>
                    </div>
                </div>
            </div>
            <div class="wrap_1050">
                <div class="pagination">
                    <a class="page_item pre"><img class="page_img" src="./images/my_arrow_180.png" alt="">
                    </a>
                    <a class="page_item now">1
                    </a>
                    <a class="page_item">2
                    </a>
                    <a class="page_item">3
                    </a>
                    <a class="page_item">4
                    </a>
                    <a class="page_item">5
                    </a>
                    <a class="page_item">6
                    </a>
                    <a class="page_item next"><img class="page_img" src="./images/my_arrow.png" alt="">
                    </a>
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