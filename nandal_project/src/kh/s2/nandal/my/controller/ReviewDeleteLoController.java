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
import kh.s2.nandal.review.model.service.ReviewPhotoService;
import kh.s2.nandal.review.model.service.ReviewService;

/**
 * Servlet implementation class ApplyCancelLoController
 */
@WebServlet("/reviewDelete.lo")
public class ReviewDeleteLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteLoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		int reviewCode = 0;
		try {
			reviewCode = Integer.parseInt(request.getParameter("reviewCode"));
		} catch(NumberFormatException e) {
			
		}
		ReviewService reService = new ReviewService();
		ReviewPhotoService rpService = new ReviewPhotoService();
		int rpCheck = rpService.delete(reviewCode);
		if(rpCheck > 0) {
			System.out.println("사진 삭제 성공");
		} else {
			System.out.println("사진이 없거나 실패");
		}
		int reCheck = reService.delete(reviewCode);
			
		
		System.out.println(reCheck);
		out.println(reCheck);
		out.flush(); //3번상태
		out.close(); //4번 - 데이터 전달
	}

}
