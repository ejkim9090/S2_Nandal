package kh.s2.nandal.crawling;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.tomcat.jni.Time;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;
import kh.s2.nandal.classdata.model.service.ClassOptionService;
import kh.s2.nandal.classdata.model.service.ClassScheduleService;
import kh.s2.nandal.classdata.model.vo.ClassOptionVo;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;
import kh.s2.nandal.classdata.model.vo.ClassScheduleVo;
import kh.s2.nandal.crawling.model.service.CrawlingClassService;
import kh.s2.nandal.review.model.service.ReviewPhotoService;
import kh.s2.nandal.review.model.service.ReviewService;
import kh.s2.nandal.review.model.vo.ReviewPhotoVo;
import kh.s2.nandal.review.model.vo.ReviewVo;



public class CrawlingClass {
	private CrawlingClassService svc = new CrawlingClassService();
	private ClassVo dto = new ClassVo();
	private ClassOptionService coSvc = new ClassOptionService();
	private ClassScheduleService csSvc = new ClassScheduleService();
	private ReviewService rSvc = new ReviewService();
	private ReviewPhotoService rpSvc = new ReviewPhotoService();
	
	public static void main(String[] args) throws IOException {
		new CrawlingClass().crawling();
//		System.out.println(cla.fileNum);
	}
	public void crawling() throws IOException {
		
		//URL 클래스 상세페이지
		String crawlingURL = "https://www.sssd.co.kr/m/class/detail/23538";
		//페이지 dom
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver drv = new ChromeDriver();   // 크롬창이 열림을 확인함.
		WebDriverWait w = new WebDriverWait(drv, 500);
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
		
		drv.findElement(By.cssSelector("body > div.common-bottom-slide-layer.app-install-propose-layer.open > div > div.btn-area > div > button.install-cancel-btn")).click();

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
		WebElement classAddressEle = drv.findElement(By.cssSelector("#map > div:nth-child(1) > div > div:nth-child(6) > div:nth-child(2) > div > div.overlay_title"));
		String classAddress = classAddressEle.getText();
		System.out.println(classAddress);
		
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
	        			 classAddress, classPrice, classLevel, classMin, classMax);
	        		svc.insertClass(dto);
	        	} else {
	        		//class_photo 테이블에 데이터 insert
	        		String cpRoute = "./images/class/"+fileName+".jpg";
	        		ClassPhotoVo cpDto = new ClassPhotoVo(classCode, cpRoute, 0);
	        		svc.insertClassPhoto(cpDto);
	        	}
	        } catch (/*IO*/Exception e) {
	        	  // 예외처리
	            e.printStackTrace();
	        }
		}
		
		//클래스 상세내용 이미지
		System.out.println("---------------------클래스 상세내용 이미지-----------------------");
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
			} catch (/*IO*/Exception e) {
				e.printStackTrace();
			}
		}
		
		//클래스 옵션 추가
		System.out.println("---------------------클래스 옵션-----------------------");
		int optionCnt = (int)(Math.random()*4) + 2;
		for(int i = 1; i< optionCnt; i++) {
			int coPrice = ((int)(Math.random()*9) + 1)*1000;
			String nEng = null;
			switch(i) {
				case 1 : nEng = "A"; break;
				case 2 : nEng = "B"; break;
				case 3 : nEng = "C"; break;
				case 4 : nEng = "D"; break;
			}
			String coName = "옵션"+nEng;
			int coCode = i;
			
			ClassOptionVo coVo = new ClassOptionVo();
			coVo.setCoCode(coCode);
			coVo.setClassCode(classCode);
			coVo.setCoName(coName);
			coVo.setCoPrice(coPrice);
			System.out.println(coVo.toString());
			coSvc.insert(coVo);
		}
		//클래스 일정 추가
		System.out.println("---------------------클래스 일정-----------------------");
		int csDay = 0;			//요일
		int scheduleCnt = (int)(Math.random()*4) + 2; 
		
		int sday = 0;
		
		for(int i = 1; i < scheduleCnt; i++) {
			String csStime = null;  //시작시간
			String csFtime = null; 	//종료시간
			String fdate = null;
			int dayRandom = (int)(Math.random()*5) + 1;
			switch(dayRandom) {
				case 1 :	csDay = 10;	break; //화,목
				case 2 :	csDay = 21;	break; //월,수,금
				case 3 :	csDay = 31;	break; //월~금
				case 4 :	csDay = 96;	break; //토~일
				case 5 :	csDay = 127; break; //월~일
			}
			
			switch(i) {
			case 1 : 
				csStime = "10:30"; csFtime = "12:00"; 
				fdate = "2023-02-17";
				break;
			case 2 : 
				csStime = "13:00"; csFtime = "14:30"; 
				fdate = "2023-03-20";
				break;
			case 3 : 
				csStime = "15:00"; csFtime = "16:30"; 
				fdate = "2023-04-18";
				break;
			case 4 : 
				csStime = "17:00"; csFtime = "18:30"; 
				fdate = "2023-01-23";
				break;
			}
			
			sday = dayRandom; //클래스 신청 시 날짜 선별용
			
			Date csSdate = Date.valueOf("2022-12-20");	//시작날짜
			Date csFdate = Date.valueOf(fdate);	//종료날짜
			ClassScheduleVo csVo = new ClassScheduleVo();
			csVo.setCsCode(i);
			csVo.setClassCode(classCode);
			csVo.setCsDay(csDay);
			csVo.setCsStime(csStime);
			csVo.setCsFtime(csFtime);
			csVo.setCsSdate(csSdate);
			csVo.setCsFdate(csFdate);
			System.out.println(csVo.toString());
			csSvc.insert(csVo);
		}
		//리뷰 사진 가져오기
		System.out.println("---------------------리뷰용 사진 3개씩 저장 하기 -----------------------");
		List<WebElement> reviewPhotoDivAll = drv.findElements(By.cssSelector("#class_info > div.class-reply-info-area > div.main-reply-list-area > div.user-reply-img-gallery.main-thumb-reply-img-list > li"));
		for(int i = 0; i < 3; i++) {
			WebElement reviewPhotoDiv = reviewPhotoDivAll.get(i);
			WebElement reviewPhotoA = reviewPhotoDiv.findElement(By.cssSelector("div > a"));
			String reviewPhoto = reviewPhotoA.getAttribute("style").split("\"")[1];
			System.out.println(reviewPhoto);
			String fileName = classCode + String.valueOf(i);
        	svc.getReviewImageUrl(classImgUrl2, fileName); //리뷰 사진 파일 저장
		}
		
		
		//신청 클래스
		System.out.println("---------------------신청 클래스-----------------------");
		int caTotal = classMin; //신청 인원수 - 해당 클래스 최소 인원수
		Date caDate = null;
		
		switch(sday) {
		case 1: caDate = Date.valueOf("2023-02-07"); break; //화요일
		case 2: 
		case 3:
		case 5: caDate = Date.valueOf("2023-02-10"); break; //금요일
		case 4: caDate = Date.valueOf("2023-02-11"); break;//토요일
		}
		
		int membercheck = 0;
		int membercheck2 = 0;
		int applyCnt = (int)(Math.random()*2) + 3; //3~4랜덤
		for(int i = 1; i < applyCnt; i++) { //신청 데이터 개수 2~3랜덤
			int caCode = i;//신청 코드 - 클래스 코드 넘버 + 뒤에 i 순
			int coCode = (int)(Math.random()*(optionCnt-1)) + 1; //옵션 코드 - 옵션코드 개수용 cnt에서 -1
			int csCode = (int)(Math.random()*(scheduleCnt-1)) + 1; //일정 코드 - 일정코드 개수용 cnt에서 -1
			int memberIdCnt = (int)(Math.random()*10) + 1; //멤버 랜덤 지정용 
			
			if(i == 1) {
				while(membercheck == memberIdCnt) {
					memberIdCnt = (int)(Math.random()*10) + 1;
					membercheck2 = memberIdCnt;
				} 
			} else if(i == 2) {
				while(memberIdCnt == membercheck || memberIdCnt == membercheck2) {
					memberIdCnt = (int)(Math.random()*10) + 1;
				} 
			} else {
				membercheck = memberIdCnt;
			}
			String memberId = "user"+memberIdCnt+"@user.com"; //신청한 멤버 아이디
			
			ClassApplyVo caVo = new ClassApplyVo();
			caVo.setCaCode(caCode);
			caVo.setMemberId(memberId);
			caVo.setClassCode(classCode);
			caVo.setCaTotal(caTotal);
			caVo.setCaDate(caDate);
			caVo.setCoCode(coCode);
			caVo.setCsCode(csCode);
			System.out.println(caVo.toString());
			svc.insertClassApply(caVo);
			
			//각 일정별 리뷰 정보 추가
			System.out.println("--------각 신청일정 리뷰 정보 추가 ---------");
			int reviewCode = caCode;
			String reviewCont = null;
			int reviewcnt = (int)(Math.random()*8) + 1;
			switch(reviewcnt) {
			case 1 : reviewCont = "친절하게 가르쳐주시고 좋은추억만들어서 좋았습니다!"; break;
			case 2 : reviewCont = "전날 연락드렸는데도 수강할수 있도록 도와주셔서 감사했어요. 강사님이 잘 이끌어주셔서 원데이로 완성할 수 있었습니다. ㅎㅎ"; break;
			case 3 : reviewCont = "똥손인 내가 처음하는데도 너무 나도 만족스러운 결과물이에요!!! 시간도 진짜 빨리지나가고 재밌어요!!! 강추"; break;
			case 4 : reviewCont = "정말 좋아요 ㅎㅎ 굿굿!!"; break;
			case 5 : reviewCont = "완성품 이쁘게 나와서 너무 좋았어요!!!찾아가는 길도 문자로 상세하게 보내주시고 수업 시간 전체적으로 친절하게 설명해주셔서 너무 만족합니당~~~"; break;
			case 6 : reviewCont = "너무 쉽게 잘 알려주시고 재밌게 잘하고 왔습니다!ㅎ"; break;
			case 7 : reviewCont = "재밋어요><"; break;
			case 8 : reviewCont = "강사님 너무 유쾌하시고 꼼꼼하게 알려주셨어요. 실습할때 컨디션이 안좋아 잘 못 따라가는데도 기다려 주시고 잘 지도해 주셔서 감사합니다!"; break;
			}
			int reviewKind = (int)(Math.random()*4) + 2; // 친절도 평점 각 2~5 랜덤
			int reviewComponent = (int)(Math.random()*3) + 3; //수업구성 평점 각 3~5 랜덤
			int reviewFacility = (int)(Math.random()*3) + 3; //시설 평점 각 3~5 랜덤
			int reviewLevel = (int)(Math.random()*4) + 2;  // 난이도적합 평점 2~5 랜덤
			int reviewGroup = (int)(Math.random()*5) + 0; // 0~4 랜덤
			double grade = (reviewKind+reviewComponent+reviewFacility+reviewLevel) / 4.0; // 평균값 구하기
			double reviewGrade = Math.round(grade*10) / 10.0; //평균값 소수 첫재짜리까지 반올림
			
			ReviewVo reVo = new ReviewVo();
			reVo.setReviewCode(reviewCode);
			reVo.setReviewCont(reviewCont);
			reVo.setReviewGrade(reviewGrade);
			reVo.setReviewKind(reviewKind);
			reVo.setReviewComponent(reviewComponent);
			reVo.setReviewFacility(reviewFacility);
			reVo.setReviewLevel(reviewLevel);
			reVo.setReviewGroup(reviewGroup);
			rSvc.insert(reVo);
			System.out.println(reVo.toString());
			System.out.println("----------------");
			
			//각 리뷰마다 사진 리뷰사진 추가
			System.out.println("--------각 리뷰마다 사진 추가 ---------");
			System.out.println();
			ReviewPhotoVo rpVo = new ReviewPhotoVo();
			rpVo.setReviewCode(reviewCode);
			String rfileName = null;
			if(applyCnt == 3) {
				if(i == 1) {
					System.out.println("--리뷰 2개일때 첫 리뷰한쪽에 사진2개--");
					rfileName = classCode + String.valueOf(i);
					rpVo.setRpRoute("./images/review/"+rfileName+".jpg");
					rpSvc.insert(rpVo);
					System.out.println(rpVo.toString());

					rfileName = classCode + String.valueOf(i+1);
					rpVo.setRpRoute("./images/review/"+rfileName+".jpg");
					rpSvc.insert(rpVo);
					System.out.println(rpVo.toString());
				} else {
					System.out.println("--리뷰 2개일때 두번째 리뷰한쪽에 사진1개--");
					rfileName = classCode + String.valueOf(i+1);
					rpVo.setRpRoute("./images/review/"+rfileName+".jpg");
					rpSvc.insert(rpVo);
					System.out.println(rpVo.toString());
				}
			} else {
				System.out.println("--리뷰 3개일때 각 사진 한개씩--");
				rfileName = classCode + String.valueOf(i);
				rpVo.setRpRoute("./images/review/"+rfileName+".jpg");
				rpSvc.insert(rpVo);
				System.out.println(rpVo.toString());
			}
			
			
		}
		
		
		
		
	}

}
