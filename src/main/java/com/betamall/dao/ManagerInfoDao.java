package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.ManagerDto;
import com.betamall.dto.ManagerInfoDto;
import com.betamall.util.JdbcUtil;


public class ManagerInfoDao {
	
private static ManagerInfoDao instance = new ManagerInfoDao();
	
	private ManagerInfoDao() {}
	
	public static ManagerInfoDao getInstance() {
		return instance;
	}
	
	public int getCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT COUNT(*) FROM MGR";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public ArrayList<ManagerInfoDto> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT MGRNO, MGRIMG, BRNAME, MGRTEL, BRADDR, MGRNAME, MGRTEL, MGREMAIL "
					   + "FROM MGR INNER JOIN BRANCH BR "
					   + " ON (BR.BRNO = MGR.BRNO)"
					   + "ORDER BY MGRNO";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ManagerInfoDto> mgrDtos = new ArrayList<ManagerInfoDto>();
			while(rs.next()) {
				mgrDtos.add(
						new ManagerInfoDto(
								rs.getInt("MGRNO"),
								rs.getString("MGRIMG"),
								rs.getString("BRNAME"),
								rs.getString("BRTEL"),
								rs.getString("BRADDR"),
								rs.getString("MGRNAME"),
								rs.getString("MGRTEL"),
								rs.getString("MGREMAIL")
							)
						);
			}
			return mgrDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public ArrayList<ManagerInfoDto> selectFromTo(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT MGRNO, MGRIMG, BRNAME, BRTEL, BRADDR, MGRNAME, MGRTEL, MGREMAIL "
					   + "FROM(SELECT MGRBR.*, ROW_NUMBER() OVER (ORDER BY MGRNO) AS ROW_NUM "
					   + 	  "FROM (SELECT * "
					   + 	  "FROM MGR INNER JOIN BRANCH BR "
					   +      "ON (BR.BRNO = MGR.BRNO)) MGRBR) "
					   + "WHERE ROW_NUM BETWEEN ? AND ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<ManagerInfoDto> mgrDtos = new ArrayList<ManagerInfoDto>();
			while(rs.next()) {
				mgrDtos.add(
						new ManagerInfoDto(
								rs.getInt("MGRNO"),
								rs.getString("MGRIMG"),
								rs.getString("BRNAME"),
								rs.getString("BRTEL"),
								rs.getString("BRADDR"),
								rs.getString("MGRNAME"),
								rs.getString("MGRTEL"),
								rs.getString("MGREMAIL")
							)
						);
			}
			return mgrDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
}
