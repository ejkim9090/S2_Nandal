package kh.s2.nandal.classdata.model.service;

import java.sql.Connection;
import java.util.List;

import kh.s2.nandal.classdata.model.dao.ClassOptionDao;
import kh.s2.nandal.classdata.model.vo.ClassOptionVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class ClassOptionService {
	private ClassOptionDao dao = new ClassOptionDao();
	// 최소 5개 CRUD
//	insert
	public int insert(ClassOptionVo vo) {
		System.out.println(">> ClassOptionService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		System.out.println(">> ClassOptionService insert return :" + result);
		return result;
	}
//	update
	public int update(ClassOptionVo vo, int coCode) {
		System.out.println(">> ClassOptionService update param coCode :" + coCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, coCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ClassOptionService update return :" + result);
		return result;
	}
//	delete
	public int delete(int coCode) {
		System.out.println(">> ClassOptionService delete param coCode :" + coCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, coCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassOptionService delete return :" + result);
		return result;
	}
//	selectList
	public List<ClassOptionVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<ClassOptionVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassOptionService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public ClassOptionVo selectOne(int coCode){
		System.out.println(">> ClassOptionService selectOne param coCode :" + coCode);
		Connection conn = JdbcTemplate.getConnection();
		ClassOptionVo vo = null;
		vo = dao.selectOne(conn, coCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassOptionService selectOne return :" + vo);
		return vo;
	}
}
