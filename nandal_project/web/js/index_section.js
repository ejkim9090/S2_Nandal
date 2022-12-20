window.onload = function() {

    //각 목록 좌우 넘기기
    var a = 0;
    document.getElementsByClassName("rmd_right_btn")[0].onclick = function () {
        a += -1157;
        var t_value = "translate3d(" + a +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[0].style.transform = t_value;

        document.getElementsByClassName("rmd_left_btn")[0].style.display = "inline-block";
        if(a == -1157*2) {
            document.getElementsByClassName("rmd_right_btn")[0].style.display = "none";
        }
    }
    document.getElementsByClassName("rmd_left_btn")[0].onclick = function () {
        a += +1157;
        var t_value = "translate3d(" + a +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[0].style.transform = t_value;

        document.getElementsByClassName("rmd_right_btn")[0].style.display = "inline-block";
        if(a == 0) {
            document.getElementsByClassName("rmd_left_btn")[0].style.display = "none";
        }
    }
    var b = 0;
    document.getElementsByClassName("rmd_right_btn")[1].onclick = function () {
        b += -1157;
        var t_value = "translate3d(" + b +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[1].style.transform = t_value;

        document.getElementsByClassName("rmd_left_btn")[1].style.display = "inline-block";
        if(b == -1157*2) {
            document.getElementsByClassName("rmd_right_btn")[1].style.display = "none";
        }
    }
    document.getElementsByClassName("rmd_left_btn")[1].onclick = function () {
        b += +1157;
        var t_value = "translate3d(" + b +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[1].style.transform = t_value;

        document.getElementsByClassName("rmd_right_btn")[1].style.display = "inline-block";
        if(b == 0) {
            document.getElementsByClassName("rmd_left_btn")[1].style.display = "none";
        }
    }
    var c = 0;
    document.getElementsByClassName("rmd_right_btn")[2].onclick = function () {
        c += -1157;
        var t_value = "translate3d(" + c +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[2].style.transform = t_value;

        document.getElementsByClassName("rmd_left_btn")[2].style.display = "inline-block";
        if(c == -1157*2) {
            document.getElementsByClassName("rmd_right_btn")[2].style.display = "none";
        }
        console.log(document.getElementsByClassName("rmd_right_btn")[3]);
        console.log("ㅎㅇㅎㅇㅎ");
    }
    document.getElementsByClassName("rmd_left_btn")[2].onclick = function () {
        c += +1157;
        var t_value = "translate3d(" + c +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[2].style.transform = t_value;

        document.getElementsByClassName("rmd_right_btn")[2].style.display = "inline-block";
        if(c == 0) {
            document.getElementsByClassName("rmd_left_btn")[2].style.display = "none";
        }
        console.log(document.getElementsByClassName("rmd_right_btn")[3]);
        console.log("ㅎㅇㅎㅇㅎ");
    }
    var d = 0;
    document.getElementsByClassName("rmd_right_btn")[3].onclick = function () {
        d += -1157;
        var t_value = "translate3d(" + d +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[3].style.transform = t_value;

        document.getElementsByClassName("rmd_left_btn")[3].style.display = "inline-block";
        if(d == -1157*2) {
            document.getElementsByClassName("rmd_right_btn")[3].style.display = "none";
        }
    }
    document.getElementsByClassName("rmd_left_btn")[3].onclick = function () {
        d += +1157;
        var t_value = "translate3d(" + d +"px, 0px, 0px)";
        document.getElementsByClassName("rmd_list_swiper")[3].style.transform = t_value;

        document.getElementsByClassName("rmd_right_btn")[3].style.display = "inline-block";
        if(d == 0) {
            document.getElementsByClassName("rmd_left_btn")[3].style.display = "none";
        }
    }
}