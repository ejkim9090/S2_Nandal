package kh.s2.nandal.my.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kh.s2.nandal.review.model.service.ReviewPhotoService;
import kh.s2.nandal.review.model.service.ReviewService;
import kh.s2.nandal.review.model.vo.ReviewPhotoVo;
import kh.s2.nandal.review.model.vo.ReviewVo;

/**
 * Servlet implementation class ReviewWriteDoController
 */
@WebServlet("/review.do")
public class ReviewWriteDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewWriteDoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/review.do 호출");
		ReviewService reService = new ReviewService();
		ReviewPhotoService rpService = new ReviewPhotoService();
		int reviewCode = 0;
		String reviewCont = null;
		int reviewKind = 0;
		int reviewFacility = 0;
		int reviewComponent = 0;
		int reviewLevel = 0;
		int reviewGroup = 0;
		double reviewGrade = 0;
		List<String> rpRouteArr = new ArrayList<String>();
		
		
		String saveFolder = "/images/review"; //저장하고 싶은 폴더
		String path = request.getServletContext().getRealPath(saveFolder); //진짜 파일을 저장하는 java/.metadata안에 경로 
		System.out.println(path);

		int size = 10 * 1024 * 1024; // 크기 지정 값
		DiskFileItemFactory factory = new DiskFileItemFactory(); //업로드 된 파일을 저장할 저장소와 관련 클래스
		File currentDir = new File(path);
		factory.setRepository(currentDir); //File 객체로 지정한 업로드된 파일을 저장할 위치
		factory.setSizeThreshold(size); //임시파일을 생성할 한계 크기 지정
		
		ServletFileUpload upload = new ServletFileUpload(factory); //  'multipart/form-data '형식으로 넘어온 데이터를 다루기 쉽게 변환
		try {
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				if(item.isFormField()) { //해당 값이 true면 text같은 일반 데이터, false면 파일데이터
					String all = item.getFieldName() + " = " + item.getString("utf-8"); //name = value, getString 시 인코딩해줘야함.
					System.out.println(all);
					String name = item.getFieldName(); //name = value, getString 시 인코딩해줘야함.
					String value = item.getString("utf-8"); //name = value, getString 시 인코딩해줘야함.
					switch(name) {
					case "reviewCode" : reviewCode = Integer.parseInt(value); break;
					case "reviewCont" : reviewCont = value.replaceAll("\n", "%%"); System.out.println("줄변환 리뷰내용 :"+reviewCont); break;
					case "kind" : reviewKind = Integer.parseInt(value); break;
					case "facility" : reviewFacility = Integer.parseInt(value); break;
					case "component" : reviewComponent = Integer.parseInt(value); break;
					case "level" : reviewLevel = Integer.parseInt(value); break;
					case "group" : reviewGroup = Integer.parseInt(value); break;
					}
				}
				else {
					System.out.println(item.getFieldName());

					String separator = File.separator; //운영체제별로 다른 파일경로 구분자 추출
					int index = item.getName().lastIndexOf(separator); //업로드한 파일 경로의 마지막 separator 뒤에오는 값이 실제 파일명
					System.out.println("index : "+index);
					String origin = item.getName().substring(index+1);
					System.out.println("origin : "+origin);
					if(origin.equals("")) {
						System.out.println("첨부된 파일이 없음");
					} else {
						
						String ext = origin.substring(origin.lastIndexOf("."));
						
						//이름 중복을 방지하기 위한 처리
						UUID uuid = UUID.randomUUID();
						String name = uuid + ext;
						System.out.println(name); //저장되는 이름 확인
						rpRouteArr.add("/images/review/" + name); //저장되는 이미지들 경로 값 추가
						
						System.out.println(item.getSize()); //파일 크기 확인
						if(!currentDir.exists()) { //저장할 경로 폴더가 있는건지 확인
							currentDir.mkdirs(); //없으면 해당 경로 폴더들 다 생성
						}
						
						File uploadFile = new File(currentDir+separator+name); //저장할 파일 생성
						item.write(uploadFile); //첨부파일을 해당 경로로 저장
					}
				}
			}
			//받아온 값으러 insert
			double grade = (reviewKind+reviewComponent+reviewFacility+reviewLevel) / 4.0; // 평균값 구하기
			reviewGrade = Math.round(grade*10) / 10.0; //평균값 소수 첫재짜리까지 반올림
			
			ReviewVo reVo = new ReviewVo();
			reVo.setReviewCode(reviewCode);
			reVo.setReviewCont(reviewCont);
			reVo.setReviewComponent(reviewComponent);
			reVo.setReviewFacility(reviewFacility);
			reVo.setReviewGrade(reviewGrade);
			reVo.setReviewGroup(reviewGroup);
			reVo.setReviewKind(reviewKind);
			reVo.setReviewLevel(reviewLevel);
			System.out.println("저장될 re:" +reVo.toString());
//			reService.insert(reVo);
			
			System.out.println("rp배열 크기 : "+rpRouteArr.size());
			if(rpRouteArr.size() > 0) { //저장된 파일 경로가 있는지 확인
				for(String rpRoute: rpRouteArr) { 
					ReviewPhotoVo rpVo = new ReviewPhotoVo();
					rpVo.setReviewCode(reviewCode);
					rpVo.setRpRoute(rpRoute);
					System.out.println("저장될 rp:" +rpVo.toString());
//					rpService.insert(rpVo);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
		//cos라이브러리 파일 업로드 시 - multiple이 지원안됨
//		MultipartRequest multi = new MultipartRequest(request
//				, path //저장 경로 입력
//				, 10*1024*1024 //10MB까지 저장가능
//				, "UTF-8"  //인코딩
//				, new DefaultFileRenamePolicy() //COS에서 제공하는 같은 이름이 있을 경우 리네임 방식
//				);
//		Enumeration<?> files = multi.getFileNames(); //여러개의 업로드 된 파일 가져오기
//		while(files.hasMoreElements()) {
//			String file = (String) files.nextElement(); //파일 이름
//			String fileName = multi.getFilesystemName(file); //첨부파일명
//			System.out.println(fileName); //
//			String dbFilePath = saveFolder + "/" + fileName; //db에 저장할 경로 명
//		}
//		
//		String uploadFile = multi.getFilesystemName("uploadFile"); //업로드한 파일의 이름 가져오기
//		String reviewCont = multi.getParameter("name"); // 넘어온 다른 input 값 가져오기
		

	}

}
