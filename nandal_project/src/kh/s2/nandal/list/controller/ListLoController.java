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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kh.s2.nandal.classdata.model.service.ClassService;
import kh.s2.nandal.classdata.model.vo.ClassVo;

/**
 * Servlet implementation class ListLoController
 */
@WebServlet("/list.lo")
public class ListLoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListLoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("/list.lo 컨트롤러");
		//gson
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		ClassService service = new ClassService();
//		
		final int pageSize = 9; // 페이지당글수 2
		final int pageBlock = 3; // 페이지링크수 3 예)게시글하단에  1 2 3 >>  << 4 5 6 >>  << 7 8
		int cnt = 0;  // 총글수 DB에서 확인하기
		int pageCnt = 0; // 총페이지수 위 pageSize와 cnt 방정식으로 계산
		int currentPage = 1; // 현재페이지. 기본1. 페이지 클릭이 되면 바뀌게됨. //TODO
		int startPage = 1;
		int endPage = 1;
		
		
		
		//키워드, 유형선택 검색 값 가져오기
		String searchword = request.getParameter("search");
		String[] searchDayArr = request.getParameterValues("day");
		String[] searchLevelArr = request.getParameterValues("level");
		int searchArea = 0;
		int searchCategory = 0;
		int searchMin = 0;
		int searchMax = 999999999;
		List<Integer> searchDay = new ArrayList<Integer>();
		List<Integer> searchLevel = new ArrayList<Integer>();
		try {
			searchArea = Integer.parseInt(request.getParameter("area"));
			searchCategory = Integer.parseInt(request.getParameter("category"));
			String MinStr = request.getParameter("priceMin");
			if(!MinStr.equals("")) {
				searchMin = Integer.parseInt(MinStr);
			}
			String MaxStr = request.getParameter("priceMax");
			if(!MaxStr.equals("")) {
				searchMax = Integer.parseInt(MaxStr);
			}
			if(searchDayArr != null) {
				for(String day : searchDayArr) {
					searchDay.add(Integer.parseInt(day));
				}
			}
			if(searchLevelArr != null) {
				for(String level : searchLevelArr) {
					searchLevel.add(Integer.parseInt(level));
				}
			}
		} catch(NumberFormatException e) {
			System.out.println("String --> int 변환 실패");
			e.printStackTrace();
		}
		System.out.println("키워드:"+searchword+",선택지역:"+searchArea+", 카테고리 :" +searchCategory + ",요일:"+searchDay+",난이도:"+searchLevel+",최소금액:"+searchMin+",최고금액:"+searchMax);
		
		//정렬 기준 가져오기
		String classLineUp = request.getParameter("classLineUp");
		int reviewLineUp = 0;
		try {
			reviewLineUp = Integer.parseInt(request.getParameter("reviewLineUp"));
		} catch(NumberFormatException e) {
			
		}
		System.out.println("classLineUp :" + classLineUp + ", reviewLineUp : " + reviewLineUp);
		
		List<ClassVo> classlist = null;
		try {
			cnt = service.selectTotalCnt(searchword, searchArea, searchCategory, searchDay, searchLevel, searchMin, searchMax);
			
			if(cnt <1) {  // 게시글 없음으로 아래 게시글 selectList 할 필요 없음.
				return;
			}
			try {
				currentPage = Integer.parseInt(request.getParameter("pagenum"));
			}catch (Exception e) {
			}
			pageCnt = (cnt/pageSize) + (cnt%pageSize==0 ? 0 : 1);		// 총페이지수 위 pageSize와 cnt 방정식으로 계산
			if(currentPage%pageBlock ==0) {
				startPage = ((currentPage/pageBlock)-1)*pageBlock+1;
			}else {
				startPage = ((currentPage/pageBlock))*pageBlock+1;
			}
			endPage = startPage+pageBlock-1;
			if(endPage > pageCnt ) {
				endPage = pageCnt;
			}
			int startRnum = (currentPage-1)*pageSize + 1;
			int endRnum = startRnum + pageSize - 1;
			//endRnum = (endRnum > cnt ? cnt: endRnum);
			if(endRnum > cnt ) {
				endRnum = cnt;
			}
			classlist = service.selectList(startRnum, endRnum, searchword, searchArea, searchCategory, searchDay, searchLevel, searchMin, searchMax, classLineUp, reviewLineUp);

		} finally {
			JsonObject page = new JsonObject();
			
			if(searchword != null && !searchword.equals("")) {
				page.addProperty("searchword", searchword);
			}
			
			//페이지 이동 nav에 필요한 값 json에추가하기
			page.addProperty("cnt", cnt);
			page.addProperty("pageCnt", pageCnt);
			page.addProperty("startPage", startPage);
			page.addProperty("endPage", endPage);
			page.addProperty("currentPage", currentPage);
			
			//받아온 List<ClassVo> classlist 데이터를 json으로 변경 후 기존 json 객체에 추가
			JsonArray jArray = new JsonArray();
			if(classlist != null) {
				for (int j = 0; j < classlist.size(); j++) {
	                JsonObject sObject = new JsonObject();//배열 내에 들어갈 json
	                sObject.addProperty("classCode", classlist.get(j).getClassCode());
	                sObject.addProperty("className", classlist.get(j).getClassName());
	                sObject.addProperty("classImg", classlist.get(j).getClassImg());
	                sObject.addProperty("classAddress", classlist.get(j).getClassAddress());
	                sObject.addProperty("classPrice", classlist.get(j).getClassPrice());
	                sObject.addProperty("allAvg", classlist.get(j).getAllAvg());
	                sObject.addProperty("allCnt", classlist.get(j).getAllCnt());
	                jArray.add(sObject);
	                
	                if (j >= classlist.size() - 1) {

	                	page.add("classlist", jArray);

	                     System.out.println("jArray:"+jArray.toString());
	               }
	            }
			}
            
			
//			String classlist2 = gson.toJson(classlist);
//			String cnt2 = gson.toJson("cnt:" + cnt);
//			String pageCnt2 = gson.toJson("pageCnt :" + pageCnt);
//			String startPage2 = gson.toJson("startPage :" + startPage);
//			String endPage2 = gson.toJson("endPage :" + endPage);
//			String currentPage2 = gson.toJson("currentPage :" + currentPage);
			
//			out.println(gson.toJson(classlist));//json형태로 넣은 데이터 추가
			System.out.println(page);
			out.println(page);//json형태로 넣은 데이터 추가
			out.flush(); //3번상태
			out.close(); //4번 - 데이터 전달
		}
		
		
	}
}
