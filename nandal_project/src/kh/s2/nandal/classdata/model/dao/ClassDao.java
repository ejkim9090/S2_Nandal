package kh.s2.nandal.classdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.classdata.model.vo.ClassVo;

public class ClassDao {
//	insert
	public int insert(Connection conn, ClassVo vo) {
		System.out.println(">>> ClassDao insert param : " + vo);
		int result = 0;
		String sql = "insert into class values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // ""안에 ; 는 없어야함
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getClassCode());
			pstmt.setInt(2, vo.getCategoryCode());
			pstmt.setString(3, vo.getClassName()); 
			pstmt.setString(4, vo.getClassImg()); 
			pstmt.setString(5, vo.getClassIntro()); 
			pstmt.setString(6, vo.getClassCur()); 
			pstmt.setString(7, vo.getClassHost()); 
			pstmt.setString(8, vo.getClassAlltime()); 
			pstmt.setString(9, vo.getClassPrd()); 
			pstmt.setInt(10, vo.getAreaCode()); 
			pstmt.setString(11, vo.getClassAddress()); 
			pstmt.setInt(12, vo.getClassPrice()); 
			pstmt.setInt(13, vo.getClassLevel()); 
			pstmt.setInt(14, vo.getClassMin()); 
			pstmt.setInt(15, vo.getClassMax()); 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao insert return : " + result);
		return result;
	}
//	update
	public int update(Connection conn, ClassVo vo, int classCode) {
		System.out.println(">>> ClassDao update param vo : " + vo);
		System.out.println(">>> ClassDao update param classcode : " + classCode);
		int result = 0;
		
		String sql = "update class set //TODO where class_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao update return : " + result);
		return result;
	}
//	delete
	public int delete(Connection conn, int classCode) {
		System.out.println(">>> ClassDao delete param classcode : " + classCode);
		int result = 0;
		
		String sql = "delete from class where class_code=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			//TODO
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao delete return : " + result);
		return result;
	}
//	selectList - 리뷰 추천유형 해당 목록
	public List<ClassVo> groupList(Connection conn,int group){
		System.out.println(">>> ClassDao groupList param group : " + group);
		List<ClassVo> volist = null;
		
		String sql = "select CLASS_CODE, CLASS_IMG, CLASS_NAME, CLASS_ADDRESS, CLASS_PRICE "
				+ "    from CLASS "
				+ "    where class_code in (select ca.CLASS_CODE "
				+ "                                from review r join CLASS_APPLY ca on r.REVIEW_CODE = ca.CA_CODE "
				+ "                                where r.REVIEW_GROUP = ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, group);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassVo>();
				do {
					ClassVo vo = new ClassVo();
					vo.setClassCode(rs.getInt("CLASS_CODE"));
					vo.setClassImg(rs.getString("CLASS_IMG"));
					vo.setClassName(rs.getString("CLASS_NAME"));
					String[] addressArr = rs.getString("CLASS_ADDRESS").split("\\s");
					String address = addressArr[0] +" "+addressArr[1];
					vo.setClassAddress(address);
					vo.setClassPrice(rs.getInt("CLASS_PRICE"));
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao groupList return : " + volist);
		return volist;
	}
//	selectList  - 목록조회
	public List<ClassVo> selectList(Connection conn){
		List<ClassVo> volist = null;
		
		String sql = "select * from class";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassVo>();
				do {
					ClassVo vo = new ClassVo();
					vo.setClassCode(rs.getInt("CLASS_CODE"));
					vo.setClassImg(rs.getString("CLASS_IMG"));
					vo.setClassName(rs.getString("CLASS_NAME"));
					String[] addressArr = rs.getString("CLASS_ADDRESS").split("\\s");
					String address = addressArr[0] +" "+addressArr[1];
					vo.setClassAddress(address);
					vo.setClassPrice(rs.getInt("CLASS_PRICE"));
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		System.out.println(">>> ClassDao selectList return : " + volist);
		return volist;
	}
//	selectList  - 목록조회 페이징 - overloading 
	public List<ClassVo> selectList(Connection conn, int startRnum, int endRnum, String searchword,int searchArea,int searchCategory, List<Integer> searchDay, List<Integer> searchLevel, int searchMin,int searchMax){
		System.out.println("키워드:"+searchword+",선택지역:"+searchArea+", 카테고리 :" +searchCategory + ",요일:"+searchDay+",난이도:"+searchLevel+",최소금액:"+searchMin+",최고금액:"+searchMax);
		
		List<ClassVo> volist = null;
		
		String sqlAllSearch = "select * from (select t1.*, rownum r from "
				+ " (select * from class c where 1=1";
//				+ "  and class_name LIKE ? " //키워드 검색 값 
//				+ "and area_code = 11 " //지역 검색
//				+ "and category_code = 3 " //카테고리 검색
//				+ "and class_level in (1,2,3) " //난이도 검색
//				+ "and class_code in(select class_code from class_schedule where bitand(CS_DAY,31) > 0 " // 요일 - 평일 확인
//				+ "																or bitand(CS_DAY,32) > 0 " // 요일 - 토요일 확인
//				+ "																or bitand(CS_DAY,64) > 0)" // 요일 - 일요일 확인
//				+ "and class_price between 0 and 9999"
//				+ "order by c.class_name asc) t1 ) t2 "
//				+ " where r between ? and ?";
		
		if(searchword != null && !searchword.equals("")) {
			sqlAllSearch += " and class_name LIKE '" + "%"+searchword+"%'";
		}
		if(searchArea != 0) {
			sqlAllSearch += " and area_code =" + searchArea;
		}
		if(searchCategory != 0) {
			sqlAllSearch += " and category_code =" + searchCategory;
		}
		if(searchLevel.size() > 0) {
			sqlAllSearch += " and class_level in (";
			for(int i = 0; i < searchLevel.size(); i++) {
				if(i == searchLevel.size()-1) sqlAllSearch += searchLevel.get(i)+")";
				else sqlAllSearch += searchLevel.get(i)+",";
			}
		}
		if(searchDay.size() > 0) {
			sqlAllSearch += " and class_code in(select class_code from class_schedule where";
			for(int i = 0; i < searchDay.size(); i++) {
				if(i == 0) sqlAllSearch += " bitand(CS_DAY," + searchDay.get(i) +") > 0";
				else sqlAllSearch += " or bitand(CS_DAY," + searchDay.get(i) +") > 0";
			}
			sqlAllSearch  += ")";
		}
		sqlAllSearch  += "and class_price between "+searchMin+" and " +searchMax;
		sqlAllSearch  += "order by c.class_name asc) t1 ) t2 where r between ? and ?";
		
		System.out.println(sqlAllSearch);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.println("키워드 있는 sql 적용");
			pstmt = conn.prepareStatement(sqlAllSearch);
			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassVo>();
				do {
					ClassVo vo = new ClassVo();
					vo.setClassCode(rs.getInt("CLASS_CODE"));
					vo.setClassImg(rs.getString("CLASS_IMG"));
					vo.setClassName(rs.getString("CLASS_NAME"));
					String[] addressArr = rs.getString("CLASS_ADDRESS").split("\\s");
					String address = addressArr[0] +" "+addressArr[1];
					vo.setClassAddress(address);
					vo.setClassPrice(rs.getInt("CLASS_PRICE"));
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}

		System.out.println(">>> ClassDao selectList return : " + volist);
		return volist;
	}
//	selectList - 키워드 목록 조회
	public List<ClassVo> keywordList(Connection conn,String keyword){
		System.out.println(">>> ClassDao keywordList param keyword : " + keyword);
		List<ClassVo> volist = null;
		
		String sql = "select CLASS_CODE, CLASS_IMG, CLASS_NAME, CLASS_ADDRESS, CLASS_PRICE from CLASS where CLASS_NAME Like '%"+keyword+"%'";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				volist = new ArrayList<ClassVo>();
				do {
					ClassVo vo = new ClassVo();
					vo.setClassCode(rs.getInt("CLASS_CODE"));
					vo.setClassImg(rs.getString("CLASS_IMG"));
					vo.setClassName(rs.getString("CLASS_NAME"));
					String[] addressArr = rs.getString("CLASS_ADDRESS").split("\\s");
					String address = addressArr[0] +" "+addressArr[1];
					vo.setClassAddress(address);
					vo.setClassPrice(rs.getInt("CLASS_PRICE"));
					volist.add(vo);
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao keywordList return : " + volist);
		return volist;
	}
//	selectTotalCnt - 클래스 목록 조회 충 클래스 수 
	public int selectTotalCnt(Connection conn, String searchword, int searchArea,int searchCategory, List<Integer> searchDay, List<Integer> searchLevel, int searchMin,int searchMax){
		
		int result = 0;

		String sql = "select count(*) cnt from class where 1=1";
		
		if(searchword != null && !searchword.equals("")) {
			sql += " and class_name LIKE '" + "%"+searchword+"%'";
		}
		if(searchArea != 0) {
			sql += " and area_code =" + searchArea;
		}
		if(searchCategory != 0) {
			sql += " and category_code =" + searchCategory;
		}
		if(searchLevel.size() > 0) {
			sql += " and class_level in (";
			for(int i = 0; i < searchLevel.size(); i++) {
				if(i == searchLevel.size()-1) sql += searchLevel.get(i)+")";
				else sql += searchLevel.get(i)+",";
			}
		}
		if(searchDay.size() > 0) {
			sql += " and class_code in(select class_code from class_schedule where";
			for(int i = 0; i < searchDay.size(); i++) {
				if(i == 0) sql += " bitand(CS_DAY," + searchDay.get(i) +") > 0";
				else sql += "or bitand(CS_DAY," + searchDay.get(i) +") > 0";
			}
			sql  += ")";
		}
		sql  += " and class_price between "+searchMin+" and " +searchMax;
		
		System.out.println(sql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao selectTotalCnt result : " + result);
		return result;
	}
//	selectOne - 상세조회
	public ClassVo selectOne(Connection conn, int classCode){
		System.out.println(">>> ClassDao selectOne param classcode : " + classCode);
		ClassVo vo = null;
		
		String sql = "select * from class where class_code=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classCode);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new ClassVo();
				vo.setClassCode(rs.getInt("class_code"));
				vo.setCategoryCode(rs.getInt("CATEGORY_CODE"));
				vo.setClassName(rs.getString("CLASS_NAME"));
				vo.setClassImg(rs.getString("CLASS_IMG"));
				vo.setClassIntro(rs.getString("CLASS_INTRO"));
				vo.setClassCur(rs.getString("CLASS_CUR"));
				vo.setClassHost(rs.getString("CLASS_HOST"));
				vo.setClassAlltime(rs.getString("CLASS_ALLTIME"));
				vo.setClassPrd(rs.getString("CLASS_PRD"));
				vo.setAreaCode(rs.getInt("AREA_CODE"));
				vo.setClassAddress(rs.getString("CLASS_ADDRESS"));
				vo.setClassPrice(rs.getInt("CLASS_PRICE"));
				vo.setClassLevel(rs.getInt("CLASS_LEVEL"));
				vo.setClassMin(rs.getInt("class_min"));
				vo.setClassMax(rs.getInt("class_max"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		System.out.println(">>> ClassDao selectOne return : " + vo);
		return vo;
	}
}
