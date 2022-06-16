package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.McatDto;
import com.betamall.util.JdbcUtil;

public class McatDao {
	
	private static McatDao instance = new McatDao();
		
	private McatDao() {}
		
	public static McatDao getInstance() {
		
		return instance;
		
	}
	
	public ArrayList<McatDto> selectNDel() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT MCATNO, MCATNAME, MCATDEL FROM MCAT WHERE MCATDEL = 0 ORDER BY MCATNO";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<McatDto> mcatDtos = new ArrayList<McatDto>();
			while(rs.next()) {
				mcatDtos.add(
						new McatDto(
								rs.getInt("MCATNO"),
								rs.getString("MCATNAME"),
								rs.getBoolean("MCATDEL")
							)
						);
			}
			return mcatDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
	public ArrayList<McatDto> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM MCAT ORDER BY MCATNO";
			pstmt =con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<McatDto> mcatDtos = new ArrayList<McatDto>();
			while(rs.next()) {
				mcatDtos.add(
						new McatDto(
								rs.getInt("MCATNO"),
								rs.getString("MCATNAME"),
								rs.getBoolean("MCATDEL")
							)
						);
			}
			return mcatDtos;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				JdbcUtil.close(con, pstmt, rs);
			}
	}
	
}
