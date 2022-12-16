package kh.s2.nandal.apply.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kh.s2.nandal.apply.model.vo.ApplyCancelVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class ApplyCancelDao {
//	insert
	public int insert(Connection conn, int acCode) {
		System.out.println(">>> ApplyCancelDao insert param acCode : " + acCode);
		int result = 0;
		String sql = "insert into apply_cancel (AC_CODE) values(?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acCode);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ApplyCancelDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ApplyCancelVo vo, int acCode) {
		System.out.println(">>> ApplyCancelDao update param vo : " + vo);
		System.out.println(">>> ApplyCancelDao update param acCode : " + acCode);
		int result = 0;
		
		String sql = "update apply_cancel set //TODO where ac_code=?";
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
		System.out.println(">>> ApplyCancelDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int acCode) {
		System.out.println(">>> ApplyCancelDao delete param categoryCode : " + acCode);
		int result = 0;
		
		String sql = "delete from apply_cancel where ac_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ApplyCancelDao delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<ApplyCancelVo> selectList(Connection conn){
		List<ApplyCancelVo> volist = null;
		
		String sql = "select * from apply_cancel";
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
		System.out.println(">>> ApplyCancelDao selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public ApplyCancelVo selectOne(Connection conn, int acCode){
		System.out.println(">>> ApplyCancelDao selectOne param acCode : " + acCode);
		ApplyCancelVo vo = null;
		
		String sql = "select * from apply_cancel where ac_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, acCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ApplyCancelDao selectOne return : " + vo);
		return vo;
	}
}
