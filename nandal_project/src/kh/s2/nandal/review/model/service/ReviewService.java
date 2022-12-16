package kh.s2.nandal.review.model.service;

import java.sql.Connection;
import java.util.List;

import kh.s2.nandal.jdbc.JdbcTemplate;
import kh.s2.nandal.review.model.dao.ReviewDao;
import kh.s2.nandal.review.model.vo.ReviewVo;

public class ReviewService {
	private ReviewDao dao = new ReviewDao();
	// 최소 5개 CRUD
//		insert
		public int insert(ReviewVo vo) {
			System.out.println(">> ReviewService insert param vo :" + vo);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.insert(conn, vo);
			System.out.println(">> ReviewService insert return :" + result);
			return result;
		}
//		update
		public int update(ReviewVo vo, int reviewCode) {
			System.out.println(">> ReviewService update param reviewCode :" + reviewCode);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.update(conn, vo, reviewCode);
			
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService update return :" + result);
			return result;
		}
//		delete
		public int delete(int reviewCode) {
			System.out.println(">> ReviewService delete param reviewCode :" + reviewCode);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.delete(conn, reviewCode);
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService delete return :" + result);
			return result;
		}
//		selectList
		public List<ReviewVo> selectList(){
			Connection conn = JdbcTemplate.getConnection();
			List<ReviewVo> volist = null;
			volist = dao.selectList(conn);
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService selectList return :" + volist);
			return volist;
		}
//		selectOne
		public ReviewVo selectOne(int reviewCode){
			System.out.println(">> ReviewService selectOne param reviewCode :" + reviewCode);
			Connection conn = JdbcTemplate.getConnection();
			ReviewVo vo = null;
			vo = dao.selectOne(conn, reviewCode);
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService selectOne return :" + vo);
			return vo;
		}
}
