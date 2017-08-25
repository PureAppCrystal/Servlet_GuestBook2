package com.bigdata2017.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.guestbook.vo.GuestBookVO;

public class GuestBookDao {
	private static final String DB_TYPE   = "oracle.jdbc.driver.OracleDriver";
	private static final String CONN_IP   = "192.168.1.22";
	private static final String CONN_PORT = "1521";
	private static final String CONN_ID   = "webdb";
	private static final String CONN_PW   = "webdb";
	private static final String CONN_ENV  = "xe";
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			//1. JDBC 드라이버 로딩(JDBC 클래스 로딩)				
			Class.forName( DB_TYPE );
			
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@"+CONN_IP+":"+CONN_PORT+":"+CONN_ENV;
			conn = DriverManager.getConnection(url, CONN_ID, CONN_PW);
			System.out.println("Connection Success");
			
		} catch (ClassNotFoundException e) {
			System.out.println( "Driver load fail : " + e );
		} 
		
		return conn;
	}
	
	
	
	
	public int delete(long no, String pw) {
		//객체 생성 
		Connection 			conn = null;
		PreparedStatement  pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "delete guestbook\r\n" + 
						 "where no=? and password = ? ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setLong( 1,	no );
			pstmt.setString( 2, pw );
			
			
			
			//5. 결과 가져오기(성공유무)
			result = pstmt.executeUpdate(); //insert 시에는 excute에 sql을 넣지 않는다.
			if ( result == 1 ) {
				System.out.println("delete Success");
			} else {
				System.out.println("delete fail. SQL : " + sql );
			}
			

			
		} catch (SQLException e) {
			System.out.println( "Error : " + e );
		} finally {
			try {
				//객체 삭제 
				if ( pstmt != null ) {	pstmt.close();	}
				if ( conn  != null ) {	conn.close();	}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	public int insert( GuestBookVO vo ) {
		//객체 생성 
		Connection 			conn = null;
		PreparedStatement  pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into guestbook\r\n" + 
					     "values(seq_guestbook.nextval, ?, ?, ?, sysdate) ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getPassword() );
			pstmt.setString( 3, vo.getContent() );
			
			
			//5. 결과 가져오기(성공유무)
			result = pstmt.executeUpdate(); //insert 시에는 excute에 sql을 넣지 않는다.
			if ( result == 1 ) {
				System.out.println("Insert Success");
			} else {
				System.out.println("Insert fail. SQL : " + sql );
			}
			

			
		} catch (SQLException e) {
			System.out.println( "Error : " + e );
		} finally {
			try {
				//객체 삭제 
				if ( pstmt != null ) {	pstmt.close();	}
				if ( conn  != null ) {	conn.close();	}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	public List<GuestBookVO> getList() {
		List<GuestBookVO> list = new ArrayList<GuestBookVO>(); 
		
		//객체 생성 
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs 	= null;
		
		try {
			conn = getConnection();
			
			//3. Statement 객체 생성.
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "select no, name, content, to_char(reg_date, 'yyyy-mm-dd') from guestbook order by no desc ";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 가져오기
			while( rs.next() ) {
				Long 	no		    = rs.getLong( 1 );
				String  name 		= rs.getString( 2 );
				String  content 	= rs.getString( 3 );
				String  reg_date	= rs.getString( 4 );
				
				
				GuestBookVO vo = new GuestBookVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(content);
				vo.setReg_date(reg_date);
				
				
				list.add( vo );
			}

			
		} catch (SQLException e) {
			System.out.println( "Error : " + e );
		} finally {
			try {
				//객체 삭제 
				if ( rs   != null )	{	rs.close();		}
				if ( stmt != null ) {	stmt.close();	}
				if ( conn != null )	{	conn.close();	}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
				
		
		
		return list;
		
	}
	
	
	
	
}
