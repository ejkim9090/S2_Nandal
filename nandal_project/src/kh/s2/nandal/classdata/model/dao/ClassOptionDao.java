package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kh.s2.nandal.classdata.model.vo.ClassOptionVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class ClassOptionDao {
//	insert
	public int insert(Connection conn, ClassOptionVo vo) {
		System.out.println(">>> ClassOptionDao insert param : " + vo);
		int result = 0;
		String sql = "insert into class_option values(?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCoCode());
			pstmt.setInt(2, vo.getClassCode());
			pstmt.setString(3, vo.getCoName());
			pstmt.setInt(4, vo.getCoPrice());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassOptionDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ClassOptionVo vo, int coCode) {
		System.out.println(">>> ClassOptionDao update param vo : " + vo);
		System.out.println(">>> ClassOptionDao update param coCode : " + coCode);
		int result = 0;
		
		String sql = "update class_option set //TODO where co_code=?";
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
		System.out.println(">>> ClassOptionDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int coCode) {
		System.out.println(">>> ClassOptionDao delete param coCode : " + coCode);
		int result = 0;
		
		String sql = "delete from class_option where co_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassOptionDao delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<ClassOptionVo> selectList(Connection conn){
		List<ClassOptionVo> volist = null;
		
		String sql = "select * from class_option";
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
		System.out.println(">>> ClassOptionDao selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public ClassOptionVo selectOne(Connection conn, int coCode){
		System.out.println(">>> ClassOptionDao selectOne param coCode : " + coCode);
		ClassOptionVo vo = null;
		
		String sql = "select * from class_option where co_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassOptionDao selectOne return : " + vo);
		return vo;
	}
}
