package kh.s2.nandal.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s2.nandal.member.model.service.MemberService;
import kh.s2.nandal.member.model.vo.MemberVo;


/**
 * Servlet implementation class LoginDoController
 */
@WebServlet("/login.do")
public class LoginDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginDoController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		System.out.println(memberId);
		System.out.println(memberPwd);
		
		MemberService service = new MemberService();
		MemberVo loginInfo = service.login(memberId, memberPwd);
		if(loginInfo != null) {
			System.out.println("로그인 성공");
			request.getSession().setAttribute("loginSsInfo", loginInfo);
			response.sendRedirect(request.getContextPath()+"/main");
		} else {
			System.out.println("로그인 실패");
		} 
	}

}
