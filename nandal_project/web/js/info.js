// 네비 누를 시 색 변경
$(function() {
	//네비 클릭 시 css 변경
    $(".prod_tab span").click(prodTabClickHandler);
    
    //일정 선택창 및 네비에 클래스 신청 버튼
    $(window).scroll(infoScrollAction);
    
    //네비 이동
    $("#nav_buy").click(navBuyClickHandler);
    
    //상단 이미지 변경
    $(".sub_img").click(topImgClickHandler);
    
});
function topImgClickHandler() {
	//클릭된 서브 이미지의 파일 경로 가져오기
    var $link = $(this).attr("src");
    //해당 src 값으로 큰 이미지의 경로를 변경
    $(".top_img").attr("src", $link);
}

function prodTabClickHandler() {
    // 다른 prod_tab에서 selected 삭제
    $(".prod_tab span").not($(this)).css("color","black");
    // 클릭한 prod_tab에서 selected 추가
    $(this).css("color","#6437e188");
}

function navBuyClickHandler() {
    var $buyPick = $(".buy_pick_wrap");
    var $pickCheck = $buyPick.css("display") == "block";
    var $navBuy = $("#nav_buy > span");
    var $navBuyCheck = $("#nav_buy > span").css("color") == "rgb(0, 0, 0)";
    console.log($navBuyCheck);
    if($pickCheck) {
        $buyPick.css("display", "none");
    } else {
        $buyPick.css("display", "block");
    }
    if($navBuyCheck) {
        $navBuy.css("color","white");
    } else {
        $navBuy.css("color","rgb(0, 0, 0)");
    }
}

function infoScrollAction() {
    var $winScroll = $(window).scrollTop();
    if($winScroll > 780) {
        $(".buy_pick_wrap").css( "position", "sticky");
        $(".buy_pick_wrap").css( "top", "156px");
        $(".buy_pick_wrap").css( "display", "none");
        $("#nav_buy > span").css("display", "block");
    } else {
        $(".buy_pick_wrap").css( "position", "absolute");
        $(".buy_pick_wrap").css( "top", "60px");
        $(".buy_pick_wrap").css( "display", "block");
        $("#nav_buy > span").css("display", "none");
    }
}