package kh.s2.nandal.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.s2.nandal.member.model.service.MemberService;
import kh.s2.nandal.member.model.vo.MemberVo;

/**
 * Servlet implementation class JoinIdCheckLoController
 */
@WebServlet("/joinIdCheck.lo")
public class JoinIdCheckLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinIdCheckLoController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int result = 0;
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId);
		MemberService service = new MemberService();
		MemberVo vo = service.selectOne(memberId);
		if(vo != null) {
			result = 1;
		} 
		out.println(result);
		out.flush(); //3번상태
		out.close(); //4번 - 데이터 전달
		
	}

}
