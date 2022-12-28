package kh.s2.nandal.my.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import kh.s2.nandal.member.model.service.MemberService;
import kh.s2.nandal.member.model.vo.MemberVo;
import kh.s2.nandal.review.model.service.ReviewPhotoService;
import kh.s2.nandal.review.model.service.ReviewService;
import kh.s2.nandal.review.model.vo.ReviewPhotoVo;
import kh.s2.nandal.review.model.vo.ReviewVo;

/**
 * Servlet implementation class rmemberUpdateDoController
 */
@WebServlet("/memberUpdate.do")
public class memberUpdateDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberUpdateDoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/memberUpdate.do 호출");
		MemberService service = new MemberService();
		String memberId = null;
		String pwdCheck = null;
		String pwdChange = null;
		String pwdChangeCheck = null;
		String memberPhone = null;
		String memberImg = null;
		String msg = null; //결과 메시지
		
		
		String saveFolder = "/images/member"; //저장하고 싶은 폴더
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
					case "memberId" : memberId = value; break;
					case "pwdCheck" : pwdCheck = value; break;
					case "pwdChange" : pwdChange = value; break;
					case "pwdChangeCheck" : pwdChangeCheck = value; break;
					case "memberPhone" : memberPhone = value; break;
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
						memberImg = "/images/member/" + name; //저장되는 이미지들 경로 값 추가
						
						System.out.println(item.getSize()); //파일 크기 확인
						if(!currentDir.exists()) { //저장할 경로 폴더가 있는건지 확인
							currentDir.mkdirs(); //없으면 해당 경로 폴더들 다 생성
						}
						
						File uploadFile = new File(currentDir+separator+name); //저장할 파일 생성
						item.write(uploadFile); //첨부파일을 해당 경로로 저장
					}
				}
			}
			System.out.println("memberId : "+memberId+",pwdCheck : "+pwdCheck+",pwdChange : "+pwdChange+",pwdChangeCheck : "+pwdChangeCheck
					+",memberPhone : "+memberPhone+",memberImg : "+memberImg);
			
			//받아온 값 insert
			MemberVo vo = new MemberVo();
			vo.setMemberImg(memberImg);
			vo.setMemberPhone(memberPhone);
			if(pwdChange == null || pwdChange.equals("")) {
				vo.setMemberPwd(pwdCheck);
			} else {
				vo.setMemberPwd(pwdChange);
			}
			
			System.out.println("저장될 re:" +vo.toString());
			int result = service.update(vo, memberId);
			if(result > 0) {
				msg = "회원정보가 수정되었습니다.";
			} else {
				msg = "회원정보 수정에 실패했습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.setAttribute("msgName", "memberUpdate");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/alert.jsp").forward(request, response);
		}
	}

}
