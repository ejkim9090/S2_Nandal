package kh.s2.nandal.classdata.model.service;

import java.sql.Connection;
import java.util.List;

import kh.s2.nandal.classdata.model.dao.ClassPhotoDao;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class ClassPhotoService {
	private ClassPhotoDao dao = new ClassPhotoDao();
	// 최소 5개 CRUD
//	insert
	public int insert(ClassPhotoVo vo) {
		System.out.println(">> ClassPhotoService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		System.out.println(">> ClassPhotoService insert return :" + result);
		return result;
	}
//	update
	public int update(ClassPhotoVo vo, int classCode) {
		System.out.println(">> ClassPhotoService update param classCode :" + classCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, classCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ClassPhotoService update return :" + result);
		return result;
	}
//	delete
	public int delete(int classCode) {
		System.out.println(">> ClassPhotoService delete param classCode :" + classCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, classCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassPhotoService delete return :" + result);
		return result;
	}
//	selectList
	public List<ClassPhotoVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<ClassPhotoVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassPhotoService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public ClassPhotoVo selectOne(int classCode){
		Connection conn = JdbcTemplate.getConnection();
		ClassPhotoVo vo = null;
		vo = dao.selectOne(conn, classCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ClassPhotoService selectOne return :" + vo);
		return vo;
	}
}
