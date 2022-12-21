package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kh.s2.nandal.classdata.model.vo.ClassScheduleVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class ClassScheduleDao {
//	insert
	public int insert(Connection conn, ClassScheduleVo vo) {
		System.out.println(">>> ClassScheduleDao insert param : " + vo);
		int result = 0;
		String sql = "insert into class_schedule values(?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCsCode());
			pstmt.setInt(2, vo.getClassCode());
			pstmt.setInt(3, vo.getCsDay());
			pstmt.setString(4, vo.getCsStime());
			pstmt.setString(5, vo.getCsFtime());
			pstmt.setDate(6, vo.getCsSdate());
			pstmt.setDate(7, vo.getCsFdate());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassScheduleDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ClassScheduleVo vo, int classCode, int csCode) {
		System.out.println(">>> ClassScheduleDao update param vo : " + vo);
		System.out.println(">>> ClassScheduleDao update param classCode : " + classCode);
		System.out.println(">>> ClassScheduleDao update param csCode : " + csCode);
		int result = 0;
		
		String sql = "update class_schedule set //TODO where class_code=? and cs_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			//TODO
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassScheduleDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int classCode, int csCode) {
		System.out.println(">>> ClassScheduleDao delete param classCode : " + classCode);
		System.out.println(">>> ClassScheduleDao delete param csCode : " + csCode);
		int result = 0;
		
		String sql = "delete from class_schedule where category_code=? and cs_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			pstmt.setInt(2, csCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassScheduleDao delete return : " + result);
		return result;
	}
//	selectList - 상세페이지 날짜별 일정 조회
	public List<ClassScheduleVo> selectList(Connection conn,int classCode, int day,String date){
		List<ClassScheduleVo> volist = null;
		
		String sql = "select CS_CODE, CS_STIME, CS_FTIME "
				+ "    from CLASS_SCHEDULE "
				+ "    where CLASS_CODE = ? and BITAND(cs_day, ?) = ? and TO_DATE(?, 'YYYY-MM-DD') between cs_sdate and cs_fdate";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			pstmt.setInt(2, day);
			pstmt.setInt(3, day);
			pstmt.setString(4, date);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassScheduleVo>();
				do {
					ClassScheduleVo vo = new ClassScheduleVo();
					vo.setCsCode(rs.getInt("CS_CODE"));
					vo.setCsStime(rs.getString("CS_STIME"));
					vo.setCsFtime(rs.getString("CS_FTIME"));
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
		System.out.println(">>> ClassScheduleDao selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public ClassScheduleVo selectOne(Connection conn, int classCode, int csCode){
		System.out.println(">>> ClassScheduleDao selectOne param classCode : " + classCode);
		System.out.println(">>> ClassScheduleDao selectOne param csCode : " + csCode);
		ClassScheduleVo vo = null;
		
		String sql = "select * from class_schedule where category_code=? and cs_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			pstmt.setInt(2, csCode);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassScheduleDao selectOne return : " + vo);
		return vo;
	}
}
