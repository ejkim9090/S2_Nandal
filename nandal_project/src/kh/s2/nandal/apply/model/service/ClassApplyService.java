package kh.s2.nandal.apply.model.service;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.apply.model.dao.ClassApplyDao;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;

public class ClassApplyService {
	private ClassApplyDao dao = new ClassApplyDao();
	// 최소 5개 CRUD
//	insert
	public int insert(ClassApplyVo vo) {
		System.out.println(">> ClassApplyService insert param vo :" + vo);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		//해당 클래스 마지막 caCode 조회
		int lastCaCode = dao.lastCaCode(conn, vo.getClassCode());
		
		int caCode = 0;
		if(lastCaCode == 0) {
			caCode = vo.getClassCode()*10 + 1;
			System.out.println("@@@@들어갈 caCode : " + caCode);
			vo.setCaCode(caCode);
		} else {
			int check1 = lastCaCode%10;
			int check2 = lastCaCode/10;
			System.out.println("@@@@마지막 번호의 나머지 값 : " + check1);
			System.out.println("@@@@마지막 번호의 나눈 값 : " + check2);
			if(check1 == 9) {
				caCode = check2*100 + 10;
				System.out.println("@@@@들어갈 caCode : " + caCode);
			} else {
				caCode = check2*10 + (check1+1);
				System.out.println("@@@@들어갈 caCode : " + caCode);
			}
			vo.setCaCode(caCode);
		}
		result = dao.insert(conn, vo);
		if(result > 0) {
			JdbcTemplate.commit(conn); // 커밋
			System.out.println("커밋성공");
		} else {
			JdbcTemplate.rollback(conn); // 롤백
			System.out.println("커밋실패");
		}
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
