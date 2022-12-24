package kh.s2.nandal.my.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.s2.nandal.apply.model.service.ApplyCancelService;
import kh.s2.nandal.apply.model.service.ClassApplyService;

/**
 * Servlet implementation class ApplyCancelLoController
 */
@WebServlet("/applyCancel.lo")
public class ApplyCancelLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyCancelLoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int caCode = 0;
		try {
			caCode = Integer.parseInt(request.getParameter("caCode"));
		} catch(NumberFormatException e) {
			
		}
		ApplyCancelService service = new ApplyCancelService();
		int result = service.insert(caCode);
		
		System.out.println(result);
		out.println(result);
		out.flush(); //3번상태
		out.close(); //4번 - 데이터 전달
	}

}
