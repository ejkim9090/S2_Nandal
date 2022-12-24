package kh.s2.nandal.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.s2.nandal.apply.model.service.ClassApplyService;
import kh.s2.nandal.apply.model.vo.MyApplyVo;
import kh.s2.nandal.member.model.vo.MemberVo;
import kh.s2.nandal.review.model.service.ReviewService;
import kh.s2.nandal.review.model.vo.ClassReviewVo;
import kh.s2.nandal.review.model.vo.ReviewVo;

/**
 * Servlet implementation class ReviewUpdateLoController
 */
@WebServlet("/reviewUpdate.lo")
public class ReviewUpdateLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateLoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//gson
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		//들어온 값 처리
		int reviewCode = 0;
		try {
			reviewCode = Integer.parseInt(request.getParameter("reviewCode"));
		} catch(NumberFormatException e) {
			
		}
		System.out.println(reviewCode);
		
		ReviewService service = new ReviewService();
		ReviewVo vo = service.selectOne(reviewCode);
		vo.setReviewCont(vo.getReviewCont().replaceAll("%%", "\n"));
		String result = gson.toJson(vo);
		
		out.println(result);//json형태로 넣은 데이터 추가\
		System.out.println(result); 
		out.flush(); //3번상태
		out.close(); //4번 - 데이터 전달
	}

}
