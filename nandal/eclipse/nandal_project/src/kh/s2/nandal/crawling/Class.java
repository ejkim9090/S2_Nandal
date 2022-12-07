package kh.s2.nandal.crawling;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;



public class Class {
	public static void main(String[] args) throws IOException {
		//URL 주소
		String crawlingURL = "https://www.sssd.co.kr/m/class/detail/32010";
		//페이지 dom
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver drv = new ChromeDriver();   // 크롬창이 열림을 확인함.
		WebDriverWait w = new WebDriverWait(drv, 100);
		JavascriptExecutor jexe = (JavascriptExecutor)drv;
		
		drv.get(crawlingURL);  // 이동하고 싶은 url
		
		//줄바꿈 유지 셋팅
		//클래스 명
		System.out.println("---------------------클래스 명-----------------------");
		WebElement classNameEle = drv.findElement(By.cssSelector("body > div.content.opened > div.container.no-lr-padding > div.title-text-area > div.title-subject"));
		String className = classNameEle.getText();
		System.out.println(className);
		
		//클래스 소개
		System.out.println("---------------------클래스 소개-----------------------");
		WebElement classIntroEle = drv.findElement(By.cssSelector("#class_info > div.class_info > p"));
		String classIntro = classIntroEle.getText();
		System.out.println(classIntro);
		
		//커리큘럼
		System.out.println("---------------------커리큘럼-----------------------");
		List<WebElement> classCurEle = drv.findElements(By.cssSelector("#class_info > div.class_curriculum > div > ul > li"));
		String classCur = "";
		for(int i = 0; i < classCurEle.size(); i++) {
			classCur = classCur.concat(classCurEle.get(i).getText()+ "\n");
		}
		System.out.println(classCur);
		
		//호스트 소개
		System.out.println("---------------------호스트 소개-----------------------");
		WebElement classHostEle = drv.findElement(By.cssSelector("#introductions"));
		String classHost = classHostEle.getText();
		System.out.println(classHost);
		
		//총소요시간
		System.out.println("---------------------총 소요시간-----------------------");
		WebElement classAlltimeEle = drv.findElement(By.cssSelector("#totalTime"));
		String classAlltime = classAlltimeEle.getText();
		System.out.println(classAlltime);
		
		//제공사항
		System.out.println("---------------------제공사항-----------------------");
		List<WebElement> classPrdEle = drv.findElements(By.cssSelector("#class_info > div:nth-child(7) > div"));
		String classPrd = "";
		for(int i = 0; i < classPrdEle.size(); i++) {
			classPrd = classPrd.concat(classPrdEle.get(i).getText()+ "\n");
		}
		System.out.println(classPrd);
		
		//유의사항
		System.out.println("---------------------유의 사항-----------------------");
		List<WebElement> classAttEle = drv.findElements(By.cssSelector("#class_info > div:nth-child(6) > p.conv_add"));
		String classAtt = "";
		for(int i = 0; i < classAttEle.size(); i++) {
			classAtt = classAtt.concat(classAttEle.get(i).getText()+ "\n");
		}
		System.out.println(classAtt);

		
		//상세주소
		System.out.println("---------------------주소-----------------------");
		WebElement classAdressEle = drv.findElement(By.cssSelector("#map > div:nth-child(1) > div > div:nth-child(6) > div:nth-child(2) > div > div.overlay_title"));
		String classAdress = classAdressEle.getText();
		System.out.println(classAdress);
		
		System.out.println("---------------------시/군/구-----------------------");
		WebElement classAreaEle = drv.findElement(By.cssSelector("body > div.content.opened > div.container.no-lr-padding > div.class-abbr-info-area > div > div:nth-child(1) > div > div > span"));
		String classArea = classAreaEle.getText();
		System.out.println(classArea);
		
		//가격
		System.out.println("---------------------가격-----------------------");
		WebElement classPriceEle = drv.findElement(By.cssSelector("#price-bar > div.row > div.detail_txt.col-xs-6 > div > span.price01"));
		String classPrice = classPriceEle.getText().replaceAll(",", "");
		System.out.println(classPrice);
		//난이도
		
		//최소,최대 인원
		System.out.println("---------------------최소/최대 인원-----------------------");
		WebElement classPeopleEle = drv.findElement(By.cssSelector("body > div.content.opened > div.container.no-lr-padding > div.class-abbr-info-area > div > div:nth-child(4) > div > div > div:nth-child(2)"));
		String[] classPeople = classPeopleEle.getText().split(Pattern.quote("("))[0].split(Pattern.quote("-"));
		String classMin = classPeople[0];
		String classMax = classPeople[1];			
		System.out.println("최소인원: "+classMin);	
		System.out.println("최대인원: "+classMax);
	}

}
