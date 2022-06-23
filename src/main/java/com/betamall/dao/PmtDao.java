package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.betamall.dto.PmtDto;
import com.betamall.util.JdbcUtil;

public class PmtDao {

	private static PmtDao instance = new PmtDao();
	
	private PmtDao() {}
	
	public static PmtDao getInstance() {
		return instance;
	}
	
	public int insert(PmtDto pmtDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "INSERT INTO PMT(ORDNO, PMTAMT, PMTTYPE, PMTDATE) "
					   + "VALUES(?, ?, ?, CURRENT_DATE)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pmtDto.getOrdNo());
			pstmt.setInt(2, pmtDto.getPmtAmt());
			pstmt.setString(3, pmtDto.getPmtType());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
//	
//	public PmtDto select(int ordNo) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			con = JdbcUtil.getCon();
//			String sql = "SELECT * FROM \"ORDER\" WHERE ORDNO = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, ordNo);
//			rs = pstmt.executeQuery();
//			PmtDto pmtDto = null;
//			if (rs.next()) {
//				pmtDto = new PmtDto(
//						rs.getInt("ORDNO"),
//						rs.getInt("MBRNO"),
//						rs.getInt("BRNO"),
//						rs.getDate("ORDDATE"),
//						rs.getString("ORDSTA"),
//						rs.getString("ORDADR"),
//						rs.getString("ORDTEL")
//					);
//			}
//			return pmtDto;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			JdbcUtil.close(con, pstmt, rs);
//		}
//	}
//	
//	public int update(PmtDto pmtDto) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = JdbcUtil.getCon();
//			String sql = "UPDATE \"ORDER\" SET ORDSTA = ? WHERE ORDNO = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, ordDto.getOrdSta());
//			pstmt.setInt(1, ordDto.getOrdNo());
//			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		} finally {
//			JdbcUtil.close(pstmt);
//			JdbcUtil.close(con);
//		}
//	}
	
}
