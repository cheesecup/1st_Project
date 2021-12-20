package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.*;

//program table db 연동
public class ProgramDAO {
	String driver="com.mysql.cj.jdbc.Driver";                   
	String url="jdbc:mysql://localhost:3306/managementdb"; 
	String userid="root";                                  
	String passwd="1234";
	Connection conn=null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	public ProgramDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //드라이버 로딩
	}
	
	//1. 검색하기, 프로그램정보 보기
	  public ArrayList<ProgramDTO> select() {
		  ArrayList<ProgramDTO> list = new ArrayList<ProgramDTO>();
		  
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from program";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProgramDTO dto = new ProgramDTO();
				dto.setProName( rs.getString("proName") );
				dto.setProCode( rs.getString("proCode") );
				dto.setsCode( rs.getString("sCode") );
				dto.setProTime( rs.getString("proTime") );
				dto.setProDay( rs.getString("proDay").charAt(0) );
				dto.setProMember( rs.getInt("proMember") );
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			    conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return list;
	  }//end select
	  
	  
	//저장 버튼 수행하는 코드 데이터베이스 안에 자료 넣기
	public void insert(String proName, String proCode, String sCode, String proTime, String proDay, int proMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into program(proName, proCode, sCode, proTime, proDay, proMember) values (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, proName);
			pstmt.setString(2, proCode);
			pstmt.setString(3, sCode);
			pstmt.setString(4, proTime);
			pstmt.setString(5, proDay);
			//pstmt.setInt(6, Integer.parseInt(proMember));
			pstmt.setInt(6, proMember);
			
			
			int n = pstmt.executeUpdate();
			if(n==1) System.out.println("저장 성공함");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}//end insert

	public void delete(String proCode) {
		// 삭제하기
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "delete from program where proCode = ? ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, proCode);
			int n = pstmt.executeUpdate();
			if(n == 1) System.out.println("삭제됨");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public void update(String proName, String proCode, String sCode, String proTime, String proDay, int proMember) {
		//수정하기 버튼 구현
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "update program set proName=?, sCode=?, proTime=?, proDay=?, proMember=? where proCode=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, proName);
			pstmt.setString(2, sCode);
			pstmt.setString(3, proTime);
			pstmt.setString(4, proDay);
			pstmt.setInt(5, proMember);
			pstmt.setString(6, proCode);
			int n = pstmt.executeUpdate();
			if(n==1) System.out.println("수정 성공함");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	//프로그램정보 조회
	public ArrayList<ProgramDTO> search(String proName) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ProgramDTO> a = new ArrayList<ProgramDTO>();
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from program where proName like ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,proName+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProgramDTO dto = new ProgramDTO();
				dto.setProName( rs.getString("proName") );
				dto.setProCode( rs.getString("proCode") );
				dto.setsCode( rs.getString("sCode") );
				dto.setProTime( rs.getString("proTime") );
				dto.setProDay( rs.getString("proDay").charAt(0) );
				dto.setProMember( rs.getInt("proMember") );
				a.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
			        if(conn != null) conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		return a;
		
	}
	
	
	
}

	
