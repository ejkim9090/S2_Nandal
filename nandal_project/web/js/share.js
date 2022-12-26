window.onscroll = function() {
    // section_scrollup 부분 코드
    if(document.body.scrollTop > 500 || document.documentElement.scrollTop > 500) {
        document.getElementById('scrollup').style.opacity = "1";
        document.getElementById('scrollup').style.bottom = "15px";
    } else {
        document.getElementById('scrollup').style.opacity = "0";
        document.getElementById('scrollup').style.bottom = "-46px";
    }
}
$(function(){
    $(".category_over, .category_list, .category_li").on("mouseover",categoryShowHandler);
    $(".category_list, .category_li").on("mouseout",categoryHideHandler);
    
    //유형 선택 요일/난이도 버튼 클릭 시 색상 변경
    $("div.check_btn > div > label.c_color").on("click",dayClickHandler);

    //키워드 검색창 클릭 시 유형 선택창 보이고 카테고리 숨기기
    var keyword = document.getElementById("keyword");
    var headerCheck = document.getElementsByClassName("header_check")[0];
    keyword.onclick = function () {
        headerCheck.style.display = "block";
        $(".header_category").css("display","none");
    }
    // 유형 선택창 외 클릭 시 유형 선택창 숨기기 카테고리 보이기
    document.getElementById("section").onclick = keywordNoneHandler;
    document.getElementById("footer").onclick = keywordNoneHandler;
    
    function keywordNoneHandler() {
        headerCheck.style.display = "none";
        $(".header_category").css("display","flex");
    }
   
});
function dayClickHandler() {
	console.log("dayClickHandler 진입");
	console.log($(this).css("background-color"));
	
	if($(this).css("background-color") == "rgb(145, 105, 255)") {
		$(this).css("background-color", "rgb(140, 100, 205)");
		$(this).css("border", "1px solid rgb(140, 100, 205)");
	} else {
		$(this).css("background-color", "rgb(145, 105, 255)");
		$(this).css("border", "1px solid rgb(145, 105, 255)");
	}
}
function categoryShowHandler() {
    $(".category_list").css("display","flex");
}
function categoryHideHandler() {
    $(".category_list").css("display","none");
}