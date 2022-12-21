package kh.s2.nandal.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.s2.nandal.jdbc.JdbcTemplate;
import kh.s2.nandal.review.model.vo.ReviewPhotoVo;

public class ReviewPhotoDao {
//	insert
	public int insert(Connection conn, ReviewPhotoVo vo) {
		System.out.println(">>> ReviewPhotoDao insert param vo : " + vo);
		int result = 0;
		String sql = "insert into review_photo values(?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getReviewCode());
			pstmt.setString(2, vo.getRpRoute());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewPhotoDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ReviewPhotoVo vo, int reviewCode) {
		int result = 0;
		
		String sql = "update review_photo set //TODO where review_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
//	delete
	public int delete(Connection conn, int reviewCode) {
		int result = 0;
		
		String sql = "delete from review_photo where review_code=?";
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
		
		return result;
	}
//	selectList - 목록조회
	public List<ReviewPhotoVo> selectList(Connection conn,int reviewCode){
		List<ReviewPhotoVo> volist = null;
		
		String sql = "select RP_ROUTE from review_photo where REVIEW_CODE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ReviewPhotoVo>();
				do {
					ReviewPhotoVo vo = new ReviewPhotoVo();
					vo.setRpRoute(rs.getString("RP_ROUTE"));
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		return volist;
	}
//	selectOne - 상세조회
	public ReviewPhotoVo selectOne(Connection conn, int reviewCode){
		ReviewPhotoVo vo = null;
	
		String sql = "select * from review_photo where review_code=?";
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
		return vo;
	}
}
