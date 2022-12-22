<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <div id="header">
            <div id="header_nav">
                <div class="wrap_1050" id="headerCon">
                    <form>
                        <div class="header_first">
                            <div class="header_title">
                                <img src="<%=request.getContextPath()%>/images/title.png" alt="main_title">
                            </div>
                            <div class="search_log_wrap">
                                <div class="header_search">
                                    <input id="keyword" class="f_12" type="text" placeholder="검색어를 입력해주세요.">
                                    <button type="button" id="btn_search"><img src="<%=request.getContextPath()%>/images/search_logo.svg"></button>
<script>
	$("#btn_search").click(function(){
		location.href = "notice?search="+$("#keyword").val();
	});
</script>
                                </div>
                                <div class="header_log">
                                	<c:choose>
                                		<c:when test="${empty loginSsInfo}">
                                    	<a href="${pageContext.request.contextPath }/login">Login</a>
                                		</c:when>
                                		<c:otherwise>
                                    	<a href="${pageContext.request.contextPath }/my">My</a>
                                    	<a href="${pageContext.request.contextPath }/logout">Logout</a>
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
                                        <input type="checkbox" name="day" value="평일" id="week"><label class="c_size c_line c_color"for="week">평일</label>
                                        <input type="checkbox" name="day" value="토요일" id="sat"><label class="c_size c_line c_color" for="sat">토요일</label>
                                        <input type="checkbox" name="day" value="일요일" id="sun"><label class="c_size c_line c_color" for="sun">일요일</label>
                                    </div>
                                    <div>
                                        <p class="c_size">난이도</p>
                                        <input type="checkbox" name="level" value="1" id="1"><label class="c_size c_line c_color" for="1">쉬움</label>
                                        <input type="checkbox" name="level" value="2" id="2"><label class="c_size c_line c_color" for="2">보통</label>
                                        <input type="checkbox" name="level" value="3" id="3"><label class="c_size c_line c_color" for="3">어려움</label>
                                    </div>
                                    <div>
                                        <p class="c_size">금액</p>
                                        <input class="c_size c_line" type="text" name="priceMin" placeholder="최소금액">
                                        <label class="c_size f_20_b" style="text-align: center;">~</label>
                                        <input class="c_size c_line" type="text" name="priceMax" placeholder="최고금액">
                                    </div>
                                </div>
                            </div>
                            <div class="check_admin">
                                <button class="c_size c_line c_color" type="reset">초기화</button>
                                <button class="c_size c_line c_color">검색</button>
                            </div>
                        </div>
                    </form>
                    <div class="header_second">
                        <div class="header_category">
                            <div class="category_over">
                                <span class="category_img"></span>
                                <span class="category_text f_12">카테고리</span>
                            </div>
                            <ul class="category_list">
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>요리</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>수공예</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>플라워</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>미술</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>음악</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>액티비티</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>뷰티</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="<%=request.getContextPath()%>/images/scrollup_max.png"></div>
                                    <label>라이프스타일</label>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    