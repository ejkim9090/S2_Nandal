package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;

public class ClassPhotoDao {
//	insert
	public int insert(Connection conn, ClassPhotoVo vo) {
		System.out.println(">>> ClassPhotoDao insert param vo : " + vo);
		int result = 0;
		System.out.println(">>> ClassPhotoDao insert return : " + result);
		String sql = "insert into class_photo values(?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getClassCode());
			pstmt.setString(2, vo.getCpRoute());
			pstmt.setInt(3, vo.getCpType()); 
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		
		return result;
	}
//	update
	public int update(Connection conn, ClassPhotoVo vo, int classCode) {
		int result = 0;
		
		String sql = "update class_photo set //TODO where class_code=?";
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
	public int delete(Connection conn, int classCode) {
		int result = 0;
		
		String sql = "delete from class_photo where class_code=?";
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
		
		return result;
	}
//	selectList - 해당 클래스의 사진 가져오기 0이면 서브사진, 1이면 내용사진
	public List<ClassPhotoVo> selectList(Connection conn,int classCode,int cpType){
		List<ClassPhotoVo> volist = null;
		
		String sql = "select * from CLASS_PHOTO "
				+ "    where class_code = ? and CP_TYPE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			pstmt.setInt(2, cpType);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassPhotoVo>();
				do {
					ClassPhotoVo vo = new ClassPhotoVo();
					vo.setClassCode(rs.getInt("class_code"));
					vo.setCpRoute(rs.getString("cp_route"));
					vo.setCpType(rs.getInt("cp_type"));
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
	public ClassPhotoVo selectOne(Connection conn, int classCode){
		ClassPhotoVo vo = null;
		
		//TODO - classcode가 pk가 아니므로 하나만 조회하는 경우로 수정해야함.
		
		String sql = "select * from class_photo where class_code=?";
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
		return vo;
	}
}
