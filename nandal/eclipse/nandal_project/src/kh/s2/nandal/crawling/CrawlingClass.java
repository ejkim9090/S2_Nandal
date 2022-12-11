package kh.s2.nandal.crawling;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;
import kh.s2.nandal.crawling.model.service.CrawlingClassService;



public class CrawlingClass {
	private CrawlingClassService svc = new CrawlingClassService();
	private ClassVo dto = new ClassVo();
	
	public static void main(String[] args) throws IOException {
		new CrawlingClass().crawling();
//		System.out.println(cla.fileNum);
	}
	public void crawling() throws IOException {
		
		//URL 클래스 상세페이지
//		String crawlingURL = "https://www.sssd.co.kr/m/class/detail/32010";
		String crawlingURL = "https://www.sssd.co.kr/m/class/detail/23538";
		//페이지 dom
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver drv = new ChromeDriver();   // 크롬창이 열림을 확인함.
		WebDriverWait w = new WebDriverWait(drv, 100);
		JavascriptExecutor jexe = (JavascriptExecutor)drv;

		//클래스 목록 페이지에서 자동 구현하기 TODO
//		String crawlingListURL = "https://www.sssd.co.kr/m/search/class/category?midx=1";
//		drv.get(crawlingListURL);  // 클래스 목록페이지로 이동
//		jexe.executeScript("window.scrollTo(0, 1500)",drv.findElement(By.cssSelector("body > div.search-container > div.search-result-panel > div")));
//		//클래스 명
//		System.out.println("---------------------클래스 상세주소 가져오기-----------------------");
//		List<WebElement> classCategoryEleAll = drv.findElements(By.cssSelector("body > div.search-container > div.search-result-panel > div > div:nth-child(2) > ul > li"));
//		WebElement classCategoryEle = classCategoryEleAll.get(0).findElement(By.cssSelector("a"));
//		String classHref = classCategoryEle.getAttribute("href");
//		System.out.println(classHref);
//		System.out.println(classCategoryEleAll.size());
		
		drv.get(crawlingURL);  // 클래스 상세페이지로 이동
		
		//클래스 코드 추출
		String[] urlstrArr = crawlingURL.split("/");
		String classCodestr = urlstrArr[urlstrArr.length-1];
		int classCode = Integer.parseInt(classCodestr);
		
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
		System.out.println(classMinstr);
		System.out.println(classMaxstr);
		int classMin = Integer.parseInt(classMinstr);
		int classMax = Integer.parseInt(classMaxstr);	
		
		//클래스 이미지
		System.out.println("---------------------클래스 대표이미지 및 클래스 이미지-----------------------");
		List<WebElement> classImgEleAll = drv.findElements(By.cssSelector("body > div.content.opened > div.container.no-lr-padding > div.detail-title.class-detail-img-area > div:nth-child(2) > div > div"));
		String	classImgUrl = null;
		for(int i = 0; i < classImgEleAll.size(); i++) {
//			classImgUrl = classImgEleAll.get(i).getAttribute("style").split("\"")[1];
			classImgUrl = classImgEleAll.get(i).getAttribute("data-src");
			System.out.println("서브이미지"+i+": "+classImgUrl);
			try {
				//추출 대표/서브 이미지 파일 저장
				String fileName = classCodestr+i;
	        	svc.getImageUrl(classImgUrl, fileName);
	        	if(i == 0) {
	        		//class 테이블에 데이터 insert
	        		int categoryCode = 1;
	        		int classLevel = (int)(Math.random()*3) + 1;
	        		String classImg = "./images/class/"+fileName+".jpg";
	        		dto = new ClassVo(classCode,  categoryCode, className, classImg, classIntro,
	        			 classCur, classHost, classAlltime, classPrd, classAtt, areaCode,
	        			 classAdress, classPrice, classLevel, classMin, classMax);
	        		svc.insertClass(dto);
	        	} else {
	        		//class_photo 테이블에 데이터 insert
	        		String cpRoute = "./images/class/"+fileName+".jpg";
	        		ClassPhotoVo cpDto = new ClassPhotoVo(classCode, cpRoute, 0);
	        		svc.insertClassPhoto(cpDto);
	        	}
	        } catch (IOException e) {
	        	  // 예외처리
	            e.printStackTrace();
	        }
		}
		
		//클래스 상세내용 이미지
		List<WebElement> classImgEleAll2 = drv.findElements(By.cssSelector("#class_info > div.class_info > div:last-of-type > ul > li"));
		String classImgUrl2 = null;
		for(int i = 0; i < classImgEleAll2.size(); i++) {
			WebElement classImgEle2 = classImgEleAll2.get(i).findElement(By.cssSelector("div.list-photo > a"));
			classImgUrl2 = classImgEle2.getAttribute("style").split("\"")[1];
			System.out.println("상세내용 이미지"+i+": "+classImgUrl2);
			try {
				//이미지 파일 저장
				int fileNum = i+classImgEleAll.size();
				String fileName = classCodestr+fileNum;
	        	svc.getImageUrl(classImgUrl2, fileName);

	        	//class_photo 테이블에 데이터 insert
	        	String cpRoute = "./images/class/"+fileName+".jpg";
	    		ClassPhotoVo cpDto = new ClassPhotoVo(classCode, cpRoute, 1);
	    		svc.insertClassPhoto(cpDto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
