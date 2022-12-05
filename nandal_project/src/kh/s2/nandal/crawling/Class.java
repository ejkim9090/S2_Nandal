package kh.s2.nandal.crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Class {
	public static void main(String[] args) throws IOException {
		//주소
		String crawlingURL = "https://www.sssd.co.kr/m/class/detail/32019";
		//페이지 dom
		Document doc = Jsoup.connect(crawlingURL).get();
		//클래스 소개
		Elements classIntro = doc.select("#class_info > div.class_info > p");
		System.out.println(classIntro.html());
	}

}
