package kh.s2.nandal.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kh.s2.nandal.jdbc.JdbcTemplate;
import kh.s2.nandal.member.model.vo.MemberVo;

public class MemberDao {
//	insert
	public int insert(Connection conn, MemberVo vo) {
		System.out.println(">>> MemberDao insert param : " + vo);
		int result = 0;
		String sql = "insert into member values(?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberId());
			pstmt.setString(2, vo.getMemberPwd());
			pstmt.setString(3, vo.getMemberName());
			pstmt.setString(4, vo.getMemberPhone());
			pstmt.setString(5, vo.getMemberImg());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> MemberDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, MemberVo vo, String memberId) {
		System.out.println(">>> MemberDao update param vo : " + vo);
		System.out.println(">>> MemberDao update param memberId : " + memberId);
		int result = 0;
		
		String sql = "update member set //TODO where member_id=?";
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
		System.out.println(">>> MemberDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, String memberId) {
		System.out.println(">>> MemberDao delete param memberId : " + memberId);
		int result = 0;
		
		String sql = "delete from member where member_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> MemberDao delete return : " + result);
		return result;
	}
//	selectList - 목록조회
	public List<MemberVo> selectList(Connection conn){
		List<MemberVo> volist = null;
		
		String sql = "select * from member";
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
		System.out.println(">>> MemberDao selectList return : " + volist);
		return volist;
	}
//	selectOne - 상세조회
	public MemberVo selectOne(Connection conn, String memberId){
		System.out.println(">>> MemberDao selectOne param memberId : " + memberId);
		MemberVo vo = null;
		
		String sql = "select * from member where member_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> MemberDao selectOne return : " + vo);
		return vo;
	}
}
