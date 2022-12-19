window.onscroll = function() {
    // section_scrollup 부분 코드
    if(document.body.scrollTop > 500 || document.documentElement.scrollTop > 500) {
        document.getElementById('scrollup').style.opacity = "1";
        document.getElementById('scrollup').style.bottom = "15px";
    } else {
        document.getElementById('scrollup').style.opacity = "0";
        document.getElementById('scrollup').style.bottom = "-46px";
    }

    //상단 고정 position 코드
    // if(document.body.scrollTop > 1 || document.documentElement.scrollTop > 1) {
    //     $("#header_nav").css("position","fixed");
    // } else {
    //     $("#header_nav").css("position","relative");
    // }
}

// window.onresize = function() {
//     var w = window.innerWidth
//     var minWidth1 = 1140;
    //상단 고정 header 경계선 화면 크기에 따라 수정
    // if(w > minWidth1) {
    //     $("#header_nav").css("width", w);
    // } else {
    //     $("#header_nav").css("width", minWidth1);
    // }
    // var minWidth2 = 700;
    // if(w > 1050) {
    //     $("#headerCon").css("width", w);
    //     $("#headerCon").css("margin", "0");
    // } else if(w > minWidth2) {
    //     $("#headerCon").css("width", w);
    //     $("#headerCon").css("margin", "0");
    // } else {
    //     $("#headerCon").css("width", minWidth2);
    //     $("#headerCon").css("margin", "0");
    // }

    //화면 가로해상도에 따라 확대배율 수정할경우
//     var minWidth = 1140;
//   var body = document.getElementsByTagName('body')[0];
//   if (window.innerWidth < minWidth) { body.style.zoom = (window.innerWidth / minWidth); }
//   else body.style.zoom = 1;
// }

$(function(){
    $(".category_over, .category_list, .category_li").on("mouseover",categoryShowHandler);
    $(".category_list, .category_li").on("mouseout",categoryHideHandler);

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
function categoryShowHandler() {
    $(".category_list").css("display","flex");
}
function categoryHideHandler() {
    $(".category_list").css("display","none");
}