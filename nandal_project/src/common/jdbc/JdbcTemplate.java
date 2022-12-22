package common.jdbc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcTemplate {
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			// 1.properties사용

			// 현재 지금 이 java파일이 있는 위치를 스트링 모양으로 뽑아내는 것
			String currentPath = JdbcTemplate.class.getResource("./").getPath();

			prop.load(new BufferedReader(new FileReader(currentPath + "driver.properties")));
			Class.forName(prop.getProperty("db.driver"));
			conn = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"),
					prop.getProperty("db.pwe"));

			// 2. 기존방법
//			Class.forName("oracle.jdbc.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "S2", "S2");

			if (conn == null) {
				System.out.println("== DB 연결 실패!!");
			} else {
				System.out.println("== DB 연결 성공");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("==========ojdbc 드라이버 로딩 실패===========");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("==========DB 연결 실패!!!===========");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if (rs != null)rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void setAutoCommit(Connection conn, boolean autoCommit) {
		try {
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
