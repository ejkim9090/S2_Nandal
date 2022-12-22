package kh.s2.nandal.classdata.model.service;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.classdata.model.dao.ClassScheduleDao;
import kh.s2.nandal.classdata.model.vo.ClassScheduleVo;

public class ClassScheduleService {
	private ClassScheduleDao dao = new ClassScheduleDao();
	// 최소 5개 CRUD
//	insert
	public int insert(ClassScheduleVo vo) {
		System.out.println(">> ClassScheduleService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		System.out.println(">> ClassScheduleService insert return :" + result);
		return result;
	}
//	update
	public int update(ClassScheduleVo vo, int classCode, int csCode) {
		System.out.println(">> ClassScheduleService update param classCode :" + classCode);
		System.out.println(">> ClassScheduleService update param csCode :" + csCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, classCode, csCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ClassScheduleService update return :" + result);
		return result;
	}
//	delete
	public int delete(int classCode, int csCode) {
		System.out.println(">> ClassScheduleService delete param classCode :" + classCode);
		System.out.println(">> ClassScheduleService delete param csCode :" + csCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, classCode, csCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassScheduleService delete return :" + result);
		return result;
	}
//	selectList - 상세페이지 날짜별 일정 조회
	public List<ClassScheduleVo> selectList(int classCode, int day,String dates){
		Connection conn = JdbcTemplate.getConnection();
		List<ClassScheduleVo> volist = null;
		volist = dao.selectList(conn,classCode,day,dates);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassScheduleService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public ClassScheduleVo selectOne(int classCode, int csCode){
		System.out.println(">> ClassScheduleService selectOne param classCode :" + classCode);
		System.out.println(">> ClassScheduleService selectOne param csCode :" + csCode);
		Connection conn = JdbcTemplate.getConnection();
		ClassScheduleVo vo = null;
		vo = dao.selectOne(conn, classCode, csCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassScheduleService selectOne return :" + vo);
		return vo;
	}
}
