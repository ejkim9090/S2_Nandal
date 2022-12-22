package kh.s2.nandal.classdata.model.service;

import java.sql.Connection;
import java.util.List;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.classdata.model.dao.CategoryDao;
import kh.s2.nandal.classdata.model.vo.CategoryVo;

public class CategoryService {
	private CategoryDao dao = new CategoryDao();
	// 최소 5개 CRUD
//		insert
		public int insert(CategoryVo vo) {
			System.out.println(">> CategoryService insert param vo :" + vo);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.insert(conn, vo);
			System.out.println(">> CategoryService insert return :" + result);
			return result;
		}
//		update
		public int update(CategoryVo vo, int categoryCode) {
			System.out.println(">> CategoryService update param categoryCode :" + categoryCode);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.update(conn, vo, categoryCode);
			
			JdbcTemplate.close(conn);
			System.out.println(">> CategoryService update return :" + result);
			return result;
		}
//		delete
		public int delete(int categoryCode) {
			System.out.println(">> CategoryService delete param categoryCode :" + categoryCode);
			Connection conn = JdbcTemplate.getConnection();
			int result = 0;
			result = dao.delete(conn, categoryCode);
			JdbcTemplate.close(conn);
			System.out.println(">> CategoryService delete return :" + result);
			return result;
		}
//		selectList
		public List<CategoryVo> selectList(){
			Connection conn = JdbcTemplate.getConnection();
			List<CategoryVo> volist = null;
			volist = dao.selectList(conn);
			JdbcTemplate.close(conn);
			System.out.println(">> CategoryService selectList return :" + volist);
			return volist;
		}
//		selectOne
		public CategoryVo selectOne(int categoryCode){
			System.out.println(">> CategoryService selectOne param categoryCode :" + categoryCode);
			Connection conn = JdbcTemplate.getConnection();
			CategoryVo vo = null;
			vo = dao.selectOne(conn, categoryCode);
			JdbcTemplate.close(conn);
			System.out.println(">> CategoryService selectOne return :" + vo);
			return vo;
		}
}
