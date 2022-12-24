package kh.s2.nandal.my.controller;

import java.io.File;
import java.io.IOException;
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
					String value = item.getFieldName() + " = " + item.getString("utf-8"); //name = value, getString 시 인코딩해줘야함.
					System.out.println(value);
				}
				else {
					System.out.println(item.getFieldName());

					String separator = File.separator; //운영체제별로 다른 파일경로 구분자 추출
					int index = item.getName().lastIndexOf(separator); //업로드한 파일 경로의 마지막 separator 뒤에오는 값이 실제 파일명
					String origin = item.getName().substring(index+1);
					System.out.println(origin);
					String ext = origin.substring(origin.lastIndexOf("."));
					
					//이름 중복을 방지하기 위한 처리
					UUID uuid = UUID.randomUUID();
					String name = uuid + ext;
					
					System.out.println(item.getSize());
					if(!currentDir.exists()) {
						currentDir.mkdirs();
					}
					
					File uploadFile = new File(currentDir+separator+name);
					item.write(uploadFile); //첨부파일을 해당 경로로 저장
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
