package kh.s2.nandal.crawling.model.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;

import common.jdbc.JdbcTemplate;
import kh.s2.nandal.crawling.model.dao.CrawlingClassDao;
import kh.s2.nandal.classdata.model.vo.ClassVo;
import kh.s2.nandal.apply.model.vo.ClassApplyVo;
import kh.s2.nandal.classdata.model.vo.ClassPhotoVo;


public class CrawlingClassService {
	private CrawlingClassDao dao = new CrawlingClassDao();
	
	public int insertClassPhoto(ClassPhotoVo dto) {
		System.out.println("insertClassPhoto()");
		
		int result = 0;
		//jdbcTemplate = 미리 만들어놓은 db와의 연동 및 try~catch문 처리 메소드용 클래스
		Connection conn = JdbcTemplate.getConnection();
		
		//try~catch문으로 묶어주지 않기 위해서 jdbcTemplate에 메소드로 만들어서 사용
		JdbcTemplate.setAutoCommit(conn, false); // 오토커밋 설정
		result = dao.insertClassPhoto(conn, dto); // DAO 호출
		if(result > 0) {
			JdbcTemplate.commit(conn); // 커밋
			System.out.println("커밋성공");
		} else {
			JdbcTemplate.rollback(conn); // 롤백
			System.out.println("커밋실패");
		}
		JdbcTemplate.close(conn);
		
		return result;
	}
	
	public int insertClassApply(ClassApplyVo vo) {
		System.out.println("insertClassApply()");
		
		int result = 0;
		//jdbcTemplate = 미리 만들어놓은 db와의 연동 및 try~catch문 처리 메소드용 클래스
		Connection conn = JdbcTemplate.getConnection();
		
		//try~catch문으로 묶어주지 않기 위해서 jdbcTemplate에 메소드로 만들어서 사용
		JdbcTemplate.setAutoCommit(conn, false); // 오토커밋 설정
		result = dao.insertClassApply(conn, vo); // DAO 호출
		if(result > 0) {
			JdbcTemplate.commit(conn); // 커밋
			System.out.println("커밋성공");
		} else {
			JdbcTemplate.rollback(conn); // 롤백
			System.out.println("커밋실패");
		}
		JdbcTemplate.close(conn);
		
		return result;
	}
	
	public int insertClass(ClassVo dto) {
		System.out.println("insertClass()");
		
		int result = 0;
		//jdbcTemplate = 미리 만들어놓은 db와의 연동 및 try~catch문 처리 메소드용 클래스
		Connection conn = JdbcTemplate.getConnection();
		
		//try~catch문으로 묶어주지 않기 위해서 jdbcTemplate에 메소드로 만들어서 사용
		JdbcTemplate.setAutoCommit(conn, false); // 오토커밋 설정
		result = dao.insertClass(conn, dto); // DAO 호출
		System.out.println("insert행동 반환값 : "+result);
		if(result > 0) {
			JdbcTemplate.commit(conn); // 커밋
			System.out.println("커밋성공");
		} else {
			JdbcTemplate.rollback(conn); // 롤백
			System.out.println("커밋실패");
		}
		JdbcTemplate.close(conn);
		
		return result;
	}
	public int selectArea(String areaName) {
		System.out.println("selectArea()");
		
		int result = 0;
		//jdbcTemplate = 미리 만들어놓은 db와의 연동 및 try~catch문 처리 메소드용 클래스
		Connection conn = JdbcTemplate.getConnection();
		
		//try~catch문으로 묶어주지 않기 위해서 jdbcTemplate에 메소드로 만들어서 사용
		result = dao.selectArea(conn, areaName); // DAO 호출
		if(result > 0) {
			System.out.println("시도코드 : "+result);
		} else {
			System.out.println("시도코드 가져오기 실패!!!");
		}
		JdbcTemplate.close(conn);
		
		return result;
	}
	public void getImageUrl(String imageUrl, String fileName) throws IOException {
        URL url = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            url = new URL(imageUrl);
            in = url.openStream();

            // 컴퓨터 또는 서버의 저장할 경로(절대패스로 지정해 주세요.)
            out = new FileOutputStream("C:/Users/ghals/Documents/GitHub/S2_Nandal/nandal_project/web/images/class/"+fileName+".jpg");

            while (true) {
                // 루프를 돌면서 이미지데이터를 읽어들이게 됩니다.
                int data = in.read();

                // 데이터값이 -1이면 루프를 종료하고 나오게 됩니다.
                if (data == -1) {
                    break;
                }

                // 읽어들인 이미지 데이터값을 컴퓨터 또는 서버공간에 저장하게 됩니다.
                out.write(data);
            }

            // 저장이 끝난후 사용한 객체는 클로즈를 해줍니다.
            in.close();
            out.close();

        } catch (Exception e) {
        	  // 예외처리
            e.printStackTrace();
        } finally {
            // 만일 에러가 발생해서 클로즈가 안됐을 가능성이 있기에
            // NULL값을 체크후 클로즈 처리를 합니다.
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
	
	public void getReviewImageUrl(String imageUrl, String fileName) throws IOException {
        URL url = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            url = new URL(imageUrl);
            in = url.openStream();

            // 컴퓨터 또는 서버의 저장할 경로(절대패스로 지정해 주세요.)
            out = new FileOutputStream("C:/Users/ghals/Documents/GitHub/S2_Nandal/nandal_project/web/images/review/"+fileName+".jpg");

            while (true) {
                // 루프를 돌면서 이미지데이터를 읽어들이게 됩니다.
                int data = in.read();

                // 데이터값이 -1이면 루프를 종료하고 나오게 됩니다.
                if (data == -1) {
                    break;
                }

                // 읽어들인 이미지 데이터값을 컴퓨터 또는 서버공간에 저장하게 됩니다.
                out.write(data);
            }

            // 저장이 끝난후 사용한 객체는 클로즈를 해줍니다.
            in.close();
            out.close();

        } catch (Exception e) {
        	  // 예외처리
            e.printStackTrace();
        } finally {
            // 만일 에러가 발생해서 클로즈가 안됐을 가능성이 있기에
            // NULL값을 체크후 클로즈 처리를 합니다.
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
