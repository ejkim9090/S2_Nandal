package kh.s2.nandal.my.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		MultipartRequest multi = new MultipartRequest(request
				, path //저장 경로 입력
				, 10*1024*1024 //10MB까지 저장가능
				, "UTF-8"  //인코딩
				, new DefaultFileRenamePolicy() //COS에서 제공하는 같은 이름이 있을 경우 리네임 방식
				);
		Enumeration<?> files = multi.getFileNames(); //여러개의 업로드 된 파일 가져오기
		while(files.hasMoreElements()) {
			String file = (String) files.nextElement(); //파일 이름
			String fileName = multi.getFilesystemName(file); //첨부파일명
			System.out.println(fileName); //
			String dbFilePath = saveFolder + "/" + fileName; //db에 저장할 경로 명
		}
		
		String uploadFile = multi.getFilesystemName("uploadFile"); //업로드한 파일의 이름 가져오기
		String reviewCont = multi.getParameter("name"); // 넘어온 다른 input 값 가져오기
		

	}

}
