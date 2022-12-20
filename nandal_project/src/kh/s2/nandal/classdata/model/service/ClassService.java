package kh.s2.nandal.classdata.model.service;

import java.sql.Connection;
import java.util.List;

import kh.s2.nandal.classdata.model.dao.ClassDao;
import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.jdbc.JdbcTemplate;


public class ClassService {
	private ClassDao dao = new ClassDao();
	// 최소 5개 CRUD
//	insert
	public int insert(ClassVo vo) {
		System.out.println(">> ClassService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		System.out.println(">> ClassService insert return :" + result);
		return result;
	}
//	update
	public int update(ClassVo vo, int classCode) {
		System.out.println(">> ClassService update param classCode :" + classCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, classCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ClassService update return :" + result);
		return result;
	}
//	delete
	public int delete(int classCode) {
		System.out.println(">> ClassService delete param classCode :" + classCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, classCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassService delete return :" + result);
		return result;
	}
//	selectList
	public List<ClassVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<ClassVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassService selectList return :" + volist);
		return volist;
	}

//	selectList - 리뷰 추천유형 해당 목록
	public List<ClassVo> groupList(int group){
		Connection conn = JdbcTemplate.getConnection();
		List<ClassVo> volist = null;
		volist = dao.groupList(conn,group);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassService groupList return :" + volist);
		return volist;
	}	
	
//	selectList - keyword 검색
	public List<ClassVo> keywordList(String keyword){
		Connection conn = JdbcTemplate.getConnection();
		List<ClassVo> volist = null;
		volist = dao.keywordList(conn,keyword);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassService keywordList return :" + volist);
		return volist;
	}	
	
//	selectOne
	public ClassVo selectOne(int classCode){
		System.out.println(">> ClassService selectOne param classCode :" + classCode);
		Connection conn = JdbcTemplate.getConnection();
		ClassVo vo = null;
		vo = dao.selectOne(conn, classCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassService selectOne return :" + vo);
		return vo;
	}
//	selectOne - 클래스 수
	public int selectKeyword(String keyword) {
		System.out.println(">> ClassService selectKeyword param keyword :" + keyword);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.selectKeyword(conn, keyword);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ClassService selectKeyword return :" + keyword);
		return result;
	}
}
