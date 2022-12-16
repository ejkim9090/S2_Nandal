package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class ClassDao {
//	insert
	public int insert(Connection conn, ClassVo vo) {
		System.out.println(">>> ClassDao insert param : " + vo);
		int result = 0;
		String sql = "insert into class values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getClassCode());
			pstmt.setInt(2, vo.getCategoryCode());
			pstmt.setString(3, vo.getClassName()); 
			pstmt.setString(4, vo.getClassImg()); 
			pstmt.setString(5, vo.getClassIntro()); 
			pstmt.setString(6, vo.getClassCur()); 
			pstmt.setString(7, vo.getClassHost()); 
			pstmt.setString(8, vo.getClassAlltime()); 
			pstmt.setString(9, vo.getClassPrd()); 
			pstmt.setString(10, vo.getClassAtt()); 
			pstmt.setInt(11, vo.getAreaCode()); 
			pstmt.setString(12, vo.getClassAdress()); 
			pstmt.setInt(13, vo.getClassPrice()); 
			pstmt.setInt(14, vo.getClassLevel()); 
			pstmt.setInt(15, vo.getClassMin()); 
			pstmt.setInt(16, vo.getClassMax()); 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ClassVo vo, int classCode) {
		System.out.println(">>> ClassDao update param vo : " + vo);
		System.out.println(">>> ClassDao update param classcode : " + classCode);
		int result = 0;
		
		String sql = "update class set //TODO where class_code=?";
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
		System.out.println(">>> ClassDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int classCode) {
		System.out.println(">>> ClassDao delete param classcode : " + classCode);
		int result = 0;
		
		String sql = "delete from class where class_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<ClassVo> selectList(Connection conn){
		List<ClassVo> volist = null;
		
		String sql = "select * from class";
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
		System.out.println(">>> ClassDao selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public ClassVo selectOne(Connection conn, int classCode){
		System.out.println(">>> ClassDao selectOne param classcode : " + classCode);
		ClassVo vo = null;
		
		String sql = "select * from class where class_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao selectOne return : " + vo);
		return vo;
	}
}
