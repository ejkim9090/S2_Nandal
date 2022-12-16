package kh.s2.nandal.apply.model.service;

import java.sql.Connection;
import java.util.List;

import kh.s2.nandal.apply.model.dao.ClassApplyDao;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class ClassApplyService {
	private ClassApplyDao dao = new ClassApplyDao();
	// 최소 5개 CRUD
//	insert
	public int insert(ClassApplyVo vo) {
		System.out.println(">> ClassApplyService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		System.out.println(">> ClassApplyService insert return :" + result);
		return result;
	}
//	update
	public int update(ClassApplyVo vo, int caCode) {
		System.out.println(">> ClassApplyService update param caCode :" + caCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, caCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ClassApplyService update return :" + result);
		return result;
	}
//	delete
	public int delete(int caCode) {
		System.out.println(">> ClassApplyService delete param caCode :" + caCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, caCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassApplyService delete return :" + result);
		return result;
	}
//	selectList
	public List<ClassApplyVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<ClassApplyVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassApplyService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public ClassApplyVo selectOne(int caCode){
		System.out.println(">> ClassApplyService selectOne param caCode :" + caCode);
		Connection conn = JdbcTemplate.getConnection();
		ClassApplyVo vo = null;
		vo = dao.selectOne(conn, caCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassApplyService selectOne return :" + vo);
		return vo;
	}
}
