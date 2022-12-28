package kh.s2.nandal.review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.review.model.vo.ClassReviewVo;
import kh.s2.nandal.review.model.vo.ReviewMainListVo;
import kh.s2.nandal.review.model.vo.ReviewVo;

public class ReviewDao {
//	insert
	public int insert(Connection conn, ReviewVo vo) {
		System.out.println(">>> ReviewDao insert param vo : " + vo);
		int result = 0;
		String sql = "insert into review (REVIEW_CODE,REVIEW_CONT,REVIEW_GRADE,REVIEW_KIND,REVIEW_COMPONENT,REVIEW_FACILITY,REVIEW_LEVEL,REVIEW_GROUP) values(?,?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getReviewCode());
			pstmt.setString(2, vo.getReviewCont());
			pstmt.setDouble(3, vo.getReviewGrade());
			pstmt.setInt(4, vo.getReviewKind());
			pstmt.setInt(5, vo.getReviewComponent());
			pstmt.setInt(6, vo.getReviewFacility());
			pstmt.setInt(7, vo.getReviewLevel());
			pstmt.setInt(8, vo.getReviewGroup());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ReviewVo vo, int reviewCode) {
		System.out.println(">>> ReviewDao update param vo : " + vo);
		System.out.println(">>> ReviewDao update param reviewCode : " + reviewCode);
		int result = 0;
		
		String sql = "update review set REVIEW_TIME=CURRENT_TIMESTAMP, REVIEW_CONT=?, REVIEW_GRADE=?, REVIEW_KIND=?, REVIEW_COMPONENT=?,"
						+"REVIEW_FACILITY=?, REVIEW_LEVEL=?, REVIEW_GROUP=? where review_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getReviewCont());
			pstmt.setDouble(2, vo.getReviewGrade());
			pstmt.setInt(3, vo.getReviewKind());
			pstmt.setInt(4, vo.getReviewComponent());
			pstmt.setInt(5, vo.getReviewFacility());
			pstmt.setInt(6, vo.getReviewLevel());
			pstmt.setInt(7, vo.getReviewGroup());
			pstmt.setInt(8, reviewCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int reviewCode) {
		System.out.println(">>> ReviewDao delete param reviewCode : " + reviewCode);
		int result = 0;
		
		String sql = "delete from REVIEW where REVIEW_CODE = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao delete return : " + result);
		return result;
	}
//	selectList - 마이페이지 리뷰 목록 가져오기
	public List<ClassReviewVo> MyReviewList(Connection conn,String memberId){
		List<ClassReviewVo> volist = null;
		
		String sql = "select class_code, c.CLASS_NAME, r.REVIEW_CODE,r.REVIEW_COMPONENT,r.REVIEW_CONT,r.REVIEW_FACILITY,r.REVIEW_GRADE,r.REVIEW_GROUP,r.REVIEW_KIND,r.REVIEW_LEVEL,TO_CHAR(r.REVIEW_TIME,'YYYY-MM-DD HH24:MI:SS') as REVIEW_TIME"
				+ "    from (select CA_CODE,CLASS_CODE "
				+ "                from CLASS_APPLY "
				+ "                where MEMBER_ID = ? and CA_CANCEL = 'N') ca "
				+ "                join REVIEW r on ca.CA_CODE = r.review_code "
				+ "                join class  c using(class_code)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassReviewVo>();
				do {
					ClassReviewVo vo = new ClassReviewVo();
					vo.setReviewCode(rs.getInt("REVIEW_CODE"));
					vo.setReviewTime(rs.getString("REVIEW_TIME"));
					vo.setReviewCont(rs.getString("REVIEW_CONT").replaceAll("%%", "<br>"));
					vo.setReviewGrade(rs.getDouble("REVIEW_GRADE"));
					vo.setReviewKind(rs.getInt("REVIEW_KIND"));
					vo.setReviewComponent(rs.getInt("REVIEW_COMPONENT"));
					vo.setReviewFacility(rs.getInt("REVIEW_FACILITY"));
					vo.setReviewLevel(rs.getInt("REVIEW_LEVEL"));
					vo.setReviewGroup(rs.getInt("REVIEW_GROUP"));
					vo.setClassName(rs.getString("CLASS_NAME"));
					vo.setClassCode(rs.getInt("class_code"));
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao MyReviewList return : " + volist);
		return volist;
	}
//	selectList - 상세페이지 해당 클래스의 리뷰 목록 가져오기
	public List<ClassReviewVo> selectClassList(Connection conn,int classCode){
		List<ClassReviewVo> volist = null;
		
		String sql = "select r.REVIEW_CODE,r.REVIEW_COMPONENT,r.REVIEW_CONT,r.REVIEW_FACILITY,r.REVIEW_GRADE,r.REVIEW_GROUP,r.REVIEW_KIND,r.REVIEW_LEVEL,TO_CHAR(r.REVIEW_TIME,'YYYY-MM-DD HH24:MI:SS') as REVIEW_TIME, member_name "
				+ "    from class_apply ca join REVIEW r on ca.CA_CODE = r.REVIEW_CODE "
				+ "                                join member m using(member_id)"
				+ "    where class_code = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassReviewVo>();
				do {
					ClassReviewVo vo = new ClassReviewVo();
					vo.setReviewCode(rs.getInt("REVIEW_CODE"));
					vo.setReviewTime(rs.getString("REVIEW_TIME"));
					vo.setReviewCont(rs.getString("REVIEW_CONT").replaceAll("%%", "<br>"));
					vo.setReviewGrade(rs.getDouble("REVIEW_GRADE"));
					vo.setReviewKind(rs.getInt("REVIEW_KIND"));
					vo.setReviewComponent(rs.getInt("REVIEW_COMPONENT"));
					vo.setReviewFacility(rs.getInt("REVIEW_FACILITY"));
					vo.setReviewLevel(rs.getInt("REVIEW_LEVEL"));
					vo.setReviewGroup(rs.getInt("REVIEW_GROUP"));
					vo.setMemberName(rs.getString("member_name"));
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao selectClassList return : " + volist);
		return volist;
	}
//	selectList - 메인화면 리뷰 목록 조회된 개수 
	public int selectListCnt(Connection conn,int reviewGrade){
		int result = 0;
		
		String sql = "select count(*) cnt"
				+ "	   from (select  distinct first_value(rp.RP_ROUTE)  over(partition by review_code) a, r.REVIEW_CONT, REVIEW_CODE, ca.class_code "
				+ "    			from review r join review_photo rp using(review_code) "
				+ "                        		join CLASS_APPLY ca on review_code = ca.ca_code "
				+ "     		where review_grade > ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewGrade);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao selectListCnt return : " + result);
		return result;
	}	
//	selectList - 메인화면 리뷰 목록 조회
	public List<ReviewMainListVo> selectList(Connection conn,int reviewGrade){
		List<ReviewMainListVo> volist = null;
		
		String sql = "select  distinct first_value(rp.RP_ROUTE)  over(partition by review_code) a, r.REVIEW_CONT, REVIEW_CODE, ca.class_code "
				+ "    from review r join review_photo rp using(review_code) "
				+ "                        join CLASS_APPLY ca on review_code = ca.ca_code "
				+ "     where review_grade > ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewGrade);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ReviewMainListVo>();
				do {
					ReviewMainListVo vo = new ReviewMainListVo();
					vo.setRpRoute(rs.getString(1));
					vo.setReviewCont(rs.getString(2));
					vo.setReviewCode(rs.getInt(3));
					vo.setClassCode(rs.getInt(4));
					volist.add(vo);
				}while(rs.next());
			}
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao selectList return : " + volist);
		return volist;
	}
	
//	selectOne - 상세조회
	public ReviewVo selectOne(Connection conn, int reviewCode){
		System.out.println(">>> ReviewDao selectOne param reviewCode : " + reviewCode);
		ReviewVo vo = null;
		
		String sql = "select * from review where review_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new ReviewVo();
				vo.setReviewCont(rs.getString("REVIEW_CONT"));
				vo.setReviewComponent(rs.getInt("REVIEW_COMPONENT"));
				vo.setReviewFacility(rs.getInt("REVIEW_FACILITY"));
				vo.setReviewGrade(rs.getInt("REVIEW_GRADE"));
				vo.setReviewGroup(rs.getInt("REVIEW_GROUP"));
				vo.setReviewKind(rs.getInt("REVIEW_KIND"));
				vo.setReviewLevel(rs.getInt("REVIEW_LEVEL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao selectOne return : " + vo);
		return vo;
	}
//	selectOne - 해당 클래스 신청에 작성된 리뷰가 있는지 여부
	public int ApplyReviewCheck(Connection conn, int reviewCode){
		System.out.println(">>> ReviewDao ApplyReviewCheck param reviewCode : " + reviewCode);
		int result = 0;
		
		String sql = "select count(review_code) as cnt "
				+ "    from REVIEW "
				+ "    where REVIEW_CODE = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ReviewDao ApplyReviewCheck return : " + result);
		return result;
	}
}
