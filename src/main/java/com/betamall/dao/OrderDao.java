package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			String sql = "INSERT INTO \"ORDER\"(ORDNO, MBRNO, BRNO, ORDDATE, ORDSTA, ORDNAME, ORDADR, ORDTEL) "
					   + "VALUES(?, ?, ?, CURRENT_DATE, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			if(ordDto.getOrdNo()==0) {
				throw new IllegalArgumentException("주문 번호를 입력하세요.");
			}
			
			pstmt.setInt(1, ordDto.getOrdNo());
			pstmt.setInt(2, ordDto.getMbrNo());
			pstmt.setInt(3, ordDto.getBrNo());
			pstmt.setString(4, ordDto.getOrdSta());
			pstmt.setString(5, ordDto.getOrdName());
			pstmt.setString(6, ordDto.getOrdAdr());
			pstmt.setString(7, ordDto.getOrdTel());
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
						rs.getString("ORDNAME"),
						rs.getString("ORDADR"),
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
			String sql = "UPDATE \"ORDER\" SET MBRNO = ?, BRNO = ?, ORDDATE = ?, ORDSTA = ?, ORDNAME = ?, ORDADR = ?, ORDTEL = ? WHERE ORDNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordDto.getMbrNo());
			pstmt.setInt(2, ordDto.getBrNo());
			pstmt.setDate(3, ordDto.getOrdDate());
			pstmt.setString(4, ordDto.getOrdSta());
			pstmt.setString(5, ordDto.getOrdName());
			pstmt.setString(6, ordDto.getOrdAdr());
			pstmt.setString(7, ordDto.getOrdTel());
			pstmt.setInt(8, ordDto.getOrdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public int delete(int ordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "DELETE FROM \"ORDER\" WHERE ORDNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public int getOrdNo() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT SEQ_ORDER.NEXTVAL FROM DUAL";
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
	
	public ArrayList<Integer> getIpOrdNos(int mbrNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT ORDNO FROM \"ORDER\" WHERE MBRNO = ? AND ORDSTA = '결제대기'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mbrNo);
			ArrayList<Integer> ordNos = new ArrayList<>(); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ordNos.add(rs.getInt(1));
			}
			return ordNos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return null;
	}
	
}
