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

import kh.s2.nandal.crawling.jdbc.JdbcTemplate;
import kh.s2.nandal.crawling.model.vo.ClassDto;


public class ClassDao {
	public int insertClass(Connection conn, ClassDto dto) {
		System.out.println("insertClass()");
		
		int result = 0;
		String sql = "insert into class values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
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
			pstmt.setString(10, dto.getClassAtt()); 
			pstmt.setInt(11, dto.getAreaCode()); 
			pstmt.setString(12, dto.getClassAdress()); 
			pstmt.setInt(13, dto.getClassPrice()); 
			pstmt.setInt(14, dto.getClassLevel()); 
			pstmt.setInt(15, dto.getClassMin()); 
			pstmt.setInt(16, dto.getClassMax()); 
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
