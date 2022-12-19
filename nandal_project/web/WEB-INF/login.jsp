<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <div id="header">
            <div id="header_nav">
                <div class="wrap_1050">
                    <form>
                        <div class="header_first">
                            <div class="header_title">
                                <img src="./images/title.png" alt="main_title">
                            </div>
                            <div class="search_log_wrap">
                                <div class="header_search">
                                    <input id="keyword" class="f_12" type="text" placeholder="검색어를 입력해주세요.">
                                    <button type="button"><img src="./images/search_logo.svg"></button>
                                </div>
                                <div class="header_log">
                                    <a href="#">My</a>
                                    <a href="#">Logout</a>
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
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>요리</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>수공예</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>플라워</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>미술</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>음악</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>액티비티</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>뷰티</label>
                                </li>
                                <li class="category_li">
                                    <div><img src="./images/scrollup_max.png"></div>
                                    <label>라이프스타일</label>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
        <div id="footer">
            <div class="wrap_1050">
                <div class="nandal_wrap">
                    <div class="nanadal_info">
                        <ul>
                            <li class="f_14_b"><a>이용약관</a></li>
                            <li class="f_14_b"><a>개인정보처리방침</a></li>
                            <li class="f_14_b"><a>이용안내</a></li>
                        </ul>
                        <div class="f_12">
                            법인명 (상호) : 주식회사 컬리 | 사업자등록번호 : 261-81-23567 <a>사용자정보 확인</a>
                            <br>통신판매업 : 제 2018-서울강남-01646 호 | 개인정보보호책임자 : 이원준
                            <br>주소 : 서울특별시 강남구 테헤란로 133, 18층(역삼동) | 대표이사 : 김슬아
                            <br>입점문의 : <a>입점문의하기</a> | 제휴문의 : <a>business@kurlycorp.com</a>
                            <br>채용문의 : <a>recruit@kurlycorp.com</a>
                            <br>팩스 : 070 - 7500 - 6098
                        </div>
                    </div>
                    <div class="nanadla_cos">
                        <h3>고객센터</h3>
                        <p class="f_20_b">1688-8888<span class="f_12">월~토요일 오전 7시 - 오후 6시</span></p>
                        <p class="f_16_b">카카오톡 문의<span class="f_12">월~토요일: 오전7시-오후6시 / 주말 및 공휴일: 오전7시-오후1시</span></p>
                        <p class="f_16_b">1:1 문의<span class="f_12">365일 고객센터 운영시간에 순차적으로 답변드리겠습니다</span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>