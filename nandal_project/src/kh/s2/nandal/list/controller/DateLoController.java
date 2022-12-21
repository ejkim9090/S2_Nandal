package kh.s2.nandal.list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.s2.nandal.classdata.model.service.ClassScheduleService;
import kh.s2.nandal.classdata.model.vo.ClassScheduleVo;

/**
 * Servlet implementation class ScheduleLoController
 */
@WebServlet("/date.lo")
public class DateLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateLoController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		int classCode = 0;
		String date = null;
		int day  = 0;
		try {
			classCode = Integer.parseInt(request.getParameter("classCode"));
			date = request.getParameter("date");
			day = Integer.parseInt(request.getParameter("day"));
		} catch(Exception e) {
			
		}
		
		System.out.println(classCode);
		System.out.println(date);
		System.out.println(day);
		
		ClassScheduleService service = new ClassScheduleService();
		List<ClassScheduleVo> volist = service.selectList(classCode, day, date);
		
		String result = gson.toJson(volist);
		System.out.println(result);
		out.println(result);
		out.flush(); //3번상태
		out.close(); //4번 - 데이터 전달
		
	}

}
