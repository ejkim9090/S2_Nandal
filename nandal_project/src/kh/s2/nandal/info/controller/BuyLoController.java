package kh.s2.nandal.info.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.s2.nandal.apply.model.service.ClassApplyService;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;
import kh.s2.nandal.classdata.model.service.ClassScheduleService;
import kh.s2.nandal.classdata.model.vo.ClassScheduleVo;
import kh.s2.nandal.member.model.vo.MemberVo;

/**
 * Servlet implementation class BuyLoController
 */
@WebServlet("/buy.lo")
public class BuyLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyLoController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		int result = 0;
		
		int csCode = 0;
		int coCode = 0;
		int caTotal = 0;
		int classCode = 0;
		Date caDate = null;
		String memberId = null;
		memberId = request.getParameter("memberId");
		
		if(memberId.equals("")) {
			result = 99;
		} else {
			try {
				csCode = Integer.parseInt(request.getParameter("csCode"));
				coCode = Integer.parseInt(request.getParameter("buyOption"));
				caTotal = Integer.parseInt(request.getParameter("buyNum"));
				classCode = Integer.parseInt(request.getParameter("classCode"));
				caDate = Date.valueOf(request.getParameter("caDate"));
				
				if(caTotal == 0) {
					result = 88;
				} else {
					ClassApplyService service = new ClassApplyService();
					ClassApplyVo vo = new ClassApplyVo();
					vo.setCaDate(caDate);
					vo.setCaTotal(caTotal);
					vo.setClassCode(classCode);
					vo.setCoCode(coCode);
					vo.setCsCode(csCode);
					vo.setMemberId(memberId);
					result = service.insert(vo);
				}
			} catch(Exception e) {
				
			}
		}
		
		
		System.out.println(memberId);
		System.out.println(result);
		out.println(result);
		out.flush(); //3번상태
		out.close(); //4번 - 데이터 전달
	}

}
