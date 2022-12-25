$(function(){
    $(".page_item").on("click",pageClickHandler);
});
function pageClickHandler() {
    if($(this).get(0)  != $(".page_item.pre").get(0) && $(this).get(0)  != $(".page_item.next").get(0) ) {
        $(".page_item").not($(this)).removeClass("now");
    $(this).addClass("now");
    }
}