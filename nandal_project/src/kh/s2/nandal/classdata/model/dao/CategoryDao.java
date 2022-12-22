package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.classdata.model.vo.CategoryVo;

public class CategoryDao {
//	insert
	public int insert(Connection conn, CategoryVo vo) {
		System.out.println(">>> CategoryDao insert param vo : " + vo);
		int result = 0;
		String sql = "insert into category values(?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCategoryCode());
			pstmt.setString(2, vo.getCategoryName());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> CategoryDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, CategoryVo vo, int categoryCode) {
		System.out.println(">>> CategoryDao update param vo : " + vo);
		System.out.println(">>> CategoryDao update param categoryCode : " + categoryCode);
		int result = 0;
		
		String sql = "update category set //TODO where category_code=?";
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
		System.out.println(">>> CategoryDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int categoryCode) {
		System.out.println(">>> CategoryDao delete param categoryCode : " + categoryCode);
		int result = 0;
		
		String sql = "delete from category where category_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> CategoryDao delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<CategoryVo> selectList(Connection conn){
		List<CategoryVo> volist = null;
		
		String sql = "select * from category";
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
		System.out.println(">>> CategoryDao selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public CategoryVo selectOne(Connection conn, int categoryCode){
		System.out.println(">>> CategoryDao selectOne param categoryCode : " + categoryCode);
		CategoryVo vo = null;
		
		String sql = "select * from category where category_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> CategoryDao selectOne return : " + vo);
		return vo;
	}
}
