package kh.s2.nandal.crawling;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import kh.s2.nandal.crawling.model.service.ClassService;
import kh.s2.nandal.crawling.model.vo.ClassDto;



public class Class {
	private static ClassService svc = new ClassService();
	private static int fileNum = 1;
	
	public static void main(String[] args) throws IOException {
		
		//URL 주소
		String crawlingURL = "https://www.sssd.co.kr/m/class/detail/32010";
		//페이지 dom
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver drv = new ChromeDriver();   // 크롬창이 열림을 확인함.
		WebDriverWait w = new WebDriverWait(drv, 1000);
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
		
		System.out.println("---------------------시도코드-----------------------");
		WebElement areaNameEle = drv.findElement(By.cssSelector("#map > div:nth-child(1) > div > div:nth-child(6) > div:nth-child(2) > div > div.overlay_title"));
		String[] areaNameArr = areaNameEle.getText().split(" ");
		String areaName = areaNameArr[0]; //시도명만 가져오기
		System.out.println(areaName);
		int areaCode = svc.selectArea(areaName); // 시도코드
		System.out.println(areaCode);
		
		//가격
		System.out.println("---------------------가격-----------------------");
		WebElement classPriceEle = drv.findElement(By.cssSelector("#price-bar > div.row > div.detail_txt.col-xs-6 > div > span.price01"));
		String classPricestr = classPriceEle.getText().replaceAll(",", "");
		int classPrice = Integer.parseInt(classPricestr);
		System.out.println(classPrice);
		//난이도
		
		//최소,최대 인원
		System.out.println("---------------------최소/최대 인원-----------------------");
		WebElement classPeopleEle = drv.findElement(By.cssSelector("body > div.content.opened > div.container.no-lr-padding > div.class-abbr-info-area > div > div:nth-child(4) > div > div > div:nth-child(2)"));
		String[] classPeople = classPeopleEle.getText().split(Pattern.quote("\n"))[0].split(Pattern.quote("-"));
		String classMinstr = classPeople[0];
		String classMaxstr = classPeople[1];		
		int classMin = Integer.parseInt(classMinstr);
		int classMax = Integer.parseInt(classMaxstr);	
		//클래스 대표이미지
		System.out.println("---------------------클래스 대표이미지-----------------------");
		List<WebElement> classImgEleAll = drv.findElements(By.cssSelector("#class_info > div.class_info > div.clsas-complete-piece-area.list-type-3 > ul > li"));
		WebElement classImgEleli = classImgEleAll.get(0);
		WebElement classImgEle = classImgEleli.findElement(By.cssSelector("div > a"));
		String	classImgUrl = classImgEle.getAttribute("style").split("\"")[1];
		System.out.println(classImgUrl);
        try {
            int check = svc.getImageUrl(classImgUrl, fileNum);
            if(check == 1) {
            	fileNum++;
            }
        } catch (IOException e) {
        	  // 예외처리
            e.printStackTrace();
        }
		
		
		//db에 insert하기
		int classCode = 1;
		int categoryCode = 1;
		int classLevel = 1;
		String classImg = "./images/class/"+fileNum+".jpg";
		ClassDto dto = new ClassDto(classCode,  categoryCode, className, classImg, classIntro,
			 classCur, classHost, classAlltime, classPrd, classAtt, areaCode,
			 classAdress, classPrice, classLevel, classMin, classMax);
		svc.insertMember(dto);
	}

}
