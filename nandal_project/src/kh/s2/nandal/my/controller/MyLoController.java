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

import kh.s2.nandal.apply.model.service.ApplyCancelService;
import kh.s2.nandal.apply.model.service.ClassApplyService;
import kh.s2.nandal.apply.model.vo.MyApplyVo;
import kh.s2.nandal.member.model.vo.MemberVo;
import kh.s2.nandal.review.model.service.ReviewService;
import kh.s2.nandal.review.model.vo.ClassReviewVo;

/**
 * Servlet implementation class MyLoController
 */
@WebServlet("/my.lo")
public class MyLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyLoController() {
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
		String navId = request.getParameter("navId");
		MemberVo sessionVo = (MemberVo)request.getSession().getAttribute("loginSsInfo");
		String memberId = sessionVo.getMemberId();
		System.out.println(navId);
		System.out.println(memberId);
		
		//처리할 service 생성
		ClassApplyService caService = new ClassApplyService();
		
		String result = null;
		
		//선택된 nav에 따라 가져오는 데이터 분류
		switch(navId) {
		case "my_apply" : 
			System.out.println("신청 내역");
			List<MyApplyVo> calist = caService.myApplyList(memberId, "N");
			System.out.println(calist.toString());
			
			//json으로 데이터 변경
			result = gson.toJson(calist);
			out.println(result);
			break;
		case "my_cancel" : 
			System.out.println("취소 내역");
			List<MyApplyVo> aclist = caService.myApplyList(memberId, "Y");
			System.out.println(aclist.toString());
			
			//json으로 데이터 변경
			result = gson.toJson(aclist);
			out.println(result);//json형태로 넣은 데이터 추가
			break;
		case "my_review" : 
			System.out.println("리뷰 관리");
			ReviewService reService = new ReviewService();
			List<ClassReviewVo> relist = reService.myReviewList(memberId);
			System.out.println(relist.toString());
			
			result = gson.toJson(relist);
			out.println(result);//json형태로 넣은 데이터 추가
			break;
		}

		System.out.println(result); 
		
		out.flush(); //3번상태
		out.close(); //4번 - 데이터 전달
		
		
		
		
	}

}
