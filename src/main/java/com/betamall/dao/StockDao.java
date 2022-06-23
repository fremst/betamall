package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.dto.StockDto;
import com.betamall.util.JdbcUtil;

public class StockDao {

	private static StockDao instance = new StockDao();
	
	private StockDao() {}
	
	public static StockDao getInstance() {
		return instance;
	}
	
	public int insert(StockDto stkDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "INSERT INTO STOCK(ITEMNO, BRNO, STKCNT) "
					   + "VALUES(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stkDto.getItemNo());
			pstmt.setInt(2, stkDto.getBrNo());
			pstmt.setInt(3, stkDto.getStkCnt());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public StockDto select(int itemNo, int brNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM STOCK WHERE ITEMNO = ? AND BRNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			pstmt.setInt(2, brNo);
			rs = pstmt.executeQuery();
			StockDto stkDto = null;
			if (rs.next()) {
				stkDto = new StockDto(
						rs.getInt("ITEMNO"),
						rs.getInt("BRNO"),
						rs.getInt("STKCNT")
					);
			}
			return stkDto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<StockDto> selectByItemNo(int itemNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM STOCK WHERE ITEMNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();
			ArrayList<StockDto> stkDtos = new ArrayList<StockDto>();
			while (rs.next()) {
				stkDtos.add(
						new StockDto(
									rs.getInt("ITEMNO"),
									rs.getInt("BRNO"),
									rs.getInt("STKCNT")
								)
							);
			}
			return stkDtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	public int changeStock(int ordNo, int sign) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "UPDATE STOCK ST "
					+ "SET STKCNT = STKCNT + ?*(SELECT ORDCNT "
					+ "                          FROM ORDITEM "
					+ "                          WHERE ORDNO = ? AND ITEMNO = ST.ITEMNO) "
					+ "WHERE BRNO = (SELECT BRNO FROM \"ORDER\" WHERE ORDNO = ?) "
					+ "  AND ITEMNO IN (SELECT ITEMNO FROM ORDITEM WHERE ORDNO = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sign);
			pstmt.setInt(2, ordNo);
			pstmt.setInt(3, ordNo);
			pstmt.setInt(4, ordNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}

}
