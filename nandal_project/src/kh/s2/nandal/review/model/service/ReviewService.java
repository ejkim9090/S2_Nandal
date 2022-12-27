package kh.s2.nandal.review.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.review.model.dao.ReviewDao;
import kh.s2.nandal.review.model.dao.ReviewPhotoDao;
import kh.s2.nandal.review.model.vo.ClassReviewVo;
import kh.s2.nandal.review.model.vo.ReviewMainListVo;
import kh.s2.nandal.review.model.vo.ReviewPhotoVo;
import kh.s2.nandal.review.model.vo.ReviewVo;

public class ReviewService {
	private ReviewDao dao = new ReviewDao();
	private ReviewPhotoDao rpDao = new ReviewPhotoDao();
	// 최소 5개 CRUD
//		insert
		public int insert(ReviewVo vo) {
			System.out.println(">> ReviewService insert param vo :" + vo);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.insert(conn, vo);
			if(result > 0) {
				JdbcTemplate.commit(conn); // 커밋
				System.out.println("커밋성공");
			} else {
				JdbcTemplate.rollback(conn); // 롤백
				System.out.println("커밋실패");
			}
			System.out.println(">> ReviewService insert return :" + result);
			return result;
		}
//		update
		public int update(ReviewVo vo, int reviewCode) {
			System.out.println(">> ReviewService update param reviewCode :" + reviewCode);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.update(conn, vo, reviewCode);
			if(result > 0) {
				JdbcTemplate.commit(conn); // 커밋
				System.out.println("커밋성공");
			} else {
				JdbcTemplate.rollback(conn); // 롤백
				System.out.println("커밋실패");
			}
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
			if(result > 0) {
				JdbcTemplate.commit(conn); // 커밋
				System.out.println("커밋성공");
			} else {
				JdbcTemplate.rollback(conn); // 롤백
				System.out.println("커밋실패");
			}
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService delete return :" + result);
			return result;
		}
//		selectList - 마이페이지 리뷰 목록 가져오기
		public List<ClassReviewVo> MyReviewList(String memberId){
			Connection conn = JdbcTemplate.getConnection();
			List<ClassReviewVo> volist = null;
			volist = dao.MyReviewList(conn,memberId);
			for(int i = 0; i < volist.size(); i++) {
				List<ReviewPhotoVo> rplist = rpDao.selectList(conn, volist.get(i).getReviewCode()); 
				if(rplist != null) {
					List<String> rpRoute = new ArrayList<String>();
					for(int j = 0; j < rplist.size(); j++) {
						rpRoute.add(rplist.get(j).getRpRoute());
					}
					volist.get(i).setRpRoute(rpRoute);
				}
			}
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService selectClassList return :" + volist);
			return volist;
		}
//		selectList - 상세페이지 해당 클래스의 리뷰 목록 가져오기
		public List<ClassReviewVo> selectClassList(int classCode){
			Connection conn = JdbcTemplate.getConnection();
			List<ClassReviewVo> volist = null;
			volist = dao.selectClassList(conn,classCode);
			for(int i = 0; i < volist.size(); i++) {
				List<ReviewPhotoVo> rplist = rpDao.selectList(conn, volist.get(i).getReviewCode()); 
				if(rplist != null) {
					List<String> rpRoute = new ArrayList<String>();
					for(int j = 0; j < rplist.size(); j++) {
						rpRoute.add(rplist.get(j).getRpRoute());
					}
					volist.get(i).setRpRoute(rpRoute);
				}
			}
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService selectClassList return :" + volist);
			return volist;
		}
//		selectList - 메인화면 리뷰 목록 조회된 개수
		public int selectListCnt(int reviewGrade){
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.selectListCnt(conn, reviewGrade);
			JdbcTemplate.close(conn);
			System.out.println(">> ReviewService selectList return :" + result);
			return result;
		}
				
//		selectList - 메인화면 리뷰 목록 조회
		public List<ReviewMainListVo> selectList(int reviewGrade){
			Connection conn = JdbcTemplate.getConnection();
			List<ReviewMainListVo> volist = null;
			volist = dao.selectList(conn, reviewGrade);
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
