package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.ScatDto;
import com.betamall.util.JdbcUtil;

public class ScatDao {

	private static ScatDao instance = new ScatDao();

	private ScatDao() {}
	
	public static ScatDao getInstance() {
		return instance;
	}
	
	public ArrayList<ScatDto> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM SCAT ORDER BY MCATNO";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ScatDto> scatDtos = new ArrayList<ScatDto>();
			while(rs.next()) {
				scatDtos.add(
					new ScatDto(
						rs.getInt("MCATNO"),
						rs.getInt("SCATNO"),
						rs.getString("SCATNAME"),
						rs.getBoolean("SCATDEL")
						)
					);
			}
			return scatDtos;
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public ArrayList<ScatDto> selectNDel() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT MCATNO, SCATNO, SCATNAME, SCATDEL FROM SCAT WHERE SCATDEL = 0 ORDER BY MCATNO";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ScatDto> scatDtos = new ArrayList<ScatDto>();
			while(rs.next()) {
				scatDtos.add(
					new ScatDto(
							rs.getInt("MCATNO"),
							rs.getInt("SCATNO"),
							rs.getString("SCATNAME"),
							rs.getBoolean("SCATDEL")
						)
					);
				}
				return scatDtos;
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				JdbcUtil.close(con, pstmt, rs);
			}
		}
	
	public ScatDto selectByName(String scatName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT MCATNO, SCATNO, SCATNAME, SCATDEL FROM SCAT WHERE SCATNAME = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, scatName);
			rs = pstmt.executeQuery();
			ScatDto scatDto = null;
			if(rs.next()) {
				scatDto = new ScatDto(
							rs.getInt("MCATNO"),
							rs.getInt("SCATNO"),
							rs.getString("SCATNAME"),
							rs.getBoolean("SCATDEL")
					);
				}
			return scatDto;
			}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				JdbcUtil.close(con, pstmt, rs);
			}
		}
}
