package kh.s2.nandal.info.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s2.nandal.classdata.model.service.ClassService;
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
        
		ClassService service = new ClassService();
		ClassVo vo = service.selectOne(classCode);
		
		//출력시 줄바꿈을 위해 클래스 소개,커리큘럼,호스트소개,기타제공사항을 배열로 변경  
		String[] introList = vo.getClassIntro().split("%%");
		String[] curList = vo.getClassCur().split("%%");
		String[] hostList = vo.getClassHost().split("%%");
		String[] prdList = vo.getClassPrd().split("%%");
		request.setAttribute("classVo", vo);
		request.setAttribute("introList", introList);
		request.setAttribute("curList", curList);
		request.setAttribute("hostList", hostList);
		request.setAttribute("prdList", prdList);
		
		System.out.println("/info 컨트롤러");
		String viewPage="/WEB-INF/info.jsp";
		request.getRequestDispatcher(viewPage).forward(request, response);
	}

	

}
