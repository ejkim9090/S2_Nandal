package kh.s2.nandal.my.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s2.nandal.apply.model.service.ApplyCancelService;
import kh.s2.nandal.apply.model.service.ClassApplyService;
import kh.s2.nandal.member.model.vo.MemberVo;
import kh.s2.nandal.review.model.service.ReviewService;

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
		String navId = request.getParameter("navId");
		MemberVo sessionVo = (MemberVo)request.getSession().getAttribute("loginSsInfo");
		String memberId = sessionVo.getMemberId();
		System.out.println(navId);
		System.out.println(memberId);
		switch(navId) {
		case "my_apply" : 
			System.out.println("신청 내역");
			ClassApplyService caService = new ClassApplyService();
			break;
		case "my_cancel" : 
			System.out.println("취소 내역");
			ApplyCancelService acService = new ApplyCancelService();
			break;
		case "my_review" : 
			System.out.println("리뷰 관리");
			ReviewService reService = new ReviewService();
			break;
		}
		
		
		
		
	}

}
