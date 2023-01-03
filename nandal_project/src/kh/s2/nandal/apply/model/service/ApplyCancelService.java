package kh.s2.nandal.apply.model.service;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.apply.model.dao.ApplyCancelDao;
import kh.s2.nandal.apply.model.dao.ClassApplyDao;
import kh.s2.nandal.apply.model.vo.ApplyCancelVo;
import kh.s2.nandal.review.model.dao.ReviewDao;
import kh.s2.nandal.review.model.dao.ReviewPhotoDao;

public class ApplyCancelService {
	private ApplyCancelDao dao = new ApplyCancelDao();
	private ClassApplyDao caDao = new ClassApplyDao();
	private ReviewDao reDao = new ReviewDao();
	private ReviewPhotoDao rpDao = new ReviewPhotoDao();
	
	// 최소 5개 CRUD
//	insert
	public int insert(int acCode) {
		System.out.println(">> ApplyCancelService insert param acCode :" + acCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		if(caDao.update(conn, acCode) > 0) {
			result = dao.insert(conn, acCode);
			if(result > 0) {
					rpDao.delete(conn, acCode);
					reDao.delete(conn, acCode);
			}
		}
		if(result > 0) {
			JdbcTemplate.commit(conn); // 커밋
			System.out.println("커밋성공");
		} else {
			JdbcTemplate.rollback(conn); // 롤백
			System.out.println("커밋실패");
		}
		System.out.println(">> ApplyCancelService insert return :" + result);
		return result;
	}
//	update
	public int update(ApplyCancelVo vo, int acCode) {
		System.out.println(">> ApplyCancelService update param categoryCode :" + acCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, vo, acCode);
		
		JdbcTemplate.close(conn);
		System.out.println(">> ApplyCancelService update return :" + result);
		return result;
	}
//	delete
	public int delete(int acCode) {
		System.out.println(">> ApplyCancelService delete param acCode :" + acCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.delete(conn, acCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ApplyCancelService delete return :" + result);
		return result;
	}
//	selectList
	public List<ApplyCancelVo> selectList(){
		Connection conn = JdbcTemplate.getConnection();
		List<ApplyCancelVo> volist = null;
		volist = dao.selectList(conn);
		JdbcTemplate.close(conn);
		System.out.println(">> ApplyCancelService selectList return :" + volist);
		return volist;
	}
//	selectOne
	public ApplyCancelVo selectOne(int acCode){
		System.out.println(">> ApplyCancelService selectOne param acCode :" + acCode);
		Connection conn = JdbcTemplate.getConnection();
		ApplyCancelVo vo = null;
		vo = dao.selectOne(conn, acCode);
		JdbcTemplate.close(conn);
		System.out.println(">> ApplyCancelService selectOne return :" + vo);
		return vo;
	}
}
