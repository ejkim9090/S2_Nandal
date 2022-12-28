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
@WebServlet("/Join.do")
public class JoinDoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinDoController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/Join.do 컨트롤러");
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		String memberName = request.getParameter("memberName");
		String memberPhone = request.getParameter("memberPhone");
		
		MemberService service = new MemberService();
		MemberVo vo = new MemberVo();
		vo.setMemberId(memberId);
		vo.setMemberPwd(memberPwd);
		vo.setMemberName(memberName);
		vo.setMemberPhone(memberPhone);
		int result = service.insert(vo);
		
		if(result > 0) {
			System.out.println("회원가입 성공");
			request.setAttribute("msgName", "memberInsert");
			request.setAttribute("msg", "회원가입에 성공하셨습니다. 로그인 페이지로 이동합니다.");
			request.getRequestDispatcher("/WEB-INF/alert.jsp").forward(request, response);
		} else {
			System.out.println("회원가입 실패");
			request.setAttribute("msg", "회원가입에 실패하였습니다. 이전 페이지로 돌아갑니다.");
			request.getRequestDispatcher("/WEB-INF/alert.jsp").forward(request, response);
		} 
	}

}
