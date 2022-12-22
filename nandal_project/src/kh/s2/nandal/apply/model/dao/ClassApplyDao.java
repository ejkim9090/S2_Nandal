package kh.s2.nandal.apply.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;

public class ClassApplyDao {
//	insert
	public int insert(Connection conn, ClassApplyVo vo) {
		System.out.println(">>> ClassApplyService insert param : " + vo);
		int result = 0;
		String sql = "insert into class_apply (CA_CODE,MEMBER_ID,CA_TOTAL,CA_DATE,CO_CODE,CS_CODE,CLASS_CODE) values(?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCaCode());
			pstmt.setString(2, vo.getMemberId());
			pstmt.setInt(3, vo.getCaTotal());
			pstmt.setDate(4, vo.getCaDate());
			pstmt.setInt(5, vo.getCoCode());
			pstmt.setInt(6, vo.getCsCode());
			pstmt.setInt(7, vo.getClassCode());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassApplyService insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ClassApplyVo vo, int caCode) {
		System.out.println(">>> ClassApplyService update param vo : " + vo);
		System.out.println(">>> ClassApplyService update param caCode : " + caCode);
		int result = 0;
		
		String sql = "update class_apply set //TODO where ca_code=?";
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
		System.out.println(">>> ClassApplyService update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int caCode) {
		System.out.println(">>> ClassApplyService delete param caCode : " + caCode);
		int result = 0;
		
		String sql = "delete from class_apply where ca_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, caCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassApplyService delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<ClassApplyVo> selectList(Connection conn){
		List<ClassApplyVo> volist = null;
		
		String sql = "select * from class_apply";
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
		System.out.println(">>> ClassApplyService selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public ClassApplyVo selectOne(Connection conn, int caCode){
		System.out.println(">>> ClassApplyService selectOne param caCode : " + caCode);
		ClassApplyVo vo = null;
		
		String sql = "select * from class_apply where ca_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, caCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassApplyService selectOne return : " + vo);
		return vo;
	}
//	selectOne - insert 전 해당 클래스 마지막 caCode 조회
	public int lastCaCode(Connection conn, int classCode){
		System.out.println(">>> ClassApplyService lastCaCode param classCode : " + classCode);
		int result = 0;
		
		String sql = "select distinct last_value(CA_CODE)  over() a"
				+ "    from class_apply "
				+ "    where class_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassApplyService lastCaCode return : " + result);
		return result;
	}
}
