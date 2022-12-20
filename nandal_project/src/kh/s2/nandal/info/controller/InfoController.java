package kh.s2.nandal.info.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s2.nandal.classdata.model.service.ClassPhotoService;
import kh.s2.nandal.classdata.model.service.ClassService;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;
import kh.s2.nandal.classdata.model.vo.ClassVo;

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
        
        int classCode = Integer.parseInt(action);
        
        //클래스 정보 추가
		ClassService service = new ClassService();
		ClassVo vo = service.selectOne(classCode);
		request.setAttribute("classVo", vo);
		
		//상단 요약 주소를 위한 값 추가
		String[] adrArr = vo.getClassAddress().split(" ");
		String sumAddress = adrArr[0] + " ";
		sumAddress = sumAddress.concat(adrArr[1]);
		request.setAttribute("sumAddress", sumAddress);
		
		//출력시 줄바꿈을 위해 클래스 소개,커리큘럼,호스트소개,기타제공사항을 배열로 변경 후 추가  
		String[] introList = vo.getClassIntro().split("%%");
		String[] curList = vo.getClassCur().split("%%");
		String[] hostList = vo.getClassHost().split("%%");
		String[] prdList = vo.getClassPrd().split("%%");
		request.setAttribute("introList", introList);
		request.setAttribute("curList", curList);
		request.setAttribute("hostList", hostList);
		request.setAttribute("prdList", prdList);
		
		//해당 클래스의 사진 서브사진,소개사진 가져오기
		ClassPhotoService cpService = new ClassPhotoService();
		//서브사진
		List<ClassPhotoVo> cpSubList = cpService.selectList(vo.getClassCode(), 0);
		request.setAttribute("cpSubList", cpSubList);
		//소개 사진
		List<ClassPhotoVo> cpIntroList = cpService.selectList(vo.getClassCode(), 1);
		request.setAttribute("cpIntroList", cpIntroList);
		
		System.out.println("/info 컨트롤러");
		String viewPage="/WEB-INF/info.jsp";
		request.getRequestDispatcher(viewPage).forward(request, response);
	}

	

}
