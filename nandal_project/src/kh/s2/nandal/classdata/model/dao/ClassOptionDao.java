package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.classdata.model.vo.ClassOptionVo;

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
	public List<ClassOptionVo> selectList(Connection conn,int classCode){
		List<ClassOptionVo> volist = null;
		
		String sql = "select CO_CODE, CO_NAME, CO_PRICE from class_option where class_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassOptionVo>();
				do {
					ClassOptionVo vo = new ClassOptionVo();
					vo.setCoCode(rs.getInt("CO_CODE"));
					vo.setCoName(rs.getString("CO_NAME"));
					vo.setCoPrice(rs.getInt("CO_PRICE"));
					volist.add(vo);
				} while(rs.next());
			}
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
//	selectOne - 마이 리뷰 각 리스트에서 옵션 정보 조회 시
	public ClassOptionVo myOptionOne(Connection conn, int coCode, int classCode){
		System.out.println(">>> ClassOptionDao MyoptionOne param coCode : " + coCode + classCode);
		ClassOptionVo vo = null;
		
		String sql = "select * from class_option where co_code=? and class_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coCode);
			pstmt.setInt(2, classCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new ClassOptionVo();
				vo.setCoName(rs.getString("co_name"));
				vo.setCoPrice(rs.getInt("co_price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassOptionDao MyoptionOne return : " + vo);
		return vo;
	}
}
