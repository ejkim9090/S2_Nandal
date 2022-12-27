package kh.s2.nandal.main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s2.nandal.classdata.model.service.ClassService;
import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.review.model.service.ReviewService;
import kh.s2.nandal.review.model.vo.ClassReviewVo;
import kh.s2.nandal.review.model.vo.ReviewMainListVo;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//클래스 데이터 받아와서 rqeuest에 넣기
		ClassService claService = new ClassService();
		ReviewService reService = new ReviewService();
		//크리스마스 키워드 목록
		int classlist1Cnt = claService.keywordListCnt("크리스마스");
		List<ClassVo> classlist1 = claService.keywordList("크리스마스");
		request.setAttribute("classlist1", classlist1);
		request.setAttribute("classlist1Cnt", classlist1Cnt);
		//연인 리뷰 추천 있는 목록
		int classlist2Cnt = claService.groupListCnt(3);
		List<ClassVo> classlist2 = claService.groupList(3);
		request.setAttribute("classlist2", classlist2);
		request.setAttribute("classlist2Cnt", classlist2Cnt);
		//가족 리뷰 추천 있는 목록
		int classlist3Cnt = claService.groupListCnt(4);
		List<ClassVo> classlist3 = claService.groupList(4);
		request.setAttribute("classlist3", classlist3);
		request.setAttribute("classlist3Cnt", classlist3Cnt);
		//리뷰 추천 목록
		int reviewlistCnt = reService.selectListCnt(4);
		List<ReviewMainListVo> reviewlist = reService.selectList(4);
		request.setAttribute("reviewlist", reviewlist);
		request.setAttribute("reviewlistCnt", reviewlistCnt);
		System.out.println("/main 컨트롤러");
		String viewPage="/WEB-INF/index.jsp";
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
