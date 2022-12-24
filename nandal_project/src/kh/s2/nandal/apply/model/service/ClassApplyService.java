package kh.s2.nandal.apply.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.apply.model.dao.ClassApplyDao;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;
import kh.s2.nandal.apply.model.vo.MyApplyVo;
import kh.s2.nandal.classdata.model.dao.ClassOptionDao;
import kh.s2.nandal.classdata.model.vo.ClassOptionVo;
import kh.s2.nandal.review.model.dao.ReviewDao;
import kh.s2.nandal.review.model.vo.ReviewPhotoVo;

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
	public int update(int caCode) {
		System.out.println(">> ClassApplyService update param caCode :" + caCode);
		Connection conn = JdbcTemplate.getConnection();
		int result = 0;
		result = dao.update(conn, caCode);
		
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
//	MyApplyList - 마이페이지 신청/취소 내역 
	public List<MyApplyVo> MyApplyList(String memberId,String check){
		Connection conn = JdbcTemplate.getConnection();
		List<MyApplyVo> volist = null;
		volist = dao.MyApplyList(conn,memberId,check);

		ClassOptionDao coDao = new ClassOptionDao();
		ReviewDao reDao = new ReviewDao();
		for(int i = 0; i < volist.size(); i++) {
			//옵션 이름,가격 가져오기
			if(volist.get(i).getCoCode() != 0) {
				ClassOptionVo coVo = coDao.MyoptionOne(conn, volist.get(i).getCoCode(), volist.get(i).getClasscode()); 
				volist.get(i).setCoName(coVo.getCoName());
				volist.get(i).setCoPrice(coVo.getCoPrice());
			} else {
				volist.get(i).setCoName("옵션 없음");
				volist.get(i).setCoPrice(0);
			}
			//해당 신청 내역에 작성된 리뷰가 있는지
			volist.get(i).setReviewCheck(reDao.ApplyReviewCheck(conn, volist.get(i).getCaCode()));
		}
		
		JdbcTemplate.close(conn);
		System.out.println(">> ClassApplyService MyApplyList return :" + volist);
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
