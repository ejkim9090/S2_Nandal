package kh.s2.nandal.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.s2.nandal.jdbc.JdbcTemplate;
import kh.s2.nandal.review.model.vo.ReviewAllVo;
import kh.s2.nandal.review.model.vo.ReviewVo;

public class ReviewDao {
//	insert
	public int insert(Connection conn, ReviewVo vo) {
		System.out.println(">>> ReviewDao insert param vo : " + vo);
		int result = 0;
		String sql = "insert into review (REVIEW_CODE,REVIEW_CONT,REVIEW_GRADE,REVIEW_KIND,REVIEW_COMPONENT,REVIEW_FACILITY,REVIEW_LEVEL,REVIEW_GROUP) values(?,?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getReviewCode());
			pstmt.setString(2, vo.getReviewCont());
			pstmt.setDouble(3, vo.getReviewGrade());
			pstmt.setInt(4, vo.getReviewKind());
			pstmt.setInt(5, vo.getReviewComponent());
			pstmt.setInt(6, vo.getReviewFacility());
			pstmt.setInt(7, vo.getReviewLevel());
			pstmt.setInt(8, vo.getReviewGroup());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ReviewVo vo, int reviewCode) {
		System.out.println(">>> ReviewDao update param vo : " + vo);
		System.out.println(">>> ReviewDao update param reviewCode : " + reviewCode);
		int result = 0;
		
		String sql = "update review set REVIEW_TIME=CURRENT_TIMESTAMP, REVIEW_CONT=?, REVIEW_GRADE=?, REVIEW_KIND=?, REVIEW_COMPONENT=?, REVIEW_FACILITY=?, REVIEW_LEVEL=?, REVIEW_GROUP=? where review_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getReviewCont());
			pstmt.setDouble(2, vo.getReviewGrade());
			pstmt.setInt(3, vo.getReviewKind());
			pstmt.setInt(4, vo.getReviewComponent());
			pstmt.setInt(5, vo.getReviewFacility());
			pstmt.setInt(6, vo.getReviewLevel());
			pstmt.setInt(7, vo.getReviewGroup());
			result = pstmt.executeUpdate();
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int reviewCode) {
		System.out.println(">>> ReviewDao delete param reviewCode : " + reviewCode);
		int result = 0;
		
		String sql = "delete from review where review_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<ReviewVo> selectList(Connection conn){
		List<ReviewVo> volist = null;
		
		String sql = "select * from review";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao selectList return : " + volist);
		return volist;
	}
	
//	selectList - 메인화면 리뷰 목록 조회
	public List<ReviewAllVo> selectList(Connection conn,int reviewGrade){
		List<ReviewAllVo> volist = null;
		
		String sql = "select distinct first_value(rp.RP_ROUTE)  over(partition by review_code) a, r.REVIEW_CONT "
				+ "    from review r join review_photo rp using(review_code) "
				+ "     where review_grade > ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewGrade);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ReviewAllVo>();
				do {
					ReviewAllVo vo = new ReviewAllVo();
					List<String> route = new ArrayList<String>();
					route.add(rs.getString(1));
					vo.setRpRoute(route);
					vo.setReviewCont(rs.getString(2));
					volist.add(vo);
				}while(rs.next());
			}
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao selectList return : " + volist);
		return volist;
	}
	
//	selectOne - 상세조회
	public ReviewVo selectOne(Connection conn, int reviewCode){
		System.out.println(">>> ReviewDao selectOne param reviewCode : " + reviewCode);
		ReviewVo vo = null;
		
		String sql = "select * from review where review_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao selectOne return : " + vo);
		return vo;
	}
}
