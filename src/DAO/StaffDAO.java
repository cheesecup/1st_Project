package DAO;

import java.util.*;
import java.sql.*;
import DTO.*;
import GUI.*;

// staff table db 연동
public class StaffDAO {

	
	String driver="com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/managementdb"; 
	String userid = "root";
	String passwd = "1234";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	
	public StaffDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} //드라이버 로딩
	}
	
	
	//1. 검색하기, 회원정보 보기
	  public ArrayList<StaffDTO> select() {
		  ArrayList<StaffDTO> list = new ArrayList<StaffDTO>();
		  
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from staff";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StaffDTO ft = new StaffDTO();
				
				ft.setsCode(rs.getString("sCode"));
				ft.setsName(rs.getString("sName"));
				ft.setsGender(rs.getString("sGender")); //.charAt(0)
				ft.setsAge(rs.getInt("sAge"));
				ft.setsPhone(rs.getString("sPhone"));
				ft.setsProgram(rs.getString("sProgram"));
				
				list.add(ft);
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
			    conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
			
		}
		return list;
	  }//end select
	
	
	//저장 버튼 수행하는 코드 데이터베이스 안에 자료 넣기
		public void insert(String sCode, String sName, String sGender, int sAge, 
				String sPhone, String sProgram) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DriverManager.getConnection(url, userid, passwd);
				String query = "insert into staff(sCode, sName, sGender, sAge, sPhone, sProgram) "
						+ "values (?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, sCode);
				pstmt.setString(2, sName);
				pstmt.setString(3, sGender);
				pstmt.setInt(4, sAge);
				pstmt.setString(5, sPhone);
				pstmt.setString(6, sProgram);
				
				int n = pstmt.executeUpdate();
				if(n==1) System.out.println("저장 성공");
				
				
				
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
	
	
		//delete
		public void delete(String sCode) {
			// 삭제하기
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DriverManager.getConnection(url, userid, passwd);
				String query = "delete from staff where sCode = ? ";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sCode);
				int n = pstmt.executeUpdate();
				if(n == 1) System.out.println("삭제완료");
				
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
	
	
		public void update(String sCode, String sName, String sGender, int sAge, String sPhone, String sProgram) {
			//수정하기 버튼 구현
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DriverManager.getConnection(url, userid, passwd);
				String query = "update staff set sName=?, sGender=?, sAge=?, sPhone=?, sProgram=? where sCode=?";
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, sName);
				pstmt.setString(2, sGender);
				pstmt.setInt(3, sAge);
				pstmt.setString(4, sPhone);
				pstmt.setString(5, sProgram);
				pstmt.setString(6, sCode);
				
				int n = pstmt.executeUpdate();
				if(n==1) System.out.println("수정 완료");
				
				
				
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
	
	
		//회원정보 조회
		public ArrayList<StaffDTO> search(String sName) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<StaffDTO> a = new ArrayList<StaffDTO>();
			
			try {
				conn = DriverManager.getConnection(url, userid, passwd);
				String query = "select * from staff where sName = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, sName);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					StaffDTO ft = new StaffDTO();
					ft.setsCode(rs.getString("sCode"));
					ft.setsName(rs.getString("sName"));
					ft.setsGender(rs.getString("sGender")); //.charAt(0)
					ft.setsAge(rs.getInt("sAge"));
					ft.setsPhone(rs.getString("sPhone"));
					ft.setsProgram(rs.getString("sProgram"));
					
					a.add(ft);
					
				}
				
				
			} catch (SQLException e) {
 
				e.printStackTrace();
			} finally {
				
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
				        if(conn != null) conn.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}
				
			}
			return a;
		}
		
		
	}
	

