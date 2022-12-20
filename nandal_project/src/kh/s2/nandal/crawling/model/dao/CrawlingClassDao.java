package kh.s2.nandal.crawling.model.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;
import kh.s2.nandal.jdbc.JdbcTemplate;


public class CrawlingClassDao {
	public int insertClassPhoto(Connection conn, ClassPhotoVo dto) {
		System.out.println("insertClassPhoto()");
		
		int result = 0;
		String sql = "insert into class_photo values(?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getClassCode());
			pstmt.setString(2, dto.getCpRoute());
			pstmt.setInt(3, dto.getCpType()); 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//정보를 dao에서 받아왔으니 db와의 연결을 끊는 부분
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertClassApply(Connection conn, ClassApplyVo vo) {
		System.out.println("insertClassApply()");
		
		int result = 0;
		String sql = "insert into class_apply(ca_code,member_id,class_code,ca_total,CA_DATE,CO_CODE,CS_CODE) values(?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, vo.getCaCode());
			pstmt.setString(2, vo.getMemberId());
			pstmt.setInt(3, vo.getClassCode());
			pstmt.setInt(4, vo.getCaTotal());
			pstmt.setDate(5, vo.getCaDate());
			pstmt.setInt(6, vo.getCoCode());
			pstmt.setInt(7, vo.getCsCode());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//정보를 dao에서 받아왔으니 db와의 연결을 끊는 부분
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertClass(Connection conn, ClassVo dto) {
		System.out.println("insertClassdaO()");
		
		int result = 0;
		String sql = "insert into class values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getClassCode());
			pstmt.setInt(2, dto.getCategoryCode());
			pstmt.setString(3, dto.getClassName()); 
			pstmt.setString(4, dto.getClassImg()); 
			pstmt.setString(5, dto.getClassIntro()); 
			pstmt.setString(6, dto.getClassCur()); 
			pstmt.setString(7, dto.getClassHost()); 
			pstmt.setString(8, dto.getClassAlltime()); 
			pstmt.setString(9, dto.getClassPrd());
			pstmt.setInt(10, dto.getAreaCode()); 
			pstmt.setString(11, dto.getClassAddress()); 
			pstmt.setInt(12, dto.getClassPrice()); 
			pstmt.setInt(13, dto.getClassLevel()); 
			pstmt.setInt(14, dto.getClassMin()); 
			pstmt.setInt(15, dto.getClassMax()); 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//정보를 dao에서 받아왔으니 db와의 연결을 끊는 부분
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	
	public int selectArea(Connection conn, String areaName) {
		System.out.println("selectArea()");
		
		int result = 0;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select area_code from area where area_name like '%"+ areaName +"%'");
			while(rs.next()){
				result = rs.getInt("area_code");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//정보를 dao에서 받아왔으니 db와의 연결을 끊는 부분
			JdbcTemplate.close(rs);
			JdbcTemplate.close(st);
			JdbcTemplate.close(conn);
		}
		return result;
	}
}
