package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.betamall.dto.OrderDto;
import com.betamall.util.JdbcUtil;

public class OrderDao {

	private static OrderDao instance = new OrderDao();
	
	private OrderDao() {}
	
	public static OrderDao getInstance() {
		return instance;
	}
	
	public int insert(OrderDto ordDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "INSERT INTO \"ORDER\"(ORDNO, MBRNO, BRNO, ORDDATE, ORDSTA, ORDARRD, ORDTEL) "
					   + "VALUES(SEQ_ORDER.NEXTVAL, ?, ?, CURRENT_DATE, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordDto.getMbrNo());
			pstmt.setInt(2, ordDto.getBrNo());
			pstmt.setString(3, ordDto.getOrdSta());
			pstmt.setString(4, ordDto.getOrdArrd());
			pstmt.setString(5, ordDto.getOrdTel());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public OrderDto select(int ordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM \"ORDER\" WHERE ORDNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			rs = pstmt.executeQuery();
			OrderDto ordDto = null;
			if (rs.next()) {
				ordDto = new OrderDto(
						rs.getInt("ORDNO"),
						rs.getInt("MBRNO"),
						rs.getInt("BRNO"),
						rs.getDate("ORDDATE"),
						rs.getString("ORDSTA"),
						rs.getString("ORDARRD"),
						rs.getString("ORDTEL")
					);
			}
			return ordDto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	public int update(OrderDto ordDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "UPDATE \"ORDER\" SET MBRNO = ?, BRNO = ?, ORDDATE = ?, ORDSTA = ?, ORDARRD = ?, ORDTEL = ? WHERE ORDNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordDto.getMbrNo());
			pstmt.setInt(2, ordDto.getBrNo());
			pstmt.setDate(3, ordDto.getOrdDate());
			pstmt.setString(4, ordDto.getOrdSta());
			pstmt.setString(5, ordDto.getOrdArrd());
			pstmt.setString(6, ordDto.getOrdTel());
			pstmt.setInt(7, ordDto.getOrdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public int getMaxOrdNo() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT MAX(ORDNO) FROM \"ORDER\"";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return -1;
	}
	
}
