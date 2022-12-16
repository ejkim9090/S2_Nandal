package kh.s2.nandal.classdata.model.service;

import java.sql.Connection;
import java.util.List;

import kh.s2.nandal.classdata.model.dao.AreaDao;
import kh.s2.nandal.classdata.model.vo.AreaVo;
import kh.s2.nandal.jdbc.JdbcTemplate;

public class AreaService {
	private AreaDao dao = new AreaDao();
	// 최소 5개 CRUD
//	insert
	public int insert(AreaVo vo) {
		System.out.println(">> AreaService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.insert(conn, vo);
		System.out.println(">> AreaService insert return :" + result);
		return result;
	}
//	update
	public int update(AreaVo vo, int areaCode) {
		System.out.println(">> AreaService update param areaCode :" + areaCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, areaCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> AreaService update return :" + result);
		return result;
	}
//	delete
	public int delete(int areaCode) {
		System.out.println(">> AreaService delete param areaCode :" + areaCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, areaCode);
		JdbcTemplate.close(conn);
		System.out.println(">> AreaService delete return :" + result);
		return result;
	}
//	selectList
	public List<AreaVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<AreaVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> AreaService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public AreaVo selectOne(int areaCode){
		System.out.println(">> AreaService selectOne param areaCode :" + areaCode);
		Connection conn = JdbcTemplate.getConnection();
		AreaVo vo = null;
		vo = dao.selectOne(conn, areaCode);
		JdbcTemplate.close(conn);
		System.out.println(">> AreaService selectOne return :" + vo);
		return vo;
	}
}
