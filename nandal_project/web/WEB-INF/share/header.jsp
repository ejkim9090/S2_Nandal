<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <div id="header">
            <div id="header_nav">
                <div class="wrap_1050" id="headerCon">
                    <form id="search_form" action="<%=request.getContextPath()%>/list" method="get">
                        <div class="header_first">
                            <div class="header_title">
                                <a href="${pageContext.request.contextPath }/main"><img src="<%=request.getContextPath()%>/images/title.png" alt="main_title"></a>
                            </div>
                            <div class="search_log_wrap">
                                <div class="header_search">
                                    <input id="keyword" name="search" class="f_12" type="text" placeholder="검색어를 입력해주세요.">
                                    <img src="<%=request.getContextPath()%>/images/search_logo.svg">
                                </div>
                                <div class="header_log">
                                	<c:choose>
                                		<c:when test="${empty loginSsInfo}">
                                    	<a class="f_16_b" href="${pageContext.request.contextPath }/join" >Join</a>
                                    	<a class="f_16_b" href="${pageContext.request.contextPath }/login">Login</a>
                                		</c:when>
                                		<c:otherwise>
                                    	<a class="f_16_b" href="${pageContext.request.contextPath }/my">My</a>
                                    	<a class="f_16_b" href="${pageContext.request.contextPath }/logout">Logout</a>
                                		</c:otherwise>
                                	</c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="header_check">
                            <div class="check_wrap">   
                                <div class="check_list">
                                    <div>
                                        <p class="c_size">지역</p>
                                        <select class="c_size_select c_line" name="area">
                                            <option value="0">선택</option>
                                            <option value="11">서울</option>
                                            <option value="21">부산</option>
                                            <option value="22">대구</option>
                                            <option value="23">인천</option>
                                            <option value="24">광주</option>
                                            <option value="25">대전</option>
                                            <option value="26">울산</option>
                                            <option value="29">세종</option>
                                            <option value="31">경기도</option>
                                            <option value="32">강원도</option>
                                            <option value="33">충청북도</option>
                                            <option value="34">충청남도</option>
                                            <option value="35">전라북도</option>
                                            <option value="36">전라남도</option>
                                            <option value="37">경상북도</option>
                                            <option value="38">경상남도</option>
                                            <option value="39">제주도</option>
                                        </select>
                                    </div>
                                    <div>
                                        <p class="c_size">카테고리</p>
                                        <select class="c_size_select c_line" name="category">
                                            <option value="0">선택</option>
                                            <option value="1">요리</option>
                                            <option value="2">수공예</option>
                                            <option value="3">플라워</option>
                                            <option value="4">미술</option>
                                            <option value="5">음악</option>
                                            <option value="6">액티비티</option>
                                            <option value="7">뷰티</option>
                                            <option value="8">라이프스타일</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="check_btn">
                                    <div>
                                        <p class="c_size">요일</p>
                                        <div><input type="checkbox" name="day" value="31" id="week"><label class="c_size c_line c_color"for="week">평일</label></div>
                                        <div><input type="checkbox" name="day" value="32" id="sat"><label class="c_size c_line c_color" for="sat">토요일</label></div>
                                        <div><input type="checkbox" name="day" value="64" id="sun"><label class="c_size c_line c_color" for="sun">일요일</label></div>
                                    </div>
                                    <div>
                                        <p class="c_size">난이도</p>
                                        <div><input type="checkbox" name="level" value="1" id="1"><label class="c_size c_line c_color" for="1">쉬움</label></div>
                                        <div><input type="checkbox" name="level" value="2" id="2"><label class="c_size c_line c_color" for="2">보통</label></div>
                                        <div><input type="checkbox" name="level" value="3" id="3"><label class="c_size c_line c_color" for="3">어려움</label></div>
                                    </div>
                                    <div>
                                        <p class="c_size">금액</p>
                                        <div><input class="c_size c_line" type="number" name="priceMin" placeholder="최소금액"></div>
                                        <div><label class="c_size f_20_b" style="text-align: center;">~</label></div>
                                        <div><input class="c_size c_line" type="number" name="priceMax" placeholder="최고금액"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="check_admin">
                                <button class="c_size c_line c_color" type="reset">초기화</button>
                                <button class="c_size c_line c_color" type="button" id="btn_search">검색</button>
                            </div>
                        </div>
                    </form>
                    <div class="header_second">
                        <div class="header_category">
                            <div class="category_over">
                                <span class="category_img"></span>
                                <span class="category_text f_16">카테고리</span>
                            </div>
                            <ul class="category_list">
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=1">
	                                    <div><img src="<%=request.getContextPath()%>/images/cooking.png"></div>
	                                    <label class="f_16_b">요리</label>
	                                </a>
                                </li>
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=2">
	                                    <div><img src="<%=request.getContextPath()%>/images/craft.png"></div>
	                                    <label class="f_16_b">수공예</label>
	                                </a>
                                </li>
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=3">
	                                    <div><img src="<%=request.getContextPath()%>/images/flower.png"></div>
	                                    <label class="f_16_b">플라워</label>
	                                </a>
                                </li>
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=4">
	                                    <div><img src="<%=request.getContextPath()%>/images/art.png"></div>
	                                    <label class="f_16_b">미술</label>
	                                </a>
                                </li>
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=5">
	                                    <div><img src="<%=request.getContextPath()%>/images/music.png"></div>
	                                    <label class="f_16_b">음악</label>
	                                </a>
                                </li>
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=6">
	                                    <div><img src="<%=request.getContextPath()%>/images/activity.png"></div>
	                                    <label class="f_16_b">액티비티</label>
	                                </a>
                                </li>
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=7">
	                                    <div><img src="<%=request.getContextPath()%>/images/beauty.png"></div>
	                                    <label class="f_16_b">뷰티</label>
	                                </a>
                                </li>
                                <li>
	                                <a class="category_li" href="${pageContext.request.contextPath }/list?category=8">
	                                    <div><img src="<%=request.getContextPath()%>/images/lifestyle.png"></div>
	                                    <label class="f_16_b">라이프스타일</label>
	                                </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    