package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.betamall.controller.MgrDeleteController;
import com.betamall.dto.ManagerDto;
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
	
	public int update(StockDto stkDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "UPDATE STOCK SET STKCNT =? WHERE ITEMNO = ? AND BRNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stkDto.getStkCnt());
			pstmt.setInt(2, stkDto.getItemNo());
			pstmt.setInt(3, stkDto.getBrNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public ArrayList<StockDto> selectByBrNo(int brNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT * FROM STOCK WHERE BRNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, brNo);
			rs = pstmt.executeQuery();
			ArrayList<StockDto> list = new ArrayList<StockDto>();
			while (rs.next()) {
				StockDto stkDto = new StockDto(rs.getInt("ITEMNO"),rs.getInt("BRNO"),rs.getInt("STKCNT"));
				list.add(stkDto);
			}
			return list;
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
	
//	public int changeStock(int ordNo, int sign) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = JdbcUtil.getCon();
//			String sql = "UPDATE STOCK ST "
//					+ "SET STKCNT = STKCNT + ?*(SELECT ORDCNT "
//					+                          "FROM ORDITEM "
//					+                          "WHERE ORDNO = ? AND ITEMNO = ST.ITEMNO) "
//					+ "WHERE BRNO = (SELECT \"ORDER\".BRNO FROM PMT, \"ORDER\""
//					+ 			    "WHERE PMT.ORDNO=\"ORDER\".ORDNO AND PMT.ORDNO = ?) "
//					+ "AND ITEMNO IN (SELECT ORDITEM.ITEMNO FROM PMT, ORDITEM WHERE PMT.ORDNO = ORDITEM.ORDNO AND PMT.ORDNO = ?)";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, sign);
//			pstmt.setInt(2, ordNo);
//			pstmt.setInt(3, ordNo);
//			pstmt.setInt(4, ordNo);
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		} finally {
//			JdbcUtil.close(con, pstmt, rs);
//		}
//	}
	public StockDto checkStock(int inputItemNo, int inputBrNo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT S.ITEMNO,S.BRNO,S.STKCNT "
				+ "FROM STOCK S JOIN ITEM I "
				+ "ON S.ITEMNO=I.ITEMNO "
				+ "INNER JOIN BRANCH B "
				+ "ON B.BRNO = B.BRNO "
				+ "WHERE I.ITEMNO = ? AND B.BRNO = ? ";
		try {
			con=JdbcUtil.getCon();
			pstmt=con.prepareCall(sql);
			pstmt.setInt(1, inputItemNo);
			pstmt.setInt(2, inputBrNo);
			rs=pstmt.executeQuery();
			
			StockDto dto=null;
			if(rs.next()) {
				int itemNo = rs.getInt("ITEMNO");
				int brNo = rs.getInt("BRNO");
				int stkCnt = rs.getInt("STKCNT");
				dto = new StockDto(itemNo, brNo, stkCnt);
			}
			return dto;
		}catch(SQLException s) {
			s.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
}
