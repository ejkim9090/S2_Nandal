package kh.s2.nandal.apply.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;
import kh.s2.nandal.apply.model.vo.MyApplyVo;

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
	public int update(Connection conn, int caCode) {
		System.out.println(">>> ClassApplyService update param caCode : " + caCode);
		int result = 0;
		
		String sql = "update class_apply set CA_CANCEL = 'Y' where ca_code = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, caCode);
			result = pstmt.executeUpdate();
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
//	selectList - 목록조회
	public List<MyApplyVo> myApplyList(Connection conn,String memberId,String check){
		List<MyApplyVo> volist = null;
		String sql = null;
		
		if(check.equals("N")) {
			sql = "select ca.CA_CODE,class_code,c.CLASS_NAME,c.CLASS_PRICE,ca.CA_TOTAL,TO_CHAR(ca.CA_DATE,'YYYY-MM-DD') as CA_DATE,TO_CHAR(ca.CA_TIME,'YYYY-MM-DD HH24:MI:SS') as TIME,ca.CO_CODE,cs.CS_STIME ,cs.CS_FTIME "
					+ "    from (select * from CLASS_APPLY where MEMBER_ID = ? and CA_CANCEL = ?) ca "
					+ "                 join CLASS c using(class_code) "
					+ "                 join CLASS_SCHEDULE cs using(class_code,cs_code)";
		} else {
			sql = "select aca.CA_CODE,class_code,c.CLASS_NAME ,c.CLASS_PRICE,aca.CA_TOTAL,TO_CHAR(aca.CA_DATE,'YYYY-MM-DD') as CA_DATE,TO_CHAR(aca.AC_TIME,'YYYY-MM-DD HH24:MI:SS') as TIME,aca.CO_CODE, cs.CS_STIME ,cs.CS_FTIME "
					+ "    from (select ca.*,ac.AC_TIME "
					+ "                from CLASS_APPLY ca join APPLY_CANCEL ac on ca.CA_CODE = ac.AC_CODE "
					+ "                where MEMBER_ID = ? and CA_CANCEL = ?) aca "
					+ "                    join CLASS c using(class_code) "
					+ "                    join CLASS_SCHEDULE cs using(class_code,cs_code)";
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, check);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<MyApplyVo>();
				do {
					MyApplyVo vo = new MyApplyVo();
					vo.setCaCode(rs.getInt("CA_CODE"));
					vo.setClassCode(rs.getInt("CLASS_CODE"));
					vo.setClassName(rs.getString("CLASS_NAME"));
					vo.setClassPrice(rs.getInt("CLASS_PRICE"));
					vo.setCaTotal(rs.getInt("CA_TOTAL"));
					vo.setCaDate(rs.getString("CA_DATE"));
					vo.setCaTime(rs.getString("TIME"));
					vo.setCoCode(rs.getInt("CO_Code"));
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
		System.out.println(">>> ClassApplyService MyApplyList return : " + volist);
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
