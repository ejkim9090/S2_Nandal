package kh.s2.nandal.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s2.nandal.classdata.model.service.ClassOptionService;
import kh.s2.nandal.classdata.model.service.ClassPhotoService;
import kh.s2.nandal.classdata.model.service.ClassService;
import kh.s2.nandal.classdata.model.vo.ClassOptionVo;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;
import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.review.model.service.ReviewService;
import kh.s2.nandal.review.model.vo.ClassReviewVo;

/**
 * Servlet implementation class InfoController
 */
@WebServlet("/info/*")
public class InfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//어노테이션에 info/ 뒤에 적힌 값 가져오기
		String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/")+1);
		System.out.println("URI : " + uri);
        System.out.println("ACTION : " + action);
        
        int classCode = 0;
        try {
            classCode = Integer.parseInt(action);
        } catch(Exception e) {
        	
        } 
        
        //클래스 정보 추가
		ClassService service = new ClassService();
		ClassVo vo = service.selectOne(classCode);
		if(vo != null) {
			request.setAttribute("classVo", vo);
			
			
			//초기 당일 일정 가져오기
			
			//상단 요약 주소를 위한 값 추가
			String[] adrArr = vo.getClassAddress().split(" ");
			String sumAddress = adrArr[0] + " ";
			sumAddress = sumAddress.concat(adrArr[1]);
			request.setAttribute("sumAddress", sumAddress);
			
			//출력시 줄바꿈을 위해 클래스 소개,커리큘럼,호스트소개,기타제공사항에 replace 처리 
			String classIntro = vo.getClassIntro().replaceAll("%%", "<br>");
			vo.setClassIntro(classIntro); 
			String classCurStr = vo.getClassCur().replaceAll("%%", "<br><br>");
			classCurStr = classCurStr.replace("1단계 :", "<h4 style='display:inline'>1단계:</h4>");
			classCurStr = classCurStr.replace("2단계 :", "<h4 style='display:inline'>2단계:</h4>");
			classCurStr = classCurStr.replace("3단계 :", "<h4 style='display:inline'>3단계:</h4>");
			classCurStr = classCurStr.replace("4단계 :", "<h4 style='display:inline'>4단계:</h4>");
			classCurStr = classCurStr.replace("5단계 :", "<h4 style='display:inline'>5단계:</h4>");
			classCurStr = classCurStr.replace("6단계 :", "<h4 style='display:inline'>6단계:</h4>");
			classCurStr = classCurStr.replace("7단계 :", "<h4 style='display:inline'>7단계:</h4>");
			classCurStr = classCurStr.replace("8단계 :", "<h4 style='display:inline'>8단계:</h4>");
			classCurStr = classCurStr.replace("9단계 :", "<h4 style='display:inline'>9단계:</h4>");
			String classCur = classCurStr;
			vo.setClassCur(classCur);
			String classHost = vo.getClassHost().replaceAll("%%", "<br>");
			vo.setClassHost(classHost);
			String classPrd = vo.getClassPrd().replaceAll("%%", "<br>");
			vo.setClassPrd(classPrd);
//			String[] introList = vo.getClassIntro().split("%%");
//			String[] curList = vo.getClassCur().split("%%");
//			String[] hostList = vo.getClassHost().split("%%");
//			String[] prdList = vo.getClassPrd().split("%%");
//			request.setAttribute("introList", introList);
//			request.setAttribute("curList", curList);
//			request.setAttribute("hostList", hostList);
//			request.setAttribute("prdList", prdList);
			
			//해당 클래스의 사진 서브사진,소개사진 가져오기
			ClassPhotoService cpService = new ClassPhotoService();
			//서브사진
			List<ClassPhotoVo> cpSubList = cpService.selectList(vo.getClassCode(), 0);
			request.setAttribute("cpSubList", cpSubList);
			//소개 사진
			List<ClassPhotoVo> cpIntroList = cpService.selectList(vo.getClassCode(), 1);
			request.setAttribute("cpIntroList", cpIntroList);
			
			//해당 클래스의 옵션 가져오기
			ClassOptionService coService = new ClassOptionService();
			List<ClassOptionVo> coList = coService.selectList(vo.getClassCode());
			request.setAttribute("coList", coList);
			
			//해당 클래스의 리뷰 가져오기
			ReviewService reService = new ReviewService();
			List<ClassReviewVo> reList = reService.selectClassList(vo.getClassCode());
			request.setAttribute("reList", reList);
			
			System.out.println("/info 컨트롤러");
			String viewPage="/WEB-INF/info.jsp";
			request.getRequestDispatcher(viewPage).forward(request, response);
		} else {
			System.out.println("읽을 수 없는 상세 페이지 번호");
        	request.setAttribute("msg","없는 클래스 번호 입니다. 이전페이지로 돌아갑니다.");
			request.getRequestDispatcher("/WEB-INF/alert.jsp").forward(request, response);
		}
		
		
	}

	

}
