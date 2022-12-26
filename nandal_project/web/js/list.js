$(function(){
    $(".page_item").on("click",pageClickHandler);
    
     //유형 선택 요일/난이도 버튼 클릭 시 색상 변경
    $("div.check_btn > div > label.c_color").on("click",dayClickHandler);
});
function pageClickHandler() {
    if($(this).get(0)  != $(".page_item.pre").get(0) && $(this).get(0)  != $(".page_item.next").get(0) ) {
        $(".page_item").not($(this)).removeClass("now");
    $(this).addClass("now");
    }
}
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