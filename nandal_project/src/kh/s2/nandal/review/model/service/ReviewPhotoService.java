package kh.s2.nandal.review.model.service;

import java.sql.Connection;
import java.util.List;

import kh.s2.nandal.jdbc.JdbcTemplate;
import kh.s2.nandal.review.model.dao.ReviewPhotoDao;
import kh.s2.nandal.review.model.vo.ReviewPhotoVo;

public class ReviewPhotoService {
	private ReviewPhotoDao dao = new ReviewPhotoDao();
	// 최소 5개 CRUD
//	insert
	public int insert(ReviewPhotoVo vo) {
		System.out.println(">> ReviewPhotoService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		System.out.println(">> ReviewPhotoService insert return :" + result);
		return result;
	}
//	update
	public int update(ReviewPhotoVo vo, int reviewCode) {
		System.out.println(">> ReviewPhotoService update param reviewCode :" + reviewCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, reviewCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ReviewPhotoService update return :" + result);
		return result;
	}
//	delete
	public int delete(int reviewCode) {
		System.out.println(">> ReviewPhotoService delete param reviewCode :" + reviewCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, reviewCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ReviewPhotoService delete return :" + result);
		return result;
	}
//	selectList
	public List<ReviewPhotoVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<ReviewPhotoVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> ReviewPhotoService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public ReviewPhotoVo selectOne(int reviewCode){
		System.out.println(">> ReviewPhotoService selectOne param reviewCode :" + reviewCode);
		Connection conn = JdbcTemplate.getConnection();
		ReviewPhotoVo vo = null;
		vo = dao.selectOne(conn, reviewCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ReviewPhotoService selectOne return :" + vo);
		return vo;
	}
}