package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kh.s2.nandal.classdata.model.vo.AreaVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class AreaDao {
//	insert
	public int insert(Connection conn, AreaVo vo) {
		System.out.println(">>> AreaDao insert param : " + vo);
		int result = 0;
		String sql = "insert into area values(?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getAreaCode());
			pstmt.setString(2, vo.getAreaName());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> AreaDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, AreaVo vo, int areaCode) {
		System.out.println(">>> AreaDao update param vo : " + vo);
		System.out.println(">>> AreaDao update param areaCode : " + areaCode);
		int result = 0;
		
		String sql = "update area set //TODO where area_code=?";
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
		System.out.println(">>> AreaDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int areaCode) {
		System.out.println(">>> AreaDao delete param areaCode : " + areaCode);
		int result = 0;
		
		String sql = "delete from area where area_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, areaCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> AreaDao delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<AreaVo> selectList(Connection conn){
		List<AreaVo> volist = null;
		
		String sql = "select * from area";
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
		System.out.println(">>> AreaDao selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public AreaVo selectOne(Connection conn, int areaCode){
		System.out.println(">>> AreaDao selectOne param areaCode : " + areaCode);
		AreaVo vo = null;
		
		String sql = "select * from area where area_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, areaCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> AreaDao selectOne return : " + vo);
		return vo;
	}
}
