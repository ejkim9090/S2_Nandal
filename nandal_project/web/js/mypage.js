$(function() {
    $("#btn_profile").on("click",memberUpdateMadalShowHandler);
    $(".madal_close").on("click",reviewWriteMadalHideHandler);
});
function reviewContHandler() {
    var $reCont = $(this).siblings("div.reCont");
    var $p = $(this).children("p");
    var $img = $(this).children("img");

    if($reCont.css("display") == "none") {
        $reCont.css("display", "flex");
    } else {
        $reCont.css("display", "none");
    }
    //텍스트 및 이미지 변경
    if($p.text() == "더보기") {
        $p.text("접기");
    } else {
        $p.text("더보기");
    }

    if($img.attr("src") == "/nandal/images/review_arrow.png") {
        $img.attr("src","/nandal/images/review_arrow_180.png");
    } else if($img.attr("src") == "/nandal/images/review_arrow_180.png") {
        $img.attr("src","/nandal/images/review_arrow.png");
    }
}
function reviewWriteMadalShowHandler() {
    $(".modal.a").show();
    var $reviewCode = $(this).siblings("input[type=hidden]").val();
    $("#review_code").val($reviewCode);
}
function memberUpdateMadalShowHandler() {
    $(".modal.b").show();
}
function reviewWriteMadalHideHandler() {
    $(this).parents("div.modal").hide();
}