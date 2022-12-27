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
		$("#btn_search").on("click",searchAction);
    

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
function searchAction() {
    	$("#search_form").serialize();
		console.log($("#search_form").serialize());
		
		location.href = "/nandal/list?"+$("#search_form").serialize();
    }
function categoryShowHandler() {
    $(".category_list").css("display","flex");
}
function categoryHideHandler() {
    $(".category_list").css("display","none");
}