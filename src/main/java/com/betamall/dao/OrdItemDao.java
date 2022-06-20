package com.betamall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.betamall.dto.OrdItemDto;
import com.betamall.util.JdbcUtil;

public class OrdItemDao {

	private static OrdItemDao instance = new OrdItemDao();
	
	private OrdItemDao() {}
	
	public static OrdItemDao getInstance() {
		return instance;
	}
	
	public int insert(OrdItemDto ordItemDto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "INSERT INTO ORDITEM(ORDNO, ITEMNO, ORDCNT, REVIEW, RATE, REVDATE) "
					   + "VALUES(?, ?, ?, NULL, NULL, NULL)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordItemDto.getOrdNo());
			pstmt.setInt(2, ordItemDto.getItemNo());
			pstmt.setInt(3, ordItemDto.getOrdCnt());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}
	
	public int getTotPmt(int ordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtil.getCon();
			String sql = "SELECT SUM(PRICE * ORDCNT) "
					   + "FROM ORDITEM INNER JOIN ITEM USING(ITEMNO) "
					   + "WHERE ORDNO = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ordNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con, pstmt, rs);
		}
		return 0;
	}

}
