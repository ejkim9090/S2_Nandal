package kh.s2.nandal.list.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		ClassService service = new ClassService();
		
		final int pageSize = 3;//페이지당 클래스 수 3
		final int pageBlock = 3;//페이지링크수 3  예)화면 하단에 1 2 3 >>    << 4 5 6 >>  << 7 8
		int cnt = 0;
		int pageCnt = 0;
		int currentPage = 1;
		
		try {
			cnt = service.selectKeyword("키워드"); //총 클래스 수 16 - 총수는 db에서 읽어오기
			if(cnt < 1) {
				return; // 클래스 수가 없으면 리턴해서 바로 finally 로
			}
			pageCnt = (cnt/pageSize) + (cnt%pageSize==0? 0 : 1);
			int startRnum = (currentPage-1)*pageSize +1;
			int endRnum = startRnum + pageSize -1;
			
			
			
			List<ClassVo> classlist = service.selectList();
			request.setAttribute("classlsit", classlist);
		} finally {
			System.out.println("/list 컨트롤러");
			String viewPage="/WEB-INF/list.jsp";
			request.getRequestDispatcher(viewPage).forward(request, response);
		}
		 //총페이지수 6 
																 //총페이지수에서 클래수 수 만큼 페이지를 만들고 나머지가 있으면 한페이지 추가
		
		
		
		
		
		
	}

}
