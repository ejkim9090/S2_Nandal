package kh.s2.nandal.member.model.service;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.member.model.dao.MemberDao;
import kh.s2.nandal.member.model.vo.MemberVo;

public class MemberService {
	private MemberDao dao = new MemberDao();
	// 최소 5개 CRUD
	public MemberVo login(String memberId, String memberPwd) {
		System.out.println(">> MemberService login param memberId :" + memberId);
		System.out.println(">> MemberService login param memberPwd :" + memberPwd);
		Connection conn = JdbcTemplate.getConnection();
		MemberVo vo = null;
		vo = dao.login(conn, memberId,memberPwd);
		JdbcTemplate.close(conn);
		System.out.println(">> MemberService login return :" + vo);
		return vo;
	}
//	insert
	public int insert(MemberVo vo) {
		System.out.println(">> MemberService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		if(result > 0) {
			JdbcTemplate.commit(conn);
			System.out.println("커밋 완료");
		} else {
			JdbcTemplate.rollback(conn);
			System.out.println("커밋 실패");
		}
		System.out.println(">> MemberService insert return :" + result);
		return result;
	}
//	update
	public int update(MemberVo vo, String memberId) {
		System.out.println(">> MemberService update param memberId :" + memberId);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, memberId);
		if(result > 0) {
			JdbcTemplate.commit(conn);
			System.out.println("커밋 완료");
		} else {
			JdbcTemplate.rollback(conn);
			System.out.println("커밋 실패");
		}
		
		JdbcTemplate.close(conn);
		System.out.println(">> MemberService update return :" + result);
		return result;
	}
//	delete
	public int delete(String memberId) {
		System.out.println(">> MemberService delete param memberId :" + memberId);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, memberId);
		JdbcTemplate.close(conn);
		System.out.println(">> MemberService delete return :" + result);
		return result;
	}
//	selectList
	public List<MemberVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<MemberVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> MemberService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public MemberVo selectOne(String memberId){
		System.out.println(">> MemberService selectOne param memberId :" + memberId);
		Connection conn = JdbcTemplate.getConnection();
		MemberVo vo = null;
		vo = dao.selectOne(conn, memberId);
		JdbcTemplate.close(conn);
		System.out.println(">> MemberService selectOne return :" + vo);
		return vo;
	}
}
