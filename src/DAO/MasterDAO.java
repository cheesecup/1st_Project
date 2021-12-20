package DAO;

import java.sql.*;
import java.util.ArrayList;
import DTO.*;
import GUI.*;

// master table db 연동
public class MasterDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/managementdb";
	String userid = "root";
	String passwd = "1234";
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null; 
	ResultSet rs = null;
	
	public MasterDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
		// idcheck
	public boolean idcheck(String masterId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select count(*) cnt from master where masterId =?";			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, masterId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				if( cnt > 0 ) {
					return true;
				}
			}
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try { 
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
		// login
	public int login(String masterId, String masterPd, String masterOk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "select masterPd,masterOk from master where masterId = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, masterId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).contentEquals(masterPd) && rs.getString(2).contentEquals(masterOk) ) { return 1;} // 로그인 성공
				else { return 0; } // 로그인 실패
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try { 
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -2;
		
	}
	
		// insert
	public void insertMaster(MasterDTO mDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into master(masterId, masterPd, masterOk, sName, sBirth, sGender, sPhone) values(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, mDTO.getMasterId());
			pstmt.setString(2, mDTO.getMasterPd());
			pstmt.setString(3, mDTO.getMasterOk());
			pstmt.setString(4, mDTO.getsName());
			pstmt.setInt(5, mDTO.getsBirth());
			pstmt.setString(6, mDTO.getsGender());
			pstmt.setString(7, mDTO.getsPhone());
			
			int n = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { 
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	} // end insertMaster()
	
	public void delete(String masterId,String masterPd) {
		// 삭제하기 코드
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, passwd);
			String query = "delete from master where masterId = ? and masterPd = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, masterId);
			pstmt.setString(2, masterPd);
			
			int n = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
			
} // end MasterDAO()
