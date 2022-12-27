package kh.s2.nandal.list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kh.s2.nandal.classdata.model.service.ClassService;
import kh.s2.nandal.classdata.model.vo.ClassVo;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/list 컨트롤러");
		
		request.setAttribute("search", request.getParameter("search"));
		request.setAttribute("area", request.getParameter("area"));
		request.setAttribute("category", request.getParameter("category"));
		request.setAttribute("priceMin", request.getParameter("priceMin"));
		request.setAttribute("priceMax", request.getParameter("priceMax"));
		
		String[] searchDayArr = request.getParameterValues("day");
		String[] searchLevelArr = request.getParameterValues("level");
		int daynum= 0;
		int levelnum =0;
		if(searchDayArr != null) {
			for(int i = 1; i < searchDayArr.length+1; i++) {
				request.setAttribute("day"+i, searchDayArr[i-1]);
				if(i == searchDayArr.length) {
					daynum = i;
				}
			}
		}
		if(searchLevelArr != null) {
			for(int i = 1; i < searchLevelArr.length+1; i++) {
				request.setAttribute("level"+i, searchLevelArr[i-1]);
				if(i == searchLevelArr.length) {
					levelnum = i;
				}
			}
		}
		request.setAttribute("daynum", daynum);
		request.setAttribute("levelnum", levelnum);
		
		String viewPage="/WEB-INF/list.jsp";
		request.getRequestDispatcher(viewPage).forward(request, response);
//		//gson
//		PrintWriter out = response.getWriter();
//		Gson gson = new Gson();
//		
//		ClassService service = new ClassService();
////		
//		final int pageSize = 9; // 페이지당글수 2
//		final int pageBlock = 3; // 페이지링크수 3 예)게시글하단에  1 2 3 >>  << 4 5 6 >>  << 7 8
//		int cnt = 0;  // 총글수 DB에서 확인하기
//		int pageCnt = 0; // 총페이지수 위 pageSize와 cnt 방정식으로 계산
//		int currentPage = 1; // 현재페이지. 기본1. 페이지 클릭이 되면 바뀌게됨. //TODO
//		int startPage = 1;
//		int endPage = 1;
//		
//		
//		
//		
//		String searchword = request.getParameter("search");
//		String[] searchDayArr = request.getParameterValues("day");
//		String[] searchLevelArr = request.getParameterValues("level");
//		int searchArea = 0;
//		int searchCategory = 0;
//		int searchMin = 0;
//		int searchMax = 999999999;
//		List<Integer> searchDay = new ArrayList<Integer>();
//		List<Integer> searchLevel = new ArrayList<Integer>();
//		
//		try {
//			searchArea = Integer.parseInt(request.getParameter("area"));
//			searchCategory = Integer.parseInt(request.getParameter("category"));
//			String MinStr = request.getParameter("priceMin");
//			if(!MinStr.equals("")) {
//				searchMin = Integer.parseInt(MinStr);
//			}
//			
//			String MaxStr = request.getParameter("priceMax");
//			if(!MaxStr.equals("")) {
//				searchMax = Integer.parseInt(MaxStr);
//			}
//			
//			if(searchDayArr != null) {
//				for(String day : searchDayArr) {
//					searchDay.add(Integer.parseInt(day));
//				}
//			}
//			if(searchDayArr != null) {
//				for(String level : searchLevelArr) {
//					searchLevel.add(Integer.parseInt(level));
//				}
//			}
//			
//		} catch(NumberFormatException e) {
//			System.out.println("String --> int 변환 실패");
//			e.printStackTrace();
//		}
//
//		
//		
//		
//		
//		System.out.println("키워드:"+searchword+",선택지역:"+searchArea+", 카테고리 :" +searchCategory + ",요일:"+searchDay+",난이도:"+searchLevel+",최소금액:"+searchMin+",최고금액:"+searchMax);
//		
//		List<ClassVo> classlist = null;
//		try {
////			if(searchword != null && !searchword.equals("")) {
////				cnt = service.selectTotalCnt(searchword);
////			} else {
////				cnt = service.selectTotalCnt();
////			}
//			cnt = service.selectTotalCnt(searchword, searchArea, searchCategory, searchDay, searchLevel, searchMin, searchMax);
//			
//			if(cnt <1) {  // 게시글 없음으로 아래 게시글 selectList 할 필요 없음.
//				return;
//			}
//			try {
//				currentPage = Integer.parseInt(request.getParameter("pagenum"));
//			}catch (Exception e) {
//			}
//			pageCnt = (cnt/pageSize) + (cnt%pageSize==0 ? 0 : 1);		// 총페이지수 위 pageSize와 cnt 방정식으로 계산
//			if(currentPage%pageBlock ==0) {
//				startPage = ((currentPage/pageBlock)-1)*pageBlock+1;
//			}else {
//				startPage = ((currentPage/pageBlock))*pageBlock+1;
//			}
//			endPage = startPage+pageBlock-1;
//			if(endPage > pageCnt ) {
//				endPage = pageCnt;
//			}
//			int startRnum = (currentPage-1)*pageSize + 1;
//			int endRnum = startRnum + pageSize - 1;
//			//endRnum = (endRnum > cnt ? cnt: endRnum);
//			if(endRnum > cnt ) {
//				endRnum = cnt;
//			}
//			
////			List<ClassVo> classlist = service.selectList(startRnum, endRnum, searchword);
//			classlist = service.selectList(startRnum, endRnum, searchword, searchArea, searchCategory, searchDay, searchLevel, searchMin, searchMax);
//			request.setAttribute("classlist", classlist);
//		} finally {
//			if(searchword != null && !searchword.equals("")) {
//				request.setAttribute("searchword", searchword);
//			}
//			request.setAttribute("cnt", cnt);
//			request.setAttribute("pageCnt", pageCnt);
//			request.setAttribute("startPage", startPage);
//			request.setAttribute("endPage", endPage);
//			request.setAttribute("currentPage", currentPage);	
//			String viewPage="/WEB-INF/list.jsp";
//			
//			request.getRequestDispatcher(viewPage).forward(request, response);
//			
//			String classlist2 = gson.toJson(classlist);
//			String cnt2 = gson.toJson(cnt);
//			String pageCnt2 = gson.toJson(pageCnt);
//			String startPage2 = gson.toJson(startPage);
//			String endPage2 = gson.toJson(endPage);
//			String currentPage2 = gson.toJson(currentPage);
//			
//			out.println(classlist2);//json형태로 넣은 데이터 추가
//			out.println(cnt2);//json형태로 넣은 데이터 추가
//			out.println(pageCnt2);//json형태로 넣은 데이터 추가
//			out.println(startPage2);//json형태로 넣은 데이터 추가
//			out.println(endPage2);//json형태로 넣은 데이터 추가
//			out.println(currentPage2);//json형태로 넣은 데이터 추가
//			out.flush(); //3번상태
//			out.close(); //4번 - 데이터 전달
//		}
	}

}
